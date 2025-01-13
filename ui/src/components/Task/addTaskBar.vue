<template>
  <div class="container">
    <div class="addBar" v-if="selectedType != 3">
      <i class="el-icon-plus"></i>
      <input
        v-model="title"
        placeholder="请输入任务内容"
        @keyup.enter="onAddTask"
      />
    </div>
    <div class="errorBar" :style="{ height: showMsg ? '36px' : 0 }">
      <el-alert title="任务名称不能为空" type="error" show-icon> </el-alert>
    </div>
  </div>
</template>

<script>
import {
  listTask,
  getTask,
  delTask,
  addTask,
  updateTask,
} from "@/api/other/task";

export default {
  name: "AddTaskBar",
  props: ["selectedType", "getTaskList"], //正在查看的类型 1进行中 2待完成 3已完成
  data() {
    return {
      // 遮罩层
      loading: true,
      showMsg: false,
      //增加的任务名称
      title: "",
      // 表单校验
      rules: {
        title1: [
          { required: true, message: "任务不能为空", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    /** 增加任务 */
    onAddTask() {
      console.log(111);
      if (!this.title) {
        this.showMsg = true;
        setTimeout(() => {
          this.showMsg = false;
        }, 2000);
      } else {
        this.showMsg = false;
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.container {
  .addBar {
    padding: 25px 20px;
    display: flex;
    align-items: center;
    font-size: 20px;
    gap: 20px;
    border-bottom: solid 1px #ddd;
    input {
      flex: 1;
      border: none;
      background-color: transparent;
      outline: none;

      &:focus {
        box-shadow: none;
      }
    }
  }

  .errorBar {
    transition: all 0.3s;
    overflow: hidden;
  }
}
</style>
