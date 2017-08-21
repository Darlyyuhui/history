<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@ attribute name="rows" type="java.lang.String" required="true"%>
<%@ attribute name="maxlength" type="java.lang.String" required="true"%>
<%@ attribute name="style" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="value" type="java.lang.String" required="false"%>
<%@ attribute name="returnRoadCode" type="java.lang.String" required="false"%>
<%@ attribute name="roadCodeValue" type="java.lang.String" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
<textarea rows="${rows}" maxlength="${maxlength}" id="text_${name}" class="span6" name="${name}" style="${style}" onclick="showMWindoe(this)" readonly="readonly">${value}</textarea>
<c:if test="${returnRoadCode!=null}">
<input type="hidden" id="${returnRoadCode}" name="${returnRoadCode}" value="${roadCodeValue}" />
</c:if>
<script type="text/javascript">
function showMWindoe(obj){
	var addressObject = obj;
	jBox.setDefaults({defaults:{top:65,left:'50px'}});
	jBox.open("iframe:${root}/device/dialog/byroad/",'<font style="font-size:14px;">请选择相关道路设备</font>',660,500,{
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
			    //地点信息
			    var ddname="";
			    var ddcode="";
			    
			    var returnArr = getCheckBoxValue.split("--");
			    var roadinfoArr = returnArr[1].split(",");
			    for(var i=0 ; i < roadinfoArr.length ; i++){
			        ddname += roadinfoArr[i].split("!")[1];
			        ddcode += roadinfoArr[i].split("!")[0];
			        if(i < roadinfoArr.length-1){
			        	ddname += ",";
			          	ddcode += ",";
			        }
			    }
			    
			    addressObject.value = ddname ;
			    //复制一个输入框
			    var road = '${returnRoadCode}';
			    if(road.length>0){
			    	document.getElementById('${returnRoadCode}').value= ddcode;
			    }
				return true;
 			 }
 		},
 		
 		loaded: function (h) { 
	 		$(h).removeAttr('style');
	 		$(h).attr('style','height: 420px; overflow-x: hidden; overflow-y: hidden; position: static; left: -10000px;');
	 		$(".jbox-button-panel").height(30);
 		}
 	});
}
</script>