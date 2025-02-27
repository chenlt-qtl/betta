<template>
  <div class="timer-container">
    <div class="header">
      <el-button size="mini" type="text" @click="() => setTime(30, 10)"
        >30-10</el-button
      >
      <el-button size="mini" type="text" @click="() => setTime(45, 12)"
        >45-12</el-button
      >
    </div>
    <div class="header">
      <span
        >上课时长:
        <el-input-number
          v-model="classDuration"
          :disabled="isRun"
          @change="(e) => (classDuration = e)"
          style="width: 150px"
        ></el-input-number
      ></span>
      <span
        >下课时长:
        <el-input-number
          v-model="afterClassDuration"
          :disabled="isRun"
          @change="(e) => (afterClassDuration = e)"
          style="width: 150px"
        ></el-input-number
      ></span>
      <div>
        <el-button @click="startClass">开始上课</el-button>
        <el-button @click="stop">暂停/继续</el-button>
        <el-popconfirm
          style="margin-left: 10px"
          :disabled="!isRun"
          title="确认要重置?"
          @confirm="reset"
          ><el-button :disabled="!isRun" slot="reference"
            >重置</el-button
          ></el-popconfirm
        >
      </div>
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

const times = [
  { start: { hour: 8, mins: 30 }, end: { hour: 12, mins: 0 } },
  { start: { hour: 13, mins: 0 }, end: { hour: 21, mins: 30 } },
];

const musics = ["music1.mp3","lonely.mp3","music1.mp3"]
let musicIdx = 0;

let intervalIndex, restTime;
export default {
  data() {
    return {
      //上课时长
      classDuration: 30,
      //下课时长
      afterClassDuration: 12,
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
    setTime(classDuration, afterClassDuration) {
      this.classDuration = classDuration;
      this.afterClassDuration = afterClassDuration;
    },
    startClass() {
      clearInterval(intervalIndex);
      intervalIndex = null;
      this.isRun = true;
      this.startInterval(true);
    },
    soundEffect(inClass) {
      if (this.checkTime()) {
        if (inClass) {
          //上课
          const player = play("/profile/sys/mp3/5c892db31ad7e23153.mp3");
          player.removeEventListener("pause",this.playMusic)
        } else {
          //下课
          const player = play("/profile/sys/mp3/5c892db3b1b9a62189.mp3");
          player.addEventListener("pause", this.playMusic);
 
        }
        
      }
    },
    playMusic(){
      play("/profile/sys/mp3/"+musics[musicIdx]);
      if(++musicIdx>=musics.length){
        musicIdx = 0;
      }
    },
    //校验是否是有效时间
    checkTime() {
      const now = new Date();

      let canPlay = false;
      times.forEach(({ start, end }) => {
        const startDate = new Date();
        startDate.setHours(start.hour);
        startDate.setMinutes(start.mins);
        const endDate = new Date();
        endDate.setHours(end.hour);
        endDate.setMinutes(end.mins);
        if (startDate <= now && now <= endDate) {
          canPlay = true;
          return;
        }
      });
      return canPlay;
    },
    stop() {
      if (intervalIndex) {
        clearInterval(intervalIndex);
        intervalIndex = null;
      } else {
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
    padding-bottom: 20px;
    flex-wrap: wrap;
    gap: 10px;
    align-items: center;
    justify-content: center;
    > span {
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
