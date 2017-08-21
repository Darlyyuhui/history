<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${root}/css/sms.css" type="text/css" rel="stylesheet">
<script src="<c:url value='/js/common.js'/>" type="text/javascript"></script>
<div class="row-fluid " id="user-content" style="height:400px;margin-top: 0;">
	<div class="row-fluid" style="margin-bottom: 5px; border-radius: 5px 5px 0 0; -moz-border-radius: 5px 5px 0 0; -webkit-border-radius: 5px 5px 0 0; padding: 6px 0 4px;border-bottom: 1px solid #bdc5d9;">
		<form class="form-inline" action="${root}/system/policeuser/choiceusers/${orgId}/${menuid}/" method="post">
			<div class="search_item pull-left mar_l10">
				<span>姓 名：</span><input class="input" style="width: 100px; height: 18px;" name="search_name" value="${search['name']}" type="text" placeholder="姓名" maxlength="10" />  
			</div>
			<div class="search_item pull-left mar_l10">
				<span>警号：</span><input class="input" style="width: 100px; height: 18px;" name="search_code" value="${search['code']}" type="text" placeholder="警号" maxlength="10" />
			</div>
			<input type="hidden" id="single" name="search_single" />
			<input type="submit" class="btn btn-small btn-info pull-left mar_l10" value="查询" style="height: 28px;" />
		</form>
	</div>
	<div class="clear"></div>
	<div class="table_box" >
		<table class="table table-striped table-bordered table-condensed table-style" id="table" width="100%" style="height:300px;">
			<thead align="center">
				<tr align="center">
					<th class="center" width="16">&nbsp;</th>
					<th class="center">姓名</th>
					<th class="center">警号</th>
					<th class="center">所属部门</th>
					<th class="center">职位</th>
					<th class="center">类别</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="rows" value="0" />
				<c:forEach items="${pageList.result}" var="user">
					<c:set var="rows" value="${rows+1}" />
					<tr id="tr_${user.id}">
						<td width="16"><input type="checkbox" id="checkbox" value="${user.id}_${user.name}_${user.note}_${user.telPhone}" onclick="checkboxw(this);"/></td>
						<td>${user.name}</td>
						<td>${user.code}</td>
						<td><tags:xiangxuncache keyName="Department" id="${user.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Dic" id="${user.postCode}" typeCode="policeuserpost"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Dic" id="${user.types}" typeCode="policetype"></tags:xiangxuncache></td>
					</tr>
				</c:forEach>
				<c:if test="${pageList.result!=null}">
					<c:forEach begin="1" end="${pageList.pageSize-rows}">
						<tr>
							<td colspan="14">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<tags:pagination page="${pageList}"></tags:pagination>
	</div>
<div class="clear"></div>
<script>
   var isSingle = "";
    $(document).ready(function() {
		isSingle = '${param.single}'==""?'${single}':'${param.single}';
		$("#single").val(isSingle);
	});
	var usarray="";
	function checkboxw(checkbox){
       if(checkbox.checked==true){
          if(usarray.toString()!=""){
            usarray+=",";
          }
          usarray+=checkbox.value;
       }else{         
           var usarrays=usarray.split(",");
	        var arrays="";
	        for(var i=0;i<usarrays.length;i++){
	           if(usarrays[i]!=checkbox.value){
	              if(arrays.toString()!=""){
	                arrays+=",";
	              }
	              arrays+=usarrays[i];
	           }
	        }
	        usarrays="";
	        usarray=""; 
	        if(arrays==""){
		        pcarray="";
		        parent.$("#usname").val("");
	        }else{
	            usarray+=arrays; 
	        }    
	        arrays="";	        	       
      }
      parent.$("#usname").val(usarray); 
    }
</script>
</div>