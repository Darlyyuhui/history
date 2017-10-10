<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .mian_white{background: white;color:#000;}
    .mian_blue{background: #0a3a5c}
    .mian_lh{line-height: 30px;}
    .mian_none{display: none}
    .divContent tr{height: 25px;}
    .divContent .groundblock{height: 140px}
    .mian_tabs{width:340px;padding: 0px;border:5px solid #0a3a5c;color:#fff;font-size: 12px;}
    .mian_tag div{cursor: pointer}
    a.white:hover{background: #fff;color: #000 !important;}
    .region-polluteLevel{font-weight: bold;}
</style>

<script type="text/javascript">
   
   function cydCountPieChart(countObj){
	   var option = {
				title : {
					text : '',
					subtext : '',
					x : 'center',
					y : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				
				toolbox : {
					show : false,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'pie', 'funnel' ],
							option : {
								funnel : {
									x : '25%',
									width : '50%',
									funnelAlign : 'left'
								}
							}
						},
						restore : {
							show : false
						},
						saveAsImage : {
							show : false
						}
					}
				},
				calculable : true,
				series : [ {
					name : '普查采样点',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '50%' ],
					
					data : [ {
						value : countObj.undergroundWaterNum,
						name : '地下水监\r\n测点'
					}, {
						value : countObj.surfaceWaterNum,
						name : '地表灌溉水\r\n监测点'
					}, {
						value : countObj.dirtNum,
						name : '底泥监测点'
					}, {
						value : countObj.airNum,
						name : '大气沉降\r\n采样点'
					}, {
						value : countObj.backNum,
						name : '背景土壤\r\n监测点'
					} , {
						value : countObj.landNum,
						name : '农田土壤\r\n监测点'
					}, {
						value : countObj.farmNum,
						name : '农作物监\r\n测点'
					}, {
						value : countObj.manureNum,
						name : '肥料采集点'
					}]
				} ],
				color : [ '#669966', '#104E8B', '#FFD700', '#FF7F00', '#EE0000',"#FF1493","#9A32CD" ,"#00EEEE"]
		};
		var myChart = echarts.init(document.getElementById('cydCountPieChart'));
		myChart.setOption(option);
   }

   function loadCydCount(){
	   MapFactory
		.Require(
				["ItmsMap/UserLayers/DataController*"],
					function(DataController) {
					var dataCon=DataController();
					var regionId=dataCon.getRegionId();
	                var countObj=dataCon.getCountData()[regionId];
	                cydCountPieChart(countObj);
				});
	  
   }

function loadEchartsPie(){
var option = {
		    title : {
		        text: '',
		        subtext: '',
		        x:'center',
		        y:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'left',
		                        max: 1
		                    }
		                }
		            },
		            restore : {show: false},
		            saveAsImage : {show: false}
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'污染程度',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            data:[
		                {value:${pollute.polluteNull}, name:'无污染'},
		                {value:${pollute.polluteLittle}, name:'轻微污染'},
		                {value:${pollute.polluteSlight}, name:'轻度污染'},
		                {value:${pollute.polluteModerate}, name:'中度污染'},
		                {value:${pollute.polluteSevere}, name:'重度污染'}
		            ]
		        }
		    ],
		    color:['#669966','#104E8B', '#FFD700','#FF7F00','#EE0000'] 
		};
	   var myChart = echarts.init(document.getElementById('pollutePieChart'));
       myChart.setOption(option);
}

  function  landClickHandle(geoId){
	//请求统计信息
		MapFactory.XHR.Post(
				path+ "/openmap/query/q/land/?geometry=&geometryPrecision=&spatialRelationship=&where=code='"+geoId +"'",
				function(list) {
					 MapFactory.Require(
								["ItmsMap/UserLayers/CustomLayers/TempLand*","MapFactory/MapManager","ItmsMap/MapConfig","MapFactory/LayerManager"],function(tempLand,MapManager,MapConfig,LayerManager){
									//底图切换
									var mapType=$(".map-toolbox_item span").html();
									if(mapType=="电子地图"){
										$(".map-toolbox_item span").eq(0).html("卫星地图");
										var _mapHandler = MapFactory._MapManagerResource[MapFactory.Engine];
										var _baseLayer=_mapHandler.getLayer(MapConfig.layers.baseMap.id);
										var _imageLayer=_mapHandler.getLayer(MapConfig.layers.imageMap.id);
										
									     _mapHandler.setBaseLayer(_imageLayer);
							             _imageLayer.setVisibility(true);
							             _baseLayer.setVisibility(false);
									}
									
									tempLand.clearLand();
									if(list && list.length>0)
									tempLand.drawLand(list[0]);
								});
				});
  }
