<template>
  <div>
    <el-dropdown @command="handleCommand" trigger="click">
      <span class="el-dropdown-link">
        <svg-icon icon-class="ellipsis-v" />
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="rename">重命名</el-dropdown-item>
        <el-dropdown-item command="move">移动</el-dropdown-item>
        <el-dropdown-item divided command="del">删除</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dialog
      title="请输入文件夹名称"
      :visible.sync="openRename"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item label="" prop="name">
          <el-input v-model="form.name" placeholder="请输入文件夹名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="rename">确 定</el-button>
        <el-button @click="() => (this.openRename = false)">取 消</el-button>
      </div>
    </el-dialog>
    <NoteMoveDialogVue ref="moveDialog"></NoteMoveDialogVue>
  </div>
</template>

<script>
import { delNoteInfo, updateName } from "@/api/note/noteInfo";
import NoteMoveDialogVue from "./noteMoveDialog.vue";

export default {
  name: "NoteTreeDropdown",
  components: { NoteMoveDialogVue },
  props: ["note"],
  data() {
    return {
      openRename: false,
      form: {},
      rules: {
        name: [{ required: true, message: "名称不能为空", trigger: "change" }],
      },
    };
  },
  computed: {
    selectedNoteId() {
      return this.$store.state.note.selectedNoteId;
    },
  },
  methods: {
    handleCommand(command) {
      if (this.note.id) {
        const { id, label, parentId } = this.note;
        switch (command) {
          case "del":
            this.delNote();
            break;
          case "rename":
            this.form = { id, name: label, parentId };
            this.openRename = true;
            break;
          case "move":
            this.$refs.moveDialog.openDialog({ ids: id, parentId });
            break;
          default:
            break;
        }
      }
    },
    rename() {
      const note = { id: this.selectedNoteId, name: this.form.name };
      updateName(note).then(() => {
        this.$modal.msgSuccess("修改成功");
        this.openRename = false;
        //刷新树
        this.$store.dispatch("note/getTreeData");
      });
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
        this.$store.dispatch("note/delNotes", [this.note.id]).then(() => {
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        });
      });
    },
  },
};
</script>