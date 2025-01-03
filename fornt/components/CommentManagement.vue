<template>
  <div class="comment-management">
    <h2>评论管理</h2>
    
    <!-- 评论列表 -->
    <el-table :data="comments" style="width: 100%" border>
      <el-table-column prop="nickname" label="用户名" width="150" />
      <el-table-column prop="content" label="评论内容" />
      <el-table-column prop="emotion" label="情感分析" width="120" />
      <el-table-column prop="reply" label="回复内容" />
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="replyToComment(scope.row.id)">回复</el-button>
          <el-button type="danger" size="small" @click="deleteComment(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 优化建议 -->
    <div class="optimization-section">
      <el-button type="success" @click="generateSuggestion">生成优化建议</el-button>
      <div v-if="suggestion" class="suggestion-result">
        <strong>优化建议：</strong>{{ suggestion }}
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/utils/http"; // 引入封装的 http.js

export default {
  name: "CommentManagement",
  props: {
    refid: {
      type: Number,
      required: true, // 关联的社团ID
    },
  },
  data() {
    return {
      comments: [], // 评论列表
      suggestion: "", // 优化建议
    };
  },
  mounted() {
    this.fetchComments(); // 初始化加载评论
  },
  methods: {
    // 获取评论列表
    async fetchComments() {
      try {
        const response = await http.get(`/pinglunguanli/list/${this.refid}`);
        this.comments = response.data.data;
      } catch (error) {
        console.error("加载评论列表失败：", error);
        this.$message.error("加载评论列表失败，请稍后重试");
      }
    },
    // 删除评论
    async deleteComment(id) {
      try {
        await http.delete(`/pinglunguanli/delete/${id}`);
        this.$message.success("评论删除成功");
        this.fetchComments(); // 重新加载评论
      } catch (error) {
        console.error("删除评论失败：", error);
        this.$message.error("删除评论失败，请稍后重试");
      }
    },
    // 回复评论
    async replyToComment(id) {
      const replyContent = prompt("请输入回复内容：");
      if (replyContent) {
        try {
          await http.post(`/pinglunguanli/reply/${id}`, { reply: replyContent });
          this.$message.success("回复成功");
          this.fetchComments(); // 重新加载评论
        } catch (error) {
          console.error("回复评论失败：", error);
          this.$message.error("回复评论失败，请稍后重试");
        }
      }
    },
    // 生成优化建议
    async generateSuggestion() {
      try {
        const response = await http.get(`/pinglunguanli/suggestions/${this.refid}`);
        this.suggestion = response.data.suggestion;
      } catch (error) {
        console.error("生成优化建议失败：", error);
        this.$message.error("生成优化建议失败，请稍后重试");
      }
    },
  },
};
</script>

<style scoped>
.comment-management {
  padding: 20px;
}
.optimization-section {
  margin-top: 20px;
}
.suggestion-result {
  margin-top: 10px;
  font-size: 16px;
  color: #333;
}
</style>


