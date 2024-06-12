import request from '@/utils/request'

// 查询收藏列表
export function listFavorite(query) {
  return request({
    url: '/note/favorite/list',
    method: 'get',
    params: query
  })
}

// 查询收藏详细
export function getFavorite() {
  return request({
    url: '/note/favorite/cur',
    method: 'get'
  })
}

// 新增收藏
export function addFavorite(data) {
  return request({
    url: '/note/favorite',
    method: 'post',
    data: data
  })
}

// 修改收藏
export function updateFavorite(data) {
  return request({
    url: '/note/favorite',
    method: 'put',
    data: data
  })
}

// 删除收藏
export function delFavorite(id) {
  return request({
    url: '/note/favorite/' + id,
    method: 'delete'
  })
}
