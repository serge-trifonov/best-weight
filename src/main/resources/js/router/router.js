import Vue from 'vue'
import VueRouter from 'vue-router'
import PersonalPage from 'pages/PersonalPage.vue';
import Start from 'pages/StartPage.vue';

Vue.use(VueRouter)

const routes = [
    { path: '/start', component: Start },
    { path: '/personalPage', component: PersonalPage }
]

export default new VueRouter({
    mode: 'history',
    routes
}) 