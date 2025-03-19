<template>
    <el-dialog title="" :visible.sync="openWin" @close="onClose" width="760px" append-to-body>
        <video-player :start="openWin"></video-player>
    </el-dialog>
</template>
<script>
import VideoPlayer from "./VideoPlayer.vue";

export default {
    props: ["open", "onClose"],
    components: { VideoPlayer },
    data() {
        return {
            openWin: this.open,
        };
    },
    watch: {
        open() {
            this.openWin = this.open;
        }

    },
};
</script>
<style lang="scss"></style>