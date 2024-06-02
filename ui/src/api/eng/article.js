import request from '@/utils/request'

// 查询英语文章列表
export function listArticle(query) {
  return request({
    url: '/eng/article/list',
    method: 'get',
    params: query
  })
}

// 查询英语文章详细
export function getArticle(id) {
  return request({
    url: '/eng/article/' + id,
    method: 'get'
  })
}

// 新增英语文章
export function addArticle(data) {
  return request({
    url: '/eng/article',
    method: 'post',
    data: data
  })
}

// 修改英语文章
export function updateArticle(data) {
  return request({
    url: '/eng/article',
    method: 'put',
    data: data
  })
}

// 删除英语文章
export function delArticle(id) {
  return request({
    url: '/eng/article/' + id,
    method: 'delete'
  })
}
