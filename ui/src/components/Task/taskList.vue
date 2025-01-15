<template>
  <div class="taskDiv" v-loading="loading">
    <ul v-for="o in listData" :key="o.id" class="taskList">
      <li
        :class="`taskItem ${o.taskStatus == 2 ? 'gray' : ''}`"
        @click="() => onEditTask(o)"
      >
        <div class="taskName">
          {{ o.content }}
        </div>
        <span>
          <el-button
            round
            size="mini"
            v-if="o.taskStatus == 1"
            @click="(e) => onChangeStatus(o, 2, e)"
            >完成</el-button
          >
          <el-button
            round
            size="mini"
            v-if="o.taskStatus == 2"
            @click="(e) => onChangeStatus(o, 1, e)"
            >重启</el-button
          >
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
import { updateTask } from "@/api/other/task";
export default {
  name: "TaskList",
  props: ["getTaskList", "listData", "onEditTask"], 
  data() {
    return {
      // 遮罩层
      loading: false,
    };
  },
  methods: {
    onChangeStatus(task, taskStatus, e) {
      e.preventDefault();
      e.stopPropagation();
      this.loading = true;
      updateTask({ ...task, taskStatus }).then((response) => {
        this.loading = false;
        this.$modal.msgSuccess("修改成功");
        this.getTaskList();
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.taskDiv {
  .taskList {
    font-size: 14px;
    padding: 0;
    margin: 0;
    .taskItem {
      padding: 5px 10px;
      display: flex;
      gap: 15px;
      border-bottom: solid 1px #ddd;
      align-items: center;

      .taskName {
        flex: 1;
      }
    }
    .gray {
      color: #aaa;
    }
  }
}
</style>
