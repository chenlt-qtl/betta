<template>
  <div class="timer-container">
    <div class="header">
      <span
        >上课时长:
        <el-input-number
          v-model="classDuration"
          :disabled="isRun"
          @change="(e) => classDuration=e"
          style="width:150px"
        ></el-input-number
      ></span>
      <span
        >下课时长:
        <el-input-number
          v-model="afterClassDuration"
          :disabled="isRun"
          @change="(e) => afterClassDuration=e"
          style="width:150px"
        ></el-input-number
      ></span>
      <el-button @click="startClass">开始上课</el-button>
      <el-button @click="stop">暂停/继续</el-button>
      <el-popconfirm :disabled="!isRun" title="确认要重置?" @confirm="reset"
        ><el-button :disabled="!isRun" slot="reference">重置</el-button></el-popconfirm
      >
    </div>

    <div class="display">
      <div>
        当前状态:
        <div :class="`status ${classStatus ? 'inClass' : 'afterClass'}`">
          {{ classStatus ? "上课" : "下课" }}
        </div>
      </div>
      <div>
        剩余时间:
        <div class="restTime">
          <span>{{ displayStr }}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { play } from "@/utils/audio";

let intervalIndex, restTime;
export default {
  data() {
    return {
      //上课时长
      classDuration: 35,
      //下课时长
      afterClassDuration: 8,
      //是否上课
      classStatus: false,
      //显示的剩余时间
      displayStr: "00 : 00",
      //是否正在运行
      isRun: false,
    };
  },
  beforeDestroy() {
    clearInterval(intervalIndex);
  },
  methods: {
    startClass() {
      clearInterval(intervalIndex);
      intervalIndex = null;
      this.isRun = true;
      this.startInterval(true);
    },
    soundEffect(inClass) {
      let mp3;
      if (inClass) {
        //上课
        mp3 = "5c892db31ad7e23153.mp3";
      } else {
        //下课
        mp3 = "5c892db3b1b9a62189.mp3";
      }
      play("/profile/sys/mp3/" + mp3);
    },
    stop() {
      if (intervalIndex) {
        console.log("暂停");

        clearInterval(intervalIndex);
        intervalIndex = null;
      } else {
        console.log("继续");
        intervalIndex = setInterval(() => {
          this.displayStr = this.displayTime(--restTime);
        }, 1000);
      }
    },
    startInterval(inClass) {
      this.soundEffect(inClass);
      restTime = (inClass ? this.classDuration : this.afterClassDuration) * 60;
      this.classStatus = inClass;
      this.displayStr = this.displayTime(restTime);
      intervalIndex = setInterval(() => {
        if (restTime <= 0) {
          clearInterval(intervalIndex);
          intervalIndex = null;
          this.startInterval(!inClass);
        } else {
          this.displayStr = this.displayTime(--restTime);
        }
      }, 1000);
    },
    reset() {
      this.classStatus = false;
      this.displayStr = this.displayTime(0);
      clearInterval(intervalIndex);
      this.isRun = false;
    },
    displayTime(restTime) {
      let str = "";
      const restMin = Math.floor(restTime / 60);
      str += restMin < 10 ? "0" : "";
      str += restMin;

      const restSec = restTime % 60;
      str += restSec < 10 ? " : 0" : " : ";
      str += restSec;
      return str;
    },
  },
};
</script>
<style lang="scss">
.timer-container {
  max-width: 800px;
  margin: 20px auto;

  .header {
    display: flex;
    gap: 10px;
    align-items: center;
    >span{
        display: flex;
        align-items: center;
        gap: 5px;
    }
  }

  .display {
    display: flex;
    padding: 50px;
    align-content: center;
    flex-direction: column;

    .status {
      padding-left: 100px;
      font-size: 50px;
    }

    .restTime {
      padding-left: 100px;
      font-size: 80px;
      display: flex;
      gap: 10px;
    }
    .inClass {
      color: chocolate;
    }

    .afterClass {
      color: cadetblue;
    }
  }
}
</style>
