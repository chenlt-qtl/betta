<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      label-width="68px"
      @submit.native.prevent
    >
      <el-form-item label="单词" prop="wordName">
        <el-input
          v-model="queryParams.wordName"
          placeholder="请输入单词"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <el-divider content-position="center"></el-divider>
    <div v-if="form.id">
      <el-descriptions
        class="margin-top"
        :title="form.wordName"
        :column="1"
        :colon="false"
      >
        <el-descriptions-item label=""
          ><div class="ph" v-if="form.phAnMp3">
            {{ form.phAm
            }}<el-button
              style="marginleft: 10px"
              type="text"
              @click="() => play(form.phAnMp3)"
              ><svg-icon icon-class="sound" /> </el-button
            ><el-button
              style="marginleft: 10px"
              type="text"
              @click="() => play(form.phAnMp3, '0,0', 0.5)"
              ><svg-icon icon-class="sound" /><span class="slow">慢</span>
            </el-button>
          </div>
          <el-button v-if="!form.relId" type="text" @click="updateRel"
            ><svg-icon icon-class="star1" />
          </el-button>
          <el-button v-if="form.relId" type="text" @click="updateRel"
            ><svg-icon icon-class="star-fill" /> </el-button
        ></el-descriptions-item>
        <template v-for="item in form.acceptation.split('|')">
          <el-descriptions-item label="" :key="item">{{
            item
          }}</el-descriptions-item>
        </template>
      </el-descriptions>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>爱词霸例句</span>
        </div>
        <div v-for="item in form.icibaSentenceList" :key="item.id">
          {{ item.orig }}<br /><br />
          {{ item.trans }}<br /><br /><br /><br />
        </div>
      </el-card>
      <br />
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>自定义例句</span>
        </div>
        <div v-for="item in form.sentenceList" :key="item.id">
          {{ item.content }}<el-button
              style="margin-left: 10px"
              type="text"
              @click="() => play(item.mp3)"
              ><svg-icon icon-class="sound" /> </el-button
            ><br /><br />
          <span v-if="item.acceptation"
            >{{ item.acceptation }}<br /><br /><br /><br
          /></span>
        </div>
      </el-card>
    </div>
  </div>
</template>
<style>
.ph {
  font-size: 20px;
  display: inline-flex;
  gap: 10px;
  align-items: center;
  background-color: #f5f6f9;
  border-radius: 20px;
  padding: 0 25px;
  margin: 0 20px 5px 0;
}

.el-button--medium {
  font-size: 22px;
  position: relative;
}

.ph .slow {
  position: absolute;
  background-color: #f5f6f9;
  color: #46a6ff;
  font-size: 12px;
  left: -5px;
  top: 13px;
}
</style>
<script>
import { getWord, delWord, addWord } from "@/api/eng/word";
import { play } from "@/utils/audio";
import { delArticleWordRel, addArticleWordRel } from "@/api/eng/articleWordRel";
export default {
  name: "Word",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wordName: null,
        acceptation: null,
        exchange: null,
      },
    };
  },
  created() {
    this.getWord();
  },
  methods: {
    /** 查询单词列表 */
    getWord() {
      if (this.queryParams.wordName) {
        this.loading = true;
        getWord(this.queryParams).then((response) => {
          this.form = response.data;
          this.loading = false;
        });
      } else {
        this.form = {};
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getWord();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    play(url, time, rate) {
      play(url, time, rate);
    },
    /** 更新关联 */
    updateRel() {
      const relId = this.form.relId;
      if (relId) {
        //删除
        delArticleWordRel(relId).then(() => {
          this.getWord();
        });
      } else {
        //增加
        addArticleWordRel({wordId:this.form.id,articleId:0}).then(() => {
          this.getWord();
        });
      }
    },
  },
};
</script>
