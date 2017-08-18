<#-- 基地面积 -->
<#setting number_format="#">
{
    tooltip: {
        trigger: "axis",
        axisPointer: {
            type: "shadow"
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
        data: [
    	<#list datas as d>
    		"${d.name}"<#if d_has_next>,</#if>
    	</#list>
        ]
    },
    grid: {
        left: "3%",
        right: "4%",
        bottom: "16%",
        containLabel: true
    },
    yAxis: {
        type: "value"
    },
    xAxis: {
        type: "category",
        data: [
    	<#list names as n>
    		"${n}"<#if n_has_next>,</#if>
    	</#list>
        ]
    },
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
            data:[
            <#list d.datas as dd>
	    		${dd}<#if dd_has_next>,</#if>
	    	</#list>
            ]
        }<#if d_has_next>,</#if>
    	</#list>
    ]
}