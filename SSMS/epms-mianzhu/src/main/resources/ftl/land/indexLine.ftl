{
    tooltip : {
        trigger: "axis",
        axisPointer : {
            type : "shadow"
        }
    },
    grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
    },
    xAxis : [
        {
            type : "category",
            data : [
            	<#list datas as d>
            	"${d.name}"<#if d_has_next>,</#if>
            	</#list>
            ],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : "value"
        }
    ],
    series : [
        {
            type:"bar",
            barWidth: "60%",
            data:[
            	<#list datas as d>
            	${d.data}<#if d_has_next>,</#if>
            	</#list>
            ]
        }
    ]
}