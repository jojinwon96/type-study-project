<script setup lang="ts">
import { useMember } from '@/apis/member';
import type { Member } from '@/apis/member/dto/member';
import { debounce } from '@/util/debounce';
import { deepClone } from '@/util/deepclone';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

// 라우팅
const route = useRoute();
const sequenceNumber = Number(route.params.sequence);

// useMember 훅
const { getMember, getMemberApi, patchMemberApi } = useMember();

// 원본데이터와 수정데이터 분리 선언
const originMember = ref<Member>();
const requestMember = ref<Member>(getMember());

// 사용자 상세 조회 요청 함수
const getMemberApiHandler = async () => {
    await getMemberApi(sequenceNumber);
    requestMember.value = getMember();
    originMember.value = deepClone(requestMember.value);
}

// 사용자 수정 요청 함수
const updateMemberHandler = debounce(async () => {
    requestMember.value.age = Number(requestMember.value.age);
    if (!isChanged()) {
        return;
    }
    if (!checkValid()) {
        return;
    }
    await patchMemberApi(sequenceNumber, requestMember.value);
}, 500);

// 입력형식 확인 함수
const checkValid = () => {
    if (!requestMember.value.id || !requestMember.value.name || !requestMember.value.age) {
        alert('공백 또는 입력 형식을 확인해주세요');
        return false;
    }
    return true;
}

// 데이터 변경 감지 함수
const isChanged = () => {
    if (JSON.stringify(requestMember.value) === JSON.stringify(originMember.value)) {
        alert('변경사항이 없습니다.');
        return false;
    }
    return true;
}


getMemberApiHandler();

</script>

<template>
    <div class="update-form-wrap">
        <div class="update-form">
            <p>아이디</p>
            <input type="text" v-model="requestMember.id">
        </div>
        <div class="update-form">
            <p>이름</p>
            <input type="text" v-model="requestMember.name">
        </div>
        <div class="update-form">
            <p>나이</p>
            <input type="text" v-model="requestMember.age">
        </div>
        <button type="button" class="update-btn" @click="updateMemberHandler">수정</button>
    </div>
</template>

<style scoped>
.update-form-wrap {
    display: flex;
    flex-direction: column;
    width: 50%;
    gap: 10px;
}

.update-form {
    width: 100%;
    display: flex;
    align-items: center;
    gap: 10px;
}

.update-form p {
    width: 50px;
    font-size: 14px;
}

.update-form input[type="text"] {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
}

.update-btn {
    align-self: flex-end;
    cursor: pointer;
    font-size: 14px;
    padding: 10px;
    border: 1px solid #ddd;
}
</style>