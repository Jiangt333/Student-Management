import { createApp } from "vue";
import App from "./App.vue";
import ElementPlus from 'element-plus'// 导入ElementPlus
import 'element-plus/dist/index.css'
// 导入css文件
import "./style.css";
import './assets/base.css'
import './assets/iconfont/iconfont.css'

import { createPinia } from "pinia"; // 引入pinia
import { UserStore } from "./stores/UserStore" // 引入UserStore

import { router } from "./routes/router"; // 引入路由

import axios from "axios"; // 引入axios
axios.defaults.baseURL = "http://localhost:8080"; // 服务端地址全局配置
// 拦截器传token
axios.interceptors.request.use((config) => {
    config.headers.authorization = `Bearer ${userStore.token}`
    return config
})

const app = createApp(App);

app.provide("axios", axios); // 将axios全局放入
app.provide("serverUrl", axios.defaults.baseURL)

app.use(ElementPlus) // 使用Element UI

app.use(createPinia()); // 引入pinia
const userStore = UserStore()

app.use(router); // 引入路由

app.mount("#app");

export {axios}