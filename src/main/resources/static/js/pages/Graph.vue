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
    }
  },

  data() {
    return {
      chartOptions: {
        chart: {
          type: "spline",
          title: "Hassaan"
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
            data: this.dats
          }
        ]
      }
    };
  }
};
</script>
