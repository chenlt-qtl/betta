<template>
  <div class="calendar-container">
    <div class="clock-in-btn">
      <el-button type="primary" round @click="showDialog">打 卡</el-button>
    </div>
    <el-calendar v-model="value">
      <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
      <template slot="dateCell" slot-scope="{ date, data }">
        <p :class="`date ${getStyle(data)} `">
          {{ data.day.split("-")[2] }}
        </p>
      </template>
    </el-calendar>
    <!-- 添加或修改打卡数据对话框 -->
    <el-dialog
      :title="dateToString(value)"
      :visible.sync="open"
      width="300px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <div
          style="padding-top: 10px"
          v-for="dict in dict.type.clock_in"
          :key="dict.value"
        >
          <el-checkbox v-model="form.value[dict.value]" :label="dict.label" />
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="() => (open = false)">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listClockInData,
  addClockInData,
  updateClockInData,
} from "@/api/other/clockInData";

export default {
  name: "ClockInData",
  dicts: ["clock_in"],
  data() {
    return {
      // 打卡数据表格数据
      clockInDataList: [],
      // 是否显示弹出层
      open: false,
      value: new Date(),
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        time: new Date(),
      },
      // 表单参数
      form: { value: {} },
      // 表单校验
      rules: {
        time: [
          { required: true, message: "打卡日期不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询打卡数据列表 */
    getList() {
      this.loading = true;
      listClockInData(this.queryParams).then((response) => {
        this.clockInDataList = response.rows;
        this.loading = false;
      });
    },
    getStyle(d) {
      const data = this.clockInDataList.find((v) => v.time == d.day);
      if (data) {
        const value = JSON.parse(data.value);
        if(Object.keys(value).find(v=>!value[v])){
            return ""
        }else{
            return "checked"
        }
      }
      return "";
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        time: null,
        value: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
      };
      this.resetForm("form");
    },
    dateToString(date) {
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);
      return `${year}-${month}-${day}`;
    },
    showDialog() {
      const dateStr = this.dateToString(this.value);
      const data = this.clockInDataList.find((data) => data.time == dateStr);
      if (data) {
        this.form = { ...data, value: JSON.parse(data.value) };
      } else {
        this.form = { value: { 1: false, 2: false }, time: this.value };
      }
      this.open = true;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.value = JSON.stringify(this.form.value);
          if (this.form.id != null) {
            updateClockInData(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClockInData(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
  },
};
</script>
<style lang="scss">
.calendar-container {
  position: relative;
  .clock-in-btn {
    position: absolute;
    left: calc(50% - 75px);
    top: 10px;
    button {
      width: 150px;
    }
  }

  .date {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .checked {
    background: #67c23a;
    color: #fff;
  }
}
</style>
