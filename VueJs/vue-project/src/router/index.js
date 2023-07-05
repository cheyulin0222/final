import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { userStore } from '../stores/userStore';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta:{
        requiredAuth:false
      }
    },
    {
      path: '/allpost',
      name: 'allpost',
      component: () => import('../views/AllPost.vue'),
    },
    {
      path: '/mypost',
      name: 'mypost',
      component: () => import('../views/MyPost.vue'),
    },
    {
      path: '/myfollowing',
      name: 'myfollowing',
      component: () => import('../views/MyPost.vue'),
    },

    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login.vue'),
    },

    {
      path: '/signup',
      name: 'signup',
      component: () => import('../views/Signup.vue'),
    },
    {
      path: '/allmember',
      name: 'allmember',
      component: () => import('../views/AllMember.vue'),
    },
    {
      path: '/test',
      name: 'test',
      component: () => import('../views/Test.vue'),
    },

  ]
})

router.beforeEach((to) => {
  const user = userStore()
  const publicPages = ["/login", "/signup"]
  const authRequired = !publicPages.includes(to.path)
  
  if(to.name !== "login" && authRequired && !isAuthorizated()) {
      return { name:"login" }
  }
});

function isAuthorizated () {
  let USER = localStorage.getItem("jwtToken");
  return !!USER;
}

export default router
