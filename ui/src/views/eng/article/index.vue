<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
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
          v-hasPermi="['eng:article:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['eng:article:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['eng:article:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['eng:article:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分组" align="center" prop="groupName" />
      <el-table-column label="图片" align="center" prop="picture" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.picture" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="音频" align="center" prop="mp3" >
         <el-button type="text">
          <svg-icon icon-class="sound" />
        </el-button>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="注释" align="center" prop="comment" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['eng:article:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['eng:article:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改英语文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分组" prop="groupId">
          <el-select v-model="form.groupId">
            <el-option
              v-for="group in groupList"
              :key="group.id"
              :label="group.name"
              :value="group.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="图片" prop="picture">
          <image-upload v-model="form.picture"/>
        </el-form-item>
        <el-form-item label="音频" prop="mp3">
          <file-upload :fileType='["mp3"]'  v-model="form.mp3"/>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="手工注释" prop="comment">
          <el-input v-model="form.comment" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">文章句子信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddEngSentence">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteEngSentence">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="engSentenceList" :row-class-name="rowEngSentenceIndex" @selection-change="handleEngSentenceSelectionChange" ref="engSentence">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="解释" prop="acceptation" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.acceptation" placeholder="请输入解释" />
            </template>
          </el-table-column>
          <el-table-column label="序号" prop="idx" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.idx" placeholder="请输入序号" />
            </template>
          </el-table-column>
          <el-table-column label="图片" prop="picture" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.picture" placeholder="请输入图片" />
            </template>
          </el-table-column>
          <el-table-column label="音频" prop="mp3" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.mp3" placeholder="请输入音频" />
            </template>
          </el-table-column>
          <el-table-column label="MP3开始结束时间" prop="mp3Time" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.mp3Time" placeholder="请输入MP3开始结束时间" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="status" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.status" placeholder="请选择$comment">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from "@/api/eng/article";
import { listGroup } from "@/api/eng/group";

export default {
  name: "Article",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedEngSentence: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 英语文章表格数据
      articleList: [],
      // 文章句子表格数据
      engSentenceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupId: null,
        title: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      groupList:[]
    };
  },
  created() {
    this.getList();
    this.getGroupList();
  },
  methods: {
    /** 查询英语文章列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
     getGroupList() {
      this.loading = true;
      listGroup({pageNum: 1, pageSize: 1000}).then(response => {
        this.groupList = response.rows;
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
        groupId: null,
        picture: null,
        mp3: null,
        title: null,
        comment: null,
        status: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null
      };
      this.engSentenceList = [];
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加英语文章";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getArticle(id).then(response => {
        this.form = response.data;
        this.engSentenceList = response.data.engSentenceList;
        this.open = true;
        this.title = "修改英语文章";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.engSentenceList = this.engSentenceList;
          if (this.form.id != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除英语文章编号为"' + ids + '"的数据项？').then(function() {
        return delArticle(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 文章句子序号 */
    rowEngSentenceIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 文章句子添加按钮操作 */
    handleAddEngSentence() {
      let obj = {};
      obj.content = "";
      obj.acceptation = "";
      obj.idx = "";
      obj.picture = "";
      obj.mp3 = "";
      obj.mp3Time = "";
      obj.status = "";
      this.engSentenceList.push(obj);
    },
    /** 文章句子删除按钮操作 */
    handleDeleteEngSentence() {
      if (this.checkedEngSentence.length == 0) {
        this.$modal.msgError("请先选择要删除的文章句子数据");
      } else {
        const engSentenceList = this.engSentenceList;
        const checkedEngSentence = this.checkedEngSentence;
        this.engSentenceList = engSentenceList.filter(function(item) {
          return checkedEngSentence.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleEngSentenceSelectionChange(selection) {
      this.checkedEngSentence = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('eng/article/export', {
        ...this.queryParams
      }, `article_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>