var unit_key;
var time;
var unit_name;
var i18Frame = parent.parent.window.frames["i18nframe"];
$(function(){
	unit_key = parent.getUnit_key();
	comp_key = $("#comp_key").val();
	time = getYear();
	parent.layer.load(2,{shade:0.3});
	$.ajax({
		type: "POST",
		url: "../../../../unit/getUnitByKey",
		data: {
			"unit_key":unit_key
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			unit_name = jo0.unit_name;
			getCchart();
			getPchart();
			var startYear=time-5;
			var str = "<div class='model-select-box' id='year' style='width: 150px;'>";
			str +="<div class='model-select-text' id='year_select' data-value='"+time+"'>"+time+"</div>"+ 
			"<ul class='model-select-option'>";
			for(var i=time;i>=startYear;i--){
				if(i==time){
					str +="<li data-option='"+time+"' class='seleced' title='"+time+"'>"+time+"</li>";
				}else{
					str +="<li data-option='"+i+"' title='"+i+"'>"+i+"</li>";
				}
			}
			str +="</ul></div>";
			$(".year").empty();
			$(".year").append(str);
			selectModel();
			Year_Change();
		}
	});
});

function Year_Change(){
	var $box = $('#year');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush();
	});
}

function flush(){
	parent.layer.load(2,{shade:0.3});
	time = $("#year_select").attr("data-value");
	if(""==time){
		layer.closeAll('loading');
		layer.tips(i18Frame.getchoose_year(),'.year',{
			tips:3,time:2000
		});
	}else{
		getCchart();
		getPchart();
	}
}

function getCchart(){
	$.ajax({
		type: "POST",
		url: "../../../../chart/getCchart",
		data: {
			"unit_key":unit_key,
			"time": time,
			"comp_key":comp_key
		},
		success:function(returnedData){
			var data = eval("("+returnedData+")");
			$('#ccontainer').highcharts({
				credits: {
		            enabled: false
		        },
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: unit_name+i18Frame.getopen_total()+"("+time+")"
		        },
		        xAxis: {
		            categories: [
		                i18Frame.getJanuary(),
		                i18Frame.getFebruary(),
		                i18Frame.getMarch(),
		                i18Frame.getApril(),
		                i18Frame.getMay(),
		                i18Frame.getJune(),
		                i18Frame.getJuly(),
		                i18Frame.getAugust(),
		                i18Frame.getSeptember(),
		                i18Frame.getOctober(),
		                i18Frame.getNovember(),
		                i18Frame.getDecember()
		            ]
		        },
		        yAxis: {
		            min: 0,
		            tickInterval: 2,
		            title: {
		                text: i18Frame.getopennum()
		            }
		        },
		        tooltip: {
		            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		            pointFormat: '<tr><td style="color:black;padding:0">'+i18Frame.getopennum()+': </td>' +
		                '<td style="padding:0"><b>{point.y}</b></td></tr>',
		            footerFormat: '</table>',
		            shared: true,
		            useHTML: true
		        },
		        plotOptions: {
		            column: {
		                pointPadding: 0.2,
		                borderWidth: 0,
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '{point.y}'
		                }
		            }
		        },
		        series: [{
		            name: unit_name,
		            data: data
		        }]
		    });
			setTimeout(function(){
				parent.layer.closeAll('loading');
			},300);
		}
	});
}

function getPchart(){
	$.ajax({
		type: "POST",
		url: "../../../../chart/getPchart",
		data: {
			"unit_key":unit_key,
			"time": time,
			"comp_key":comp_key
		},
		success:function(returnedData){
			var data = eval("("+returnedData+")");
			var pdata = [
		                   {
		                       name: unit_name,
		                       y: data[0],
		                       sliced: true,
		                       selected: true
		                   },
		                   [i18Frame.getother_unit(),  data[1]]
		               ];
			$('#pcontainer').highcharts({
				credits: {
		            enabled: false
		        },
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		            text: unit_name+i18Frame.getopen_percentage()+"("+time+")"
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.2f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: i18Frame.getpercentage(),
		            data: pdata
		        }]
		    });
		}
	});
}

function getYear(){
	var date = new Date();
	var currentdate = date.getFullYear();
	return currentdate;
}