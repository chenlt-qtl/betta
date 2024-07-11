<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="单词内容" prop="wordName">
        <el-input
          v-model="queryParams.wordName"
          placeholder="请输入单词内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手动注释" prop="exchange">
        <el-input
          v-model="queryParams.exchange"
          placeholder="请输入手动注释"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['eng:word:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['eng:word:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['eng:word:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['eng:word:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="wordList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="单词内容" align="center" prop="wordName" />
      <el-table-column label="音标" align="center" prop="phAm" />
      <el-table-column label="解释" align="center" prop="acceptation" />
      <el-table-column label="手动注释" align="center" prop="exchange" />
      <el-table-column label="音频" align="center" prop="phAnMp3">
        <template v-if="scope.row.phAnMp3" slot-scope="scope">
          <el-button type="text" @click="() => play(scope.row.phAnMp3)">
            <svg-icon icon-class="sound" />
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['eng:word:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['eng:word:remove']"
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

    <!-- 添加或修改单词对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="单词内容" prop="wordName">
          <el-input v-model="form.wordName" placeholder="请输入单词内容" />
        </el-form-item>
        <el-form-item label="音标" prop="phAm">
          <el-input v-model="form.phAm" placeholder="请输入音标" />
        </el-form-item>
        <el-form-item label="解释" prop="acceptation">
          <el-input
            v-model="form.acceptation"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="手动注释" prop="exchange">
          <el-input v-model="form.exchange" placeholder="请输入手动注释" />
        </el-form-item>
        <el-form-item label="音频位置" prop="phAnMp3">
          <file-upload v-model="form.phAnMp3" />
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
  listWord,
  getWordById,
  delWord,
  addWord,
  updateWord,
} from "@/api/eng/word";
import { play } from "@/utils/audio";

export default {
  name: "Word",
  data() {
    return {
      // 遮罩层
      loading: true,
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
      // 单词表格数据
      wordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wordName: null,
        acceptation: null,
        exchange: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wordName: [
          { required: true, message: "单词内容不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    play(url, mp3Time) {
      play(url, mp3Time);
    },
    /** 查询单词列表 */
    getList() {
      this.loading = true;
      listWord(this.queryParams).then((response) => {
        this.wordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        wordName: null,
        phAm: null,
        acceptation: null,
        exchange: null,
        parts: null,
        phAnMp3: null,
        status: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
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
      this.title = "添加单词";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getWordById(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改单词";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateWord(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWord(this.form).then((response) => {
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
        .confirm('是否确认删除单词编号为"' + ids + '"的数据项？')
        .then(function () {
          return delWord(ids);
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
        "eng/word/export",
        {
          ...this.queryParams,
        },
        `word_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
