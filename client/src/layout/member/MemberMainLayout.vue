<script setup lang="ts">
import { ref, watch, type ComponentPublicInstance } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import CommonModal from '@/components/common/CommonModal.vue';
import { useMember } from '@/apis/member';
import type { Member } from '@/apis/member/dto/member';

const { deleteMemberApi, getMembersApi, getMembers } = useMember();
const route = useRoute();
const router = useRouter();
const pageTitle = ref(route.meta.title);

// MemberMainView에 전달할 checklist와 members 데이터
const checkedList = ref<Member[]>([]);
const members = ref<Member[]>([]);

// 체크리스트 업데이트 이벤트 처리
const updateCheckedList = (newCheckedList: Member[]) => {
  checkedList.value = newCheckedList;
}

// 사용자 추가 페이지 이동 함수
const goCreateView = () => {
  router.push({ path: '/member/create' });
}

// 페이지 타이틀 변경 훅
watch(() => route.meta.title, (newTitle) => {
  pageTitle.value = newTitle;
});

// 모달 컴포넌트 참조
const commonModal = ref<ComponentPublicInstance<{ openModal: () => void }> | null>(null);

// 체크리스트 삭제 모달 보여주는 함수
const showDeleteMembersModal = () => {
  if (commonModal.value) {
    commonModal.value.openModal(); // openModal 메서드를 호출
  }
}

// 선택된 체크리스트 사용자 제거 함수
const deleteCheckedMembers = async () => {
  await deleteMemberApi(checkedList.value);
  // 삭제 후 최신 목록을 불러옴
  await fetchMembers();
}

// 페이지 초기화 시, 회원 목록 가져오기
const fetchMembers = async () => {
  await getMembersApi();
  members.value = getMembers();
  // 체크 리스트 초기화
  checkedList.value = [];
};

fetchMembers(); // 초기화
</script>

<template>
  <div class="main-container">
    <div class="main-menu">
      <button type="button" class="add-btn" @click="goCreateView">사용자 추가</button>
    </div>
    <div class="main-content">
      <div class="main-content-header">
        <h3>{{ pageTitle }}</h3>
        <button v-show="checkedList.length > 0" type="button" @click="showDeleteMembersModal">선택 삭제</button>
      </div>
      <router-view :checkedList="checkedList" :members="members" @update:checkedList="updateCheckedList" />
    </div>
  </div>
  <CommonModal ref="commonModal" @deleteCheckedMembers="deleteCheckedMembers">
    <h3>삭제할 내용</h3>
    <p v-for="(member) in checkedList" :key="member.sequence">{{ member.name }}</p>
  </CommonModal>
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

.main-content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.main-content-header > button {
  border: 1px solid red;
  background-color: #fff;
  height: 30px;
  font-weight: bold;
  cursor: pointer;
}

.add-btn {
  border: 1px solid #ddd;
  font-size: 14px;
  padding: 10px;
  cursor: pointer;
}
</style>
