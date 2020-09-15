<template>
    <div class="container-fluid mt-3">
	<div class="row">
	<form class="col-3 text-center">
 	 <div class="form-group">
	    <label for="actualWeight"><h4>Votre poids actuel</h4></label>
	    <input type="number" v-model="project.currentWeight" :class="emptyWeight?'form-control border-danger':'form-control'" id="actualWeight" placeholder="Poids actuel">
	    <span v-if="emptyWeight" class="text-danger">{{$t("noweight")}}</span>
	  </div>
	  <button type="submit" class="btn btn-success" @click.stop="addWeight">{{$t("reload")}}</button>
	</form>
	<div class="col-6 text-center">
		<div v-if="project.currentWeight && project.currentWeight!=0" :class="getColor()">
			<h5>{{$t("yourimc")}} : {{getImc()}}</h5>
			<h5><em>{{$t("comments"+comments ())}}</em></h5>
		</div>
		<p class="bg-light mt-3">{{$t("bestweightinterval", { min: minPoids, max: maxPoids})}}</p>
			<h6 v-if="project.desiredWeight">{{$t("weightgoal")}} : {{project.desiredWeight}} kg</h6>
			<h6 v-else>{{$t("noweightgoal")}}</h6>
	</div>
	<form v-if="!project.startDate||changeProject" class="col-3 text-center">
 	  <div class="form-group">
	    <label for="desiredWeight"><h4>{{$t("weightgoal")}}</h4></label>
	    <input type="number" v-model="project.desiredWeight" class="form-control" id="desiredWeight" placeholder="Poids souhaité">
	  </div>
	  <button type="submit" class="btn btn-success" @click.stop="addDesiredWeight">{{$t("submit")}}</button>
	</form>
	<div v-else class="col-3 text-center">
		<h4>{{$t("changeproject")}}</h4>
		<button class="btn btn-success" @click.stop="changeProjectFunction">{{$t("newproject")}}</button> 
	</div>
    	</div>
	<div v-if="project.startDate" class="m-5 text-center text-muted">
		<h5>{{$t("yourproject", { date: project.expectedFinishDate})}}</h5>
		<div :class="'alert alert-'+getMessageType()" role="alert">
			{{$t('message'+getMessageType()+getAdvice())}}
		</div>
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
		userProfile: {},
		project: {},
		minPoids: 0,
		maxPoids: 0,
		alldates: [0],
		allcategories: [""],
		realdates: [0],
		daydates: [],
		dayexpecteddates: [],
		daycategories: [],
		changeProject: false,
		emptyWeight: false
	   }
       },
       methods: {
            async addWeight(event){
		event.preventDefault();
		this.project.currentWeight = parseFloat(this.userProfile.weight);
		if(this.project.currentWeight != 0) {
			this.emptyWeight = false;
			var request = await this.$http.post("/projects/add/"+this.userProfile.id, this.project);
			this.project.currentWeight = request.data;
			user = request.data;
			this.countImc();
			location.reload();
		}
            },
	    async addDesiredWeight(event) {
		event.preventDefault();
		if(!this.project.currentWeight || this.project.currentWeight == 0) {
			this.emptyWeight = true;
		}
		else {
			this.project.startWeight = this.project.currentWeight;
			this.project.author = this.userProfile;
			var request = await this.$http.post("/projects", this.project);
			this.project = request.data;
			this.changeProject = false;
			location.reload();
		}

	    },
	    changeProjectFunction(event) {
		event.preventDefault();
		this.changeProject = true;
	    },
	    comments () {
		if (this.getImc()<16.5) {
			return "1";
		}
		else if(this.getImc()<18.5) {
			return "2";
		}
		else if(this.getImc()<=25) {
			return "3";
		}
		else if(this.getImc()<30) {
			return "4";
		}
		else if(this.getImc()<35) {
			return "5";
		}
		else if(this.getImc()<40) {
			return "6";
		}
		else {
			return "7";
		}
	    },
	    countImc() {
		this.minPoids = (((this.userProfile.height/100)*(this.userProfile.height/100))*18.5).toFixed(2);
		this.maxPoids = (((this.userProfile.height/100)*(this.userProfile.height/100))*25).toFixed(2);
	    },
	    getColor() {
		if (this.getImc()<25 && this.getImc()>=18.5) {
			return "text-success";
		}
		else if(this.getImc()<16.5 || this.getImc()>=30) {
			return "text-danger";
		}
		else {
			return "text-warning";
		}
	    },
	    getMessageType() {
		var expectedToday = this.project.expectedDayWeightMesures[this.project.expectedDayWeightMesures.length-1];
		var difference = this.project.currentWeight - expectedToday.weight;
		if(difference<=1.5) {
			return "success";
		}
		else if(difference<=2.5) {
			return "warning";
		}
		else {
			return "danger";
		}
	    },
	    getAdvice() {
		var expectedToday = this.project.expectedDayWeightMesures[this.project.expectedDayWeightMesures.length-1];
		if((this.project.currentWeight>expectedToday.weight && this.project.startWeight<this.project.desiredWeight) || 
			(this.project.currentWeight<expectedToday.weight && this.project.startWeight>this.project.desiredWeight)) {
			return "samedirection";
		}
		else {
			return "oppositedirection";
		}
	    },
	    graphUpgrade() {
		this.alldates = this.project.expectedWeekWeightMesures.map(m=>m.weight);
		this.realdates = this.project.weekWeightMesures.map(m=>m.weight);
		this.allcategories = this.project.expectedWeekWeightMesures.map(m=>m.mesureDate);
		this.daydates = this.project.weightMesures.map(m=>m.weight),
		this.dayexpecteddates = this.project.expectedDayWeightMesures.map(m=>m.weight),
		this.daycategories = this.project.expectedDayWeightMesures.map(m=>m.mesureDate)
	    },
	    getImc() {
		console.log(this.project);
		return (this.project.currentWeight/((this.userProfile.height/100)*(this.userProfile.height/100))).toFixed(2);
	    }
        },
	async beforeCreate(){
		var result = await this.$http.get("/projects");
		var userInfo = await this.$http.get("user");
		this.userProfile = userInfo.data;
		this.project = result.data;
		this.countImc();
		if(!this.project){
			this.project={};
		}
		else {
			this.graphUpgrade();
		}
	}
    }
</script>

<style>
</style>