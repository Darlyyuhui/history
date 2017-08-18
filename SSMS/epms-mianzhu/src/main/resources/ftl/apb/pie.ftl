<#setting number_format="#">
{
    tooltip : {
        trigger: "item",
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: "vertical",
        left: "left",
        data:[
    	<#list datas as d>
    		"${d.name}"<#if d_has_next>,</#if>
    	</#list>
        ]
    },
    series : [
        {
            type: "pie",
            radius : "55%",
            center: ["50%", "60%"],
            data:[
            <#list datas as d>
	    		{name:"${d.name}", value:${d.data}}<#if d_has_next>,</#if>
	    	</#list>
            ]
        }
    ]
}