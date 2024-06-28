<template>
  <div class="read-container">
    <div class="article">
      <div
        class="img"
        v-if="article.picture"
        :style="`background-image: url(${baseUrl + article.picture})`"
      />
      <div class="article-info">
        <section class="title">{{ article.title }}</section>
        <section class="group">{{ article.groupName }}</section>
      </div>
    </div>
    <el-radio-group v-model="type" size="mini">
      <el-radio-button label="sentence">句子</el-radio-button>
      <el-radio-button label="word">单词</el-radio-button>
    </el-radio-group>

    <div v-if="type == 'sentence'">
      <el-table
        v-loading="loading"
        :data="sentenceList"
        size="mini"
        :show-header="false"
      >
        <el-table-column prop="content">
          <template slot-scope="scope">
            <section class="sentence">
              <el-button
                type="text"
                v-if="scope.row.mp3 || (scope.row.mp3Time && article.mp3)"
                @click="
                  () => play(scope.row.mp3 || article.mp3, scope.row.mp3Time)
                "
              >
                {{ scope.row.content }}
              </el-button>
              <div v-if="!scope.row.mp3 && !(scope.row.mp3Time && article.mp3)">
                {{ scope.row.content }}
              </div>
              <span>
                {{ scope.row.acceptation }}
              </span>
            </section>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div v-if="type == 'word'">
      <el-table
        v-loading="loading"
        :data="wordList"
        size="mini"
        :show-header="false"
      >
        <el-table-column label="单词" align="center" prop="wordName">
          <template v-if="scope.row.phAnMp3" slot-scope="scope">
            <div class="word-container">
              <section class="word">
                <el-button type="text" @click="() => play(scope.row.phAnMp3)">
                  {{ scope.row.wordName }}
                </el-button>
                <span>{{ scope.row.phAm }}</span>
              </section>
              <span>{{
                scope.row.exchange
                  ? scope.row.exchange
                  : acceptationFormatter(scope.row)
              }}</span>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { getArticle } from "@/api/eng/article";
import { listSentence } from "@/api/eng/sentence";
import { listWordByArticle } from "@/api/eng/word";
import { play } from "@/utils/audio";

export default {
  data() {
    return {
      articleId: 0,
      article: {},
      baseUrl: process.env.VUE_APP_BASE_API,
      // 遮罩层
      loading: true,
      type: "sentence",
      // 英语文章表格数据
      sentenceList: [],
      wordList: [],
    };
  },
  created() {
    this.articleId = this.$route.params && this.$route.params.articleId;
    if (this.articleId) {
      this.getArticle();
      this.getSentenceList();
      this.getWordList();
    }
  },
  methods: {
    /** 查询英语文章 */
    getArticle() {
      this.loading = true;
      getArticle(this.articleId).then(({ data }) => {
        this.article = data;
        this.loading = false;
      });
    },
    play(url, mp3Time) {
      play(url, mp3Time);
    },
    /** 查询英语句子列表 */
    getSentenceList() {
      this.loading = true;
      listSentence({
        pageNum: 1,
        pageSize: 1000,
        articleId: this.articleId,
      }).then((response) => {
        this.sentenceList = response.rows;
        this.loading = false;
      });
    },
    /** 查询单词列表 */
    getWordList() {
      this.loading = true;
      listWordByArticle({
        pageNum: 1,
        pageSize: 1000,
        articleId: this.articleId,
      }).then((response) => {
        this.wordList = response.rows;
        this.loading = false;
      });
    },
    acceptationFormatter(row) {
      const acceptation = row.acceptation;
      const strs = acceptation.split("|");
      return strs[0] + (strs.length > 1 ? "..." : "");
    },
  },
};
</script>
<style scoped lang="scss">
.read-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;

  .article {
    display: flex;
    padding: 24px 10px;
    gap: 20px;

    .img {
      border-radius: 50%;
      width: 40px;
      height: 40px;
      background-size: cover; /* 背景图片覆盖整个元素 */
      background-repeat: no-repeat; /* 背景图片不重复 */
      background-position: center;
    }
    .article-info {
      .name {
        font-weight: bold;
        font-size: 18px;
      }
      .group {
        padding-top: 10px;
        color: #ccc;
        font-size: 12px;
      }
    }
  }
  .sentence {
    span {
      padding-top: 5px;
      display: block;
      font-size: 12px;
    }
  }
  .word-container {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    .word {
      display: flex;
      gap: 20px;
      align-items: center;
    }
  }
}
</style>
