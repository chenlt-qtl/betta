<template>
  <div class="video-container">
    <el-tree
      class="tree"
      :data="treeOptions"
      @node-click="handleNodeClick"
      :props="defaultProps"
    ></el-tree>
    <div class="list">
      <div class="top" v-if="video.id">
        <image-preview :src="video.img" :width="250" :height="150" />
        <a :href="video.url" target="_blank">
          {{ video.title }}
        </a>
      </div>
      <a
        v-for="video in videoList"
        :key="video.id"
        :href="video.url"
        target="_blank"
      >
        {{ video.title }}
      </a>
    </div>
  </div>
</template>

<script>
import { getBrife, getChildren, getVideo } from "@/api/video/video";

export default {
  name: "Video",
  data() {
    return {
      // 树选择器数据
      treeOptions: [],
      //视频详细信息
      video: {},
      defaultProps: {
        label: "title",
      },
      // 遮罩层
      loading: true,
      // 视频信息数据
      videoList: [],
    };
  },
  created() {
    this.getTreeselect();
  },
  methods: {
    /** 查询树选择器数据 */
    getTreeselect() {
      this.loading = true;
      getBrife("N").then((response) => {
        this.treeOptions = [];
        const root = { id: 0, title: "根节点", children: [] };
        root.children = this.handleTree(response.data, "id", "pid");
        this.treeOptions.push(root);
        console.log(this.treeOptions);
        this.loading = false;
      });
    },
    handleNodeClick(node) {
      this.videoList = [];
      this.video = {};
      //获取子节点
      getChildren(node.id).then((res) => {
        this.videoList = res.data.filter((d) => d.url);
      });
      //获取详细信息
      getVideo(node.id).then((response) => {
        this.video = response.data;
      });
    },
  },
};
</script>

<style lang="scss">
.video-container {
  display: flex;
  height: calc(100vh - 84px);
  .tree {
    padding: 24px;
    width: 300px;
    border-right: solid 1px #ddd;
  }
  .list {
    .top{
      display: flex;
      flex-direction: column;
      gap: 10px;
      padding-bottom: 20px;
      align-items: center;
      border-bottom: solid 1px #ddd;
    }
    padding: 24px;
    flex: 1;
    display: flex;
    gap: 10px;
    flex-direction: column;
  }
}
</style>
