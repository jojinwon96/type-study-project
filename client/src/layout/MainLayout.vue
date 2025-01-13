<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

const goView = (pathValue: string) => {
    router.push({ path: pathValue })
}

</script>

<template>
    <div class="main-layout">
        <header>
            <button class="tab-btn" @click="goView('/post')">글 관리</button>
            <button class="tab-btn" @click="goView('/member')">사용자 관리</button>
        </header>
        <router-view />
    </div>
</template>

<style scoped>
.main-layout {
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    gap: 10px;
}

header {
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    border: 1px solid blue;
}

.tab-btn {
    font-size: 16px;
    padding: 5px 10px;
}
</style>