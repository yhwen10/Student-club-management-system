<template>
  <div class="club-ranking">
    <h2>社团人气排行</h2>
    <el-table :data="clubRankings" style="width: 100%" border>
      <el-table-column prop="rank" label="排名" width="80">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="clubId" label="社团ID" />
      <el-table-column prop="memberCount" label="成员人数" />
      <el-table-column prop="eventParticipation" label="活动参与人数" />
      <el-table-column prop="popularityScore" label="人气分数" />
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
  name: "ClubRanking",
  data() {
    return {
      clubRankings: [], // 社团排行数据
      loading: true, // 数据加载状态
    };
  },
  mounted() {
    this.fetchClubRankings(); // 加载数据
  },
  methods: {
    async fetchClubRankings() {
      try {
        // 调用后端接口，获取社团排行数据
        const response = await http.get("/shetuanrenqi/rankings");
        this.clubRankings = response.data; // 将响应数据赋值到 clubRankings
      } catch (error) {
        console.error("获取社团人气排行数据失败：", error);
        this.$message.error("加载社团排行数据失败，请稍后重试");
      } finally {
        this.loading = false; // 数据加载完成
      }
    },
  },
};
</script>

<style scoped>
.club-ranking {
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
