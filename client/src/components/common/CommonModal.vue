<script setup lang="ts">
import { ref } from 'vue';

// 모달의 가시성 상태를 관리
const isVisible = ref<boolean>(false);

// 모달을 열기 위한 함수
const openModal = () => {
  isVisible.value = true;
};

// 모달을 닫기 위한 함수
const closeModal = () => {
  isVisible.value = false;
};

const emit = defineEmits(['deleteCheckedMembers']);

// 확인 버튼 클릭 시 부모로 이벤트를 전달
const deleteCheckedMembers = () => {
  emit('deleteCheckedMembers');  // 부모 컴포넌트에 confirm 이벤트 전달
  closeModal();     // 모달 닫기
};

// 부모 컴포넌트에서 모달을 제어할 수 있도록 emit
defineExpose({
  openModal,
  closeModal,
});
</script>

<template>
  <div v-if="isVisible" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <slot></slot>
      <button class="submit-btn" @click="deleteCheckedMembers">확인</button>
      <button class="cancel-btn" @click="closeModal">취소</button>
    </div>
  </div>
</template>


<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  width: 300px;
  text-align: center;
}

.submit-btn{
  padding: 8px;
  margin: 0 4px;
  border: 1px solid #007bff;
  border-radius: 4px;
  cursor: pointer;
}

.cancel-btn {
  padding: 8px;
  margin: 0 4px;
  border: 1px solid red;
  border-radius: 4px;
  cursor: pointer;
}

.close-btn:hover {
  background-color: #0056b3;
}
</style>
