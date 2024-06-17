import request from '@/utils/request'

// 查询笔记内容
export function search(searchStr) {
  return request({
    url: '/es/note',
    method: 'get',
    params: {key:searchStr}
  })
}
