<template>
  <div class="note-content">
    <OpenedTab></OpenedTab>
    <div v-if="openedNote.id" class="toolbar">
      <input
        maxLength="100"
        v-model="title"
        @input="changeTitle"
        @blur="updateTitle"
      />
      <i class="el-icon-time"></i>
      <i class="el-icon-download"></i>
      <i class="el-icon-star-off orange"></i>
      <i class="el-icon-star-on orange"></i>
      <i
        v-if="isSave"
        style="color: #78e08f"
        class="el-icon-circle-check icon"
      ></i>
      <i v-if="!isSave" class="el-icon-warning-outline orange icon"></i>
    </div>
    <MdEditor
      v-if="openedNote.id"
      :value="content.text"
      :propClass="editorClass"
      @blur="updateText"
      @change="changeText"
    ></MdEditor>
    <el-empty v-if="!openedNote.id" description=""></el-empty>
  </div>
</template>

<script>
import MdEditor from "@/components/MarkDownEditor";
import OpenedTab from "@/components/Note/noteOpenedTab";
import { getNoteInfo, addNoteInfo, updateNoteInfo } from "@/api/note/noteInfo";
import { getContent, updateContent } from "@/api/note/content";

export default {
  name: "NoteList",
  props: ["note"],
  components: { MdEditor, OpenedTab },
  data() {
    return {
      title: "",
      editorClass: { height: "100vh" },
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      noteName: "",
      content: {},
      isSave: true,
    };
  },
  created() {
    this.getData();
  },
  watch: {
    openedNote() {
      this.isSave = true;
      this.getData();
    },
  },
  computed: {
    openedNote() {
      return this.$store.state.note.openedNote;
    },
  },
  methods: {
    /** 查询文件夹列表 */
    getData() {
      const { name, contentId } = this.openedNote;
      this.title = name;
      if (contentId) {
        getContent(contentId).then((res) => {
          this.content = res.data;
        });
      } else {
        this.content = {};
      }
    },
    changeTitle() {
      this.isSave = false;
    },
    updateTitle() {
      if (!this.title) {
        this.$modal.msgError("标题不能为空");
        return;
      }
      const note = { ...this.openedNote, name: this.title };
      updateNoteInfo(note).then(() => {
        this.$modal.msgSuccess("修改成功");
        this.isSave = true;

        //更新openedNotes
        const data = new Map(this.$store.state.note.openedNotes);
        data.set(String(this.openedNote.id), note);
        this.$store.dispatch("note/setOpenedNotes", data);
        this.$store.dispatch("note/getListData");
      });
    },
    changeText() {
      this.isSave = false;
    },
    updateText(text) {
      //如果有改动，才保存
      if (!this.isSave) {
        updateContent({ ...this.content, text }).then(() => {
          this.isSave = true;
          this.$modal.msgSuccess("修改成功");
        });
      }
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        contentId: null,
        name: null,
        parentId: null,
        tag: null,
        source: null,
        isLeaf: [],
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
        status: null,
        parentIds: null,
      };
      this.resetForm("form");
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getNoteInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文件夹";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateNoteInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNoteInfo(this.form).then((response) => {
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
<style lang="scss" scoped>
.note-content {
  .toolbar {
    margin-bottom: 15px;
    display: flex;
    gap: 10px;
    align-items: center;
    i {
      cursor: pointer;
    }

    .orange {
      color: #e6a23c;
    }

    .icon {
      cursor: default;
    }
    input {
      flex: 1;
      border: none;
      font-size: 14px;
      font-weight: bold;
      background-color: transparent;
      outline: none;

      &:focus {
        box-shadow: none;
      }
    }
  }
}
</style>