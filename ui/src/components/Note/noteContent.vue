<template>
  <div class="note-content">
    <OpenedTab></OpenedTab>
    <div v-if="openedNote.id">
      <div class="toolbar">
        <input
          ref="title"
          maxLength="100"
          v-model="title"
          @input="changeTitle"
          @blur="updateTitle"
        />
        <i class="el-icon-time"></i>
        <i class="el-icon-download"></i>
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
          v-if="isSave"
          style="color: #78e08f"
          class="el-icon-circle-check icon"
        ></i>
        <i v-if="!isSave" class="el-icon-warning-outline orange icon"></i>
      </div>
      <MdEditor
        :value="content.text"
        :propClass="editorClass"
        @blur="updateText"
        @change="changeText"
      ></MdEditor>
    </div>
    <el-empty v-if="!openedNote.id" description=""></el-empty>
  </div>
</template>

<script>
import MdEditor from "@/components/MarkDownEditor";
import OpenedTab from "@/components/Note/noteOpenedTab";
import { updateName } from "@/api/note/noteInfo";
import { getContent, updateContent } from "@/api/note/content";
import {
  listFavorite,
  getFavorite,
  delFavorite,
  addFavorite,
  updateFavorite,
} from "@/api/note/favorite";

export default {
  name: "NoteList",
  props: ["note"],
  components: { MdEditor, OpenedTab },
  data() {
    return {
      title: "",
      editorClass: { height: "100vh" },
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      noteName: "",
      content: {},
      fav: {},
      isFav: false,
      isSave: true,
    };
  },
  created() {
    this.getContent();
    this.getFav();
  },
  watch: {
    openedNote() {
      if (this.openedNote.id) {
        this.isSave = true;
        this.getContent();
      }
    },
  },
  computed: {
    openedNote() {
      return this.$store.state.note.openedNote;
    },
  },
  methods: {
    /** 查询文件夹列表 */
    getContent() {
      const { name, contentId } = this.openedNote;
      this.title = name;
      if (contentId) {
        getContent(contentId).then((res) => {
          this.content = res.data;
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
          console.log('================this.isFav====================');
          console.log(this.isFav);
          console.log('====================================');
        }
      });
    },
    changeTitle() {
      this.isSave = false;
    },
    updateTitle() {
      if (!this.title) {
        this.$modal.msgError("标题不能为空");
        return;
      }
      const note = { ...this.openedNote, name: this.title };
      updateName(note).then(() => {
        this.$modal.msgSuccess("修改成功");
        this.isSave = true;

        //更新openedNotes
        const data = new Map(this.$store.state.note.openedNotes);
        data.set(String(this.openedNote.id), note);
        this.$store.dispatch("note/setOpenedNotes", data);
        this.$store.dispatch("note/getListData");
      });
    },
    changeText() {
      this.isSave = false;
    },
    updateText(text) {
      //如果有改动，才保存
      if (!this.isSave) {
        updateContent({ ...this.content, text }).then(() => {
          this.isSave = true;
          this.$modal.msgSuccess("修改成功");
        });
      }
    },
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
}
</style>