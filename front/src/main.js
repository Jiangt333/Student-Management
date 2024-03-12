import { createApp } from "vue";
import App from "./App.vue";
import ElementPlus from 'element-plus'// 导入ElementPlus
import 'element-plus/dist/index.css'
// 导入css文件
import "./style.css";
import './assets/base.css'
import './assets/iconfont/iconfont.css'

import { createPinia } from "pinia"; // 引入pinia
import { router } from "./routes/router"; // 引入路由
import { UserStore } from "./stores/UserStore" // 引入UserStore

const app = createApp(App);

app.use(ElementPlus) // 使用Element UI

app.use(createPinia()); // 引入pinia
const userStore = UserStore()

app.use(router); // 引入路由

app.mount("#app");
