<template>
  <div class="test-step1">
    <div v-if="!isStart" class="word-table">
      <el-table :data="wordList" size="mini" :show-header="false" class="table">
        <el-table-column label="单词" prop="wordName">
          <template v-if="scope.row.phAnMp3" slot-scope="scope">
            <div class="word-container">
              <section class="word">
                {{ scope.row.wordName }}
                <span> / {{ scope.row.phAm }} /</span>
              </section>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <div class="toolbar">
        <button class="block-button" @click="start">开始</button>
      </div>
    </div>
    <div v-if="isStart" class="word-detail">
      <section class="wordName">{{ word.wordName }}</section>
      <div class="ph" v-if="word.phAnMp3">
        / {{ word.phAm }} /
        <el-button
          style="marginleft: 10px"
          type="text"
          @click="() => play(word.phAnMp3)"
          ><svg-icon icon-class="sound" />
        </el-button>
      </div>
      <span style="flex: 1">
        {{ word.exchange ? word.exchange : acceptation.split("|").join("") }}
      </span>
      <div class="toolbar">
        <button class="block-button" style="width: 52px" @click="nextStep">
          <i class="el-icon-right"></i>
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import { play } from "@/utils/audio";

export default {
  props: ["wordList", "questionList"],
  data() {
    return {
      index: 0,
      word: {},
      isStart: false,
    };
  },
  computed: {
    acceptation() {
      if (this.word && this.word.acceptation) {
        return this.word.acceptation;
      } else {
        return "";
      }
    },
  },
  watch: {
    word() {
      if (this.word && this.word.phAnMp3) {
        play(this.word.phAnMp3);
      }
    },
  },
  methods: {
    changeIndex(value) {
      this.index = this.index + value;
      this.word = this.wordList[this.index];
    },
    play(url) {
      play(url);
    },
    start() {
      this.isStart = true;
      this.word = this.wordList[this.index];
    },
    nextStep() {
      if (this.index < this.wordList.length - 1) {
        this.changeIndex(1);
      } else {
        this.$emit("next");
      }
    },
  },
};
</script>
<style lang="scss">
.test-step1 {
  height: 100%;
  .word-table {
    display: flex;
    flex-direction: column;
    height: 100%;
    .table {
      flex: 1;
    }
    .el-table {
      background-color: transparent;
      tr {
        background-color: transparent;
      }
      td.el-table__cell {
        border-color: #fff;
      }
    }
  }
  .word-detail {
    height: 100%;
    display: flex;
    flex-direction: column;
  }
  .toolbar {
    text-align: center;
    padding: 10px 0;
  }
}
</style>
