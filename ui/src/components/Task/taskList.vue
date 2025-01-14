<template>
  <div class="taskDiv">
    <ul v-for="o in listData" :key="o.id" class="taskList">
      <li :class="`taskItem ${selectedType == 3 ? 'gray' : ''}`">
        <el-checkbox
          v-if="selectedType != 2"
          :value="selectedType == 3 ? true : false"
        />
        <div class="taskName">
          {{ o.content }}
          <span v-if="selectedType == 1" class="taskNameBar">
            <el-button
              type="text"
              size="mini"
              v-if="selectedType == 1"
              title="移动到未完成"
              @click="() => onChangeType(o, 2)"
              icon="el-icon-refresh-right"
            />
            <el-button
              type="text"
              size="mini"
              title="删除"
              @click="() => onDelete(o)"
              icon="el-icon-delete"
            />
          </span>
        </div>
        <span class="toolbar">
          <el-button
            type="primary"
            v-if="selectedType == 1"
            round
            @click="() => onChangeType(o, 3)"
            >完成</el-button
          >
          <el-button
            type="text"
            size="mini"
            v-if="selectedType != 1"
            title="开始任务"
            @click="() => onChangeType(o, 1)"
            icon="el-icon-caret-right"
          />
          <el-button
            v-if="selectedType != 1"
            type="text"
            size="mini"
            icon="el-icon-delete"
            title="删除"
            @click="() => onDelete(o)"
          />
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
import { delTask, updateTask } from "@/api/other/task";

export default {
  name: "TaskList",
  props: ["selectedType", "getTaskList", "listData"], //正在查看的类型 1进行中 2待完成 3已完成
  data() {
    return {
      // 遮罩层
      loading: true,
    };
  },
  methods: {
    onChangeType(task, type) {
      updateTask({ ...task, type }).then((response) => {
        this.$modal.msgSuccess("修改成功");
        this.content = "";
        this.getTaskList();
      });
    },
    /** 删除按钮操作 */
    onDelete(row) {
      this.$modal
        .confirm('删除任务"' + row.content + '"？')
        .then(function () {
          return delTask(row.id);
        })
        .then(() => {
          this.getTaskList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
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
      line-height: 30px;
      width: 100%;
      display: flex;
      gap: 15px;
      border-bottom: solid 1px #ddd;
      padding: 10px 20px;
      align-items: center;

      .taskName {
        display: flex;
        flex: 1;
        flex-direction: column;
        justify-content: flex-start;
        .taskNameBar {
          .el-button--text {
            color: #aaa;
          }
        }
      }

      .toolbar {
        text-align: right;
        width:70px;
      }
    }
    .gray {
      color: #bbb;
    }
  }
}
</style>
