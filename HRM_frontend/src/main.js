// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'

import JsonExcel from 'vue-json-excel'
Vue.component('downloadExcel', JsonExcel)


//跨域配置
axios.defaults.baseURL="/api";

Vue.config.productionTip = false
Vue.prototype.$axios= axios

// 配置直接使用 axios
window.axios = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
