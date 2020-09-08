import Vue from 'vue';
import VueResource from 'vue-resource';
import router from 'router/router';
import App from 'pages/Home.vue';
import '@babel/polyfill'
import 'bootstrap'
import 'assets/app.scss'

Vue.use(VueResource);

new Vue({
    el: '#app',
    router,
    render: a => a(App)
});