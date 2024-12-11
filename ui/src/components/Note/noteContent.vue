<template>
  <div class="note-content">
    <OpenedTab></OpenedTab>
    <div v-if="openedNote.id">
      <div class="toolbar">
        <input
          ref="title"
          maxLength="100"
          v-model="title"
          @input="onChange"
          @blur="updateTitle"
        />
        <NoteHistoryListBtn></NoteHistoryListBtn>
        <NoteHistorySaveBtn :text="content.text"></NoteHistorySaveBtn>
        <i
          v-if="!isFav"
          @click="() => updateFav(true)"
          class="el-icon-star-off orange"
        ></i>
        <i
          v-if="isFav"
          @click="() => updateFav(false)"
          class="el-icon-star-on orange"
        ></i>
        <i
          v-if="isSaved"
          style="color: #78e08f"
          class="el-icon-circle-check icon"
        ></i>
        <i v-if="!isSaved" class="el-icon-warning-outline orange icon"></i>
      </div>

      <div v-if="content.id == null" class="blank">
        <i style="color: gray; font-size: 80px" class="el-icon-monitor"></i>
      </div>
      <div :style="{ display: content.id == null ? 'none' : 'block' }">
        <MdEditor
          :value="content.text"
          @blur="updateText"
          @change="onChange"
        ></MdEditor>
      </div>
    </div>
    <el-empty v-if="!openedNote.id" description=""></el-empty>
  </div>
</template>

<script>
import MdEditor from "@/components/MarkDownEditor";
import OpenedTab from "@/components/Note/noteOpenedTab";
import { updateName } from "@/api/note/noteInfo";
import { getContent, updateContent } from "@/api/note/content";
import { getFavorite, addFavorite, updateFavorite } from "@/api/note/favorite";
import NoteHistorySaveBtn from "./noteHistorySaveBtn.vue";
import NoteHistoryListBtn from "./noteHistoryListBtn.vue";

export default {
  components: {
    MdEditor,
    OpenedTab,
    NoteHistorySaveBtn,
    NoteHistoryListBtn,
  },
  data() {
    return {
      title: "",
      // 遮罩层
      loading: true,
      content: {},
      fav: {},
      //是否已收藏
      isFav: false,
      //是否已经保存到数据库
      isSaved: true,
    };
  },
  created() {
    this.getContent();
    this.getFav();
  },
  computed: {
    openedNote() {
      return this.$store.state.note.openedNote;
    },
  },
  watch: {
    openedNote() {
      if (this.openedNote.id) {
        this.isSaved = true;
        this.getContent();
      }
    },
  },
  methods: {
    /** 查询笔记内容 */
    getContent() {
      const { name, contentId } = this.openedNote;
      this.title = name;
      if (contentId) {
        getContent(contentId).then((res) => {
          this.content = res.data;
          if (this.content && this.content.text) {
            const reg = new RegExp("(?<=\\]\\()/profile(?=\\/note)", "g");
            this.content.text = this.content.text.replaceAll(
              reg,
              process.env.VUE_APP_BASE_API + "/profile"
            );
          }
        });
      } else {
        this.content = {};
      }
    },
    getFav() {
      getFavorite().then(({ data }) => {
        if (data) {
          this.fav = data;
          this.fav.favNoteIds = (data.noteIds || "").split(",");
          this.isFav = this.fav.favNoteIds.includes(String(this.openedNote.id));
        }
      });
    },
    //当有修改时触发
    onChange() {
      this.isSaved = false;
    },
    updateTitle() {
      if (!this.title) {
        this.$modal.msgError("标题不能为空");
        return;
      }
      const note = { ...this.openedNote, name: this.title };
      updateName(note).then(() => {
        this.$modal.msgSuccess("修改成功");
        this.isSaved = true;

        //更新openedNotes
        const data = new Map(this.$store.state.note.openedNotes);
        data.set(String(this.openedNote.id), note);
        this.$store.dispatch("note/setOpenedNotes", data);
        //更新列表
        this.$store.dispatch("note/getListData");
      });
    },
    updateText(text) {
      //如果有改动，才保存
      if (!this.isSaved) {
        process.env.VUE_APP_BASE_API;
        let newText = "";
        if (text) {
          newText = text.replaceAll(
            new RegExp(
              "(?<=\\]\\()" +
                process.env.VUE_APP_BASE_API +
                "/profile(?=\\/note)",
              "g"
            ),
            "/profile"
          );
        }

        this.content = { ...this.content, text: newText };
        updateContent(this.content).then(() => {
          this.isSaved = true;
          this.$modal.msgSuccess("修改成功");
        });
      }
    },
    //isAdd true 添加到收藏夹
    updateFav(isAdd) {
      let noteIds = [];
      const { favNoteIds, id } = this.fav;
      if (isAdd) {
        noteIds = [...favNoteIds, this.openedNote.id];
      } else {
        noteIds = favNoteIds.filter((id) => id != this.openedNote.id);
      }
      const data = { id, noteIds: noteIds.join(",") };
      if (id) {
        updateFavorite(data).then(() => {
          this.getFav();
        });
      } else {
        addFavorite(data).then(() => {
          this.getFav();
        });
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.note-content {
  .toolbar {
    margin-bottom: 15px;
    display: flex;
    gap: 10px;
    align-items: center;
    i {
      cursor: pointer;
    }

    .orange {
      color: #e6a23c;
    }

    .icon {
      cursor: default;
    }
    input {
      flex: 1;
      border: none;
      font-size: 14px;
      font-weight: bold;
      background-color: transparent;
      outline: none;

      &:focus {
        box-shadow: none;
      }
    }
  }
  .blank {
    height: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-top: solid 2px #dfe4ed;
  }
}
</style>
