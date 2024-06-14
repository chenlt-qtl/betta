import request from '@/utils/request'

// 查询历史列表
export function listHistory(query, type) {
  return request({
    url: '/note/history/list/' + type,
    method: 'get',
    params: query
  })
}

// 查询历史详细
export function getHistory(id) {
  return request({
    url: '/note/history/' + id,
    method: 'get'
  })
}

// 新增历史
export function addHistory(data) {
  return request({
    url: '/note/history',
    method: 'post',
    data: data
  })
}

// 修改历史
export function updateHistory(data) {
  return request({
    url: '/note/history',
    method: 'put',
    data: data
  })
}

// 删除历史
export function delHistory(id) {
  return request({
    url: '/note/history/' + id,
    method: 'delete'
  })
}
