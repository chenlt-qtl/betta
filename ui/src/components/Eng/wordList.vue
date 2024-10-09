<template>
  <div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAddWord"
        >
          添加
        </el-button>
      </el-col>
    </el-row>
    <el-table v-loading="loading" :data="data">
      <el-table-column label="单词" align="center" prop="wordName" />
      <el-table-column label="音标" align="center" prop="phAm" />
      <el-table-column
        label="解释"
        align="center"
        prop="acceptation"
        :formatter="acceptationFormatter"
      />
      <el-table-column label="熟悉度" align="center" prop="familiarity" />
      <el-table-column label="注释" align="center" prop="exchange" />
      <el-table-column label="音频" align="center" prop="phAnMp3">
        <template v-if="scope.row.phAnMp3" slot-scope="scope">
          <el-button type="text" @click="() => play(scope.row.phAnMp3)">
            <svg-icon icon-class="sound" />
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDeleteRel(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 添加或修改单词对话框 -->
    <el-dialog title="添加" :visible.sync="open" width="500px" append-to-body>
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="80px"
        @submit.native.prevent
      >
        <el-form-item label="单词内容" prop="wordName">
          <el-input
            v-model="form.wordName"
            placeholder="请输入单词内容"
            @keyup.enter.native="searchWord"
          />
        </el-form-item>
        <el-form-item>
          <el-button v-if="phAnMp3" type="text" @click="() => play(phAnMp3)">
            <svg-icon icon-class="sound" />
          </el-button>
          <div v-for="str in acceptations" :key="str">
            {{ str }}
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="searchWord">查詢</el-button>
        <el-button
          v-if="acceptations && acceptations.length > 0"
          type="primary"
          @click="submitForm"
          >添加</el-button
        >
        <el-button @click="() => (open = false)">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addWordByArticle, getWord } from "@/api/eng/word";
import { delArticleWordRel } from "@/api/eng/articleWordRel";

export default {
  props: ["data", "loading", "canEdit", "articleId", "getWordList", "play"],
  data() {
    return {
      open: false,
      form: {},
      acceptations: [],
      phAnMp3: "",
      // 表单校验
      rules: {
        wordName: [
          { required: true, message: "单词内容不能为空", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    acceptationFormatter(row) {
      const acceptation = row.acceptation;
      if (acceptation) {
        const strs = acceptation.split("|");
        return strs[0] + (strs.length > 1 ? "..." : "");
      } else {
        return "";
      }
    },
    handleAddWord() {
      this.open = true;
      this.resetForm("form");
      this.acceptations = [];
      this.phAnMp3 = "";
    },
    searchWord() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          getWord({ wordName: this.form.wordName }).then((res) => {
            if (res.data && res.data.acceptation) {
              this.acceptations = res.data.acceptation.split("|");
            }
            if (res.data && res.data.phAnMp3) {
              this.phAnMp3 = res.data.phAnMp3;
            }
          });
        }
      });
    },
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          addWordByArticle(this.articleId, this.form.wordName).then((res) => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getWordList();
          });
        }
      });
    },
    handleDeleteRel(row) {
      this.$modal
        .confirm("是否确认删除选中的单词？")
        .then(function () {
          return delArticleWordRel(row.relId);
        })
        .then(() => {
          this.getWordList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped lang="scss">
</style>
