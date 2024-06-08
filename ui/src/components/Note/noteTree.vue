<template>
  <div>
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
    <div class="head-container">
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
          <span>
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
        id:"id"
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
      const selectNode = this.$refs.tree.getNode(note.parentId) || {};
      console.log("============tree watch opennote========================");
      console.log(note.parentId, selectNode,selectNode.id);
      console.log("====================================");
      this.$refs.tree.setCurrentKey(selectNode.id);
      this.$store.dispatch("note/setSelectedTreeNote", selectNode.data || {});
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
      console.log("==============data======================");
      console.log(data);
      console.log("====================================");
      this.$store.dispatch("note/setSelectedTreeNote", data);
    },
  },
};
</script>
<style>
.note-info .custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
  overflow: hidden;
}

.note-info .label {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.note-info .custom-tree-node i {
  display: none;
}

.custom-tree-node:hover i {
  display: inline-block;
}
</style>