</script>

<div class="mian_tabs mian_blue">
       <!--   <div class="title clearfix mian_blue mian_lh">
            <div class="titlie_l pull-left main_padding">富士镇</div>
            <div class="none pull-right main_padding"><a href="javascript:void(0)" onclick="infoHide(this)" class="white" style="padding:0 10px 3px">x</a></div>
        </div>-->
        <div class="mian_content">
            <div class="mian_tag clearfix text-center mian_blue mian_lh">
                <div class="width-25 pull-left mian_white" onclick="tabClickChange(this,0)">污染地块</div>
                <div class="width-25 pull-left" onclick="tabClickChange(this,1)">污染程度</div>
                <div class="width-25 pull-left" onclick="tabClickChange(this,2)">指标情况</div>
                <div class="width-25 pull-left" onclick="tabClickChange(this,3)">普查分析</div>
            </div>
            <div class="mian_major mian_white" style="padding-top:5px;">
                <div class="divContent">
                    <table class="width-100 text-center mian_white groundblock">
                        <thead class="text-center">
                        <tr>
                            <th class="text-center">地块名称</th>
                            <th class="text-center">面积</th>
                            <th class="text-center">土壤类型</th>
                        </tr>
                        </thead>
                        <tbody>
                       <c:forEach items="${lands}" var="land" varStatus="m">
							<tr>
								<td style="cursor:pointer;" onClick="landClickHandle('${land.id}')">${land.name}</td>
								<td>${land.area }</td>
								<td><tags:xiangxuncache keyName="LANDTYPE_NAME" id="${land.soilType }"/></td>
							</tr>
						</c:forEach>
						<c:if test="${lands.size() <5}">
							<c:forEach begin="0" end="${5-lands.size()}" step="1">
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</c:forEach>
						</c:if>
                        </tbody>
                    </table>
                </div>
                <div class="mian_none divContent" style="height: 175px" id="pollutePieChart">
                   
                </div>
                <div class="mian_none divContent">
                    <table class="width-100 text-center mian_white">
                        <thead class="text-center">
                        <tr>
                            <th class="text-center">指标项</th>
                            <th class="text-center">最小值</th>
                            <th class="text-center">平均值</th>
                            <th class="text-center">最大值</th>
                            <th class="text-center">标准差</th>
                            <th class="text-center">变异系数</th>
                            <th class="text-center">超标率</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="region-polluteLevel">ph</td>
                            <td>${pollute.phMin}</td>
                            <td>${pollute.phAvg}</td>
                            <td>${pollute.phMax}</td>
                            <td>${pollute.phSD}</td>
                            <td>${pollute.phCoefficient}</td>
                            <td  rowspan=3>${pollute.exceedRate}</td>
                        </tr>
                         <tr>
                            <td class="region-polluteLevel">镉</td>
                            <td>${pollute.crMin}</td>
                            <td>${pollute.crAvg}</td>
                            <td>${pollute.crMax}</td>
                            <td>${pollute.crSD}</td>
                            <td>${pollute.crCoefficient}</td>
                        </tr>
                         <tr>
                            <td class="region-polluteLevel">有效态镉</td>
                            <td>${pollute.crValidMin}</td>
                            <td>${pollute.crValidAvg}</td>
                            <td>${pollute.crValidMax}</td>
                            <td>${pollute.crValidSD}</td>
                            <td>${pollute.crValidCoefficient}</td>
                        </tr>
                        <tr></tr>
                         <tr></tr>
                          <tr></tr>
                        </tbody>
                    </table>
                </div>
                <div class="mian_none divContent" style="height: 175px" id="cydCountPieChart">
                   
                </div>
            </div>
        </div>
    </div>
 <script type="text/javascript">
      
       function tabClickChange(b,index){
 
    	   var tags = $(".mian_tag div");
    	   tags.removeClass("mian_white");
	        $(b).addClass("mian_white");
	        //切换内容
	        $(".mian_major .divContent").hide();
	        $(".mian_major .divContent").eq(index).show();
	        
	        if(index==1){
		        loadEchartsPie();
		    }
	        
	        if(index==3){
	        	loadCydCount();
	        }
	        
       }
</script>