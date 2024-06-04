let player = new Audio();
let timer;

/**播放MP3 */
export const play = (url, timeStr = "0,0", rate = 1) => {    

  player.src = process.env.VUE_APP_BASE_API + url;
  player.load();

  //先重置
  clearTimeout(timer)
  player.pause();

  const [startTimeStr,durationStr] = timeStr.split(",");

  const startTime = parseInt(startTimeStr)//秒
  const duration = parseFloat(durationStr)//毫秒

  player.currentTime = startTime;
  player.playbackRate = rate;//速率

  player.play();

  if (duration) {

      const realDuration = duration / rate;
      console.log("duration", realDuration);

      timer = setTimeout(() => {
          player.pause();
      }, realDuration * 1000)

  }

}
