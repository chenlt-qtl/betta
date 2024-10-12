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
      <el-form-item label="单词" prop="wordName">
        <el-input
          v-model="queryParams.wordName"
          placeholder="请输入单词"
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
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="wordList"
      @selection-change="handleSelectionChange"
      @sort-change="handleSortChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="单词" align="center" prop="wordName" />
      <el-table-column label="音标" align="center" prop="phonetics" />
      <el-table-column
        label="解释"
        align="center"
        prop="acceptation"
        :formatter="acceptationFormatter"
      />
      <el-table-column label="注释" align="center" prop="exchange" />
      <el-table-column
        label="熟悉度"
        align="center"
        prop="familiarity"
        sortable="custom"
      >
        <template slot-scope="scope">
          {{ scope.row.familiarity ? scope.row.familiarity : 0 }}
        </template>
      </el-table-column>
      <el-table-column label="音频" align="center" prop="phMp3">
        <template v-if="scope.row.phMp3" slot-scope="scope">
          <el-button type="text" @click="() => play(scope.row.phMp3)">
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
  </div>
</template>

<script>
import { delWord } from "@/api/eng/word";
import { listByUser } from "@/api/eng/score";
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
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询单词列表 */
    getList() {
      this.loading = true;
      listByUser(this.queryParams).then((response) => {
        this.wordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    play(url) {
      play(url);
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
        /** 排序触发事件 */
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;
      this.queryParams.isAsc = column.order;
      this.getList();
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
    acceptationFormatter(row, column) {
      const acceptation = row.acceptation;
      const strs = acceptation.split("|");
      return strs[0] + (strs.length > 1 ? "..." : "");
    },
  },
};
</script>
