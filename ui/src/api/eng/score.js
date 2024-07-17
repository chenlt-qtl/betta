import request from '@/utils/request'

// 查询用户成绩列表
export function listScore(query) {
  return request({
    url: '/eng/score/list',
    method: 'get',
    params: query
  })
}

// 查询用户成绩详细
export function getScore(id) {
  return request({
    url: '/eng/score/' + id,
    method: 'get'
  })
}

// 新增用户成绩
export function addScore(data) {
  return request({
    url: '/eng/score',
    method: 'post',
    data: data
  })
}

// 修改用户成绩
export function updateScore(data) {
  return request({
    url: '/eng/score',
    method: 'put',
    data: data
  })
}

// 批量修改用户成绩
export function batchUpdate(listData) {
  return request({
    url: '/eng/score/batch',
    method: 'put',
    data: listData
  })
}

// 删除用户成绩
export function delScore(id) {
  return request({
    url: '/eng/score/' + id,
    method: 'delete'
  })
}
// 查询用户成绩列表
export function listByArticle(articleId, limit = 5) {
  return request({
    url: '/eng/score/list/article/' + articleId + "/" + limit,
    method: 'get'
  })
}
