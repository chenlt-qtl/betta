<template>
  <div>
    <div id="editorSection"></div>
  </div>
</template>


<script>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css"; // Editor's Style
import "codemirror/lib/codemirror.css";

let editor;

export default {
  name: "markDownEditor",
  props: ["value"],
  mounted() {
    editor = new Editor({
      el: document.querySelector("#editorSection"),
      language: "zh-CN",
      initialEditType: "markdown",
      previewStyle: "vertical",
      initialValue:this.content
    });
  },
  watch: {
    value: value=>{
        editor.setMarkdown(value)
    },
  },
  beforeDestroy() {
    this.tuieditor = null;
    delete this.tuieditor;
  },
  methods: {
    initialize() {},
  },
};
</script>
 
