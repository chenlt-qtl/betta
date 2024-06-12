import request from '@/utils/request'

// 上传文件
export function uploadNoteImg(fileStr) {
  return request({
    url: '/common/uploadImg/note',
    method: 'post',
    data: {
      file: fileStr
    }
  })
}
