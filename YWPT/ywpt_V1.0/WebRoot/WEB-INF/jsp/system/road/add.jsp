<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script type="text/javascript">
	// 地图加载完成事件
	var map;
	function iframeMapLoadCallback(mapAPI) {
		map = mapAPI;
	}
	function gisCheckRoadName(roadname, showQueryResult) {
		if(!map) {
			showQueryResult(null);
			return;
		}
		map.queryRoad(roadname, function(result){
			var dlmcs = [];
			if(result && result.length>0) {
				var flag = false;
				for(var i=0,len=result.length; i<len; i++) {
					var graphic = result[i];
					var dlmc = graphic.attributes.NAME;
					flag = false;
					for(var j in dlmcs) {
						if(dlmcs[j] == dlmc) {
							flag = true;
							break;
						}
					}
					if(!flag)dlmcs.push(dlmc);
				}
			}
			showQueryResult(dlmcs);
		});
	}
</script>
<iframe src="${root}/forward/map/mapTools/iframe/iframe_map/" width="100" height="1" scrolling="no" style="padding:0; margin:0; position: absolute;" frameborder="0"></iframe>
<SCRIPT type="text/javascript">
		<!--


var status = "N";
function roadTypeClick(code){
	$("#coderoadtype").attr("value", code);
}
function checkRoadName(){
  if(status=="Y"){
    $("#loadingdiv").css("display","");
    var roadname = $("#name").val();
    if(roadname==""){
       $("#gisroadnamelist").html("");
       $("#gisroadnamediv").css("display","");
	   $("#loadingdiv").css("display","none");
       return;
    }

    gisCheckRoadName(roadname, function(resultList) {
        var content = "",
        	length = resultList.length;
    	if(resultList.length == 0) {
    		$("#gisroadnamelist").html("<font color='#3A6EA5'>没有匹配到GIS库中的道路,无提示信息,请手动输入</font>");
    		$("#gisroadnamediv").css("display","");
    		$("#loadingdiv").css("display","none");
			return;
		}

		for(var j = 0; j < length; j++) {
			content += "<li><a onclick=\"add_to_input('" + resultList[j] + "')\" title=\"'" + resultList[j] + "'\">" + resultList[j] + "</a></li>";
		}
		$("#gisroadnamelist").html(content);
		$("#gisroadnamediv").css("display","");
		$("#loadingdiv").css("display","none");
    });
  }
}
function add_to_input(roadname){
   $("#name").val(roadname);
   status = "N";
}

// add by kouyunhao 添加验证，阻止非法提交。
function checkForm(){
	var checkFlag = true;
	var info = $("#gisroadnamelist").html();
//	道路录入修改为不强制 GIS库匹配 UPDATE BY YANTAO
//  if(document.getElementById("loadingdiv").style.display == '' || info.indexOf('没有匹配到GIS库中的道路,请重新输入!')>=0){
//		checkFlag = false;
//	}
	var coderoadtype = $("#coderoadtype").val();
	var codeRoadDh = $("#codeRoadDh").val();
	var codeRoadZh = $("#codeRoadZh").val();
	var codeRoadMi = $("#codeRoadMi").val();
	if(coderoadtype != '' && codeRoadDh != '' && codeRoadZh != '' && codeRoadMi != ''){
		var uploadcode = coderoadtype + codeRoadDh + codeRoadZh + codeRoadMi;
		check_selectedval(uploadcode);
		if($("#info_div").html() != ''){
			checkFlag = false;
		}
	}
	return checkFlag;
}

function check_selectedval(value){
	var url="${root}/system/road/uploadcodeExist/"+value+"/";
	$.ajax( {
		type : 'GET',
		url : url,
		async : false,
		dataType : "json",
		success:function(data){
			if(data.message != ''){
				$("#info_div").css("display", "");
				$("#info_div").html("").html('<font color="red">'+data.message+'</font>');
			}else{
				$("#info_div").html("");
				$("#info_div").css("display", "none");
			}
		}
	});
}
		//-->
</SCRIPT>
<style type="text/css">
.bh1, .bh2, .bh3, .bh4 {
	/*
	float:left;
	position:relative;
	*/
	margin:2px 0;
}
.bh1 .error, .bh2 .error, .bh3 .error, .bh4 .error {
	/*
	position:absolute;
	left:5px;
	top:30px;
	*/
	
}

