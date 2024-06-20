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
  //树选中的节点Id
  selectedNoteId: "",
  //树选中的节点Name
  selectedNoteName: "",
  //列表数据
  listNote: [],
  //打开的笔记
  openedNote: {},
  //打开的笔记TAB集合
  openedNotes: new Map(),
  //列表类型
  listType: "",
  //搜索内容
  search: "",
}
const mutations = {
  SET_TREE_DATA: (state, treeData) => {
    state.treeData = treeData
  },
  SET_SELECTED_NOTE_ID: (state, data) => {
    if (!state.selectedNoteId || state.selectedNoteId != data) {
      state.selectedNoteId = data
    }
  },
  SET_SELECTED_NOTE_NAME: (state, data) => {
    state.selectedNoteName = data
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
  SET_SEARCH: (state, search) => {
    state.search = search
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
        //没有父节点ID不发送请求
        if (!selectedNoteId && selectedNoteId != "0") {
          return;
        }
        if (searchStr) {
          queryParams = {
            pageNum: 1,
            pageSize: 1000,
            name: searchStr
          };
        } else {
          queryParams = {
            pageNum: 1,
            pageSize: 1000,
            parentId: selectedNoteId,
          };
        }

        method = listNoteInfo;
    }

    return new Promise((resolve, reject) => {
      method(queryParams).then(res => {
        commit('SET_LIST_NOTE', res.rows || res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 打开note
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
          commit('SET_SELECTED_NOTE_ID', res.data.parentId)
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
  setSelectedNoteId({
    commit
  }, data) {
    commit('SET_SELECTED_NOTE_ID', data)
  },
  setSelectedNoteName({
    commit
  }, data) {
    commit('SET_SELECTED_NOTE_NAME', data)
  },
  setOpenedNotes({
    commit
  }, data) {
    commit('SET_OPENED_NOTES', data)
  },
  setOpendNote({
    commit
  }, data4) {
    commit("SET_OPENED_NOTE", data4)
  },
  setListType({
    commit
  }, data) {
    commit("SET_LIST_TYPE", data)
  },
  setSearch({
    commit
  }, data) {
    commit("SET_SEARCH", data)
  },
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
