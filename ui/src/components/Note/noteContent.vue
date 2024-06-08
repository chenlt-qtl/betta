<template>
  <div class="note-content">
    <div class="toolbar">
      <input
        maxLength="100"
        v-model="title"
        @blur="handleBlur"
        @input="handleTitleChange"
      ></input>
      <i class="el-icon-time"></i>
      <i class="el-icon-star-off orange"></i>
      <i class="el-icon-star-on orange"></i>
      <i style="color: #78e08f" class="el-icon-circle-check icon"></i>
      <i class="el-icon-warning-outline orange icon"></i>
    </div>
    <MdEditor v-model="text"></MdEditor>
  </div>
</template>

<script>
import MdEditor from "@/components/MarkDownEditor";
import { getNoteInfo, addNoteInfo, updateNoteInfo } from "@/api/note/noteInfo";
import { getContent } from "@/api/note/content";

export default {
  name: "NoteList",
  props: ["note"],
  components: { MdEditor },
  data() {
    return {
      title: "",
      text: "abc",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      noteName: "",
    };
  },
  created() {
    this.getData();
  },
  watch: {
    openedNote() {
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
          this.text = res.data.text;
        });
      } else {
        this.text = "";
      }
    },
    handleBlur() {},
    handleTitleChange() {},
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