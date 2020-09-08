<template>
    <div>
	<form>
	  <div class="form-group">
	    <label for="exampleInputEmail1" >Username</label>
	    <input type="text" v-model="profile.username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter username">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputEmail1">Email address</label>
	    <input type="email" v-model="profile.email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
	    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
	  </div>
	 <div class="form-group">
	    <label for="exampleInputPassword1">Height in cm</label>
	    <input type="number" class="form-control" v-model="profile.height" id="exampleInputPassword1" placeholder="Height">
	 </div>
 	 <div class="form-group">
	    <label for="exampleInputPassword1">Password</label>
	    <input type="password" v-model="profile.password" class="form-control" id="exampleInputPassword1" placeholder="Password">
	  </div>
	  <button type="submit" class="btn btn-primary" @click.stop="submit" >Submit</button>
	</form>
    </div>
</template>

<script>
    export default {
	data() {
            return {
		profile: {
			username: "",
			email: "",
			password: "",
			height: ""
		}
            }
       },
       methods: {
            async submit(event){
		event.preventDefault();
		await this.$http.post("/registration", this.profile);
            }
        },
	created(){

	}
    }
</script>

<style>
</style>