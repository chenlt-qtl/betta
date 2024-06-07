import request from '@/utils/request'

// 查询笔记内容列表
export function listContent(query) {
  return request({
    url: '/note/content/list',
    method: 'get',
    params: query
  })
}

// 查询笔记内容详细
export function getContent(id) {
  return request({
    url: '/note/content/' + id,
    method: 'get'
  })
}

// 新增笔记内容
export function addContent(data) {
  return request({
    url: '/note/content',
    method: 'post',
    data: data
  })
}

// 修改笔记内容
export function updateContent(data) {
  return request({
    url: '/note/content',
    method: 'put',
    data: data
  })
}

// 删除笔记内容
export function delContent(id) {
  return request({
    url: '/note/content/' + id,
    method: 'delete'
  })
}
