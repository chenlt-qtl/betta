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

// 删除文件夹
export function delNoteInfo(id) {
  return request({
    url: '/note/noteInfo/' + id,
    method: 'delete'
  })
}
