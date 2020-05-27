import Vue from 'vue'
import App from './App.vue'
import router from './router'
// import Vuetify from 'vuetify'
// import 'vuetify/dist/vuetify.min.css'
import vuetify from '@/plugins/vuetify'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import infiniteScroll from "vue-infinite-scroll"
Vue.use(infiniteScroll);
import store from "./store";

// Vue.config.productionTip = false

new Vue({
    // el:'#app',
    router,
    vuetify,
    store,
    render:h => h(App)
}).$mount('#app')