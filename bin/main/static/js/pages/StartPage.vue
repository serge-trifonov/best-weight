<template>
    	<div class="row ml-2 mt-5">
	  <div class="col-4">
	    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
	      <a class="nav-link active startbutton" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">
			<div class="button-text">About the program</div>
	      </a>
	      <a class="nav-link startbutton" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">
			<div class="button-text">Enter</div>
	      </a>
	    </div>
	  </div>
	  <div class="col-4">
	    <div class="tab-content" id="v-pills-tabContent">
	      <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">Home home</div>
	      <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
		<nav>
		  <div class="nav nav-tabs" id="nav-tab" role="tablist">
		    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Login</a>
		    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Registration</a>
		  </div>
		</nav>
	      <div class="tab-content form center-block" id="nav-tabContent">
		  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<form class="text-center">
			  <div class="form-group">
			    <label for="exampleInputEmail1" >Username</label>
			    <input type="text" v-model="profile.username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter username">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" v-model="profile.password" class="form-control" id="exampleInputPassword1" placeholder="Password">
			  </div>
			  <button type="submit" class="btn startbutton-white" @click.stop="login">Submit</button>
			</form>
		  </div>
		  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<form class="text-center">
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
			  <button type="submit" class="btn startbutton-white" @click.stop="registration">Submit</button>
			</form>
		  </div>
	      </div>
	    </div>
	  </div>
	 </div>
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
	    async login(event){
		event.preventDefault();
		var formData = new FormData();
		formData.append("username", this.profile.username);
		formData.append("password", this.profile.password);
		var result = await this.$http.post("/login", formData);
		console.log(result);
            },
	    async registration(event){
		event.preventDefault();
		await this.$http.post("/registration", this.profile);
            }
        },
	created(){
		console.log(user);
	}
    }
</script>

<style>
</style>