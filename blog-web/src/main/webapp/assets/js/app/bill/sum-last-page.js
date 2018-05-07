define(function(require, exports, module){

    var $ = require('jquery');
    var echarts = require('echarts');
    require('echarts/chart/bar');
    require('echarts/chart/line');
    require('echarts/chart/pie');
    require('bootstrap');
    require('jquery.layer');
    var List = require('../../util/list.js');

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

    exports.loadSumMaterial = function(){
        var getting = $.get($CONFIG.base_url + "/api/bill/sum/material", {type: 1});
        getting.done(function (data) {
            if(data.totalWeight == 0) return;

            $(".sum-in-site").text(data.sumInSite);
            $(".sum-out-site").text(data.sumOutSite);
            $(".sum-not-out-site").text(data.sumNotOutSite);

            chart.clear();
            chartOption.title.text= '总量：'+data.totalWeight;
            chartOption.yAxis[0].data=[];
            chartOption.yAxis[0].data=data.legends;
            chartOption.series[0].data=[];
            chartOption.series[0].data=data.datas;

            chart.setOption(chartOption);
            chart.refresh();
        })
    }
    exports.loadSumMaterial();

});
