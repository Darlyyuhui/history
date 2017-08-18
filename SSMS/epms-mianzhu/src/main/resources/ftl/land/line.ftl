<#-- 土壤区域分析 -->
<#setting number_format="#.00">
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
        data:[
    	<#list names as n>
    		<#if n??>
            "${n}"<#if n_has_next>,</#if>
            </#if>
    	</#list>
        ]
    },
    xAxis: [
        {
            type: "category",
            data: [
        	<#list regions as r>
        		<#if r??>
            	"${r}"<#if r_has_next>,</#if>
            	</#if>
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
            name: "PH值",
            min: 0,
            max: 14,
            interval: 2,
            axisLabel: {
                formatter: "{value}"
            }
        },
        {
            type: "value",
            name: "镉含量",
            min: 0,
            max: 2,
            axisLabel: {
                formatter: "{value} mg/Kg"
            }
        }
    ],
    dataZoom: [{
        type: "slider",
        start: 0,
        end: 100
    }, {
	    type: "inside",
	    start: 0,
	    end: 100
    }],
    series: [
    	<#list datas as d>
		{
            name:"${d.name}",
            type:"bar",
            <#if d.yAxisIndex??>
            yAxisIndex:"${d.yAxisIndex}",
            </#if>
            data:[
            <#list d.datas as dd>
	    		${dd}<#if dd_has_next>,</#if>
	    	</#list>
            ]
        }<#if d_has_next>,</#if>
    	</#list>
    ]
}