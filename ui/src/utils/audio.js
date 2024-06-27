let player = new Audio();
let timer;

/**播放MP3 */
export const play = (url, timeStr = "", rate = 1) => {

  player.src = process.env.VUE_APP_BASE_API + url;
  player.load();

  //先重置
  clearTimeout(timer)
  player.pause();

  let duration, startTime;
  const timeArr = timeStr.split(",");

  if (timeArr.length == 2) {
    //处理开始时间
    if (/^\d+$/.test(timeArr[0])) { //格式1
      startTime = parseInt(timeArr[0]) //秒
    } else if (/^\d+:\d+$/.test(timeArr[0])) { //格式2
      const arr = timeArr[0].split(":");
      startTime = parseInt(arr[0]) * 60 + parseInt(arr[1])
    }

    //处理时长
    if (/^\d+(.\d+)*$/.test(timeArr[1])) {
      duration = parseFloat(timeArr[1]) //秒
    }
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
