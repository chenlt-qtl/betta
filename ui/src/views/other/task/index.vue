<template>
  <div class="app-container" v-loading="loading">
    <TopBar
      :timeType="timeType"
      :changeTimeType="onTimeTypeChange"
      :openDialog="
        () => {
          task = {};
          open = true;
        }
      "
    ></TopBar>
    <TaskTabs
      :taskStatus="queryParams.taskStatus"
      :changeStatus="
        (taskStatus) => (queryParams = { ...queryParams, taskStatus })
      "
    ></TaskTabs>
    <span class="cardName">普通任务</span>
    <el-row class="content">
      <div class="card">
        <TaskList
          :getTaskList="getList"
          :listData="normalData"
          :onEditTask="onEditTask"
        ></TaskList>
      </div>
    </el-row>
    <span class="cardName">长期任务</span>
    <el-row class="content">
      <div class="card">
        <TaskList
          :getTaskList="getList"
          :listData="longTermData"
          :onEditTask="onEditTask"
        ></TaskList>
      </div>
    </el-row>
    <TaskEditDialog
      :task="task"
      :open="open"
      :setOpen="(open) => (this.open = open)"
      :getTaskList="getList"
    ></TaskEditDialog>
  </div>
</template>

<script>
import { listTask } from "@/api/other/task";
import TopBar from "@/components/Task/topBar.vue";
import TaskList from "@/components/Task/taskList.vue";
import TaskTabs from "@/components/Task/taskTabs.vue";
import TaskEditDialog from "@/components/Task/taskEditDialog.vue";

export default {
  name: "Task",
  components: { TaskList, TopBar, TaskTabs, TaskEditDialog },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 任务表格数据
      taskList: [],
      open: false,
      task: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 1000,
        taskStatus: 1,
        params: { timeType: 0 },
      },
    };
  },
  computed: {
    normalData() {
      return this.taskList.filter((t) => t.type == 1);
    },
    longTermData() {
      return this.taskList.filter((t) => t.type == 2);
    },
    timeType(){
      return this.queryParams.params.timeType;
    }
  },
  watch: {
    queryParams() {
      this.getList();
    },
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询任务列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then((response) => {
        this.taskList = response.rows;
        this.loading = false;
      });
    },
    onEditTask(task) {
      this.task = task;
      this.open = true;
    },
    onTimeTypeChange(timeType) {
      this.queryParams = { ...this.queryParams, params: { ...this.queryParams.params, timeType } };
    },
  },
};
</script>
<style lang="scss" scoped>
.app-container {
  color: #666;
  height: calc(100vh - 85px);
  overflow: auto;
  max-width: 800px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 10px 20px;

  .cardName {
    font-weight: 700;
  }

  .content {
    flex: 1;
    .card {
      height: 100%;
      border-radius: 5px;
      background: #fff;
      box-shadow: 1px 1px 2px #efefef, -1px -1px 2px #efefef;
      background-color: rgba(64, 158, 255, 0.02);
    }
  }
}
</style>
