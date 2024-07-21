<template>
  <div class="test-step2">
    <span class="tip">{{ tip }}</span>
    <section class="question">
      <section>{{ this.question.question }}</section>
      <span v-if="!question.type" class="phAm">
        / {{ this.question.word.phAm }} /
        <svg-icon
          @click="() => play(this.question.word.phAnMp3)"
          icon-class="sound"
        />
      </span>
    </section>
    <section class="answer">
      <ul>
        <li
          :class="'box-block ' + getAnswerClass(index)"
          v-for="(answer, index) in this.question.answers"
          :key="answer"
          @click="() => choseAnswer(index)"
        >
          {{ answer }}
        </li>
      </ul>
    </section>
  </div>
</template>
<script>
import { play } from "@/utils/audio";

export default {
  props: ["wordList", "result"],
  data() {
    return {
      index: 0,
      userAnswer: -1,
      question: {},
      isStart: false,
      questionList: [],
      type: 0, //0：词选意思  1：意思选词， 2：句子填空
      tip: "",
    };
  },
  created() {
    //生成题目
    const questionList = [];
    this.wordList.forEach((word) => {
      questionList.push(this.getQuestion(word, 0));
      questionList.push(this.getQuestion(word, 1));
      const question3 = this.getQuestion(word, 2);
      question3 && questionList.push(question3);
    });
    //打乱顺序
    for (let i = 0; i < questionList.length; i++) {
      const j = parseInt(Math.random() * questionList.length);
      const temp = questionList[i];

      questionList[i] = questionList[j];
      questionList[j] = temp;
    }
    this.questionList = questionList;
    this.question = this.questionList[this.index];
  },
  watch: {
    question() {
      if (this.question.type == 0 && this.question.word.phAnMp3) {
        play(this.question.word.phAnMp3);
      }
      this.tip =
        this.index + this.wordList.length + 1 + "/" + this.wordList.length * 4;
    },
  },
  methods: {
    getAnswerClass(index) {
      if (this.userAnswer != -1) {
        if (this.userAnswer == index) {
          if (this.question.answerKey == index) {
            return "right";
          } else {
            return "wrong";
          }
        } else {
          if (this.question.answerKey == index) {
            return "right";
          }
        }
      }
      return "";
    },
    getRandomWords(number, word) {
      const result = [];
      while (true) {
        const j = parseInt(Math.random() * this.wordList.length);
        const randomWord = this.wordList[j];
        const exist = result.find(
          (word) => word.wordName && word.wordName == randomWord.wordName
        );
        if (!exist && randomWord.wordName != word.wordName) {
          result.push(randomWord);
        }

        if (result.length >= number) {
          break;
        }
      }
      return result;
    },
    getExchange(word) {
      return word.exchange || word.acceptation.split("|").join("");
    },
    getWordName(word) {
      return word.wordName;
    },
    //获取题目
    getQuestion(word, type) {
      let method, question;
      switch (
        type //0：词选意思  1：意思选词， 2：句子填空
      ) {
        case 0:
          method = this.getExchange;
          question = this.getWordName(word);
          break;
        case 1:
          method = this.getWordName;
          question = this.getExchange(word);
          break;
        case 2:
          if (!word.sentence) {
            return;
          }
          method = this.getWordName;
          question = word.sentence.replace(
            new RegExp(word.wordName, "i"),
            "_____"
          );
          break;
      }
      const answer = method(word);
      const answers = [];
      const answerKey = parseInt(Math.random() * 4);
      answers[answerKey] = answer;
      const otherWords = this.getRandomWords(3, word);
      for (let i = 0; i < 4; i++) {
        if (i != answerKey) {
          const randomWord = otherWords.pop();
          answers[i] = method(randomWord);
        }
      }
      return {
        type,
        answerKey,
        answers,
        question,
        word,
      };
    },
    changeIndex(value) {
      this.index = this.index + value;
      this.word = this.wordList[this.index];
    },
    play(url) {
      play(url);
    },
    choseAnswer(i) {
      if (this.userAnswer != -1) {
        return;
      }
      this.userAnswer = i;
      play(this.question.word.phAnMp3);
      //答对了
      if (i == this.question.answerKey) {
        this.question.word.familiarity =
          (this.question.word.familiarity || 0) + 1;
        this.result.right = this.result.right + 1;
      } else {
        //答错了
        this.question.word.familiarity =
          (this.question.word.familiarity || 0) - 1;
        this.result.wrong = this.result.wrong + 1;
      }
      setTimeout(() => {
        if (this.index < this.questionList.length - 1) {
          this.index++;
          this.userAnswer = -1;
          this.question = this.questionList[this.index];
        } else {
          this.$emit("next");
        }
      }, 1000);
    },
  },
};
</script>
<style scoped lang="scss">
.test-step2 {
  display: flex;
  flex-direction: column;
  height: 100%;
  .question {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 24px;
    font-weight: 600;
    gap: 5px;
    overflow: hidden;
  }
  .answer {
    ul {
      padding: 0;
    }
    li {
      list-style: none;
    }
  }
  .right {
    background: rgba(46, 204, 113, 0.2);
  }
  .wrong {
    background: rgba(231, 76, 60, 0.15);
  }
}
</style>
