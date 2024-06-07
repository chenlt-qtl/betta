<template>
  <div class="app-container">
    <el-row :gutter="20" class="note-info">
      <!--部门数据-->
      <el-col :span="8" :xs="24">
        <NoteTree/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  listNoteInfo,
  getNoteInfo,
  delNoteInfo,
  addNoteInfo,
  updateNoteInfo,
  listNoteTree,
} from "@/api/note/noteInfo";
import NoteTree from '@/components/Note/noteTree'

export default {
  name: "NoteInfo",
  components: { NoteTree },
  dicts: ["sys_yes_no"],
  data() {
    return {
      deptName: "",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 笔记树
      noteOptions: undefined,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 文件夹表格数据
      noteInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        parentId: null,
        tag: null,
        source: null,
        isLeaf: null,
        parentIds: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.getNoteTree();
  },
  methods: {
    /** 查询文件夹列表 */
    getList() {
      this.loading = true;
      listNoteInfo(this.queryParams).then((response) => {
        this.noteInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询部门下拉树结构 */
    getNoteTree() {
      this.loading = true;
      listNoteTree({ parentId: 0, isLeaf: 0 }).then((response) => {
        this.noteOptions = response.data;
        this.loading = false;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.handleQuery();
    },
    handleCommand(command) {
      this.$message("click on item " + command);
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
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "note/noteInfo/export",
        {
          ...this.queryParams,
        },
        `noteInfo_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
<style>
.note-info .custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}

.note-info .custom-tree-node i {
  display: none;
}

.custom-tree-node:hover i{
  display:inline-block;
}
</style>