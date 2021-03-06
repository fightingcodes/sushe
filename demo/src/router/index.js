import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'
import table from "@/views/table";
import Login from "@/views/Login";
import Register from "@/views/Register";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import(/* webpackChunkName: "about" */ '../views/Manage.vue'),
    redirect:"/home",
    children:[
      { path: 'home', name: 'Home', component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue')},
      { path: 'user', name: 'User', component: () => import(/* webpackChunkName: "about" */ '../views/User.vue')},
      { path: 'person', name: 'Person', component: () => import(/* webpackChunkName: "about" */ '../views/Person.vue')}
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name:'登录',
    component:Login
  },
  {
    path: '/register',
    name:'注册',
    component:Register
  },
  {
    path: '/table',
    component: table
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
