import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/global.css'
import Element from 'element-ui';
// import axios from "axios";

// Vue.prototype.axios = axios;


Vue.config.productionTip = false
// 生产环境CDN引入element-ui的情况，改变默认的size
// 插入到main.js
Vue.prototype.$ELEMENT = {
  size: 'medium',
  zIndex: 3000
}


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
