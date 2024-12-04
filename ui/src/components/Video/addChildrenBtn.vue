<template>
  <span>
    <el-button
      size="mini"
      type="text"
      icon="el-icon-edit"
      @click="handleAddChildren"
      v-hasPermi="['video:video:edit']"
    >
      增加子视频
    </el-button>
    <el-dialog
      title="增加子视频"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="80px"
        label-position="top"
      >
        <el-form-item label="" prop="text">
          <el-input
            type="textarea"
            :rows="8"
            v-model="form.text"
            placeholder="请输入子视频名称，多个子视频请换行"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="() => (open = false)">取 消</el-button>
      </div>
    </el-dialog>
  </span>
</template>

<script>
import { addChildren } from "@/api/video/video";

export default {
  props: ["pid", "getList"],
  data() {
    return {
      open: false,
      form: {},
      //表单校验
      rules: {
        text: [{ required: true, message: "内容不能为空", trigger: "change" }],
      },
    };
  },
  methods: {
    handleAddChildren() {
      this.form = {};
      this.open = true;
    },
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          addChildren({ text: this.form.text, pid: this.pid }).then(() => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
  },
};
</script>
