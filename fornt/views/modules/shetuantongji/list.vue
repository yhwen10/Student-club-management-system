<template>
  <div class="shetuantongji">
    <el-card class="ranking-card">
      <ClubRanking />
    </el-card>

    <el-card class="ranking-card">
      <ActivityRanking />
    </el-card>

    <el-card class="prediction-card">
      <ParticipationPrediction />
    </el-card>
  </div>
</template>

<script>
// 使用正确的绝对路径导入组件
import ClubRanking from '@/components/ClubRanking.vue';
import ActivityRanking from '@/components/ActivityRanking.vue';
import ParticipationPrediction from '@/components/ParticipationPrediction.vue';

export default {
  name: "SheTuanTongJi",
  components: {
    ClubRanking,
    ActivityRanking,
    ParticipationPrediction,
  },
};
</script>

<style scoped>
.shetuantongji {
  padding: 20px;
}
.ranking-card {
  margin-bottom: 20px;
}
.prediction-card {
  margin-top: 20px;
}
</style>


