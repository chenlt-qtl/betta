import request from '@/utils/request'

// 查询单词列表
export function listWord(query) {
  return request({
    url: '/eng/word/list',
    method: 'get',
    params: query
  })
}

// 查询单词列表
export function listWordByArticle(query) {
  return request({
    url: '/eng/word/list/' + query.articleId,
    method: 'get',
    params: query
  })
}

// 查询单词详细
export function getWord(id) {
  return request({
    url: '/eng/word/' + id,
    method: 'get'
  })
}

// 新增单词
export function addWord(data) {
  return request({
    url: '/eng/word',
    method: 'post',
    data: data
  })
}
// 新增文章对应的单词
export function addWordByArticle(articleId, wordList) {
  return request({
    url: '/eng/word/' + articleId,
    method: 'post',
    contentType:"application/x-www-form-urlencoded",
    data: {
      words: wordList
    }
  })
}


// 修改单词
export function updateWord(data) {
  return request({
    url: '/eng/word',
    method: 'put',
    data: data
  })
}

// 删除单词
export function delWord(id) {
  return request({
    url: '/eng/word/' + id,
    method: 'delete'
  })
}
