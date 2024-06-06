<template>
  <div>
    <NoteTree />
  </div>
</template>

<script>
import NoteTree from "./noteTree.vue";

export default {
  data() {
    return {};
  },
  name: "note",
  props: ["expression", "hideComponent"],
  methods: {},

  components: {
    NoteTree,
  },
};
</script>

