<template>
  <div>
    <highcharts
      :options="chartOptions"
      ref="lineCharts"    
    ></highcharts>
  </div>
</template>

<script>
import { Chart } from "highcharts-vue";
import Highcharts from "highcharts";
import exportingInit from "highcharts/modules/exporting";
import stockInit from "highcharts/modules/stock";

stockInit(Highcharts);
exportingInit(Highcharts);

export default {
  props: {
    cats: {
      type: Array
    },
    dats: {
      type: Array
    },
    rdats: {
      type: Array
    },
    title: {
      type: String
    }
  },

  data() {
    return {
      chartOptions: {
        chart: {
          type: "spline",
          title: "Hassaan"
        },
        title: {
          text: this.title
        },
	yAxis: {
	  title: {
            text: this.$t('weightkg')
          },
	},
        xAxis: {
          categories: this.cats
        },
        tooltip: {
          crosshairs: true,
          shared: true
        },
        credits: {
          enabled: false
        },
        plotOptions: {
          spline: {
            marker: {
              radius: 4,
              lineColor: "#666666",
              lineWidth: 1
            }
          }
        },
        series: [
          {
	    name: this.$t('expectedDatas'),
            data: this.dats
          },
	  {
	    name: this.$t('realDatas'),
	    data: this.rdats
	  }
        ]
      }
    };
  }
};
</script>
