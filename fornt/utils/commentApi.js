import http from "@/utils/http";

// 添加评论
export const addComment = (data) => {
  return http.post("/api/pinglunguanli", data);
};

// 获取评论列表
export const getComments = (refid) => {
  return http.get(`/api/pinglunguanli/${refid}`);
};

// 回复评论
export const replyComment = (commentId, replyContent) => {
  return http.post(`/api/pinglunguanli/${commentId}/reply`, { replyContent });
};

// 删除评论
export const deleteComment = (commentId) => {
  return http.delete(`/api/pinglunguanli/${commentId}`);
};

