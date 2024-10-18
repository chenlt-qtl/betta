<template>
  <editor
    ref="editorRef"
    :options="editorOptions"
    height="500px"
    previewStyle="vertical"
    @blur="onEditorBlur"
    @keyup="onInput"
  />
</template>


<script>
import { Editor } from "@toast-ui/vue-editor";
import "@toast-ui/editor/dist/toastui-editor.css"; // Editor's Style
import "codemirror/lib/codemirror.css";
import { uploadNoteImg } from "@/api/common";

export default {
  props: ["value"],
  components: {
    editor: Editor,
  },
  watch: {
    value() {
      this.$refs.editorRef.invoke("setMarkdown", this.value || "", false);
    },
  },
  data() {
    return {
      editorOptions: {
        hooks: { addImageBlobHook: this.uploadImg },
      },
    };
  },
  methods: {
    onEditorBlur() {
      this.$emit("blur", this.$refs.editorRef.invoke("getMarkdown"));
    },
    onInput() {
      this.$emit("change");
    },
    uploadImg(file, callback) {
      if (!this.handleBeforeUpload(file)) {
        return;
      }
      const reader = new FileReader();
      reader.onload = async () => {
        uploadNoteImg(reader.result).then((res) => {
          if (res.data) {
            this.$emit("change");
            callback(process.env.VUE_APP_BASE_API + res.data);
          }
        });
      };
      reader.readAsDataURL(file);
    },
    // 上传前校检格式和大小
    handleBeforeUpload(file) {
      const type = ["image/jpeg", "image/jpg", "image/png", "image/svg"];
      const isJPG = type.includes(file.type);
      // 检验文件格式
      if (!isJPG) {
        this.$message.error(`图片格式错误!`);
        return false;
      }
      // 校检文件大小
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$message.error(`上传文件大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      return true;
    },
  },
};
</script>

