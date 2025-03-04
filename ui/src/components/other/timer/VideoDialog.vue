<template>
    <el-dialog title="" :visible.sync="openWin" @close="onClose" width="760px" append-to-body>
        <el-button v-for="video, index in videos" :key="video.src" size="mini" :type="index == videoIdx ? '' : 'text'"
            @click="() => playSelect(index - 1)">{{ video.name }}</el-button>
        <video ref="videoPlayer" width="720" height="480" controls>
            <source :src="videoSrc" type="video/mp4">
            </source>
        </video>
    </el-dialog>
</template>
<script>
export default {
    props: ["open", "onClose"],
    data() {
        return {
            videoSrc: "",
            openWin: this.open,
        };
    },
    created() {
        this.videos = [{ name: "Lonely", src: "lonely" },
        { name: "小丸子", src: "wangzi" },
        { name: "Da Da Da", src: "DaDaDa" },
        { name: "嘀嘀哇", src: "DiDiDiDiWa" },
        { name: "Hot", src: "HotHotHot" },
        { name: "印度歌", src: "India" },
        { name: "眉飞色舞", src: "mfsw" },
        { name: "Toca", src: "TocaToca" },
        { name: "The Greatest", src: "TheGreatest" }];

        this.videoIdx = -1;

        this.$nextTick(() => {
            this.$refs.videoPlayer.addEventListener("ended", this.playVideo);
        })
    },
    beforeDestroy() {
        this.$refs.videoPlayer.removeEventListener("ended", this.playVideo);
    },
    watch: {
        open() {
            this.openWin = this.open;
            if (this.open) {
                //打开窗口
                //播放音乐
                this.$nextTick(() => {
                    this.playVideo();
                })
            } else {
                //关闭窗口
                //关闭音乐
                if (this.$refs.videoPlayer) {
                    if (this.$refs.videoPlayer) {
                        this.$refs.videoPlayer.pause();
                    }
                }
            }
        }

    },
    methods: {
        playVideo() {
            if (++this.videoIdx >= this.videos.length) {
                this.videoIdx = 0;
            }
            this.videoSrc = process.env.VUE_APP_BASE_API + "/profile/sys/video/" + this.videos[this.videoIdx].src + ".mp4";
            this.$refs.videoPlayer.load();
            this.$refs.videoPlayer.play();
        },
        //播放指定的音乐
        playSelect(index) {
            this.videoIdx = index;
            this.playVideo();
        },
    },
};
</script>
<style lang="scss"></style>