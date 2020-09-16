<template>
    <div class="container-fluid-lg text-center">
	<h3>{{$t("itsademoversion")}}</h3>
	<div class="row">
	<form class="col-4 text-center">
 	 <div class="form-group">
	    <label for="actualWeight"><h4>{{$t("height")}}</h4></label>
	    <input type="number" v-model="demoProject.height" :class="emptyHeight?'form-control border-danger':'form-control'" id="actualWeight" placeholder="Poids actuel">
	    <span v-if="emptyWeight" class="text-danger">{{$t("noheight")}}</span>
	  </div>
	  <div class="form-group">
	    <label for="actualWeight"><h4>{{$t("currentweight")}}</h4></label>
	    <input type="number" v-model="demoProject.weight" :class="emptyWeight?'form-control border-danger':'form-control'" id="actualWeight" placeholder="Poids actuel">
	    <span v-if="emptyWeight" class="text-danger">{{$t("noweight")}}</span>
	  </div>
	  <div class="form-group">
	    <label for="desiredWeight"><h4>{{$t("weightgoal")}}</h4></label>
	    <input type="number" v-model="demoProject.desiredWeight" class="form-control" id="desiredWeight" placeholder="Poids souhaité">
	  </div>
	  <button type="submit" class="btn btn-success" @click.stop="countDemo">{{$t("submit")}}</button>
	</form>
	<div v-if="demoProject.weight!=0 && demoProject.height != 0" class="col-8 text-center">
		<div :class="color">
			<h5>{{$t("yourimc")}} : {{imc}}</h5>
			<h5><em>{{$t("comments"+comments)}}</em></h5>
		</div>
		<p class="bg-light mt-3">{{$t("bestweightinterval", { min: minPoids, max: maxPoids})}}</p>
			<h6>{{$t("weightgoal")}} : {{demoProject.desiredWeight}} kg</h6>
	</div>
	<div v-else class="col-8 text-center">
		<div class="alert alert-dark text-center mr-3" role="alert">
			{{$t("miniversion")}}<br/>
			{{$t("fullaccess")}}
		</div>
	</div>
    	</div>
	<div v-if="generated" class="m-5 text-center text-muted">
		<h5>{{$t("yourproject", { date: this.demoProject.expectedFinishDate})}}</h5>
		<highcharts :dats="demoProject.alldates" rdats="" :cats="demoProject.allcategories" :title="$t('fullproject')"/>
		<br/>
	</div>
	<div v-else>
				<img src="/images/demo.png" class="demo">
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
		demoProject: {
			minPoids: 0,
			maxPoids: 0,
			alldates: [0],
			allcategories: [""],
			expectedFinishDate: "",
			height: 0,
			weight: 0,
			desiredWeight: 0
		},
		emptyHeight: false,
		emptyWeight: false,
		generated: false
	   }
       },
  	computed: {
    		imc: function () {
      			return (this.demoProject.weight/((this.demoProject.height/100)*(this.demoProject.height/100))).toFixed(2);
    		},
		comments: function () {
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
		color: function() {
			if (this.imc<25 && this.imc>=18.5) {
				return "text-success";
			}
			else if(this.imc<16.5 || this.imc>=30) {
				return "text-danger";
			}
			else {
				return "text-warning";
			}
	   	},
		minPoids: function() {
			return (((this.demoProject.height/100)*(this.demoProject.height/100))*18.5).toFixed(2);
		},
		maxPoids: function() {
			return (((this.demoProject.height/100)*(this.demoProject.height/100))*25).toFixed(2);
		}
       },
       methods: {
	    async countDemo(event) {
		event.preventDefault();
		var result = await this.$http.post("/demo", this.demoProject);
		this.demoProject = result.data;
		this.generated=true;
	    }
        }
    }
</script>

<style>
</style>