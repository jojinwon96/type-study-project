<script setup lang="ts">
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import type { Member } from '@/apis/member/dto/member';

const router = useRouter();

// 부모에서 전달받은 checkedList와 members
const props = defineProps<{
  checkedList: Member[];
  members: Member[];
}>();

const emit = defineEmits<{
  (e: 'update:checkedList', newCheckedList: Member[]): void;
}>();

const checkedList = ref<Member[]>(props.checkedList);

// 전체 선택/해제 체크박스 (computed)
const allCheck = computed({
  get: () => props.members.length === checkedList.value.length,
  set: (e) => {
    checkedList.value = e ? [...props.members] : [];
    emit('update:checkedList', checkedList.value);
  }
});

// 상세 페이지 이동
const goDetailMemberView = (sequence: number) => {
  router.push({ name: 'detailMemberView', params: { sequence } });
};

// 개별 체크박스 선택/해제 처리 11
const toggleSelection = (member: Member) => {
  const isSelected = checkedList.value.some((item) => item.sequence === member.sequence);
  checkedList.value = isSelected ? checkedList.value.filter((item) => item.sequence !== member.sequence) : [...checkedList.value, member];
  emit('update:checkedList', checkedList.value);
};

// props의 checkedList가 변경될 때마다 반영
// watch(() => props.checkedList, (newCheckedList) => {
//   checkedList.value = newCheckedList;
// });
</script>

<template>
  <table>
    <thead>
      <tr>
        <th><input type="checkbox" v-model="allCheck" /></th>
        <th>아이디</th>
        <th>이름</th>
        <th>나이</th>
        <th>생성일</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="member in props.members" :key="member.sequence" @click="goDetailMemberView(Number(member.sequence))">
        <td>
          <input type="checkbox" :checked="checkedList.some(m => m.sequence === member.sequence)"
            @change="toggleSelection(member)" @click.stop />
        </td>
        <td>{{ member.id }}</td>
        <td>{{ member.name }}</td>
        <td>{{ member.age }}</td>
        <td>{{ member.createDate }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  text-align: center;
}

th, td {
  font-size: 14px;
  padding: 10px;
  border: 1px solid #ddd;
}

th {
  background-color: #f4f4f4;
}

tr {
  cursor: pointer;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

tr:hover {
  background-color: #f1f1f1;
}
</style>
