{
    tooltip: {
        trigger: "axis",
        axisPointer: {
            type: "cross",
            crossStyle: {
                color: "#999"
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: true},
            magicType: {show: true, type: ["line", "bar"]},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:["样品数","占比(%)"]
    },
    xAxis: [
        {
            type: "category",
            data: [
            <#list xNames as n>
	    		"${n}"<#if n_has_next>,</#if>
	    	</#list>
            ],
            axisPointer: {
                type: "shadow"
            }
        }
    ],
    yAxis: [
        {
            type: "value",
			splitLine: {
				show: false
			}
        },
        {
            type: "value",
            min: 0,
            max: 100,
            axisLabel: {
                formatter: "{value} %"
            }
        }
    ],
    series: [
        {
            name:"样品数",
            type:"bar",
            data:[
            <#list datas1 as d>
	    		${d}<#if d_has_next>,</#if>
	    	</#list>
            ]
        },
        {
            name:"占比(%)",
            type:"line",
            yAxisIndex: 1,
            data:[
            <#list datas2 as d>
	    		${d}<#if d_has_next>,</#if>
	    	</#list>
            ]
        }
    ]
}