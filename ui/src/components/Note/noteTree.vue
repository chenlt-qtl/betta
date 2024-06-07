<template>
  <el-row :gutter="20" class="note-info">
    <!--部门数据-->
    <el-col :span="12" :xs="24">
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
          :data="noteOptions"
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
              <NoteTreeDropdown :note="node" @refreshTree="getNoteTree" />
            </span>
          </span>
        </el-tree>
      </div>
    </el-col>

    <el-col :span="12" :xs="24">
      <NoteList :parentId="selectNode.id" :parentName="selectNode.label"></NoteList>
    </el-col>
  </el-row>
</template>

<script>
import { listNoteTree } from "@/api/note/noteInfo";
import NoteTreeDropdown from "./noteTreeDropdown.vue";
import NoteList from "./noteList.vue";

export default {
  name: "NoteTree",
  components: { NoteTreeDropdown, NoteList },
  data() {
    return {
      noteName: "",
      selectNode:{},
      // 遮罩层
      loading: true,
      // 树数据
      noteOptions: undefined,
      defaultProps: {
        children: "children",
        label: "label",
      },
    };
  },
  created() {
    this.getNoteTree();
  },
  watch: {
    // 根据名称筛选部门树
    noteName(val) {
      this.$refs.tree.filter(val);
    },
  },
  methods: {
    /** 查询树结构 */
    getNoteTree() {
      this.loading = true;
      listNoteTree({ parentId: 0, isLeaf: 0 }).then((response) => {
        this.noteOptions = response.data;
        this.loading = false;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
        this.selectNode = data;
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