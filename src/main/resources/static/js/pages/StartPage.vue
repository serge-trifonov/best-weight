<template>
    	<div class="row ml-2 mt-5">
	  <div class="col-4">
	    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
	      <a class="nav-link active startbutton" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="!loginselected" @click="choseTab(7)">
			<div class="button-text">{{$t("aboutprogram")}}</div>
	      </a>
	      <a class="nav-link startbutton" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="loginselected" @click="choseTab(4)">
			<div class="button-text">{{$t("enter")}}</div>
	      </a>
	    </div>
	  </div>
	  <div :class="'col-'+size">
	    <div class="tab-content" id="v-pills-tabContent">
	      <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab"><demo/></div>
	      <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
		<nav>
		  <div class="nav nav-tabs" id="nav-tab" role="tablist">
		    <a class="nav-item nav-link active" id="nav-login-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-login" aria-selected="true">{{$t("login")}}</a>
		    <a class="nav-item nav-link" id="nav-registration-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-registration" aria-selected="false">{{$t("registration")}}</a>
		  </div>
		</nav>
	      <div class="tab-content form center-block" id="nav-tabContent">
		  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<form class="text-center">
			  <div class="form-group">
			    <span v-if="loginfailed" class="text-danger">{{$t("notidentified")}}</span><br/>
			    <label for="username-log" >{{$t("username")}}</label><br/>
			    <span v-if="noUsernameLog" class="text-danger">{{$t("noemail")}}</span>
			    <input type="text" v-model="profile.username" class="form-control" id="username-log" aria-describedby="emailHelp" placeholder="Enter username">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">{{$t("password")}}</label><br/>
			    <span v-if="noPasswordLog" class="text-danger">{{$t("nopassword")}}</span>
			    <input type="password" v-model="profile.password" class="form-control" id="exampleInputPassword1" placeholder="Password">
			  </div>
			  <button type="submit" class="btn startbutton-white" @click.stop="login">{{$t("submit")}}</button>
			</form>
		  </div>
		  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<form class="text-center">
			  <div class="form-group">
			    <label for="username-reg">{{$t("username")}}</label><br/>
			    <span v-if="noUsername" class="text-danger">{{$t("nousername")}}</span>
			    <input type="text" v-model="profile.username" :class="noUsername?'form-control border-danger':'form-control'" id="username-reg" aria-describedby="emailHelp" placeholder="Enter username">
			  </div>
			  <div class="form-group">
			    <label for="email-reg">{{$t("email")}}</label><br/>
			    <span v-if="noEmail" class="text-danger">{{$t("noemail")}}</span>
			    <input type="email" v-model="profile.email" :class="noEmail?'form-control border-danger':'form-control'" id="email-reg" aria-describedby="emailHelp" placeholder="Enter email">
			  </div>
			  <div class="form-group">
			    <label for="height-reg">{{$t("height")}}</label><br/>
			    <span v-if="noHeight" class="text-danger">{{$t("noheight")}}</span>
			    <input type="number" :class="noHeight?'form-control border-danger':'form-control'" v-model="profile.height" id="height-reg" placeholder="Height">
			  </div>
		 	  <div class="form-group">
			    <label for="password-reg">{{$t("password")}}</label><br/>
			    <span v-if="noPassword" class="text-danger">{{$t("nopassword")}}</span>
			    <input type="password" v-model="profile.password" :class="noPassword?'form-control border-danger':'form-control'" id="password-reg" placeholder="Password">
			  </div>
			  <button type="submit" class="btn startbutton-white" @click.stop="registration">{{$t("submit")}}</button>
			</form>
		</div>
	      </div>
	    </div>
	  </div>
	 </div>
	</div>
</template>

<script>
    import Demo from "./Demo";
    export default {
	components: {
	    demo: Demo
  	},
	data() {
            return {
		profile: {
			username: "",
			email: "",
			password: "",
			height: ""
		},
		user: user,
		loginselected: false,
		noHeight: false,
		noUsername: false,
		noPassword: false,
		noEmail: false,
		loginfailed: false,
		noUsernameLog: false,
		noPasswordLog: false,
		size: 7
            }
       },
       methods: {
	    async login(event){
		event.preventDefault();
		this.noPasswordLog = this.profile.password.length<3;
		this.noUsernameLog = this.profile.username==="";
		if(!(this.noUsernameLog||this.noPasswordLog)) {
			var formData = new FormData();
			formData.append("username", this.profile.username);
			formData.append("password", this.profile.password);
			var result = await this.$http.post("/login", formData);
			var currentUser = await this.$http.get("/user");
			if (currentUser.data.id) {
				location.reload();
			}
			else {
				this.loginfailed = true;
			}
		}
            },
	    choseTab(largeur) {
		this.size=largeur;
	    },
	    async registration(event){
		event.preventDefault();
		var re = /\S+@\S+\.\S+/;
		this.noHeight = !this.profile.height || this.profile.height === 0;
		this.noEmail = !re.test(this.profile.email);
		this.noPassword = this.profile.password.length<3;
		this.noUsername = this.profile.username==="";
		if(!(this.noHeight||this.noUsername||this.noPassword||this.noEmail)) {
			await this.$http.post("/registration", this.profile);
			document.getElementById('nav-login-tab').click();
		}
            }
        }
    }
</script>

<style>
</style>