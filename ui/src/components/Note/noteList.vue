<template>
  <div class="note-list">
    <el-row :gutter="10" class="mb8" type="flex" justify="end">
      <el-col :span="1.5">
        <el-button
          v-if="!isCheck"
          type="text"
          size="mini"
          :disabled="listNote.length <= 0"
          @click="handleCheck"
          v-hasPermi="['note:noteInfo:remove']"
          ><svg-icon v-if="!isCheck" icon-class="checkbox"
        /></el-button>
        <el-button
          v-if="isCheck"
          type="text"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['note:noteInfo:remove']"
        ></el-button>
        <el-button
          v-if="isCheck"
          type="text"
          size="mini"
          @click="() => (this.isCheck = false)"
          >取消</el-button
        >
      </el-col>
      <!-- <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar> -->
    </el-row>
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
  </div>
</template>

<script>
import {
  listNoteInfo,
  getNoteInfo,
  delNoteInfo,
  addNoteInfo,
  updateNoteInfo,
} from "@/api/note/noteInfo";

export default {
  name: "NoteList",
  data() {
    return {
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
      // 显示搜索条件
      showSearch: true,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      noteName: "",
      label: "",
    };
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
  },
  watch: {
    selectedTreeNote() {
      this.label = this.selectedTreeNote.label;
    },
    listNote() {
      this.selectOpenRow();
    },
  },
  methods: {
    selectOpenRow() {
      if (this.openedNote.id) {
        const row = this.listNote.find((n) => n.id == this.openedNote.id);
        row && this.$refs.table.setCurrentRow(row);
      }
    },
    // 选中数据
    handleRowClick(selection) {
      this.$router.push("/n/note?id=" + selection.id);
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
  },
};
</script>
<style lang="scss" scoped>
.note-list {
  .table {
    max-height: calc(100vh - 250px);
    overflow: auto;
    tr {
      cursor: pointer;
    }
  }
}
</style>