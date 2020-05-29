import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// import Vuetify from 'vuetify'
// import 'vuetify/dist/vuetify.min.css'
import vuetify from "@/plugins/vuetify";
import "material-design-icons-iconfont/dist/material-design-icons.css";

import infiniteScroll from "vue-infinite-scroll";
Vue.use(infiniteScroll);

// Vue.config.productionTip = false

new Vue({
  // el:'#app',
  store,
  router,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
