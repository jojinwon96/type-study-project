<script setup lang="ts">
import type { Post } from '@/types/post';
import { ref } from 'vue';

const post = ref<Post>({
    id:0,
    title: '',
    body: '',
    userId: 1,
});

const createPostHandler = () => {
    console.log('전송');
}

</script>

<template>
    <div class="create-form-wrap">
        <div class="create-form">
            <p>제목</p>
            <input type="text">
        </div>
        <div class="create-form">
            <p>내용</p>
            <textarea></textarea>
        </div>

        <button type="button" class="create-btn" @click="createPostHandler">전송</button>
    </div>
</template>

<style scoped>
.create-form-wrap {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

.create-form {
    display: flex;
    align-items: center;
    gap: 10px;
}

.create-form p {
    font-size: 14px;
    width: 30px;
}

.create-form input[type="text"],
textarea {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
}

textarea {
    resize: none;
    height: 200px;
}

.create-btn {
    align-self: flex-end;
    width: 60px;
    cursor: pointer;
    font-size: 14px;
    padding: 10px;
    border: 1px solid #ddd;
}
</style>