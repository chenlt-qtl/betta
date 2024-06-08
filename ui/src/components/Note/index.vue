<template>
  <div>
    <el-row>
      <NoteTree />
      <NoteContent />
    </el-row>
  </div>
</template>

<script>
import NoteTree from "./noteTree.vue";
import NoteContent from "./noteContent.vue";

export default {
  data() {
    return {};
  },
  name: "note",
  props: ["expression", "hideComponent"],
  methods: {},

  components: {
    NoteTree,
    NoteContent,
  },
};
</script>

