<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" isCallback="1" />
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<script src="${root}/js/business.validate.js" type="text/javascript"></script>
<script src="${root}/js/SuperMap.Include.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);
    </script>
</c:if>
<div class="page-header">
    <h1>
       土壤修复项目档案
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增档案
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/repair/project/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
             <table class="width-100">
              <tr class="td_h">
                <td style="position: relative">
                    <!--        地图显示区域 -->
                    <div id="map" class="col-xs-12"
                         style="position:absolute;top:0;height:750px;">
                        <div id="mapNavigationBox"></div>
                        <div id="mapViewSwitcher"></div>
                        <div id="mapZoomSlider"></div>
                    </div>
                    <!--        地图显示区域 -->
                    <!-- 地图统一样式引入 -->
                    <tags:mapheader rotateImage="true"></tags:mapheader>
           </div>
                </td>
                <td style="width: 430px;padding-left:20px;">
                    <div style="border:none;min-height:720px;">
                        <div class="profile-user-info profile-user-info-striped width-100">

                        <input type="hidden" id="blockIds" name="blockIds" />
                        <div class="profile-info-row">
                            <div class="profile-info-name">项目名称</div>
                            <div class="profile-info-value">
                                <input type="text" id="name" name="name" maxlength="40"
                                       style="min-width:120px; width: 200px;" class="input-large required"/>
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">开始时间</div>
                            <div class="profile-info-value">
                                <input id="beginTime" name="beginTime" type="text"
                                       class="input-large required" readonly="readonly" style="width: 200px;"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
                                <span style="color: red">*</span>
                            </div> </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">结束时间</div>
                            <div class="profile-info-value">
                                <input id="endTime" name="endTime" type="text"
                                       class="input-large" readonly="readonly" style="width: 200px;"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">修复范围</div>
                            <div class="profile-info-value">
                                <input type="text" id="regionName" readonly="readonly"
                                       style="min-width:120px; width: 200px;" class="input-large required" />
                                <input type="hidden" id="regionId" name="regionId" />
                                <span style="color: red">*</span>
                            </div> </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">修复技术</div>
                            <div class="profile-info-value">
                                <select id="technology" name="technology" style="min-width:120px; width: 200px;" class="required">
                                    <tags:diccache typeCode="LAND_REPAIR_TECHNOLOGY" />
                                </select>
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">修复面积（亩）</div>
                            <div class="profile-info-value">
                                <input type="text" id="area" name="area" maxlength="10"
                                       style="min-width:120px; width: 200px;" class="input-large number required"/>
                                <span style="color: red">*</span>
                            </div> </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">任务及目标</div>
                            <div class="profile-info-value">
                                <input type="text" id="missionTarget" name="missionTarget" maxlength="50"
                                       style="min-width:120px; width: 200px;" class="input-large"/>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">修复效果</div>
                            <div class="profile-info-value">
                                <input type="text" id="effect" name="effect" maxlength="50"
                                       style="min-width:120px; width: 200px;" class="input-large"/>
                            </div> </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">修复进度</div>
                            <div class="profile-info-value">
                                <select id="schedule" name="schedule" style="min-width:120px; width: 200px;" class="required">
                                    <tags:diccache typeCode="LAND_REPAIR_SCHEDULE" />
                                </select>
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">修复单位</div>
                            <div class="profile-info-value">
                                <input type="text" id="dept" name="dept" maxlength="30"
                                       style="min-width:120px; width: 200px;" class="input-large"/>
                            </div> </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">验收时间</div>
                            <div class="profile-info-value">
                                <input id="acceptionTime" name="acceptionTime" type="text"
                                       class="input-large" readonly="readonly" style="width: 200px;"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
                            </div>
                        </div>
                            <div class="profile-info-row" >
                                   
                            </div>

                            <div class="profile-info-row" >
                                <div class="profile-info-name">修复说明</div>
                                <div class="profile-info-value">
                                    <textarea rows="5" style="min-width:120px; width: 200px;" id="explain" name="explain"
                                              onkeyup="countChars(this, 'textExplain')"></textarea>
                                    <br/>
                                    <span style="margin-right: 10px;">最大字符数：800</span>
                                    <span id="textExplain" style="color: blue;">当前字符数：0</span>
                                </div>
                            </div>

                            <div class="profile-info-row" >
                                <div class="profile-info-name">项目附件</div>
                                <div class="profile-info-value">
                                    <tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png.pdf" maxFileSize="50" />
                                </div>
                            </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-2 col-md-10">
                            <button class="btn btn-primary" type="button" onclick="checkForm()">
                                <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                            </button>
                            <button class="btn" type="reset" onclick="window.location.href='${root}/repair/project/list/${menuid }/?page=${page }&isgetsession=1'">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                            </button>
                        </div>
                    </div> </div>
                </td>
            </tr>
        </table>
    </form>
