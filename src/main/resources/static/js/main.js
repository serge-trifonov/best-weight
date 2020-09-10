import Vue from 'vue';
import VueResource from 'vue-resource';
import router from 'router/router';
import App from 'pages/Home.vue';
import '@babel/polyfill'
import 'bootstrap'
import 'assets/app.scss'
import {i18n} from 'i18n/i18n'

Vue.use(VueResource);

new Vue({
    el: '#app',
    i18n,
    router,
    render: a => a(App)
});