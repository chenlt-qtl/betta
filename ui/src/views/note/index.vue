<template>
  <div class="app-container">
    <el-row :gutter="20" class="note-info">
      <el-col :span="8" :xs="24">
        <NoteMenu/>
      </el-col>
      <el-col :span="16" :xs="24">
        <NoteContent/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  listNoteInfo,
  getNoteInfo,
  delNoteInfo,
  addNoteInfo,
  updateNoteInfo,
  listNoteTree,
} from "@/api/note/noteInfo";
import NoteMenu from "@/components/Note/noteMenu";
import NoteContent from "@/components/Note/noteContent";

export default {
  name: "NoteInfo",
  components: { NoteMenu, NoteContent },
  data() {
    return {
      dataStr: "ABC",
      loading: true,
      note: {},
    };
  },
  created() {
    const noteId = (this.$route.query && this.$route.query.id) || "";
    this.getNote(noteId);
  },
  watch: {
    $route(route) {
      const noteId = (route.query && route.query.id) || "";
      this.getNote(noteId);
    },
  },
  methods: {
    getNote(noteId) {
      if (noteId) {
        this.$store.dispatch("note/openNote",noteId);
      } else {
        this.$store.dispatch("note/setOpendNote",{});
      }
    },
  },
};
</script>
<style>
</style>