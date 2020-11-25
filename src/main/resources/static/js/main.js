/*
0:00 = 45
01:00 = 40
05:00 = 20
09:00 = 0
13:00 = -20
17:00 = -40
21:00 = -60
24:00 = -75
 */


var today = new Date();
var defaultoffset = 45;
var offsetperhour = 5.3;
var offset = defaultoffset - (offsetperhour * today.getHours())
document.getElementById("day1").style["margin-left"] = offset + "px";




var ms = new Date().getTime() - 172800000;
var options = {
    series: [],
    chart: {
        id: 'area-datetime',
        type: 'area',
        height: 200,
        zoom: {
            autoScaleYaxis: true
        }
    },
    stroke: {
        width: [3],
        curve: 'smooth',
        dashArray: [0.01]
    },
    dataLabels: {
        enabled: false
    },

    xaxis: {
        type: 'datetime',
        min: ms,
        tickAmount: 6,
        labels: {
            show: false,
        }
    },
    yaxis: {
        labels: {
            show: true,
            formatter: function (value) {
                return value + "°";
            },
            style: {
                fontSize: '20px',
                fontFamily: 'Cambay, sans-serif'

            }
        },
    },
    tooltip: {
        x: {
            format: 'dd MMM yyyy'
        }
    },
    fill: {
        color: '#000',
        opacity: 1,
    },
    theme: {
        monochrome: {
            enabled: true,
            color: '#000',
            shadeTo: 'light',
            shadeIntensity: 0.9
        }
    },
};
var chart = new ApexCharts(document.querySelector("#chart"), options);
chart.render();



$.getJSON('api/temperature', function(response) {
    chart.updateSeries([{
        name: 'Temperature',
        data: response
    }])
});