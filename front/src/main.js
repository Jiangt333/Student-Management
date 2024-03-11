import { createApp } from 'vue'
import App from './App.vue'
import router from './routes/router'

import './assets/main.css'

import ElementPlus from 'element-plus'// 导入ElementPlus
import 'element-plus/dist/index.css'

const app = createApp(App)

app.use(router)

app.use(ElementPlus) // 使用Element UI

app.mount('#app')
