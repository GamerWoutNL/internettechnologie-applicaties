import { RouteConfig } from 'vue-router'

const routes: RouteConfig[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/InputPage.vue') },
      { path: '/result', component: () => import('pages/ResultPage.vue') }
    ]
  }
]

export default routes
