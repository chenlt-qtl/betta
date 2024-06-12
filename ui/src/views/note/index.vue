<template>
  <div class="app-container">
    <el-row :gutter="20" class="note-info">
      <el-col :span="8" :xs="24">
        <NoteMenu />
      </el-col>
      <el-col :span="16" :xs="24">
        <NoteContent />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import NoteMenu from "@/components/Note/noteMenu";
import NoteContent from "@/components/Note/noteContent";

export default {
  name: "NoteInfo",
  components: { NoteMenu, NoteContent },
  created() {
    this.getNote();
  },
  computed: {
    treeData() {
      return this.$store.state.note.treeData;
    },
    noteId() {
      return this.$route.query && this.$route.query.id;
    },
  },
  watch: {
    noteId() {
      this.getNote();
    },
    treeData(data) {
      this.getNote();
    },
  },
  methods: {
    getNote() {
      //有noteId且加载完tree才加载openNote
      if (this.noteId && this.treeData && this.treeData.length > 0) {
        this.$store.dispatch("note/openNote", this.noteId);
      } else {
        this.$store.dispatch("note/setOpendNote", {});
      }
    },
  },
};
</script>
<style>
</style>