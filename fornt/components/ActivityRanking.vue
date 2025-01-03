<template>
  <div class="activity-ranking">
    <h2>近期活动排行</h2>
    <el-table :data="activityRankings" style="width: 100%" border>
      <el-table-column prop="rank" label="排名" width="80">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="活动名称" />
      <el-table-column prop="organizer" label="主办社团" />
      <el-table-column prop="participants" label="参与人数" />
      <el-table-column prop="startTime" label="开始时间">
        <!-- 格式化时间 -->
        <template slot-scope="scope">
          {{ formatDate(scope.row.startTime) }}
        </template>
      </el-table-column>
    </el-table>
    <div v-if="loading" class="loading-container">
      <el-loading-spinner />
      正在加载数据，请稍候...
    </div>
  </div>
</template>

<script>
// 使用封装的 http.js
import http from "@/utils/http";

export default {
  name: "ActivityRanking",
  data() {
    return {
      activityRankings: [], // 活动排行数据
      loading: true, // 数据加载状态
    };
  },
  mounted() {
    this.fetchActivityRankings(); // 加载数据
  },
  methods: {
    // 获取活动排行数据
    async fetchActivityRankings() {
      try {
        const response = await http.get("/jinqihuodongpaiming/list");
        this.activityRankings = response.data.map((item, index) => ({
          rank: index + 1,
          title: item.biaoti,
          organizer: item.shetuanmingcheng,
          participants: item.huodongrenshu,
          startTime: item.kaishishijian, // 时间戳原始数据
        }));
      } catch (error) {
        console.error("获取近期活动排行数据失败：", error);
        this.$message.error("加载近期活动排行数据失败，请稍后重试");
      } finally {
        this.loading = false; // 数据加载完成
      }
    },
    // 格式化日期
    formatDate(timestamp) {
      const date = new Date(timestamp);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}`;
    },
  },
};
</script>

<style scoped>
.activity-ranking {
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

