<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { usePost } from '@/apis/post';
import Pagination from '@/components/common/Pagination.vue';
import type { Post } from '@/types/post';

const { getPosts, fetchPosts } = usePost();

// 게시글
const posts = ref<Post[]>([]);

// API 호출 핸들러
const getPostsHandler = async () => {
  await fetchPosts();
  posts.value = getPosts();
};

// 현재 페이지
const currentPage = ref<number>(1);
// 한 페이지에 보여줄 게시글 수
const postsPerPage: number = 10;

// 페이지 또는 게시물 변경시 현재 페이지에 보여줄 게시글 재계산 하는 computed
const currentPosts = computed(() => {
  const startIndex = (currentPage.value - 1) * postsPerPage;
  const endIndex = startIndex + postsPerPage;
  return posts.value.slice(startIndex, endIndex);
});

// 전체 페이지 갯수 계산하는 computed
const totalPages = computed(() => {
  return Math.ceil(posts.value.length / postsPerPage);
});

// 페이지 변경 함수
const changePage = (page: number) => {
  if (page < 1) {
    currentPage.value = 1;
  } else if (page > totalPages.value) {
    currentPage.value = totalPages.value;
  } else {
    currentPage.value = page;
  }
};

onMounted(() => {
  getPostsHandler();
});
</script>

<template>
  <table>
    <thead>
      <tr>
        <th>글 번호</th>
        <th>제목</th>
        <th>내용</th>
        <th>유저아이디</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(post) in currentPosts" :key="post.id">
        <td>{{ post.id }}</td>
        <td>{{ post.title }}</td>
        <td class="body-cell">{{ post.body }}</td>
        <td>{{ post.userId }}</td>
      </tr>
    </tbody>
  </table>

  <Pagination :currentPage="currentPage" :totalPages="totalPages" @update:currentPage="changePage" />
</template>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  text-align: center;
}

th,
td {
  font-size: 14px;
  padding: 10px;
  border: 1px solid #ddd;
}

th {
  background-color: #f4f4f4;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

tr:hover {
  background-color: #f1f1f1;
}

td.body-cell {
  max-width: 300px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
