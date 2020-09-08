<template>
    <div>
	<form>
	  <div class="form-group">
	    <label for="exampleInputEmail1" >Username</label>
	    <input type="text" v-model="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter username">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputPassword1">Password</label>
	    <input type="password" v-model="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
	  </div>
	  <button type="submit" class="startbutton" @click.stop="submit" >Submit</button>
	</form>
    </div>
</template>

<script>
    export default {
	data() {
            return {
		username: "",
		password: ""
            }
       },
       methods: {
            async submit(event){
		event.preventDefault();
		var formData = new FormData();
		formData.append("username", this.username);
		formData.append("password", this.password);
		await this.$http.post("/login", formData);
            }
        },
	created(){

	}
    }
</script>

<style>
</style>