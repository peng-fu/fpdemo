import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "../public/reset.css";
import './plugins/iview.js'
import "./axios"

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
