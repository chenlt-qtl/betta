<template>
  <div>
    <el-button size="mini" @click="handleShowDialog" type="primary">输入网址</el-button>
    {{ fileName }}
    <!-- 输入网址弹窗 -->
    <el-dialog
      title=""
      :visible.sync="dialogVisible"
      append-to-body
      width="30%"
    >
      <el-input placeholder="输入网址" v-model="fileUrl"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleUploadUrl">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { uploadByUrl } from "@/api/common";

export default {
  props: ["uploadType"],
  data() {
    return {
      dialogVisible: false,
      fileUrl: "",
      fileName: "",
    };
  },
  methods: {
    handleUploadUrl() {
      uploadByUrl(this.fileUrl, this.uploadType).then((res) => {
        if ((res.code = 200)) {
          this.dialogVisible = false;
          this.$emit("input", res.url);
          console.log('====================================');
          console.log(res.url);
          console.log('====================================');
          this.fileName = res.fileName;
        }
      });
    },
    handleShowDialog() {
      this.dialogVisible = true;
      this.fileUrl = "";

    },
  },
};
</script>