</style>
<div class="conten_box" style="margin-left:190px;">
	<h4 class="xtcs_h4" style="margin:0;">新道路--添加</h4>
    <form id="inputForm" class="form-horizontal" action="${root}/system/road/doAdd/${menuid}/" method="post" style="margin:0;">
     <input type="hidden" name="pid" value="${pid}">
     <div class="mar_5">
       <table class="table table-border-bot table-border-rl bukong-table">
         <tr>
           <td class="device_td_bg3">上级道路名称：</td>
           <td><tags:xiangxuncache keyName="RoadInfo" id="${pid}"></tags:xiangxuncache></td>
         </tr>
         <tr>
           <td class="device_td_bg3">道路名称：</td>
           <td><input type="text" id="name" placeholder="道路名称" name="name" maxlength="30" onkeyup="checkRoadName();" class="required" specialcharfilter="true">
             <font color="red">&nbsp;*</font> <span id="loadingdiv" style="display:none"><img style="width:25px; height:25px;" src="${root}/images/loading.gif" /></span></td>
         </tr>
         <tr id="gisroadnamediv" style="display:none">
           <td class="device_td_bg3" >已匹配道路：</td>
           <td>
             <div id="gisroadname">
               <div class="showmore"><span class="toggleBtn btn-info">- 精简显示道路</span></div>
               <script type="text/javascript">
			     $(function(){
			     	$(".toggleBtn").toggle(function(){
			     		$(this).text("+ 显示全部道路");
			     		$(".road_list li:gt(27)").slideUp();
			     	},function(){
			     		$(this).text("- 精简显示道路");
			     		$(".road_list li:gt(27)").slideDown();
			     	});
			 	  });
			    </script>
                <ul id="gisroadnamelist" class="road_list">
                  <div class="clear"></div>
                </ul>
                <div class="clear"></div>
             </div>
           </td>
         </tr>
         <tr>
           <td class="device_td_bg3">上传编号：</td>
           <td>
            <div class="input-prepend">
               <div class="bh1">
                 <div class="btn-group">
                  <button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;"> 道路类型<span class="caret"></span> </button>
                  <ul class="dropdown-menu">
                    <c:forEach items="${roadTypeList}" var="roadType">
                      <li><a onclick="roadTypeClick('${roadType.code}')";>${roadType.name}</a></li>
                    </c:forEach>
                  </ul>
                 </div>
                 <input type="text" id="coderoadtype" style="width:35px" readonly name="coderoadtype" class="required" digits="true" >
                 <font color="red">&nbsp;*</font>
               </div>
               
               <div class="bh2">
                 <div class="btn-group"><button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;">道路代号：</button></div>
                 <input type="text" id="codeRoadDh" style="width:35px" name="codeRoadDh" minlength="4" maxlength="4" class="required" digits="true" >
                 <font color="red">&nbsp;*</font>
               </div>
               
               <div class="bh3">
                 <div class="btn-group"><button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;">公路里程桩号：</button></div>
                 <input type="text" id="codeRoadZh" style="width:35px" name="codeRoadZh" minlength="4" maxlength="4" class="required" digits="true" >
                 <font color="red">&nbsp;*</font>
               </div>
                  
               <div class="bh4">
                 <div class="btn-group"><button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;">超过：</button></div>
                 <input type="text" id="codeRoadMi" style="width:35px" name="codeRoadMi" minlength="3" maxlength="3" class="required" digits="true" >
                 <font color="red">&nbsp;*</font>
                 <button class="btn dropdown-toggle" data-toggle="dropdown">米</button>
               </div>
               
              </div>
              <div id="info_div" style="display: none;"></div>
             </td>
         </tr>
         <tr>
           <td class="device_td_bg3">备　　注：</td>
           <td><textarea rows="3" class="span8" style="min-width:200px;" maxlength="100" name="note"></textarea>
             <span></span></td>
         </tr>
       </table>
     </div>
     <div class="btn_line">
       <button class="btn btn-info mar_r10" onclick="javascript: return checkForm();" type="submit">保存</button>
     </div>
   </form>
</div>
<script>
		
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/system/road/groupNameExist",
						type:"post",
						data:{
							filename:function(){
								return $("#name").val();
							}
						}
					}
				}
			}
		});
		});
	</script>
