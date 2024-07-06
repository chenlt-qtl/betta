<template>
  <div class="hw-container">
    <section class="day" v-for="data in Object.keys(workData)" :key="data">
      <span class="title">{{ isNaN(data) ? data : "" }}</span>
      <div class="detail">
        <div v-if="workData[data]">
          <section>
            <span class="item">内容</span>
            <span>自评</span>
            <span>妈妈</span>
          </section>
          <section v-for="(item, index) in workData[data]" :key="index">
            <span class="item">{{ item }}</span>
            <span><el-checkbox></el-checkbox></span>
            <span><el-checkbox></el-checkbox></span>
          </section>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
export default {
  data() {
    return {
      workData: {},
    };
  },
  created() {
    let start = 706,
      end = 731;
    let total = Math.ceil((end - start) / 3) * 3;
    const newWorkData = {};
    //豆芽
    const everyday = ["计算能手3页", "阅读30分钟", "字帖两页", "英语作业"];
    for (let i = 0; i < total; i++) {
      let work, key;
      if (start <= end) {
        key = Math.floor(start / 100) + "-" + (start % 100);
        work = [...everyday, ...(this.workData[key] || [])];
        start++;
        if ((i + 1) % 3 == 0) {
          work.push("数学暑假计算练习");
        }
        if (i % 3 == 0) {
          work.push("暑假新启航3页(语文)");
        }
        if (i % 3 == 1) {
          work.push("暑假新启航3页(数学)");
        }
        if (i % 3 == 2) {
          work.push("暑假新启航3页(英语)");
        }
        if ((i+1) % 10 == 0) {
          work.push("作文");
        }
        work.push(" ");
        work.push(" ");
      } else {
        key = -i;
      }

      newWorkData[key] = work;
    }

    this.workData = newWorkData;
  },
};
</script>
<style lang="scss" scoped>
.hw-container {
  display: flex;
  flex-wrap: wrap;
  margin: 24px;
  font-family: 微软雅黑;
  border-left: solid 1px #ccc;
  border-bottom: solid 1px #ccc;
  .day {
    border-right: solid 1px #ccc;
    width: 33.333%;
    padding-bottom: 5px;
    .title {
      height: 44px;
      display: block;
      padding: 10px 20px;
      font-weight: bold;
      border-top: solid 2px #aaa;
      border-bottom: solid 1px #ccc;
      text-align: center;
    }

    .detail {
      section {
        display: flex;
        gap: 5px;
        border-bottom: solid 1px #ccc;
        span {
          padding: 5px;
          border-left: solid 1px #ccc;
          width: 43px;
        }
        .item {
          flex: 1;
          border-left: none;
          width: auto;
        }
      }
    }
  }
}
</style>
