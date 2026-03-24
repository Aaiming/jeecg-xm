import { createRouter, createWebHistory } from 'vue-router';
import DevicePage from '../views/coal/DevicePage.vue';
import ControlPage from '../views/coal/ControlPage.vue';

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/coal/device' },
    { path: '/coal/device', component: DevicePage },
    { path: '/coal/control', component: ControlPage }
  ]
});
