import { createApp } from 'vue';
import { router } from './router';

const App = { template: `<div><h1>选煤厂组态系统</h1><router-link to='/coal/device'>设备</router-link> | <router-link to='/coal/control'>控制</router-link><router-view /></div>` };

createApp(App).use(router).mount('#app');
