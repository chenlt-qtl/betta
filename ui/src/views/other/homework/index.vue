<template>
  <div class="hw-container">
    <ol>
      <li>当天完成作业+10</li>
      <li>书写分一个字+1</li>
      <li>书写差一个字-1</li>
      <li>作业全对+5</li>
    </ol>
    <el-radio-group v-model="person">
      <el-radio label="dy">豆芽</el-radio>
      <el-radio label="tt">桐桐</el-radio>
    </el-radio-group>
    <div class="content">
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      workData: {},
      person: "dy",
    };
  },
  watch: {
    person() {
      this.getData();
    },
  },
  created() {
    this.getData();
  },
  methods: {
    getData() {
      let start = 729,
        end = 731;
      let total = Math.ceil((end - start) / 4) * 4;
      const newWorkData = {};

      const other = this.person == "dy" ? ["专项练习——周长与面积(第二面)"] : [];
      //豆芽
      const everyday =
        this.person == "dy"
          ? [
              { name: "计算能手", start: 44, step: 2 },
              { name: "字帖", start: 26, step: 1 },
              "阅读30分钟",
              "跳绳",
            ]
          : [
              "读看图写话分三步1篇*3",
              "阅读30分钟",
              "听写12个词语",
              { name: "字帖", start: 43, step: 1 },
              { name: "课课优默", start: 42, step: 2 },
              { name: "数学(3)", start: 9, step: 3 },
              "跳绳",
              "每周一题",
            ];
      const tt1 = { name: "舒尔特方格(初级)", start: 21, step: 1 };
      const dy5 = { name: "舒尔特方格(中级)", start: 10, step: 1 };
      const dy1 = { name: "暑假新启航(语文)", start: 29, step: 3 };
      const dy2 = { name: "暑假新启航(数学)", start: 61, step: 3 };
      const dy3 = { name: "暑假新启航(科学)", start: 108, step: 3 };
      const dy4 = { name: "数学暑假计算练习", start: 4, step: 1 };
      for (let i = 0; i < total; i++) {
        let work = [],
          key;
        if (start <= end) {
          key = Math.floor(start / 100) + "-" + (start % 100);
          everyday.forEach((w) => this.addWork(work, w));
          start++;
          if (other[i]) {
            work.push(other[i]);
          }
          if (this.person == "dy") {
            if ((i + 1) % 3 == 0) {
              this.addWork(work, dy4);
            }
            if (i % 3 == 0) {
              this.addWork(work, dy1);
            }
            if (i % 3 == 1) {
              this.addWork(work, dy2);
            }
            if (i % 3 == 2) {
              this.addWork(work, dy3);
            }
            if (i % 3 == 3) {
              this.addWork(work, dy3);
            }
            if ((i + 10) % 10 == 0) {
              // work.push("作文");
            }
            this.addWork(work, dy5);
          } else {
            if ((i + 14) % 14 == 0) {
              // work.push("看图写画");
            }
            this.addWork(work, tt1);
          }
          work.push("英语作业");
          work.push(" ");
        } else {
          key = -i;
        }

        newWorkData[key] = work;
      }
      this.workData = newWorkData;
    },
    addWork(workList, work) {
      if (typeof work == "string") {
        workList.push(work);
      } else {
        const { name, start, step } = work;

        const page = step > 1 ? start + "-" + (start + step - 1) : start;
        workList.push(name + "第" + page + "页");
        work.start = start + step;
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.hw-container {
  margin: 24px;
  .content {
    padding-top: 10px;
    display: flex;
    flex-wrap: wrap;
    font-family: 微软雅黑;
    border-left: solid 1px #ccc;
    border-bottom: solid 1px #ccc;
    .day {
      border-right: solid 1px #ccc;
      width: 25%;
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
}
</style>
