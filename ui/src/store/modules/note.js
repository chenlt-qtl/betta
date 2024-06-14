import {
  listNoteInfo,
  getNoteInfo,
  listLast,
  listNoteTree,
} from "@/api/note/noteInfo";
import {
  listFavorite
} from "@/api/note/favorite";

const state = {
  //树节点数据
  treeData: new Array(),
  //树选中的节点
  selectedTreeNote: {},
  //列表数据
  listNote: [],
  //打开的笔记
  openedNote: {},
  //打开的笔记TAB集合
  openedNotes: new Map(),
  //列表类型
  listType: ""
}
const mutations = {
  SET_TREE_DATA: (state, treeData) => {
    state.treeData = treeData
  },
  SET_SELECTED_TREE_NOTE: (state, note) => {
    if (state.selectedTreeNote.id != note.id) {
      state.selectedTreeNote = note
    }
  },
  SET_OPENED_NOTE: (state, note) => {
    state.openedNote = note
  },
  SET_OPENED_NOTES: (state, notes) => {
    state.openedNotes = notes
  },
  SET_LIST_NOTE: (state, notes) => {
    state.listNote = notes
  },
  SET_LIST_TYPE: (state, type) => {
    state.listType = type
  },
}

const actions = {
  // 获取note树
  getTreeData({
    commit
  }) {
    return new Promise((resolve, reject) => {
      listNoteTree({
        parentId: -1,
        isLeaf: 0
      }).then(res => {
        commit('SET_TREE_DATA', res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 获取note列表
  getListData({
    commit,
    state
  }) {
    const type = state.listType;
    let method;
    switch (type) {
      case "fav":
        method = listFavorite;
        break;
      case "last":
        method = listLast;
        break;
      default:
        method = listNoteInfo;
    }

    const queryParams = {
      pageNum: 1,
      pageSize: 1000,
      parentId: state.selectedTreeNote.id,
      isLeaf: true,
    };
    return new Promise((resolve, reject) => {
      method(queryParams).then(res => {
        commit('SET_LIST_NOTE', res.rows || res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 打开note树
  openNote({
    commit,
    state
  }, id) {
    const openedNotes = new Map(state.openedNotes)
    return new Promise((resolve, reject) => {
      if (id) {
        getNoteInfo(id).then(res => {
          commit('SET_OPENED_NOTE', res.data)
          openedNotes.set(id, res.data)
          commit('SET_OPENED_NOTES', openedNotes)
          resolve(res)
        }).catch(error => {
          commit('SET_OPENED_NOTE', {})
          reject(error)
        })
      } else {
        commit('SET_OPENED_NOTE', {})
        resolve()
      }
    })
  },
  setSelectedTreeNote({
    dispatch,
    commit
  }, data) {
    commit('SET_SELECTED_TREE_NOTE', data)
  },
  setOpenedNotes({
    commit
  }, data) {
    commit('SET_OPENED_NOTES', data)
  },
  setOpendNote({
    commit
  }, data) {
    commit("SET_OPENED_NOTE", data)
  },
  setListType({
    commit
  }, data) {
    commit("SET_LIST_TYPE", data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
