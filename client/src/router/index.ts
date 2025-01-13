import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'mainLayout',
      component: () => import('@/layout/MainLayout.vue'),
      children: [
        {
          path: 'post',
          name: 'postMainLayout',
          component: () => import('@/layout/post/PostMainLayout.vue'),
          children: [
            {
              path: '',
              name: 'postMainView',
              component: () => import('@/views/post/PostMainView.vue'),
              meta: { title: '글 목록' }
            },
            {
              path: '/post/create',
              name: 'createView',
              component: () => import('@/views/post/CreateView.vue'),
              meta: { title: '글 작성' }
            },
          ]
        },
        {
          path: 'member',
          name: 'memberMainLayout',
          component: () => import('@/layout/member/MemberMainLayout.vue'),
          children: [
            {
              path: '',
              name: 'memberMainView',
              component: () => import('@/views/member/MemberMainView.vue'),
              meta: { title: '사용자 목록' }
            },
            {
              path: '/member/create',
              name: 'createMemberView',
              component: () => import('@/views/member/CreateMemberView.vue'),
              meta: { title: '사용자 추가' }
            },
            {
              path: '/member/:sequence',
              name: 'detailMemberView',
              component: () => import('@/views/member/DetailMemberView.vue'),
              meta: { title: '사용자 상세정보' }
            },
            {
              path: '/member/update/:sequence',
              name: 'updateMemberView',
              component: () => import('@/views/member/UpdateMemberView.vue'),
              meta: { title: '사용자 수정' }
            },
          ]
        }
      ]
    },
  ],
})

export default router
