<template>
  <div class="open-tab">
    <el-tabs
      :value="`${openedNoteId}`"
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
      Object.keys(openedNotes).forEach((id) => {
        entrys.push({ id, name: openedNotes[id] });
      });
      this.tabData = entrys;
    },
  },
  methods: {
    handleClick(data) {
      this.$router.push({
        path: "/n/note",
        query: { id: data.name },
      });
    },
    handleTabsEdit(targetName, action) {
      const openedNoteId = this.$store.state.note.openedNote.id;
      if (action === "remove") {
        delete this.openedNotes[targetName];
        this.$store.dispatch("note/setOpenedNotes", this.openedNotes);
        if (openedNoteId == targetName) {
          const query = { ...this.$route.query };
          delete query.id;
          this.$router.push({
            path: "/n/note",
            query,
          });
        }
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.open-tab {
  height: 54px;
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
