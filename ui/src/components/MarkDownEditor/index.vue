<template>
  <div>
    <editor :style="{ 'display': isViewer ? 'none' : 'block' }" ref="editor" :options="editorOptions" height="500px"
      previewStyle="vertical" @blur="onEditorBlur" @keyup="onInput" />

    <div class="viewer-container" :style="{ 'display': isViewer ? 'flex' : 'none' }">
      <!-- 左侧目录 -->
      <div class="toc-wrapper">
        <toc :headings="headings" />
      </div>
      <viewer class="viewer" ref="viewRef" height="500px" />
    </div>

  </div>
</template>


<script>
import { Editor, Viewer } from "@toast-ui/vue-editor";
import "@toast-ui/editor/dist/toastui-editor.css"; // Editor's Style
import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import "codemirror/lib/codemirror.css";
import { uploadNoteImg } from "@/api/common";
import Toc from './Toc.vue';

export default {
  props: ["value", "isViewer"],
  components: {
    editor: Editor,
    viewer: Viewer,
    toc: Toc,
  },
  watch: {
    value() {
      this.$refs.viewRef && this.$refs.viewRef.invoke("setMarkdown", this.value || "", false);
      this.$refs.editor && this.$refs.editor.invoke("setMarkdown", this.value || "", false);
      this.$nextTick(() => {
        this.parseHeadings();
      })
    }
  },
  data() {
    return {
      editorOptions: {
        hooks: { addImageBlobHook: this.uploadImg },
      },
      updateEditor: false,
      headings: [],
    };
  },
  methods: {
    onEditorBlur() {
      setTimeout(() => {
        this.$emit("blur", this.$refs.editor.invoke("getMarkdown"));
      }, 0);
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
            callback(process.env.VUE_APP_RESOURCE + res.data);
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
    parseHeadings() {
      const html = this.$refs.editor.invoke('getHTML');
      const parser = new DOMParser();
      const doc = parser.parseFromString(html, 'text/html');

      const headings = [];
      let index = 0;

      doc.querySelectorAll('h1, h2, h3, h4, h5, h6').forEach((element) => {
        const level = parseInt(element.tagName.slice(1), 10);
        const text = element.textContent;
        const id = this.slugify(text, index++);

        // 添加锚点ID到编辑器内容
        element.id = id;

        headings.push({
          level,
          text,
          id
        });
      });

      // 更新编辑器内容（添加ID后的HTML）
      this.$refs.viewRef.invoke('setHTML', doc.body.innerHTML);

      this.headings = headings;
    },
    slugify(text, index) {
      const baseSlug = text
        .toLowerCase()
        .replace(/[^a-z0-9\u4e00-\u9fa5\s-]/g, '')
        .replace(/\s+/g, '-')
        .replace(/-+/g, '-');

      return `${baseSlug}-${index}`;
    }
  }
};
</script>

<style lang="scss">
.viewer-container {
  >div{
    padding: 5px;
  }

  .toc-wrapper {
    width: 200px;
    border-right: 1px solid #e0e0e0;
    overflow-y: auto;
    flex-shrink: 0;
    font-size: 12px;
  }

  .toastui-editor-contents {

    h1,
    h2,
    h3,
    h4 {
      scroll-margin-top: 20px;
      /* 滚动定位偏移量 */
    }
  }
}
</style>
