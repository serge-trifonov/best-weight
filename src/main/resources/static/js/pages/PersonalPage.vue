<template>
    <div class="container-fluid mt-3">
	<div class="row">
	<form class="col-3 text-center">
 	 <div class="form-group">
	    <label for="actualWeight"><h4>Votre poids actuel</h4></label>
	    <input type="text" v-model="userProfile.weight" class=" form-control" id="actualWeight" placeholder="Poids actuel">
	  </div>
	  <button type="submit" class="btn btn-primary" @click.stop="addWeight">Submit</button>
	</form>
	<div class="col-6 text-center">
		<div :class="getColor()">
			<h5>{{$t("yourimc")}} : {{imc}}</h5>
			<h5><em>{{$t("comments"+comments ())}}</em></h5>
		</div>
		<p class="bg-light">{{$t("bestweightinterval", { min: minPoids, max: maxPoids})}}</p>
	</div>
	<form class="col-3 text-center">
 	 <div class="form-group">
	    <label for="desiredWeight"><h4>Objectif de poids</h4></label>
	    <input type="text" v-model="currentproject.desiredWeight" class="form-control" id="desiredWeight" placeholder="Poids souhaité">
	  </div>
	  <button type="submit" class="btn btn-primary" @click.stop="addWeight">Submit</button>
	</form>
    	</div>
	<div v-if="currentproject.startDate">projects</div>
	<div v-else>
		<div class="alert alert-primary text-center m-5" role="alert">
			{{$t("noprojects")}}
		</div>
		<img src="/images/balance.jpg" alt="bg" class="bg">
	</div>
    </div>
</template>

<script>
    export default {
	data() {
            return {
		userProfile: user,
		oldprojects: [],
		currentproject: {},
		imc: 0,
		minPoids: 0,
		maxPoids: 0
            }
       },
       methods: {
            async addWeight(event){
		event.preventDefault();
		console.log(this.userProfile);
		this.userProfile.weight = parseFloat(this.userProfile.weight);
		console.log(this.userProfile);
		var request = await this.$http.post("/user/"+this.userProfile.id, this.userProfile);
		this.userProfile = request.data;
		user = request.data;
		console.log("request ", user);
		this.countImc();
            },
	    comments () {
		if (this.imc<16.5) {
			return "1";
		}
		else if(this.imc<18.5) {
			return "2";
		}
		else if(this.imc<=25) {
			return "3";
		}
		else if(this.imc<30) {
			return "4";
		}
		else if(this.imc<35) {
			return "5";
		}
		else if(this.imc<40) {
			return "6";
		}
		else {
			return "7";
		}
	    },
	    countImc() {
		this.imc = (this.userProfile.weight/((this.userProfile.height/100)*(this.userProfile.height/100))).toFixed(2);
		this.minPoids = (((this.userProfile.height/100)*(this.userProfile.height/100))*18.5).toFixed(2);
		this.maxPoids = (((this.userProfile.height/100)*(this.userProfile.height/100))*25).toFixed(2);
	    },
	    getColor() {
		if (this.imc<25 && this.imc>=18.5) {
			return "text-success";
		}
		else if(this.imc<16.5 || this.imc>=30) {
			return "text-danger";
		}
		else {
			return "text-warning";
		}
	    }
        },
	async created(){
		var result = await this.$http.get("/projects");
		var projects = result.data;
		if(projects){
			this.currentproject = projects.filter(p => p.current);
			this.oldprojects = projects.filter(p => !p.current);
		}
		this.countImc();
	}
    }
</script>

<style>
</style>