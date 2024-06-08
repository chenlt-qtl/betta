import {
  listNoteInfo,
  getNoteInfo,
  delNoteInfo,
  addNoteInfo,
  updateNoteInfo,
  listNoteTree,
} from "@/api/note/noteInfo";

const state = {
  //树节点数据
  treeData: new Array(),
  //树选中的节点
  selectedTreeNote: {},
  //打开的笔记
  openedNote: {},
  //打开的笔记TAB集合
  openedNotes: new Map(),
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
}

const actions = {
  // 获取note树
  getTreeData({
    commit
  }) {
    return new Promise((resolve, reject) => {
      listNoteTree({
        parentId: 0,
        isLeaf: 0
      }).then(res => {
        commit('SET_TREE_DATA', res.data)
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
    const openedNotes = state.openedNotes
    return new Promise((resolve, reject) => {
      getNoteInfo(id).then(res => {
        commit('SET_OPENED_NOTE', res.data)
        openedNotes.set(id, res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  setSelectedTreeNote({
    commit
  }, data) {
    commit('SET_SELECTED_TREE_NOTE', data)
  },
  setOpendNote({
    commit
  }, data) {
    commit("SET_OPENED_NOTE", data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
