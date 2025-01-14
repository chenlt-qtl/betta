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
        <el-radio-group v-model="symbol">
          <el-radio :label="1">+</el-radio>
          <el-radio :label="-1">-</el-radio>
        </el-radio-group>
        <!-- <el-radio-group v-model="symbol">
          <el-radio-button label="1">+</el-radio-button>
          <el-radio-button label="-1">-</el-radio-button>
        </el-radio-group> -->
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

      <el-button slot="reference" @click="showConfirm" round>提交</el-button>
      <el-button type="text" @click="showHistory"
        ><i class="el-icon-time"></i
      ></el-button>
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
        >
          <div class="quick">
            <span
              :class="item.value > 0 ? 'green' : 'red'"
              v-for="item in listData[dict.value]"
              :key="item.id"
              @click="() => addScore(item.value, item.name)"
            >
              {{ item.name }} {{ item.value > 0 ? "+" : "" }}{{ item.value }}
            </span>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- 加卡询问窗口-->
    <el-dialog
      :title="
        accountData[accountId].name +
        (symbol == 1 ? ' + ' : ' - ') +
        score +
        ' ？'
      "
      :visible.sync="openConfirm"
      width="300px"
      append-to-body
    >
      <el-input v-model="content" placeholder="请输入内容"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="() => (this.openConfirm = false)">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 查看历史 -->
    <el-dialog
      title="历史记录"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-table v-loading="loading" :data="historyList">
        <el-table-column label="内容" prop="value" />
        <el-table-column label="时间" prop="createTime" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="() => (open = false)">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { listItem, updateItem } from "@/api/card/item";
import { listHistory } from "@/api/system/history";

export default {
  dicts: ["card_type"],
  created() {
    this.getList();
  },
  data() {
    return {
      data: [50, 20, 10, 5, 3, 2, 1],
      //符号
      symbol: 1,
      accountId: 0,
      accountData: [{}, {}],
      score: 0,
      activeName: "1",
      listData: {},
      open: false,
      openConfirm: false,
      content: "",
      historyList: [],
      loading: false,
    };
  },
  computed: {
    value() {
      const score = this.score || 0;
      return this.symbol * score;
    },
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
      this.symbol = 1;
      this.content = "";
    },
    addScore(value, name) {
      const score = value + this.value;
      if (score < 0) {
        this.symbol = -1;
      } else {
        this.symbol = 1;
      }
      this.score = Math.abs(score);
      if (name) {
        if (this.content) {
          this.content += ",";
        }
        this.content += name;
      }
    },
    showConfirm() {
      this.openConfirm = true;
    },
    onSubmit() {
      const newData = this.accountData[this.accountId];
      newData.value = newData.value * 1 + this.value;
      updateItem({ ...newData, content: this.content }).then(() => {
        this.$modal.msgSuccess("操作成功");
        this.openConfirm = false;
        this.onReset();
      });
    },
    showHistory() {
      this.loading = true;
      listHistory({
        pageNum: 1,
        pageSize: 20,
        type: 1,
      }).then((response) => {
        this.loading = false;
        this.historyList = response.rows;
      });
      this.open = true;
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
