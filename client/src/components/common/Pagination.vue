<script setup lang="ts">
import { defineProps, computed, defineEmits } from 'vue';

const props = defineProps({
  currentPage: {
    type: Number,
    required: true
  },
  totalPages: {
    type: Number,
    required: true
  }
});

const emit = defineEmits(['update:currentPage']);

const visiblePages = computed(() => {
  const total = props.totalPages;
  const current = props.currentPage;

  const groupSize = 5;
  const groupStart = Math.floor((current - 1) / groupSize) * groupSize + 1;
  const groupEnd = Math.min(groupStart + groupSize - 1, total);

  const pages = [];
  for (let i = groupStart; i <= groupEnd; i++) {
    pages.push(i);
  }
  return pages;
});

// "다음" 버튼 클릭 시, 다음 그룹으로 이동
const goToNextGroup = () => {
  const nextGroupFirstPage = Math.floor((props.currentPage - 1) / 5) * 5 + 6;
  if (nextGroupFirstPage <= props.totalPages) {
    emit('update:currentPage', nextGroupFirstPage);
  }
};

// "이전" 버튼 클릭 시, 이전 그룹으로 이동
const goToPreviousGroup = () => {
  const prevGroupFirstPage = Math.floor((props.currentPage - 1) / 5) * 5 - 4;
  if (prevGroupFirstPage >= 1) {
    emit('update:currentPage', prevGroupFirstPage);
  }
};

// 페이지 변경
const changePage = (page: number) => {
  if (page >= 1 && page <= props.totalPages) {
    emit('update:currentPage', page);
  }
};
</script>

<template>
  <div class="pagination">
    <!-- 이전 그룹으로 이동 -->
    <button :disabled="currentPage === 1" @click="goToPreviousGroup">이전</button>

    <!-- 페이지 번호 출력 -->
    <button v-for="page in visiblePages" :key="page" :class="{ active: page === currentPage }"
      @click="changePage(page)">
      {{ page }}
    </button>

    <!-- 다음 그룹으로 이동 -->
    <button :disabled="currentPage === totalPages" @click="goToNextGroup">다음</button>
  </div>
</template>

<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-top: 20px;
}

button {
  padding: 5px 10px;
  border: 1px solid #ddd;
  background-color: white;
  cursor: pointer;
}

button:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}

button.active {
  background-color: #007bff;
  color: white;
}
</style>