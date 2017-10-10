<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<style type="text/css">
.chart_title {
	text-align: center;
	font-size: 18px;
}
</style>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      土壤修复阶段对比
        <small><i class="ace-icon fa fa-angle-double-right"></i>
              土壤修复阶段对比
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/statistics/repair/count/${menuid}/" method="post">
    </form>
    <div  style="background-color: gray;height: 60px;">
      <div>
        <label style="margin-top: 19px;margin-left: 20%;">选择项目:</label>
         <select id="project" style="margin-left:5px;width:200px;">
			  <option value ="volvo">Volvo</option>
			  <option value ="saab">Saab</option>
			  <option value="opel">Opel</option>
			  <option value="audi">Audi</option>
		 </select>
		 <input type="button"  value="对比分析" style="width: 100px;height: 40px;margin-left:25%" onclick="searchInfo()"/>
	   </div>
            
    </div>
    <div   style="height: 60px;">
    	<label style="margin-top: 19px;margin-left: 20%;">选择修复阶段:</label>
		         <select id="repaire1" style="margin-left:5px;width:15%;">
					  <option value ="volvo">Volvo</option>
					  <option value ="saab">Saab</option>
					  <option value="opel">Opel</option>
					  <option value="audi">Audi</option>
				 </select>
	   <label style="margin-top: 19px;margin-left: 20%;">选择修复阶段:</label>
		         <select  id="repaire2" style="margin-left:5px;width:15%;">
					  <option value ="volvo">Volvo</option>
					  <option value ="saab">Saab</option>
					  <option value="opel">Opel</option>
					  <option value="audi">Audi</option>
				 </select>
    </div>
</div>
<div style="width:50%;float:left;">
<table class="table table-striped table-bordered table-hover table-style">
    <tr><td colspan="4">修复前</td></tr>
    <tr><td>总镉平均值</td><td>123</td><td>PH平均值</td><td>6</td>
</tr>
<tr><td>有效态镉</td><td>123</td><td>酸碱度</td><td>6</td>
</tr>
<tr><td>点位超标率</td><td>123</td><td>采样数</td><td>6</td>
</tr>
</table>
<div id="map1" style="width:100%;"></div>
</div>
<div style="width:50%;float:right;">
<table class="table table-striped table-bordered table-hover table-style">
<tr><td colspan="4">修复前</td></td>
</tr>
<tr><td>总镉平均值</td><td>123</td><td>PH平均值</td><td>6</td>
</tr>
<tr><td>有效态镉</td><td>123</td><td>酸碱度</td><td>6</td>
</tr>
<tr><td>点位超标率</td><td>123</td><td>采样数</td><td>6</td>
</tr>
</table>
<div id="map2" style="width:100%;width:100%"></div>
</div>

<div class="row" style="margin-top:1px;">
    <div class="col-xs-12">
        <table id="table" class="table table-bordered" style="text-align: center;min-height:500px">
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript">
		function searchInfo(){
			var project=$("#project").val();
			var repaire1=$("#repaire1").val();
			var repaire2=$("#repaire2").val();
			if(project=="" || repaire1=="" || repaire2==""){
				alert("请选择修复项目或修复阶段");
			 }else{
				 $.post(
						"${root}/compare/land/searchInfo"+"/"+project+"/"+repaire1+","+ repaire2+"/",
						function(data){
						alert(data);	
						}
					)
			} 
		}




</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>