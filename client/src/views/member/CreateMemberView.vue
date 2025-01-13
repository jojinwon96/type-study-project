<script setup lang="ts">
import { useMember } from '@/apis/member';
import type { Member } from '@/apis/member/dto/member';
import { debounce } from '@/util/debounce';
import { ref } from 'vue';

const { getMember, createMemberApi } = useMember();

const requestMember = ref<Member>(getMember());

const createMemberHandler = debounce(() => { createMemberApi(requestMember.value) }, 500);

</script>

<template>
    <div class="create-form-wrap">
        <div class="create-form">
            <p>아이디</p>
            <input type="text" v-model="requestMember.id">
        </div>
        <div class="create-form">
            <p>이름</p>
            <input type="text" v-model="requestMember.name">
        </div>
        <div class="create-form">
            <p>나이</p>
            <input type="number" v-model="requestMember.age">
        </div>
        <button type="button" class="create-btn" @click="createMemberHandler">추가</button>
    </div>
</template>

<style scoped>
.create-form-wrap {
    display: flex;
    flex-direction: column;
    width: 50%;
    gap: 10px;
}

.create-form {
    width: 100%;
    display: flex;
    align-items: center;
    gap: 10px;
}

.create-form p {
    width: 50px;
    font-size: 14px;
}

.create-form input[type="text"],
input[type="number"] {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
}

.create-btn {
    align-self: flex-end;
    cursor: pointer;
    font-size: 14px;
    padding: 10px;
    border: 1px solid #ddd;
}
</style>