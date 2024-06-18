<template>
  <!-- 授权用户 -->
  <el-dialog
    title="选择用户"
    :visible.sync="visible"
    width="800px"
    top="5vh"
    append-to-body
  >
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true">
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
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
        <el-button icon="el-icon-plus" size="mini" @click="onCreateIndex"
          >创建索引</el-button
        >
        <el-button icon="el-icon-delete" size="mini" @click="onDeleteIndex"
          >删除索引</el-button
        >
      </el-form-item>
    </el-form>
    <el-row>
      <el-table
        @row-click="clickRow"
        ref="table"
        :data="userList"
        height="260px"
        highlight-current-row
      >
        <el-table-column
          label="用户名称"
          prop="userName"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="用户昵称"
          prop="nickName"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="邮箱"
          prop="email"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="手机"
          prop="phonenumber"
          :show-overflow-tooltip="true"
        />
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <dict-tag
              :options="dict.type.sys_normal_disable"
              :value="scope.row.status"
            />
          </template>
        </el-table-column>
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          width="180"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
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
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="handleSelectUser">更新DOC</el-button>
      <el-button @click="visible = false">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { listUser } from "@/api/system/user";
import { addHistory, addIndex, delIndex } from "@/api/es/history";
export default {
  dicts: ["sys_normal_disable"],
  data() {
    return {
      // 遮罩层
      visible: false,
      // 选中数组值
      userName: null,
      // 总条数
      total: 0,
      // 未授权用户数据
      userList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  methods: {
    // 显示弹框
    show() {
      this.getList();
      this.visible = true;
    },
    clickRow(row) {
      this.userName = row.userName;
    },
    // 查询表数据
    getList() {
      listUser(this.queryParams).then((res) => {
        this.userList = res.rows;
        this.total = res.total;
      });
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
    handleSelectUser() {
      if (!this.userName) {
        this.$modal.msgError("请选择要操作的用户");
        return;
      }
      addHistory(this.userName).then((res) => {
        if (res.code === 200) {
          this.$modal.msgSuccess("操作成功，增加 " + res.data + " 条数据");
          this.visible = false;
          this.$emit("ok");
        }
      });
    },
    onCreateIndex() {
      if (!this.userName) {
        this.$modal.msgError("请选择要操作的用户");
        return;
      }
      addIndex(this.userName).then((res) => {
        if (res.code === 200) {
          this.$modal.msgSuccess("操作成功");
          this.visible = false;
          this.$emit("ok");
        }
      });
    },
    onDeleteIndex() {
      if (!this.userName) {
        this.$modal.msgError("请选择要操作的用户");
        return;
      }
      delIndex(this.userName).then((res) => {
        if (res.code === 200) {
          this.$modal.msgSuccess("操作成功");
          this.visible = false;
          this.$emit("ok");
        }
      });
    },
  },
};
</script>
