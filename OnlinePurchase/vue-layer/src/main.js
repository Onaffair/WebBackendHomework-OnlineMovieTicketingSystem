import { createApp } from 'vue'
import App from './App.vue'

/* Element UI*/
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

/*pinia*/
import {createPinia} from "pinia";
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'


import router from './router'


const app = createApp(App)


app.use(ElementPlus)
app.use(router)


const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)

app.mount('#app')



