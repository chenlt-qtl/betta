let player = new Audio();
let timer;

/**播放MP3 */
export const play = (url, timeStr, rate = 1) => {

  player.src = process.env.VUE_APP_BASE_API + url;
  player.load();

  //先重置
  clearTimeout(timer)
  player.pause();

  let duration, startTime;

  if (/^\d+,\d+(.\d+)*$/.test(timeStr)) {
    const [startTimeStr = 0, durationStr = 0] = timeStr.split(",");
    startTime = parseInt(startTimeStr) //秒
    duration = parseFloat(durationStr) //毫秒
  }

  player.currentTime = startTime | 0;
  player.playbackRate = rate; //速率

  player.play();

  if (duration) {

    const realDuration = duration / rate;
    console.log("duration", realDuration);

    timer = setTimeout(() => {
      player.pause();
    }, realDuration * 1000)

  }

  return player;

}
