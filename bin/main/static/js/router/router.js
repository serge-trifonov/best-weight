import Vue from 'vue'
import VueRouter from 'vue-router'
import Registration from 'pages/Registration.vue'
import PersonalPage from 'pages/PersonalPage.vue';
import Start from 'pages/StartPage.vue';
import Login from 'pages/Login.vue';

Vue.use(VueRouter)

const routes = [
    { path: '/registration', component: Registration },
    { path: '/login', component: Login },
    { path: '/start', component: Start },
    { path: '/personalPage', component: PersonalPage }
]

export default new VueRouter({
    mode: 'history',
    routes
}) 