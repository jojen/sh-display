var config = {
    type: "line",
    data: {
        labels: [],
        datasets: [{
            label: "Messung",
            backgroundColor: "black",
            borderColor: "black",
            data: [],
            fill: false,
            pointRadius: 0
        }, {
            label: "Vorhersage",
            backgroundColor: "blue",
            borderColor: "blue",
            data: [],
            fill: false,
            pointRadius: 0
        }]
    },
    options: {
        responsive: true,
        legend: {
            display: false
        },
        tooltips: {
            mode: "index",
            intersect: false,
        },
        hover: {
            mode: "nearest",
            intersect: true
        },
        scales: {
            xAxes: [{
                type: "time",
                time: {
                    unit: 'day',
                    displayFormats: {
                        day: 'MMM DD'
                    }
                },
                display: true
            }],
            yAxes: [{
                display: true
            }]
        }
    }
};

fetch("api/temperature")
    .then(response => response.json())
    .then(json => {
        config.data.labels = json.map(row => moment(row.time).toDate());
        config.data.datasets[0].data = json.map(row => row.value);
        console.table(config.data.datasets[0].data);

        fetch("api/temperatureforecast")
            .then(response => response.json())
            .then(json => {
                config.data.labels = json.map(row => moment(row.time).toDate());
                config.data.datasets[1].data = json.map(row => row.value);
                console.table(config.data.datasets[1].data);
                var ctx = document.getElementById("canvas").getContext("2d");
                var chart = new Chart(ctx, config);
            })
    })

