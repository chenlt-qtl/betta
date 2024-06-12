<template>
  <el-dialog title="移动到" :visible.sync="open" width="500px" append-to-body>
    <div class="note-move">
      <div class="head-container">
        <el-input
          v-model="noteName"
          placeholder="请输入名称"
          clearable
          size="small"
          prefix-icon="el-icon-search"
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
        />
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="() => (this.open = false)">取 消</el-button>
      </div>
    </div>
  </el-dialog>
</template>

<script>
import { updateParent } from "@/api/note/noteInfo";

export default {
  data() {
    return {
      noteName: "",
      form: {},
      open: false,
      // 遮罩层
      loading: true,
      newTreeId: null,
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
  },
  watch: {
    // 根据名称筛选树
    noteName(val) {
      this.$refs.tree.filter(val);
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
      this.newTreeId = data.id;
    },
    openDialog(data) {
      this.form = data;
      this.open = true;
      const parentId = this.form.parentId;
      if (parentId != undefined) {
        this.$nextTick(function () {
          const selectNode = this.$refs.tree.getNode(parentId);
          function expandParent(node) {
            node.expand();
            node.parent && expandParent(node.parent);
          }

          //展开
          if (selectNode && !selectNode.expanded) {
            expandParent(selectNode);
          }
          if (selectNode) {
            this.$refs.tree.setCurrentKey(parentId);
          }
        });
      }
    },
    submitForm() {
      if (this.form.parentId == this.newTreeId) {
        this.$modal.msgError("新旧位置不能相同");
      } else {
        updateParent(this.form.ids, this.newTreeId).then(() => {
          this.$modal.msgSuccess("操作成功");
          this.open = false;
          //刷新树
          this.$store.dispatch("note/getTreeData");
        });
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.note-move {
  .tree-container {
    height: 400px;
    overflow: auto;
    margin: 10px 0;
  }
}
</style>