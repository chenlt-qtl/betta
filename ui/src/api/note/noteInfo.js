import request from '@/utils/request'

// 查询文件夹列表
export function listNoteInfo(query) {
  return request({
    url: '/note/noteInfo/list',
    method: 'get',
    params: query
  })
}

// 查询文件夹列表
export function listNoteTree(query) {
  return request({
    url: '/note/noteInfo/tree',
    method: 'get',
    params: query
  })
}

// 查询文件夹详细
export function getNoteInfo(id) {
  return request({
    url: '/note/noteInfo/' + id,
    method: 'get'
  })
}

// 新增文件夹
export function addNoteInfo(data) {
  return request({
    url: '/note/noteInfo',
    method: 'post',
    data: data
  })
}

// 修改文件夹
export function updateNoteInfo(data) {
  return request({
    url: '/note/noteInfo',
    method: 'put',
    data: data
  })
}

// 修改名称
export function updateName(data) {
  return request({
    url: '/note/noteInfo/' + data.id,
    method: 'put',
    data: data
  })
}

// 移动
export function updateParent(ids,parentId) {
  return request({
    url: '/note/noteInfo/parent?ids=' + ids + "&parentId=" + parentId,
    method: 'put'
  })
}

// 删除文件夹
export function delNoteInfo(id) {
  return request({
    url: '/note/noteInfo/' + id,
    method: 'delete'
  })
}
