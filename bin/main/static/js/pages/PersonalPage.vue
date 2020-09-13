<template>
    <div class="container-fluid mt-3">
	<div class="row">
	<form class="col-3 text-center">
 	 <div class="form-group">
	    <label for="actualWeight"><h4>Votre poids actuel</h4></label>
	    <input type="text" v-model="userProfile.weight" class=" form-control" id="actualWeight" placeholder="Poids actuel">
	  </div>
	  <button type="submit" class="btn btn-success" @click.stop="addWeight">{{$t("reload")}}</button>
	</form>
	<div class="col-6 text-center">
		<div :class="getColor()">
			<h5>{{$t("yourimc")}} : {{imc}}</h5>
			<h5><em>{{$t("comments"+comments ())}}</em></h5>
		</div>
		<p class="bg-light">{{$t("bestweightinterval", { min: minPoids, max: maxPoids})}}</p>
	</div>
	<form v-if="!project.startDate||changeProject" class="col-3 text-center">
 	  <div class="form-group">
	    <label for="desiredWeight"><h4>{{$t("weightgoal")}}</h4></label>
	    <input type="number" v-model="project.desiredWeight" class="form-control" id="desiredWeight" placeholder="Poids souhaité">
	  </div>
	  <button type="submit" class="btn btn-success" @click.stop="addDesiredWeight">{{$t("newproject")}}</button>
	</form>
	<div v-else class="col-3 text-center">
		<h4>{{$t("changeproject")}}</h4>
		<button class="btn btn-success" @click.stop="changeProjectFunction">{{$t("newproject")}}</button> 
	</div>
    	</div>
	<div v-if="project.startDate" class="m-5 text-center text-muted">
		<h5>{{$t("yourproject", { date: project.expectedFinishDate})}}</h5>
		<highcharts :dats="alldates" :rdats="realdates" :cats="allcategories" :title="$t('fullproject')"/>
		<br/>
		<highcharts :dats="dayexpecteddates" :rdats="daydates" :cats="daycategories" :title="$t('lastmonthproject')"/>
		<br/>
	</div>
	<div v-else>
		<div class="alert alert-dark text-center m-5" role="alert">
			{{$t("noprojects")}}
		</div>
		<div class="row">
			<div class="col-3">
				<img src="/images/balance.jpg" class="balance">
			</div>
			<div class="col-9">
				<div class="alert alert-dark text-center m-5" role="alert">
					{{$t("description")}}
				</div>
			</div>
		</div>
	</div>
    </div>
</template>

<script>
import Chart from "./Graph";
    export default {
	components: {
	    highcharts: Chart
  	},
	data() {
            return {
		userProfile: user,
		project: {},
		imc: 0,
		minPoids: 0,
		maxPoids: 0,
		alldates: [0],
		allcategories: [""],
		realdates: [0],
		daydates: [],
		dayexpecteddates: [],
		daycategories: [],
		changeProject: false
	   }
       },
       methods: {
            async addWeight(event){
		event.preventDefault();
		this.userProfile.weight = parseFloat(this.userProfile.weight);
		var request = await this.$http.post("/projects/add/"+this.userProfile.id, this.userProfile);
		this.userProfile = request.data;
		user = request.data;
		this.countImc();
            },
	    async addDesiredWeight(event) {
		event.preventDefault();
		this.project.startWeight = this.userProfile.weight;
		this.project.author = this.userProfile;
		var request = await this.$http.post("/projects", this.project);
		this.project = request.data;
		this.changeProject = false;

	    },
	    changeProjectFunction(event) {
		event.preventDefault();
		this.changeProject = true;
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
		console.log("result", result);
		var userInfo = await this.$http.get("user/"+this.userProfile.id);
		this.userProfile = userInfo.data;
		this.project = result.data;
		if(!this.project){
			this.project={};
		}
		else {
			this.alldates = this.project.expectedWeekWeightMesures.map(m=>m.weight);
			this.realdates = this.project.weekWeightMesures.map(m=>m.weight);
			this.allcategories = this.project.expectedWeekWeightMesures.map(m=>m.mesureDate);
			this.daydates = this.project.weightMesures.map(m=>m.weight),
			this.dayexpecteddates = this.project.expectedDayWeightMesures.map(m=>m.weight),
			this.daycategories = this.project.expectedDayWeightMesures.map(m=>m.mesureDate)
		}
		this.countImc();
	}
    }
</script>

<style>
</style>