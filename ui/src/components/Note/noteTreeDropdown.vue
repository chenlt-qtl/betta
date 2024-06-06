<template>
  <div>
    <el-dropdown @command="handleCommand">
      <svg-icon icon-class="ellipsis-v"/>
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
    return {
      noteName: "",
      // 树数据
      noteOptions: undefined,
      defaultProps: {
        children: "children",
        label: "label",
      },
    };
  },
  watch: {
    // 根据名称筛选部门树
    noteName(val) {
      this.$refs.tree.filter(val);
    },
  },
  methods: {
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
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
          this.$emit('refreshTree');
        });
      });
    },
    // 节点单击事件
    handleNodeClick(data) {},
  },
};
</script>