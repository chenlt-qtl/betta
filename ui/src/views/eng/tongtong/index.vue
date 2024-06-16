<template>
  <div class="tong-container">
    <img :src="p71" alt="" />
    <div class="left">
      <span
        @click="
          () => {
            playMp3('bus');
          }
        "
        style="top: 370px; width: 43px"
      ></span>
      <span
        @click="
          () => {
            playMp3('clown');
          }
        "
        style="top: 405px; width: 56px"
      ></span>
      <span
        @click="
          () => {
            playMp3('coat');
          }
        "
        style="top: 440px; width: 48px"
      ></span>
      <span
        @click="
          () => {
            playMp3('dad');
          }
        "
        style="top: 475px; width: 43px"
      ></span>
      <span
        @click="
          () => {
            playMp3('day');
          }
        "
        style="top: 509px; width: 43px"
      ></span>
      <span
        @click="
          () => {
            playMp3('dress');
          }
        "
        style="top: 541px; width: 64px"
      ></span>
      <span
        @click="
          () => {
            playMp3('every');
          }
        "
        style="top: 575px; width: 64px"
      ></span>
      <span
        @click="
          () => {
            playMp3('finger');
          }
        "
        style="top: 607px; width: 64px"
      ></span>
      <span
        @click="
          () => {
            playMp3('get');
          }
        "
        style="top: 640px; width: 34px"
      ></span>
      <span
        @click="
          () => {
            playMp3('dress');
          }
        "
        style="left: 258px; top: 640px; width: 64px"
      ></span>
      <span
        @click="
          () => {
            playMp3('has');
          }
        "
        style="top: 673px; width: 43px"
      ></span>
      <span
        @click="
          () => {
            playMp3('knee');
          }
        "
        style="top: 706px; width: 48px"
      ></span>
    </div>
    <div class="right">
      <span
        @click="
          () => {
            playMp3('mum');
          }
        "
        style="top: 370px; width: 43px"
      ></span>
      <span
        @click="
          () => {
            playMp3('old');
          }
        "
        style="top: 405px; width: 43px"
      ></span>
      <span
        @click="
          () => {
            playMp3('one');
          }
        "
        style="top: 440px; width: 43px"
      ></span>
      
      <span
        @click="
          () => {
            playMp3('really');
          }
        "
        style="top: 509px; width: 60px"
      ></span>
      <span
        @click="
          () => {
            playMp3('shoulder');
          }
        "
        style="top: 541px; width: 80px"
      ></span>
      <span
        @click="
          () => {
            playMp3('skirt');
          }
        "
        style="top: 575px; width: 48px"
      ></span>
      <span
        @click="
          () => {
            playMp3('song');
          }
        "
        style="top: 607px; width: 48px"
      ></span>
      <span
        @click="
          () => {
            playMp3('toe');
          }
        "
        style="top: 640px; width: 34px"
      ></span>
      
      <span
        @click="
          () => {
            playMp3('trousers');
          }
        "
        style="top: 673px; width: 76px"
      ></span>
      <span
        @click="
          () => {
            playMp3('with');
          }
        "
        style="top: 706px; width: 48px"
      ></span>
    </div>
  </div>
</template>

<script>
import p71 from "@/assets/images/p71.png";
import { play } from "@/utils/audio";

export default {
  data() {
    return {
      p71: p71,
    };
  },
  methods: {
    playMp3(name) {
      play("/profile/word/" + name.substring(0, 1) + "/" + name + ".mp3");
    },
  },
};
</script>

<style scoped lang="scss">
.tong-container {
  position: relative;
  img {
    margin: 0 auto;
    width: 1000px;
  }
  .left {
    position: absolute;
    top: 0;
    left: 0;

    span {
      position: absolute;
      left: 225px;
      display: block;
      height: 29px;
      border: 1px solid #df27bd;
      cursor: pointer;
    }
  }

  .right {
    position: absolute;
    top: 2px;
    left: 318px;
    span {
      position: absolute;
      left: 225px;
      display: block;
      height: 29px;
      border: 1px solid #df27bd;
      cursor: pointer;
    }
  }
}
</style>

