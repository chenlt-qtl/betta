<template>
  <div>
    <el-button icon="el-icon-time" type="text" @click="onOpenList"></el-button>

    <el-dialog
      :title="title"
      :visible.sync="openList"
      width="800px"
      append-to-body
      custom-class="note-history-list"
    >
      <el-form
        :model="queryParams"
        ref="queryForm"
        size="small"
        :inline="true"
        v-show="showSearch"
        label-width="68px"
      >
        <el-form-item label="标题" prop="title">
          <el-input
            v-model="queryParams.title"
            placeholder="请输入标题"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            icon="el-icon-search"
            size="mini"
            @click="handleQuery"
            >搜索</el-button
          >
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
            >重置</el-button
          >
        </el-form-item>
      </el-form>

      <el-row :gutter="10" class="mb8 toolbar">
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['note:history:remove']"
            >删除</el-button
          >
        </el-col>
        <el-col :span="3.5" style="flex: 1">
          <el-radio v-model="dataType" label="name">自定义</el-radio>
          <el-radio v-model="dataType" label="auto">自动</el-radio>
        </el-col>
        <right-toolbar
          :showSearch.sync="showSearch"
          @queryTable="getList"
        ></right-toolbar>
      </el-row>

      <el-table
        v-loading="loading"
        :data="historyList"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column
          v-if="dataType == 'name'"
          label="标题"
          align="center"
          prop="title"
        />
        <el-table-column label="日期" align="center" prop="createTime" />
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-search"
              @click="handleView(scope.row)"
              v-hasPermi="['note:history:query']"
              >查看</el-button
            >
            <el-button
              v-if="dataType == 'name'"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['note:history:edit']"
              >修改</el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['note:history:remove']"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

      <!-- 添加或修改历史对话框 -->
      <el-dialog
        :title="title"
        :visible.sync="openEdit"
        width="500px"
        append-to-body
      >
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="标题" prop="title">
            <el-input v-model="form.title" placeholder="请输入标题" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
      <!-- 查看历史对话框 -->
      <el-dialog
        :title="title"
        :visible.sync="openView"
        width="500px"
        append-to-body
      >
        <el-input
          type="textarea"
          :rows="20"
          placeholder="请输入内容"
          :value="form.text"
        >
        </el-input>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="copy">复 制</el-button>
          <el-button @click="cancelView">取 消</el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
import {
  listHistory,
  getHistory,
  delHistory,
  addHistory,
  updateHistory,
} from "@/api/note/history";

export default {
  name: "History",
  data() {
    return {
      // 遮罩层
      loading: true,
      //数据的类型
      dataType: "name",
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 历史表格数据
      historyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      openList: false,
      openEdit: false,
      openView: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        noteId: "",
        title:"",
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [{ required: true, message: "标题不能为空", trigger: "blur" }],
      },
    };
  },
  computed: {
    openedNote() {
      return this.$store.state.note.openedNote;
    },
  },
  watch: {
    dataType() {
      this.getList();
    },
  },
  methods: {
    onOpenList() {
      this.openList = true;
      if (this.queryParams.noteId != this.openedNote.id) {
        this.queryParams = { ...this.queryParams, noteId: this.openedNote.id };
        this.getList();
      }
    },
    /** 查询历史列表 */
    getList() {
      this.loading = true;
      listHistory(this.queryParams, this.dataType).then((response) => {
        this.historyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancelView() {
      this.openView = false;
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        noteId: null,
        title: null,
        text: null,
        createBy: null,
        createTime: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加历史";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.form = row;
      this.openEdit = true;
      this.title = "修改历史";
    },
    /** 查看操作 */
    handleView(row) {
      this.openView = true;
      const id = row.id || this.ids;
      getHistory(id).then((response) => {
        this.form = response.data;
        this.title = "查看历史";
        this.openView = true;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateHistory(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.openEdit = false;
              this.getList();
            });
          } else {
            addHistory(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.openEdit = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除历史编号为"' + ids + '"的数据项？')
        .then(function () {
          return delHistory(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    copy() {
      navigator.clipboard
        .writeText(this.form.text)
        .then(() => {
          this.$modal.msgSuccess("Text copied to clipboard");
        })
        .catch((err) => {
          this.$modal.msgError("Failed to copy text: ", err);
        });
    },
  },
};
</script>
<style scoped lang="scss">
.note-history-list {
  .toolbar {
    display: flex;
    align-items: center;
    gap: 10px;
  }
}
</style>