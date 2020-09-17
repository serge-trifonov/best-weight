<template>
    <div>
	<header class="text-center"><img src="/images/name.png" alt="choose_your_weight" class="name"><a href="/logout" class="logout">{{$t('logout')}}</a></header>
	<img src="/images/fitness.jpg" alt="bg" class="bg">
	<router-view></router-view>
    </div>
</template>

<script>
    export default {
	data() {
            return {
		user: user
            }
       },
       methods: {
	
       },
       created(){
		if(user === null){
			this.$router.push({ path: 'start' })
		}
		else {
			this.$router.push({ path: 'personalPage' })
		}
	}
    }
</script>

<style>
</style>