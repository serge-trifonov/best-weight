<template>
    <div class="container-fluid mt-3">{{userProfile.weight}}
	<form>
 	 <div class="form-group">
	    <label for="exampleInputPassword1">Votre poids actuel</label>
	    <input type="text" v-model="userProfile.weight" class="form-control" id="exampleInputPassword1" placeholder="Password">
	  </div>
	  <button type="submit" class="btn btn-primary" @click.stop="addWeight">Submit</button>
	</form>
	Votre IMC : {{imc}}
    </div>
</template>

<script>
    export default {
	data() {
            return {
		userProfile: user,
		oldprojects: [],
		currentproject: {},
		imc: 0
            }
       },
       methods: {
            async addWeight(event){
		event.preventDefault();
		console.log(this.userProfile);
		this.userProfile.weight = parseFloat(this.userProfile.weight);
		console.log(this.userProfile);
		var request = await this.$http.post("/user/"+this.userProfile.id, {user: this.userProfile});
		this.userProfile = request.data;
		console.log("request ", request);
		this.imc = this.userProfile.weight/((this.userProfile.height/100)*(this.userProfile.height/100));
            }
        },
	async created(){
		var result = await this.$http.get("/projects");
		var projects = result.data;
		console.log(this.userProfile);
		if(projects){
			this.currentproject = projects.filter(p => p.current);
			this.oldprojects = projects.filter(p => !p.current);
		}
		this.imc = this.userProfile.weight/((this.userProfile.height/100)*(this.userProfile.height/100));
	}
    }
</script>

<style>
</style>