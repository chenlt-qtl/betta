<template>
  <div class="note-list">
    <el-row :gutter="10" class="mb8" v-if="!type">
      <el-col :span="24">
        <el-input
          placeholder="请输入内容"
          v-model="searchStr"
          class="input-with-select"
          @keyup.enter.native="onSearch"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            size="mini"
            @click="onSearch"
          ></el-button>
        </el-input>
      </el-col>
    </el-row>
    <div class="toolbar">
      <span class="label">
        {{ label }}
      </span>
      <span>
        <el-button
          v-if="!isCheck"
          type="text"
          size="mini"
          :disabled="listNote.length <= 0 || type != null"
          @click="handleCheck"
          v-hasPermi="['note:noteInfo:remove']"
          ><svg-icon v-if="!isCheck" icon-class="checkbox"
        /></el-button>
        <el-button
          v-if="isCheck"
          type="text"
          icon="el-icon-truck"
          size="mini"
          :disabled="multiple"
          @click="handleMove"
          v-hasPermi="['note:noteInfo:edit']"
          ></el-button
        >
        <el-button
          v-if="isCheck"
          type="text"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['note:noteInfo:remove']"
          >删除</el-button
        >
        <el-button
          v-if="isCheck"
          type="text"
          size="mini"
          @click="() => (this.isCheck = false)"
          >取消</el-button
        >
      </span>
    </div>
    <div class="table">
      <el-table
        ref="table"
        highlight-current-row
        v-loading="loading"
        :data="listNote"
        @row-click="handleRowClick"
        size="mini"
        @selection-change="handleSelectionChange"
      >
        <el-table-column v-if="isCheck" type="selection" width="28">
        </el-table-column>
        <el-table-column
          v-if="showSelect"
          type="selection"
          width="55"
          align="center"
        />
        <el-table-column :label="label" prop="name" show-overflow-tooltip />
      </el-table>
    </div>
    <noteMoveDialog ref="moveDialog"></noteMoveDialog>
  </div>
</template>

<script>
import { delNoteInfo } from "@/api/note/noteInfo";
import noteMoveDialog from "./noteMoveDialog.vue";

export default {
  name: "NoteList",
  components: { noteMoveDialog },
  data() {
    return {
      //搜索内容
      searchStr: "",
      //是否是多选模式
      isCheck: false,
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      showSelect: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      label: "",
    };
  },
  created() {
    this.setLabel();
  },
  computed: {
    selectedTreeNote() {
      return this.$store.state.note.selectedTreeNote || {};
    },
    listNote() {
      return this.$store.state.note.listNote;
    },
    openedNote() {
      return this.$store.state.note.openedNote;
    },
    type() {
      return this.$route.query && this.$route.query.type;
    },
  },
  watch: {
    listNote() {
      this.selectOpenRow();
    },
    type() {
      this.setLabel();
    },
    selectedTreeNote() {
      this.setLabel();
    },
  },
  methods: {
    setLabel() {
      switch (this.type) {
        case "fav":
          this.label = "收藏夹";
          break;
        case "last":
          this.label = "最近文档";
          break;
        default:
          this.label = this.selectedTreeNote.label;
      }
    },
    selectOpenRow() {
      if (this.openedNote.id) {
        const row = this.listNote.find((n) => n.id == this.openedNote.id);
        row && this.$refs.table.setCurrentRow(row);
      }
    },
    // 选中数据
    handleRowClick(selection) {
      this.$router.push({
        path: "/n/note",
        query: { ...this.$route.query, id: selection.id },
      });
    },
    onSearch() {
      this.label = "搜索结果";
      this.$store.dispatch("note/getListData", this.searchStr);
    },
    handleCheck() {
      this.isCheck = true;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除文件夹编号为"' + ids + '"的数据项？')
        .then(function () {
          return delNoteInfo(ids);
        })
        .then(() => {
          this.$store.dispatch("note/getListData");
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    handleMove() {
      this.$refs.moveDialog.openDialog({
        ids: this.ids,
        parentId: this.selectedTreeNote.id,
      });
    },
  },
};
</script>
<style lang="scss">
.note-list {
  .el-input-group {
    input {
      height: 30px;
    }
    .el-input-group__append {
      padding: 0 10px;
    }
  }
  .el-table__header-wrapper {
    display: none;
  }

  .toolbar {
    display: flex;
    align-items: center;
    border-bottom: 1px solid #ccc;
    .label {
      flex: 1;
      font-size: 12px;
      font-weight: 600;
    }
  }

  .table {
    max-height: calc(100vh - 250px);
    overflow: auto;
    tr {
      cursor: pointer;
    }
  }
}
</style>