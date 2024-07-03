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
    this.initData();
  },
  computed: {
    noteId() {
      return this.$route.query && this.$route.query.id;
    },
    type() {
      return this.$route.query && this.$route.query.type;
    },
    search() {
      return this.$route.query && this.$route.query.search;
    },
  },
  watch: {
    noteId() {
      this.getNote();
    },
    type() {
      //设置listType
      this.$store.dispatch("note/setListType", this.type);
    },
    search(){
      this.$store.dispatch("note/setSearch", this.search);
    }
  },
  methods: {
    getNote() {
      return this.$store.dispatch("note/openNote", this.noteId);
    },
    initData() {
      //设置listType
      this.$store.dispatch("note/setListType", this.type);
      this.$store.dispatch("note/setSearch", this.search);

      //加载Note
      this.getNote().then(() => {
        //加载树
        this.$store.dispatch("note/getTreeData");
      });
    },
  },
};
</script>
<style>
</style>