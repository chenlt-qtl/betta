<template>
  <div class="note-history-save">
    <el-button icon="el-icon-download" type="text" @click="() => (open = true)"></el-button>
    <el-dialog
      :title="`请输入标题`"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item label="" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { addHistory } from "@/api/note/history";

export default {
  props: ["text"],
  data() {
    return {
      open: false,
      form: {},
      rules: {
        title: [{ required: true, message: "标题不能为空", trigger: "change" }],
      },
    };
  },
  computed: {
    openedNote() {
      return this.$store.state.note.openedNote;
    },
  },
  methods: {
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          const history = {
            title: this.form.title,
            noteId: this.openedNote.id,
            text: this.text,
          };
          addHistory(history).then(() => {
            this.$modal.msgSuccess("保存成功");
            this.open = false;
          });
        }
      });
    },
    cancel() {
      this.open = false;
    },
  },
};
</script>
<style scoped lang='scss'>
.note-add-btn {
  .el-dropdown-link {
    cursor: pointer;
    color: #409eff;
    display: inline-block;
    width: 46px;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
}
</style>


