<template>
  <div>
    <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link">
        <svg-icon icon-class="ellipsis-v" />
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="rename">重命名</el-dropdown-item>
        <el-dropdown-item command="move">移动</el-dropdown-item>
        <el-dropdown-item divided command="del">删除</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import { delNoteInfo } from "@/api/note/noteInfo";

export default {
  name: "NoteTreeDropdown",
  props: ["note"],
  data() {
    return {};
  },

  methods: {
    handleCommand(command) {
      if (this.note.id) {
        switch (command) {
          case "del":
            this.delNote();
            break;
          case "rename":
            this.rename();
            break;
          case "move":
            this.move();
            break;
          default:
            break;
        }
      }
    },
    delNote() {
      this.$confirm(
        "删除文件夹 [ " + this.note.label + " ] , 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(() => {
        delNoteInfo(this.note.data.id).then((res) => {
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          this.$store.dispatch("note/getTreeData");
        });
      });
    },
  },
};
</script>