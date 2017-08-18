<#setting number_format="#.0000">
{
    tooltip : {
        trigger: "item",
        formatter: "{a} <br/>{b} : {c} 亩 ({d}%)"
    },
    legend: {
        orient: "vertical",
        left: "left",
        data:[
    	<#list datas as d>
    		"${d.cLv}"<#if d_has_next>,</#if>
    	</#list>
        ]
    },
    series : [
        {
            name: "污染面积",
            type: "pie",
            radius : "55%",
            center: ["50%", "60%"],
            data:[
            <#list datas as d>
	    		{name:"${d.cLv}", value:${d.wrArea}}<#if d_has_next>,</#if>
	    	</#list>
            ]
        }
    ]
}