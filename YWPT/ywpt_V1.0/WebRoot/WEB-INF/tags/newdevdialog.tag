<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@ attribute name="rows" type="java.lang.String" required="true"%>
<%@ attribute name="maxlength" type="java.lang.String" required="true"%>
<%@ attribute name="style" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="menuid" type="java.lang.String" required="false"%>
<%@ attribute name="value" type="java.lang.String" required="false"%>
<%@ attribute name="codeValue" type="java.lang.String" required="false"%>
<%@ attribute name="returnDeviceCode" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>

<textarea rows="${rows}" maxlength="${maxlength}" id="text_${name}" name="${name}" onclick="showMWindoe(this)" readonly="readonly" style="${style}">${value}</textarea>
<input type="hidden" id="${returnDeviceCode}" name="${returnDeviceCode}" value="${codeValue}" />
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
 		jBox.open("iframe:${root}/device/dialog/newdialog/?menuid=${menuid}&authority="+authorityValue,'<font style="font-size:14px;">请选择相关道路设备</font>',700,520,{
 			submit: function (v, h, f) { 
			if (v == 'ok') {
				var jf = h.find("#jbox-iframe")[0].contentWindow;
				var checkboxValue = jf.sendValue();
				if(checkboxValue == undefined){
			    	return false;
			    }
				if(checkboxValue.length==0){
					return false;
				}
				//设备信息
				var sbcode = "";
				var sbname = "";
				var deviceArr = checkboxValue;
				for(var i=0 ; i < deviceArr.length ; i++){
					
					sbname += deviceArr[i].split("!")[0];
				    sbcode += deviceArr[i].split("!")[1];
				    if(i < deviceArr.length-1){
				    	sbname += " , ";
				    	sbcode += ",";
				    }
				}
				addressObject.value = sbname;
				document.getElementById('${returnDeviceCode}').value= sbcode;
				}
			},
			
	 		loaded:function (h) { 
		 		$(h).removeAttr('style');
		 		$(h).attr('style','height:442px;overflow:hidden;position:absolut;left:0;')
		 		$(".jbox-button-panel").height(30).css("border-top","1px solid #aaa");
		 		$("#jbox-iframe").height("442px").attr("scrolling","no");
	 		}
	 	});
	}

</script>