<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@ attribute name="rows" type="java.lang.String" required="true"%>
<%@ attribute name="maxlength" type="java.lang.String" required="true"%>
<%@ attribute name="style" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="menuid" type="java.lang.String" required="false"%>
<%@ attribute name="value" type="java.lang.String" required="false"%>
<%@ attribute name="codeValue" type="java.lang.String" required="false"%>
<%@ attribute name="returnCodeName" type="java.lang.String" required="true"%>
<%@ attribute name="returnRoadCode" type="java.lang.String" required="false"%>
<%@ attribute name="roadCodeValue" type="java.lang.String" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
<textarea rows="${rows}" maxlength="${maxlength}" onclick="showMWindoe(this)" readonly="readonly" style="width:160px;"></textarea>
<input type="hidden" id="${returnCodeName}" name="${returnCodeName}" value="${codeValue}" />
<c:if test="${returnRoadCode!=null}">
<input type="hidden" id="${returnRoadCode}" name="${returnRoadCode}" value="${roadCodeValue}" />
</c:if>
<script type="text/javascript">

	function showMWindoe(obj){
		var addressObject = obj;
		 var authorityValue = '';
	     if('${authority}'==1){
	     	authorityValue='1';
	     }else{
	     	authorityValue='2';
	     }
		jBox.setDefaults({defaults:{top:65,left:'50px'}});
 		jBox.open("iframe:${root}/device/dialog/limitrule/?menuid=${menuid}&authority="+authorityValue,'<font style="font-size:14px;">请选择相关道路设备</font>',660,500,{
 			submit: function (v, h, f) { 
			if (v == 'ok') {
				var jf = h.find("#jbox-iframe")[0].contentWindow;
				var getCheckBoxValue = jf.sendValue();
				if(getCheckBoxValue == undefined){
			    	return false;
			    }
				if(getCheckBoxValue.length==0){
					return false;
				}
				if(getCheckBoxValue.indexOf("##")==getCheckBoxValue.length-2){
					return false;
				}
				//设备信息
			      var sbcode="";
			      var direct = "";
			      //地点信息
			      var ddname="";
			      var ddcode="";
			      
			      var returnArr = getCheckBoxValue.split("##");
			      var deviceArr = returnArr[0].split(",");
			      for(var i=0 ; i < deviceArr.length ; i++){
			          sbcode += deviceArr[i].split("!")[0];
			          if(deviceArr[i].split("!")[1] == ''){
			          	direct += "null";
			          }else{
			          	direct += deviceArr[i].split("!")[1];
			          }
			          if(i < deviceArr.length-1){
			          	sbcode += ",";
			          	direct += ",";
			          }
			      }
			      var roadinfoArr = returnArr[1].split(",");
			      for(var i=0 ; i < roadinfoArr.length ; i++){
			          ddcode += roadinfoArr[i].split("!")[0];
			          ddname += roadinfoArr[i].split("!")[1];
			          if(i < roadinfoArr.length-1){
			          	ddcode += ",";
			          	ddname += ",";
			          }
			      }
			      addressObject.value = ddname;
			      document.getElementById('${returnCodeName}').value= sbcode;
			      var road = '${returnRoadCode}';
					if(road.length>0){
						document.getElementById('${returnRoadCode}').value= ddcode;
					}
			      tolist_by_ajax(sbcode);
				}
			},
	 		loaded: function (h) { 
	 		$(h).removeAttr('style');
	 		$(h).attr('style','height: 420px; overflow-x: hidden; overflow-y: hidden; position: static; left: -10000px;')
	 		$(".jbox-button-panel").height(30);
	 		}
	 	});
	}
	
	function tolist_by_ajax(sbcode){
		var url="${root}/device/devicephysical/toListByAjax/"+sbcode+"/";
		$.ajax( {
			type : 'GET',
			url : url,
			dataType : "json",
			success:function(data){
				if(data != null){
	   				 var tbody_div = '';
	   				 $("#table_div").attr("style","width: 95%;height:280px;overflow-y:scroll;");
	   				 $("#table_div").css("");
	   				 for(var i = 0; i < data.length; i++){
	   				 	tbody_div += '<tr>'+
						'<td><input type="radio" name="deviceCode" value="'+data[i].code+'" ></td>'+
						'<td>'+data[i].road.split("-")[1]+'</td>'+
	   				 	'<td>'+data[i].name+'</td>';
	   				 	tbody_div += '</tr>';
	   				}
	   				$("#tbody").html("").html(tbody_div);
	   				$("#devlist_div").css("display", "");
	   			}
			}
		});
	}
</script>