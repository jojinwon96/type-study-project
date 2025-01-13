<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute()
const router = useRouter();
const pageTitle = ref(route.meta.title);

const goCreateView = () => {
  router.push({ path: '/post/create' })
}
watch(() => route.meta.title, (newTitle) => {
  pageTitle.value = newTitle;
})

</script>
<template>
  <div class="main-container">
    <div class="main-menu">
      <button type="button" class="add-btn" @click="goCreateView">글 작성</button>
    </div>
    <div class="main-content">
      <h3>{{ pageTitle }}</h3>
      <router-view />
    </div>
  </div>
</template>
<style scoped>
.main-container {
  margin: 0 auto;
  width: 1400px;
  height: 800px;
  display: flex;
  justify-content: space-between;
}

.main-menu {
  border: 1px solid blue;
  width: 280px;

  padding: 20px;
  display: flex;
  flex-direction: column;
}

.main-content {
  border: 1px solid blue;
  width: 1020px;
  padding: 20px;
}

.add-btn {
  border: 1px solid #ddd;
  font-size: 14px;
  padding: 10px;
  cursor: pointer;
}
</style>
