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
    <el-dialog
      title=""
      :visible.sync="open"
      width="760px"
      append-to-body
    >
      <video  ref="videoPlayer" width="720" height="480">
        <source :src="videoSrc" type="video/mp4">
        </source>
      </video>
    </el-dialog>
  </div>
</template>
<script>
import { play } from "@/utils/audio";

const times = [
  { start: { hour: 8, mins: 30 }, end: { hour: 12, mins: 0 } },
  { start: { hour: 13, mins: 0 }, end: { hour: 21, mins: 30 } },
];

const videos = ["lonely","wangzi","DaDaDa","DiDiDiDiWa","HotHotHot","India","mfsw","TocaToca","TheGreatest"]
let videoIdx = 0;

let intervalIndex, restTime;
export default {
  data() {
    return {
      //上课时长
      classDuration: localStorage.getItem("classDuration")||30,
      //下课时长
      afterClassDuration: localStorage.getItem("afterClassDuration")||12,
      //是否上课
      classStatus: false,
      //显示的剩余时间
      displayStr: "00 : 00",
      //是否正在运行
      isRun: false,
      open:false,
      videoSrc:"",
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
      localStorage.setItem("classDuration",this.classDuration)
      localStorage.setItem("afterClassDuration",this.afterClassDuration)
      clearInterval(intervalIndex);
      intervalIndex = null;
      this.isRun = true;
      this.startInterval(true);
    },
    playVideo(){
      this.videoSrc=process.env.VUE_APP_BASE_API + "/profile/sys/video/"+videos[videoIdx]+".mp4";
      if(++videoIdx>=videos.length){
        videoIdx = 0;
      }
      this.$refs.videoPlayer.load();
      this.$refs.videoPlayer.play();
    },
    soundEffect(inClass) {
      if (this.checkTime()) {
        if (inClass) {
          //上课
          play("/profile/sys/mp3/5c892db31ad7e23153.mp3");
          if(this.$refs.videoPlayer){
            this.$refs.videoPlayer.removeEventListener("pause", this.playVideo);
            this.$refs.videoPlayer.pause();
          }
          this.open=false;
        } else {
          //下课
          play("/profile/sys/mp3/5c892db3b1b9a62189.mp3");
          this.open=true;
          //videoIdx = 0;
          this.$nextTick(() => {
            this.playVideo();
            this.$refs.videoPlayer.addEventListener("pause", this.playVideo);
          })
        }

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
