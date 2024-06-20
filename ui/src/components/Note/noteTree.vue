<template>
  <div class="note-tree">
    <div class="head-container">
      <el-input
        v-model="noteName"
        placeholder="请输入笔记名称"
        clearable
        size="small"
        prefix-icon="el-icon-search"
      />
      <NoteAddBtn />
    </div>
    <div class="tree-container">
      <el-tree
        :data="treeData"
        node-key="id"
        :props="defaultProps"
        :filter-node-method="filterNode"
        ref="tree"
        highlight-current
        @node-click="handleNodeClick"
      >
        <span class="custom-tree-node" slot-scope="{ node }">
          <span class="label" :title="node.label">{{ node.label }}</span>
          <span class="dropdown" v-if="node.data.id">
            <NoteTreeDropdown :note="node.data" />
          </span>
        </span>
      </el-tree>
    </div>
  </div>
</template>

<script>
import NoteTreeDropdown from "./noteTreeDropdown.vue";
import NoteAddBtn from "./noteAddBtn.vue";

export default {
  name: "NoteTree",
  components: { NoteTreeDropdown, NoteAddBtn },
  data() {
    return {
      noteName: "",
      // 遮罩层
      loading: true,
      defaultProps: {
        children: "children",
        label: "label",
        id: "id",
      },
    };
  },
  computed: {
    treeData() {
      return this.$store.state.note.treeData;
    },
    openedNote() {
      return this.$store.state.note.openedNote;
    },
    selectedNoteId() {
      return this.$store.state.note.selectedNoteId;
    },
  },
  watch: {
    // 根据名称筛选树
    noteName(val) {
      this.$refs.tree.filter(val);
    },
    openedNote() {
      this.selectNode(this.openedNote.parentId);
    },
    selectedNoteId() {
      this.selectNode(this.selectedNoteId);
    },
    treeData() {
      this.$nextTick(() => {
        this.selectNode(this.openedNote.parentId);
      });
    },
  },
  methods: {
    // 筛选节点
    filterNode(value, data1) {
      if (!value) return true;
      return data1.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data2) {
      this.$store.dispatch("note/setSelectedNoteId", data2.id);
      this.$store.dispatch("note/setSelectedNoteName", data2.label);
      const query = { ...this.$route.query };
      delete query.type;
      this.$router.push({ path: "/n/note", query });
    },
    //展开并选中节点
    selectNode(id = 0) {
      const selectNode = this.$refs.tree.getNode(id);
      function expandParent(node) {
        node.expand();
        node.parent && expandParent(node.parent);
      }

      //展开
      if (selectNode) {
        expandParent(selectNode);
      }

      if (selectNode) {
        this.$refs.tree.setCurrentKey(id);
        this.$store.dispatch("note/setSelectedNoteName", selectNode.label);
      }
    },
  },
};
</script>
<style lang="scss">
.note-tree {
  input {
    height: 28px;
  }
  .head-container {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
    padding-bottom: 15px;
  }
  .tree-container {
    max-height: calc(100vh - 180px);
    overflow: auto;

    .custom-tree-node {
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: space-between;
      font-size: 14px;
      padding-right: 8px;
      overflow: hidden;
      .label {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .dropdown {
        display: none;
      }
      &:hover .dropdown {
        display: inline-block;
      }
    }
  }
}
</style>
