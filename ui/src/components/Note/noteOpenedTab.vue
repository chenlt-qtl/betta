<template>
  <div class="open-tab">
    <el-tabs
      :value="`${openedNoteId}`"
      type="card"
      closable
      @edit="handleTabsEdit"
      @tab-click="handleClick"
    >
      <el-tab-pane :key="item.id" v-for="item in tabData" :name="`${item.id}`">
        <span slot="label" class="tab-item">{{ item.name }}</span>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      editableTabsValue: "2",
      tabData: [],
    };
  },
  computed: {
    openedNotes() {
      return this.$store.state.note.openedNotes;
    },
    openedNoteId() {
      return this.$store.state.note.openedNote.id;
    },
  },
  watch: {
    openedNotes() {
      const openedNotes = this.$store.state.note.openedNotes;
      const entrys = [];
      openedNotes.forEach((note) => {
        entrys.push(note);
      });
      this.tabData = entrys;
    },
  },
  methods: {
    handleClick(data) {
      this.$router.push("/n/note?id=" + data.name);
    },
    handleTabsEdit(targetName, action) {
      const openedNoteId = this.$store.state.note.openedNote.id;
      if (action === "remove") {
        const openedNotes = new Map(this.openedNotes);
        openedNotes.delete(targetName);
        this.$store.dispatch("note/setOpenedNotes", openedNotes);
        if(openedNoteId==targetName){
          this.$store.dispatch("note/setOpendNote", {});
        }
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.open-tab {
  height: 48px;
  padding: 1px;
  overflow: hidden;
  .el-tabs__nav {
    .el-tabs__item {
      .tab-item {
        font-size: 12px;
      }
    }
  }
}
</style>