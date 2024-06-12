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
  //列表数据
  listNote: [],
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
  SET_LIST_NOTE: (state, notes) => {
    state.listNote = notes
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
    const parentId = state.selectedTreeNote.id;
    const queryParams = {
      pageNum: 1,
      pageSize: 1000,
      parentId,
      isLeaf: true,
    };
    return new Promise((resolve, reject) => {
      listNoteInfo(queryParams).then(res => {
        commit('SET_LIST_NOTE', res.rows)
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
      getNoteInfo(id).then(res => {
        commit('SET_OPENED_NOTE', res.data)
        openedNotes.set(id, res.data)
        commit('SET_OPENED_NOTES', openedNotes)
        resolve(res)
      }).catch(error => {
        commit('SET_OPENED_NOTE', {})
        reject(error)
      })
    })
  },
  setSelectedTreeNote({
    dispatch,
    commit
  }, data) {
    commit('SET_SELECTED_TREE_NOTE', data)
    dispatch('getListData')
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
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
