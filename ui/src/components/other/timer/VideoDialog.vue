<template>
    <el-dialog title="" :visible.sync="openWin" @close="onClose"  width="760px" append-to-body>
        <video ref="videoPlayer" width="720" height="480">
            <source :src="videoSrc" type="video/mp4">
            </source>
        </video>
    </el-dialog>
</template>
<script>

const videos = ["lonely", "wangzi", "DaDaDa", "DiDiDiDiWa", "HotHotHot", "India", "mfsw", "TocaToca", "TheGreatest"]
let videoIdx = 0;


export default {
    props: ["open", "onClose"],
    data() {
        return {
            videoSrc: "",
            openWin:this.open
        };
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
                    this.$refs.videoPlayer.removeEventListener("pause", this.playVideo);
                    this.$refs.videoPlayer.pause();
                }
            }
        }

    },
    methods: {
        playVideo() {
            this.videoSrc = process.env.VUE_APP_BASE_API + "/profile/sys/video/" + videos[videoIdx] + ".mp4";
            if (++videoIdx >= videos.length) {
                videoIdx = 0;
            }
            this.$refs.videoPlayer.load();
            this.$refs.videoPlayer.play();
        },
    },
};
</script>
<style lang="scss"></style>