import request from "@/utils/request";

// 查询视频信息列表
export function listVideo(query) {
  return request({
    url: "/video/list",
    method: "get",
    params: query,
  });
}

// 查询视频信息详细
export function getVideo(id) {
  return request({
    url: "/video/" + id,
    method: "get",
  });
}

// 查询子视频信息详细
export function getChildren(pid) {
  return request({
    url: "/video/" + pid + "/children",
    method: "get",
  });
}

// 查询全部视频信息简短内容
export function getBrife(isLeaf) {
  return request({
    url: "/video/list/brief" + (isLeaf ? "?isLeaf=" + isLeaf : ""),
    method: "get",
  });
}

// 新增视频信息
export function addVideo(data) {
  return request({
    url: "/video",
    method: "post",
    data: data,
  });
}

// 新增子视频信息
export function addChildren(data) {
  return request({
    url: "/video/children",
    method: "post",
    data: data,
  });
}

// 修改视频信息
export function updateVideo(data) {
  return request({
    url: "/video",
    method: "put",
    data: data,
  });
}

// 删除视频信息
export function delVideo(id) {
  return request({
    url: "/video/" + id,
    method: "delete",
  });
}
