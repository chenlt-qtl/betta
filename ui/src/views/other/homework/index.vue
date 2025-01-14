<template>
  <div class="hw-container">
    <ol>
      <!-- <li>当天完成作业+10</li>
      <li>书写分一个字+1</li>
      <li>书写差一个字-1</li>
      <li>作业全对+5</li> -->
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
      person: "tt",
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
      let start = 205,
        end = 215;
      let total = Math.ceil((end - start + 1) / 4) * 4;
      const newWorkData = {};

      const other = []; //this.person == "dy" ? ["专项练习——周长与面积(第二面)"] : [];
      //豆芽
      const everyday =
        this.person == "dy"
          ? [
              { name: "背诵", start: 7, step: 1 },
              { name: "语文字帖", start: 11, step: 2 },
              "阅读飞向太空和雨来",
              "阅读笨狼的故事",
              "阅读数学花园漫游记",
              "运动",
              "做家务",
              { name: "一本", start: 31, step: 4, max: 68 },
              { name: "举一反三", start: 10, step: 2 },
              // { name: "舒尔特方格(中级)", start: 20, step: 1 },
            ]
          : [
              "读二下课文5遍",
              { name: "写字课堂", start: 16, step: 4, max: 74 },
              "阅读+读书笔记",
              { name: "一本计算", start: 11, step: 3 },
              { name: "举一反三", start: 17, step: 2 },
              "运动",
            ];
      const dy1 = { name: "英语字帖", start: 4, step: 1 };
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
            if (i % 2 == 0) {
              this.addWork(work, dy1);
            }
            if ((i + 5) % 10 == 0) {
              work.push("书法展示");
            }
            if ((i + 12) % 10 == 0) {
              work.push("阅读记录卡");
            }
            if ((i + 13) % 10 == 0) {
              work.push("读书小报");
            }

            if ((i + 1) % 10 == 0) {
              work.push("春节作文");
            }

            if ((i + 9) % 10 == 0) {
              work.push("我做的年夜饭");
            }

            if ((i + 5) % 10 == 0) {
              work.push("红色基地打卡");
            }

            if ((i + 7) % 10 == 0) {
              work.push("红色观影");
            }

            if ((i + 3) % 5 == 0) {
              work.push("手工");
            }

            if ((i + 6) % 10 == 0) {
              work.push("英语祝福短信");
            }
          } else {
            if ((i + 3) % 5 == 0) {
              work.push("古诗作品");
            }
            if ((i + 4) % 10 == 0) {
              work.push("最开心的照片");
            }
            if ((i + 1) % 10 == 0) {
              work.push("剪窗花");
            }

            if ((i + 7) % 10 == 0) {
              work.push("英语海报");
            }

            if ((i + 2) % 10 == 0) {
              work.push("蛇年福字");
            }

            if ((i + 5) % 10 == 0) {
              work.push("无音高小乐器");
            }

            if ((i + 8) % 10 == 0) {
              work.push("乐器解说");
            }

            if ((i + 9) % 10 == 0) {
              work.push("吸管排萧");
            }

            if ((i + 6) % 10 == 0) {
              work.push("数学表格");
            }
          }
          work.push("英语打卡");
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
        const { name, start, step, max } = work;
        if (start > max) {
          return;
        }
        let page;
        if (step > 1) {
          const end = start + step - 1;
          page = start + "-" + (max ? (end > max ? max : end) : end);
        } else {
          page = start;
        }
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
