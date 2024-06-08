<template>
  <el-row :gutter="20" class="note-info">
    <el-col :span="12" :xs="24">
      <NoteTree></NoteTree>
    </el-col>

    <el-col :span="12" :xs="24">
      <NoteList></NoteList>
    </el-col>
  </el-row>
</template>

<script>
import NoteTree from "./noteTree.vue";
import NoteList from "./noteList.vue";

export default {
  name: "NoteMenu",
  components: { NoteTree, NoteList },
  data() {
    return {
    };
  }
};
</script>
<style>

</style>