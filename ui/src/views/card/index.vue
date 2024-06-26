<template>
  <div class="card-plus">
    <div class="head">
      <div
        @click="() => setAccount(0)"
        :class="(accountId == 0 ? 'active' : 'noActive') + ' account'"
      >
        <h5>{{ accountData[0].name }}</h5>
        <h2>{{ accountData[0].value }}张</h2>
        <img src="../../assets/images/girl-5.svg" />
      </div>
      <div
        @click="() => setAccount(1)"
        :class="(accountId == 1 ? 'active' : 'noActive') + ' account'"
      >
        <h5>{{ accountData[1].name }}</h5>
        <h2>{{ accountData[1].value }}张</h2>
        <img src="../../assets/images/boy-2.svg" />
      </div>
    </div>
    <div class="input-bar">
      <div class="symbol">
        <el-radio v-model="symbol" label="1">+</el-radio>
        <el-radio v-model="symbol" label="-1">-</el-radio>
      </div>
      <el-input-number
        placeholder="请输入内容"
        v-model="score"
        class="input-with-select"
        clearable
        controls-position="right"
        :min="0"
      >
        <el-button slot="append" icon="el-icon-check"></el-button>
      </el-input-number>
      <el-button round @click="onReset" type="text">清空</el-button>
      <el-button round @click="onSubmit">提交</el-button>
    </div>
    <div class="quick">
      <span
        class="red"
        v-for="item of data"
        :key="'-' + item"
        @click="() => addScore(-item)"
        >-{{ item }}</span
      >
    </div>
    <div class="quick">
      <span
        class="green"
        v-for="item of data"
        :key="item"
        @click="() => addScore(item)"
        >{{ item }}</span
      >
    </div>
    <div class="tab">
      <el-tabs v-model="activeName">
        <el-tab-pane
          v-for="dict in dict.type.card_type"
          :key="dict.value"
          :label="dict.label"
          :name="dict.value"
          ><el-tag
            class="item"
            v-for="item in listData[dict.value]"
            :key="item.id"
            @click="() => addScore(item.value)"
            :type="item.value > 0 ? 'success' : 'danger'"
            >{{ item.name }} {{ item.value > 0 ? "+" : ""
            }}{{ item.value }}</el-tag
          ></el-tab-pane
        >
      </el-tabs>
    </div>
  </div>
</template>
<script>
import { listItem, updateItem } from "@/api/card/item";

export default {
  dicts: ["card_type"],
  created() {
    this.getList();
  },
  data() {
    return {
      data: [100, 50, 30, 20, 10, 5, 3, 1],
      //符号
      symbol: "1",
      accountId: 0,
      accountData: [{}, {}],
      score: 0,
      activeName: "1",
      listData: {},
    };
  },
  computed:{
    value(){
      const score = this.score||0;
      return (this.symbol == "1" ? score : -score)*1;
    }
  },
  methods: {
    /** 查询卡片项明细列表 */
    getList() {
      this.loading = true;
      listItem({
        pageNum: 1,
        pageSize: 1000,
      }).then((response) => {
        const data = response.rows;
        const result = {};
        data.forEach((value) => {
          if (!result[value.type]) {
            result[value.type] = [];
          }
          result[value.type].push(value);
        });
        this.accountData = result["0"];
        this.listData = result;
        this.loading = false;
      });
    },
    setAccount(id) {
      this.accountId = id;
    },
    onReset() {
      this.score = 0;
      this.symbol = "1";
    },
    addScore(value) {
      const score = value + this.value;
      if (score < 0) {
        this.symbol = "-1";
      } else {
        this.symbol = "1";
      }
      this.score = Math.abs(score);
    },
    onSubmit() {
      this.$modal
        .confirm(
          this.accountData[this.accountId].name +
            (this.symbol == "1" ? " + " : " - ") +
            this.score +
            " ？"
        )
        .then(() => {
          const oldNumber = this.accountData[this.accountId].value;
          const newNumber = oldNumber * 1 + this.value;
          this.accountData[this.accountId].value = newNumber;
          updateItem(this.accountData[this.accountId]);
        })
        .then(() => {
          this.$modal.msgSuccess("操作成功");
          this.onReset();
        })
        .catch(() => {});
    },
  },
};
</script>

<style lang="scss">
.card-plus {
  padding: 55px 20px 20px 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  .head {
    max-width: 600px;
    width: 100%;
    padding: 20px;
    display: flex;
    gap: 10px;

    .account {
      position: relative;
      flex: 1;
      cursor: pointer;

      h2 {
        font-size: 20px;
        font-weight: bold;
        margin-top: 16px;
        margin-bottom: 0;
      }

      img {
        position: absolute;
        right: 10px;
        bottom: 40px;
        transition: 0.3s;
      }
    }

    .active {
      background-color: #f28086;
      color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 8px #f0f1f2;
      padding: 20px;
      transform: scale(1.1);
      transition: 0.3s;

      h5,
      h2 {
        color: #fff;
      }

      img {
        bottom: 60px;
        right: 5px;
        width: 80px;
      }
    }

    .noActive {
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 8px #f0f1f2;
      padding: 20px;
      transform: scale(0.9);

      h5 {
        color: #888;
      }

      img {
        width: 50px;
      }
    }
  }
  .input-bar {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: center;
    gap: 20px;
    .symbol {
      display: flex;
      flex-wrap: nowrap;
    }
  }

  .quick {
    padding-top: 10px;
    span {
      cursor: pointer;
      display: inline-block;
      border-radius: 5px;
      padding: 5px 15px;
      text-align: center;
      margin: 5px;
    }
    .red {
      border: #f28086 1px solid;
      color: #f28086;
    }
    .green {
      border: #53ac68 1px solid;
      color: #53ac68;
    }
  }
  .tab {
    max-width: 600px;
    width: 100%;
    .item {
      cursor: pointer;
      margin: 5px;
    }
  }
}
</style>