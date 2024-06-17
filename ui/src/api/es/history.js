import request from '@/utils/request'

// 查询ES历史列表
export function listHistory(query) {
  return request({
    url: '/es/history/list',
    method: 'get',
    params: query
  })
}

// 查询ES历史详细
export function getHistory(id) {
  return request({
    url: '/es/history/' + id,
    method: 'get'
  })
}

// 新增ES历史
export function addHistory(userName) {
  return request({
    url: '/es/note',
    method: 'post',
    params: {
      userName
    }
  })
}

// 修改ES历史
export function updateHistory(data) {
  return request({
    url: '/es/history',
    method: 'put',
    data: data
  })
}

// 删除ES历史
export function delHistory(id) {
  return request({
    url: '/es/history/' + id,
    method: 'delete'
  })
}

// 删除索引
export function delIndex(userName) {
  return request({
    url: '/es/note/index/' + userName,
    method: 'delete'
  })
}

// 创建索引
export function addIndex(userName) {
  return request({
    url: '/es/note/index',
    method: 'post',
    params: {
      userName
    }
  })
}
