define(function(require, exports, module){

    var $ = require('jquery');
    var template = require('template');
    var echarts = require('echarts');
    require('echarts/chart/bar');
    require('bootstrap');

    var html = template("sum-template");
    $("#sum-today").empty().append(html);
    $('#sum-tabs li:eq(0)').addClass('active');

    // rule charts
    var chart = echarts.init(document.getElementById('chart'),'macarons');
    chart.setTheme('macarons');
    var chartOption = {
        title : {
            text: '收料情况',
            subtext: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['材料进出场统计（吨）']
        },
        toolbox: {
            show : false,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'value',
                boundaryGap : [0, 0.01]
            }
        ],
        yAxis : [
            {
                type : 'category',
                data : ['总量']
            }
        ],
        series : [
            {
                name:'材料进出场统计（吨）',
                type:'bar',
                itemStyle: {
                    normal: {
                        color: 'tomato',
                        barBorderColor: 'tomato',
                        barBorderWidth: 6,
                        barBorderRadius:0,
                        label : {
                            show: true, position: 'right'
                        }
                    }
                },
                data:[0]
            }
        ]
    };
    chart.setOption(chartOption);

    exports.loadSumMaterial = function(type){
        var getting = $.get($CONFIG.base_url + "/api/bill/sum/material", {type: type});
        getting.done(function (data) {
            //if(data.totalWeight == 0) return;

            $(".sum-in-site").text(data.sumInSite);
            $(".sum-out-site").text(data.sumOutSite);
            $(".sum-not-out-site").text(data.sumNotOutSite);

            chart.clear();
            chartOption.title.text= '总量：'+data.totalWeight;

            if(data.totalWeight == 0){
                chartOption.yAxis[0].data=['总量'];
                chartOption.series[0].data=[0];
            }else{
                chartOption.yAxis[0].data=[];
                chartOption.yAxis[0].data=data.legends;
                chartOption.series[0].data=[];
                chartOption.series[0].data=data.datas;
            }

            chart.setOption(chartOption);
            chart.refresh();
        })
    }
    exports.loadSumMaterial(0);

    $('#sum-tabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');

        var type = $(this).attr("data-type");
        exports.loadSumMaterial(type);

    })
});
