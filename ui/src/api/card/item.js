import request from '@/utils/request'

// 查询卡片项明细列表
export function listItem(query) {
  return request({
    url: '/card/item/list',
    method: 'get',
    params: query
  })
}

// 查询卡片项明细详细
export function getItem(id) {
  return request({
    url: '/card/item/' + id,
    method: 'get'
  })
}

// 新增卡片项明细
export function addItem(data) {
  return request({
    url: '/card/item',
    method: 'post',
    data: data
  })
}

// 修改卡片项明细
export function updateItem(data) {
  return request({
    url: '/card/item',
    method: 'put',
    data: data
  })
}

// 删除卡片项明细
export function delItem(id) {
  return request({
    url: '/card/item/' + id,
    method: 'delete'
  })
}
