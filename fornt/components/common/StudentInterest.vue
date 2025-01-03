<template>
  <div class="student-interest-analysis">
    <h2>学生兴趣分析</h2>
    <el-card>
      <el-table :data="studentInterests" style="width: 100%" border>
        <el-table-column prop="studentName" label="学生姓名" width="150" />
        <el-table-column prop="tags" label="兴趣标签" />
        <el-table-column prop="participationCount" label="参与活动数" width="150" />
        <el-table-column prop="categories" label="活动类别" />
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="viewDetails(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div v-if="loading" class="loading-container">
        <el-loading-spinner />
        正在加载数据，请稍候...
      </div>
    </el-card>
  </div>
</template>

<script>
import http from "@/utils/http";

export default {
  name: "StudentInterest",
  data() {
    return {
      studentInterests: [], // 学生兴趣分析数据
      loading: true, // 数据加载状态
    };
  },
  mounted() {
    this.fetchStudentInterests(); // 初始化加载学生兴趣分析数据
  },
  methods: {
    // 获取学生兴趣分析数据
    async fetchStudentInterests() {
      try {
        const response = await http.get("/studentInterest/analysis");
        this.studentInterests = response.data.map(item => ({
          studentName: item.studentName, // 学生姓名
          tags: item.tags.join(", "), // 兴趣标签，数组转换成字符串
          participationCount: item.participationCount, // 参与活动数
          categories: item.categories.join(", "), // 活动类别，数组转换成字符串
        }));
      } catch (error) {
        console.error("加载学生兴趣分析数据失败：", error);
        this.$message.error("加载学生兴趣分析数据失败，请稍后重试！");
      } finally {
        this.loading = false; // 加载完成
      }
    },
    // 查看详情
    viewDetails(row) {
      this.$message.info(`查看 ${row.studentName} 的详细数据`);
      // 这里可以跳转到详情页或者展示更多信息
    },
  },
};
</script>

<style scoped>
.student-interest-analysis {
  padding: 20px;
}
.loading-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  font-size: 16px;
  color: #999;
}
</style>
