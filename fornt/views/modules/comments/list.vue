<template>
  <div class="comments">
    <h1>评论管理模块</h1>
    <pinglunguanli />
  </div>
</template>

<script>
import Pinglunguanli from "@/components/common/pinglunguanli.vue";

export default {
  name: "CommentsList",
  components: {
    Pinglunguanli,
  },
};
</script>

<style scoped>
.comments {
  padding: 20px;
}
</style>