</div>

<script>
	var v;
	var isSelect=false;
	  var landIds=[];
	  var areas=[];
	  var _map;
	  var i=0,j=0;
	 var areasSum=0;
    $(document).ready(function () {
    	 $("#area").val("");
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate({
        	rules: {
        		explain: {
    				textLength: 800
    			}
    		}
        });
        $("#name").focus();
        initMap("map");
        // 初始化地图
        function initMap(mapDiv) {
            if (typeof window["mapTag"] == "undefined")return;
            mapTag().init(mapDiv, function (map) {
            	_map=map;
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                MapFactory.Require(["ItmsMap/UserLayers/CustomLayers/Land*","MapFactory/LayerManager","MapFactory/GraphicManager","MapFactory/SymbolConfig*"],function(Land,LayerManager,GraphicManager,SymbolConfig){
                	Land().drawRegions();
                  	  var landLyrPologn=LayerManager("landLyrPologn");
                  	  //graphicHighlightLayer
                  	   landLyrPologn.addOnClickEvent(function(obj){
                  		  var n=landIds.length;
                  		if(landIds.length>0){
                  			for(j=0;j<landIds.length;j++){
                  				if(landIds[j]===obj.graphic.attributes.ids || landIds[j]===obj.graphic.attributes.id){
                  					var ids=obj.graphic.attributes.id?obj.graphic.attributes.id:obj.graphic.attributes.ids;
                  					 GraphicManager(obj.graphic.id).remove();
                  					 removeByValue(landIds,ids);
                  					 break;
                  				}
                  			}
                  			if(n==landIds.length){
                  				var ids=obj.graphic.attributes.id?obj.graphic.attributes.id:obj.graphic.attributes.ids;
              					landIds.push(ids);
              				    GraphicManager({geo:obj.graphic.geo,symbol:{outLineWidth:1,outLineColor : "#6666aa",outLineStyle : "dashed",backgroundOpacity:0},attributes:{ids:ids}}).highlight("landLyrPologn");
              				}
                  		}else {
                  			var ids=obj.graphic.attributes.id?obj.graphic.attributes.id:obj.graphic.attributes.ids;
                  			landIds.push(ids);
                  			GraphicManager({geo:obj.graphic.geo,symbol:{outLineWidth:1,outLineColor : "#6666aa",outLineStyle : "dashed",backgroundOpacity:0},attributes:{ids:ids}}).highlight("landLyrPologn");
                  		 }
                  		  allResultLand(landIds);
                  	     $("#blockIds").val(landIds.join());
                  	  });
                })
            });
        }
    });
    
    
    function allResultLand(selectedLand){
    	 areasSum=0;
    	 $("#area").val("");
    	if(selectedLand.length>0){
        	MapFactory.XHR.Post(path
    				+ "/map/all/landblock/",
    		function(target) {
        	    for(var j=0;j<target.length;j++){
        	    	for(var k=0;k<selectedLand.length;k++){
        	    		  if(target[j].id===selectedLand[k]){
        	    			  (function(target,j){
        	    				  areasSum=areasSum+target[j].area;
            	    			  $("#area").val(makeDecimal(areasSum, 4));
        	    			  })(target,j);
      	    		   }
        	    	}
        	    }
    	  }
    	)}else{
    		areasSum=0;
    		$("#area").val("");
    	}
    }
    
    function removeByValue(arr, val) {
    	  for(var i=0; i<arr.length; i++) {
    	    if(arr[i] == val) {
    	      arr.splice(i, 1);
    	      break;
    	    }
    	  }
    	}
    	
    
    function checkForm() {
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    
    function regionTreeCB() {
    	$.get(
       		"${root}/bs/region/getLocation/"+$("#regionId").val()+"/",
       		function(data) {
       			if(data){
       				_map.centerAt(data.longitude,data.latitude,4);	
       			}
       			
       		}
       	);
    }
    
</script>


<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>