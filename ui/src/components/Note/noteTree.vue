<template>
  <div class="note-tree">
    <div class="head-container">
      <el-input
        v-model="noteName"
        placeholder="请输入笔记名称"
        clearable
        size="small"
        prefix-icon="el-icon-search"
        style="margin-bottom: 20px"
      />
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
          <span class="dropdown">
            <NoteTreeDropdown :note="node" />
          </span>
        </span>
      </el-tree>
    </div>
  </div>
</template>

<script>
import NoteTreeDropdown from "./noteTreeDropdown.vue";

export default {
  name: "NoteTree",
  components: { NoteTreeDropdown },
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
  created() {
    this.$store.dispatch("note/getTreeData");
  },
  computed: {
    treeData() {
      return this.$store.state.note.treeData;
    },
    openedNote() {
      return this.$store.state.note.openedNote;
    },
  },
  watch: {
    // 根据名称筛选树
    noteName(val) {
      this.$refs.tree.filter(val);
    },
    openedNote(note) {
      const selectNode = this.$refs.tree.getNode(note.parentId);
      function expandParent(node) {
        node.expand();
        node.parent && expandParent(node.parent);
      }

      //展开
      if (selectNode && !selectNode.expanded) {
        expandParent(selectNode);
      }

      if (selectNode) {
        this.$refs.tree.setCurrentKey(note.parentId);
        this.$store.dispatch("note/setSelectedTreeNote", selectNode.data);
      }
    },
  },
  methods: {
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.$store.dispatch("note/setSelectedTreeNote", data);
    },
  },
};
</script>
<style lang="scss" scoped>
.note-tree {
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