import {
  listNoteInfo,
  getNoteInfo,
  listLast,
  listNoteTree,
  delNoteInfo,
} from "@/api/note/noteInfo";
import { listFavorite } from "@/api/note/favorite";

const MAX_OPENED_NUM = 10; //最多能打开多少TAB

const state = {
  //树节点数据
  treeData: new Array(),
  //树选中的节点Id
  selectedNoteId: "",
  //树选中的节点Name
  selectedNoteName: "",
  //列表数据
  listNote: [],
  //打开的笔记
  openedNote: {},
  //打开的笔记TAB集合
  openedNotes: JSON.parse(localStorage.getItem("opened_notes_b")) || [],
  //列表类型
  listType: "",
  //搜索内容
  search: "",
};
const mutations = {
  SET_TREE_DATA: (state, treeData) => {
    state.treeData = treeData;
  },
  SET_SELECTED_NOTE_ID: (state, data) => {
    if (!state.selectedNoteId || state.selectedNoteId != data) {
      state.selectedNoteId = data;
    }
  },
  SET_SELECTED_NOTE_NAME: (state, data) => {
    state.selectedNoteName = data;
  },
  SET_OPENED_NOTE: (state, note) => {
    state.openedNote = note;
  },
  SET_OPENED_NOTES: (state, notes) => {
    if (notes.length > MAX_OPENED_NUM) {
      notes.splice(0, notes.length - MAX_OPENED_NUM - 1);
    }
    state.openedNotes = [...notes];
    localStorage.setItem("opened_notes_b", JSON.stringify(notes));
  },
  SET_LIST_NOTE: (state, notes) => {
    state.listNote = notes;
  },
  SET_LIST_TYPE: (state, type) => {
    state.listType = type;
  },
  SET_SEARCH: (state, search) => {
    state.search = search;
  },
};

const actions = {
  // 获取note树
  getTreeData({ commit }) {
    return new Promise((resolve, reject) => {
      listNoteTree({
        parentId: -1,
        isLeaf: 0,
      })
        .then((res) => {
          commit("SET_TREE_DATA", res.data);
          resolve(res);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  // 获取note列表
  getListData({ commit, state }) {
    const type = state.listType;
    const searchStr = state.search;
    const selectedNoteId = state.selectedNoteId;
    let method, queryParams;
    switch (type) {
      case "fav":
        method = listFavorite;
        break;
      case "last":
        method = listLast;
        break;
      default:
        if (searchStr) {
          queryParams = {
            pageNum: 1,
            pageSize: 1000,
            name: searchStr,
          };
        } else {
          //没有父节点ID不发送请求
          if (!selectedNoteId && selectedNoteId != "0") {
            return;
          }
          queryParams = {
            pageNum: 1,
            pageSize: 1000,
            parentId: selectedNoteId,
          };
        }

        method = listNoteInfo;
    }

    return new Promise((resolve, reject) => {
      method(queryParams)
        .then((res) => {
          commit("SET_LIST_NOTE", res.rows || res.data);
          resolve(res);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  // 打开note
  openNote({ commit, state, dispatch }, id) {
    const openedNotes = [...state.openedNotes];
    const search = state.search;
    return new Promise((resolve, reject) => {
      if (id) {
        getNoteInfo(id)
          .then((res) => {
            const openedNote = res.data;
            commit("SET_OPENED_NOTE", openedNote);

            //加到openedNotes中
            let exists = false;
            openedNotes.forEach((data) => {
              if (data.id == id) {
                data.name = openedNote.name;
                exists = true;
                return;
              }
            });
            if (!exists) {
              openedNotes.push({ id, name: openedNote.name });
            }
            commit("SET_OPENED_NOTES", openedNotes);
            if (!search) {
              if (openedNote.isLeaf) {
                dispatch("setSelectedNoteId", openedNote.parentId);
              } else {
                dispatch("setSelectedNoteId", openedNote.id);
              }
            }
            resolve(res);
          })
          .catch((error) => {
            commit("SET_OPENED_NOTE", {});
            reject(error);
          });
      } else {
        commit("SET_OPENED_NOTE", {});
        resolve();
      }
    });
  },
  delNotes({ commit, state, dispatch }, ids) {
    return new Promise((resolve, reject) => {
      if (ids) {
        delNoteInfo(ids)
          .then((res) => {
            const openedNotes = state.openedNotes.filter(
              (i) => !ids.includes(i)
            );
            commit("SET_OPENED_NOTES", openedNotes);
            dispatch("getTreeData");
            dispatch("getListData");
            resolve(res);
          })
          .catch((error) => {
            reject(error);
          });
      } else {
        resolve();
      }
    });
  },
  setSelectedNoteId({ commit, dispatch }, data) {
    commit("SET_SELECTED_NOTE_ID", data);
    dispatch("getListData");
  },
  setSelectedNoteName({ commit }, data) {
    commit("SET_SELECTED_NOTE_NAME", data);
  },
  setOpenedNotes({ commit }, data) {
    commit("SET_OPENED_NOTES", data);
  },
  setOpendNote({ commit }, data4) {
    commit("SET_OPENED_NOTE", data4);
  },
  setListType({ commit, dispatch }, data) {
    commit("SET_LIST_TYPE", data);
    dispatch("getListData");
  },
  setSearch({ commit, dispatch }, data) {
    commit("SET_SEARCH", data);
    dispatch("getListData");
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
