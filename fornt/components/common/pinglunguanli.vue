<template>
  <div class="pinglunguanli">
    <h2>评论管理</h2>
    <el-table :data="comments" style="width: 100%" border>
      <el-table-column prop="nickname" label="用户名" width="150" />
      <el-table-column prop="content" label="评论内容" />
      <el-table-column prop="emotion" label="情感分析" width="120" />
      <el-table-column prop="reply" label="管理员回复" />

      <!-- 管理员操作 -->
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="showReplyDialog(scope.row)"
          >
            回复
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteComment(scope.row.id)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 回复弹窗 -->
    <el-dialog
      title="回复评论"
      :visible.sync="replyDialogVisible"
      width="30%"
      @close="clearReplyContent"
    >
      <el-input
        v-model="replyContent"
        type="textarea"
        rows="5"
        placeholder="请输入回复内容"
      />
      <span slot="footer" class="dialog-footer">
        <el-button @click="replyDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="replyToComment">提交回复</el-button>
      </span>
    </el-dialog>

    <div v-if="loading" class="loading-container">
      <el-loading-spinner />
      正在加载评论数据，请稍候...
    </div>
  </div>
</template>

<script>
import { getComments, addComment, replyComment, deleteComment } from "@/utils/commentApi";

export default {
  name: "Pinglunguanli",
  data() {
    return {
      comments: [], // 评论数据
      replyDialogVisible: false, // 回复弹窗显示状态
      replyContent: "", // 回复内容
      currentCommentId: null, // 当前回复的评论ID
      loading: true, // 数据加载状态
    };
  },
  mounted() {
    this.fetchComments(); // 初始化加载评论数据
  },
  methods: {
    // 获取评论列表
    async fetchComments() {
      try {
        const response = await getComments(1); // 假设 refid 为 1
        if (response.data) {
          this.comments = response.data;
        } else {
          throw new Error("数据为空");
        }
      } catch (error) {
        console.error("获取评论数据失败：", error);
        this.$message.error("加载评论数据失败，请稍后重试");
      } finally {
        this.loading = false;
      }
    },
    // 显示回复弹窗
    showReplyDialog(comment) {
      this.replyDialogVisible = true;
      this.currentCommentId = comment.id;
      this.replyContent = comment.reply || "";
    },
    // 清空回复内容
    clearReplyContent() {
      this.replyContent = "";
      this.currentCommentId = null;
    },
    // 提交回复
    async replyToComment() {
      if (!this.replyContent) {
        this.$message.error("回复内容不能为空");
        return;
      }
      try {
        await replyComment(this.currentCommentId, this.replyContent);
        this.$message.success("回复成功");
        this.fetchComments(); // 刷新评论列表
        this.replyDialogVisible = false;
      } catch (error) {
        console.error("回复评论失败：", error);
        this.$message.error("回复失败，请稍后重试");
      }
    },
    // 删除评论
    async deleteComment(commentId) {
      try {
        await deleteComment(commentId);
        this.$message.success("评论删除成功");
        this.fetchComments(); // 刷新评论列表
      } catch (error) {
        console.error("删除评论失败：", error);
        this.$message.error("删除失败，请稍后重试");
      }
    },
  },
};
</script>

<style scoped>
.pinglunguanli {
  padding: 10px;
}

.loading-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100px;
  font-size: 16px;
  color: #999;
}
</style>

