<template>
  <div class="note-add-btn">
    <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link">
        增加<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="file">笔记</el-dropdown-item>
        <el-dropdown-item command="dir">文件夹</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dialog
      :title="`请输入${this.isLeaf ? '笔记' : '文件夹'}名称`"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" @submit.native.prevent>
        <el-form-item label="" prop="name">
          <el-input v-model="form.name" placeholder="请输入标题" @keyup.enter.native="submitForm"/>
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
import { addNoteInfo } from "@/api/note/noteInfo";

export default {
  data() {
    return {
      open: false,
      form: {},
      isLeaf: false,
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
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          const note = {
            name: this.form.name,
            parentId: this.selectedNoteId,
            isLeaf: this.isLeaf,
          };
          addNoteInfo(note).then((res) => {
            this.$modal.msgSuccess("保存成功");
            this.open = false;
            this.$store.dispatch("note/getListData");
            if (this.isLeaf) {
              this.$router.push({
                path: "/n/note",
                query: { ...this.$route.query, id: res.data },
              });
            } else {
              this.$store.dispatch("note/getTreeData");
            }
          });
        }
      });
    },
    cancel() {
      this.open = false;
    },

    handleCommand(command) {
      if (this.selectedNoteId == undefined) {
        this.$modal.msgError("请选择要增加的文件夹");
      } else {
        this.form = {};
        this.open = true;
        switch (command) {
          case "file":
            this.isLeaf = true;
            break;
          case "dir":
            this.isLeaf = false;
            break;
        }
      }
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
    font-size: 12px ;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
}
</style>


