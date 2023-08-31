import { createApp } from 'vue'
import router from "@/script/router"
import store from "@/script/store"
import App from './App.vue'

createApp(App).use(store).use(router).mount('#app')
