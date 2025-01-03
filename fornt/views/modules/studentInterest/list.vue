<template>
  <div class="student-interest">
    <h2>学生兴趣分析</h2>

    <div class="form">
      <el-form :model="form" label-width="120px">
        <el-form-item label="学生ID">
          <el-input v-model="form.studentId" placeholder="请输入学生ID"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="fetchStudentInterest" :loading="loading">
            获取兴趣数据
          </el-button>
          <el-button type="success" @click="generateStudentInterest" :loading="loading">
            生成兴趣数据
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <div v-if="interestData" class="result">
      <h3>学生兴趣数据</h3>
      <el-card>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-descriptions title="基本信息" :column="1" border>
              <el-descriptions-item label="ID">{{ interestData.id }}</el-descriptions-item>
              <el-descriptions-item label="学生ID">{{ interestData.studentId }}</el-descriptions-item>
              <el-descriptions-item label="兴趣标签">{{ interestData.interestTags }}</el-descriptions-item>
              <el-descriptions-item label="活动数量">{{ interestData.activityCount }}</el-descriptions-item>
              <el-descriptions-item label="更新时间">{{ formatTimestamp(interestData.updatedAt) }}</el-descriptions-item>
            </el-descriptions>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover">
              <h4>分类详情</h4>
              <el-table :data="parseCategoryDetails(interestData.categoryDetails)" border style="width: 100%;">
                <el-table-column prop="category" label="分类" width="150"></el-table-column>
                <el-table-column prop="count" label="数量" width="150"></el-table-column>
              </el-table>
            </el-card>
          </el-col>
        </el-row>
      </el-card>
    </div>

    <el-alert v-if="errorMessage" :title="errorMessage" type="error" show-icon />
  </div>
</template>

<script>
import { getStudentInterest, generateStudentInterest } from "@/utils/studentInterestApi";

export default {
  name: "StudentInterest",
  data() {
    return {
      form: {
        studentId: "",
      },
      interestData: null,
      loading: false,
      errorMessage: "",
    };
  },
  methods: {
    async fetchStudentInterest() {
      if (!this.form.studentId) {
        this.$message.warning("请输入学生ID");
        return;
      }

      this.loading = true;
      this.errorMessage = "";
      try {
        const response = await getStudentInterest(this.form.studentId);

        // 设置返回数据
        this.interestData = response && response.data ? response.data : null;

        this.$message.success("兴趣数据获取成功");
      } catch (error) {
        this.errorMessage =
          error &&
          error.response &&
          error.response.data &&
          error.response.data.message
            ? error.response.data.message
            : "获取数据失败";
      } finally {
        this.loading = false;
      }
    },
    async generateStudentInterest() {
      if (!this.form.studentId) {
        this.$message.warning("请输入学生ID");
        return;
      }

      this.loading = true;
      this.errorMessage = "";
      try {
        await generateStudentInterest(this.form.studentId);
        this.$message.success("兴趣数据生成成功");
      } catch (error) {
        this.errorMessage =
          error &&
          error.response &&
          error.response.data &&
          error.response.data.message
            ? error.response.data.message
            : "生成数据失败";
      } finally {
        this.loading = false;
      }
    },
    // 格式化时间戳
    formatTimestamp(timestamp) {
      const date = new Date(timestamp);
      return date.toLocaleString();
    },
    // 解析分类详情
    parseCategoryDetails(details) {
      try {
        const parsed = JSON.parse(details);
        return Object.keys(parsed).map((key) => ({
          category: key,
          count: parsed[key],
        }));
      } catch (e) {
        return [];
      }
    },
  },
};
</script>

<style scoped>
.student-interest {
  padding: 20px;
}
.result {
  margin-top: 20px;
}
h4 {
  margin: 10px 0;
}
</style>





