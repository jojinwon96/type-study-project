<script setup lang="ts">
import { useMember } from '@/apis/member';
import type { Member } from '@/apis/member/dto/member';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

// 라우팅
const route = useRoute();
const router = useRouter();
const sequenceNumber = Number(route.params.sequence);
// member 데이터
const member = ref<Member>();
// useMember 훅
const { getMember, getMemberApi } = useMember();

// member 데이터 api 호출 함수
const getMemberApiHandler = async () => {
    await getMemberApi(sequenceNumber);
    member.value = getMember();
}

// 수정 페이지 이동 함수
const goUpdateView = () => {
    router.push({ name: 'updateMemberView', params: { sequenceNumber } })
}

// member 데이터 api 호출
getMemberApiHandler();

</script>

<template>
    <div class="detail-form-wrap">
        <div class="detail-form">
            <p>아이디 : </p>
            <span>{{ member?.id }}</span>
        </div>
        <div class="detail-form">
            <p>이름 : </p>
            <span>{{ member?.name }}</span>
        </div>
        <div class="detail-form">
            <p>나이 : </p>
            <span>{{ member?.age }}</span>
        </div>
        <div class="detail-form">
            <p>생성일 : </p>
            <span>{{ member?.createDate }}</span>
        </div>
        <button type="button" class="update-btn" @click="goUpdateView">수정하기</button>
    </div>
</template>

<style scoped>
.detail-form-wrap {
    display: flex;
    flex-direction: column;
    width: 50%;
    gap: 10px;
}

.detail-form {
    width: 100%;
    display: flex;
    gap: 10px;
}

.detail-form p {
    width: 60px;
    font-size: 14px;
}

.detail-form span {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
}

.update-btn {
    align-self: flex-end;
    cursor: pointer;
    font-size: 14px;
    padding: 10px;
    border: 1px solid #ddd;
}
</style>