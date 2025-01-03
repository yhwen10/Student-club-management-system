import http from './http'; // 假设你已有 http 封装，基于 Axios

// 获取学生兴趣分析数据
export const getStudentInterest = (studentId) => {
  return http.get(`/api/student-interest/${studentId}`);
};

// 生成学生兴趣分析数据
export const generateStudentInterest = (studentId) => {
  return http.post(`/api/student-interest/generate/${studentId}`);
};
