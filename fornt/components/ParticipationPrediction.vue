<template>
  <div class="participation-prediction">
    <h2>活动参与预测</h2>
    <!-- 未审核活动表格 -->
    <el-table :data="activities" border style="width: 100%" v-loading="loading">
      <el-table-column prop="biaoti" label="活动标题" />
      <el-table-column prop="shetuanmingcheng" label="社团名称" />
      <el-table-column prop="kaishishijian" label="开始时间" />
      <el-table-column prop="jieshushijian" label="结束时间" />
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            @click="predictParticipation(scope.row)"
          >
            预测参与人数
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 预测结果弹窗 -->
    <el-dialog
      title="预测结果"
      :visible.sync="showPredictionDialog"
      width="30%"
    >
      <p>活动标题: {{ selectedActivity.biaoti }}</p>
      <p>社团名称: {{ selectedActivity.shetuanmingcheng }}</p>
      <p>预测参与人数: {{ predictedParticipants }}</p>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showPredictionDialog = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import http from "@/utils/http";

export default {
  name: "ParticipationPrediction",
  data() {
    return {
      activities: [], // 未审核活动数据
      loading: false, // 加载状态
      showPredictionDialog: false, // 是否显示预测结果弹窗
      selectedActivity: {}, // 当前选中的活动
      predictedParticipants: null, // 预测参与人数
    };
  },
  mounted() {
    this.fetchUnverifiedActivities();
  },
  methods: {
    // 获取未审核活动列表
    async fetchUnverifiedActivities() {
      this.loading = true;
      try {
        const response = await http.get("/huodongcanyuyuce/unverified");
        this.activities = response.data.data || [];
      } catch (error) {
        console.error("获取未审核活动失败：", error);
        this.$message.error("获取未审核活动失败，请稍后重试");
      } finally {
        this.loading = false;
      }
    },
    // 预测活动参与人数
    async predictParticipation(activity) {
      this.selectedActivity = activity;
      try {
        const response = await http.post("/huodongcanyuyuce/predict", activity);
        this.predictedParticipants = response.data.predictedParticipants;
        this.showPredictionDialog = true;
      } catch (error) {
        console.error("预测活动参与人数失败：", error);
        this.$message.error("预测活动参与人数失败，请稍后重试");
      }
    },
  },
};
</script>

<style scoped>
.participation-prediction {
  padding: 20px;
}

.el-table {
  margin-top: 20px;
}

.dialog-footer {
  text-align: right;
}
</style>





