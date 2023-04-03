import { createApp } from 'vue'
import App from './views/DashBoard/Goods/GoodsRegWeb.vue'
import router from './router'
import './assets/main.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import { ElInput, ElIcon } from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
app.use(ElementPlus)
app.use(router)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

app.mount('#app')

app.config.globalProperties.$axios = axios;
axios.defaults.baseURL = "/api";
axios.defaults.withCredentials = true;

