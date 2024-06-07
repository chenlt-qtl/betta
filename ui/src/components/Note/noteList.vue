<template>
  <div class="note-list">
    <el-form
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-input
        placeholder="请输入标题"
        clearable
        @keyup.enter.native="handleQuery"
      />
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['note:noteInfo:remove']"
          >删除</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="noteInfoList"
      @selection-change="handleSelectionChange"
      size="mini"
    >
      <el-table-column
        v-if="showSelect"
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column :label="noteName" prop="name" show-overflow-tooltip />
    </el-table>
    <!-- 添加或修改文件夹对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="内容ID" prop="contentId">
          <el-input v-model="form.contentId" placeholder="请输入内容ID" />
        </el-form-item>
        <el-form-item label="标题" prop="name">
          <el-input v-model="form.name" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="父ID" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父ID" />
        </el-form-item>
        <el-form-item label="标签" prop="tag">
          <el-input v-model="form.tag" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="来源" prop="source">
          <el-input v-model="form.source" placeholder="请输入来源" />
        </el-form-item>
        <el-form-item label="${comment}" prop="parentIds">
          <el-input
            v-model="form.parentIds"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
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
  props: ["parentName", "parentId"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      showSelect: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 文件夹表格数据
      noteInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      noteName: "",
    };
  },
  created() {
    this.getList();
  },
  watch: {
    parentId: "getList",
  },
  methods: {
    /** 查询文件夹列表 */
    getList() {
      if (this.parentId) {
        this.noteName = this.parentName;
        this.loading = true;
        // 查询参数
        const queryParams = {
          pageNum: 1,
          pageSize: 1000,
          parentId: this.parentId,
          isLeaf: true,
        };
        listNoteInfo(queryParams).then((response) => {
          this.noteInfoList = response.rows;
          this.loading = false;
        });
      } else {
        this.noteInfoList = [];
        this.loading = false;
        0;
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        contentId: null,
        name: null,
        parentId: null,
        tag: null,
        source: null,
        isLeaf: [],
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
        status: null,
        parentIds: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
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
      this.title = "添加文件夹";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getNoteInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文件夹";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateNoteInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNoteInfo(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
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
        .confirm('是否确认删除文件夹编号为"' + ids + '"的数据项？')
        .then(function () {
          return delNoteInfo(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
  },
};
</script>
<style>
.note-list .el-table__row{
  cursor: pointer;
}
</style>