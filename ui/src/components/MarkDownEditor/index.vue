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

export default {
  props: ["value"],
  components: {
    editor: Editor,
  },
  watch: {
    value() {
      this.$refs.editorRef.invoke("setMarkdown", this.value, false);
    },
  },
  data() {
    return {
      editorOptions: {},
    };
  },
  methods: {
    onEditorBlur() {
      this.$emit("blur", this.$refs.editorRef.invoke("getMarkdown"));
    },
    onInput(){
      this.$emit("change");
    },
  },
};
</script>

