<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="returnUserName" type="java.lang.String" required="true"%>
<%@ attribute name="returnUserId" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
/**function showUserModalDialog(dialogId,selectedValue){
	return window.showModalDialog("${root}/sms/dialog/"+dialogId+"/",selectedValue,"dialogWidth=680px;dialogHeight=577px;center=yes;middle=yes;help=no;status=no;scroll=no;resizable=no");
}
function showMWindoeSms(dialogId){
      var selectedValue ="请选择";
      var val="";
      var getCheckBoxValue=showUserModalDialog(dialogId,selectedValue);
      
      if(getCheckBoxValue == undefined){
      		return;
      }
      //联系人姓名
      var cname="";
      //联系人ID
      var cid="";
     
      for(var i=0 ; i<getCheckBoxValue.length ; i++){
          cid = getCheckBoxValue[i].split("!")[0]+","+cid;
          cname = getCheckBoxValue[i].split("!")[1]+","+cname;
      }
      
      document.getElementById('${returnUserName}').value = cname;
      document.getElementById('${returnUserId}').value= cid;
     
}*/
function showMWindoeSms(dialogId){
     jBox.setDefaults({defaults:{top:65,left:'50px'}});
 	  jBox.open("iframe:${root}/sms/dialog/"+dialogId+"/",'<font style="font-size:14px;">请选择联系人</font>',675,500,{
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
			     //联系人姓名
				var cname="";
				//联系人ID
				var cid="";
				
				for(var i=0 ; i<getCheckBoxValue.length ; i++){
				    cid = getCheckBoxValue[i].split("!")[0]+","+cid;
				    cname = getCheckBoxValue[i].split("!")[1]+","+cname;
				}
				document.getElementById('${returnUserName}').value = cname;
				document.getElementById('${returnUserId}').value= cid;
				return true;
 			 }
 		},
 		
 		loaded: function (h) { 
 		$(h).removeAttr('style');
 		$(h).attr('style','height: 420px; overflow-x: hidden; overflow-y: hidden; position: static; left: -10000px;')
 		$(".jbox-button-panel").height(30);
 		}
 	});
}
</script>