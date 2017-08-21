<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>自定义桌面模块</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<!-- <link rel="stylesheet" type="text/css" href="/theme/2/person_info.css"> -->

<script>
function insert_left()
{
 for (i=0; i<document.form1.select2.options.length; i++)
 {
   if(document.form1.select2.options[i].selected)
   {
     option_text=document.form1.select2.options[i].text;
     option_value=document.form1.select2.options[i].value;
     option_style_color=document.form1.select2.options[i].style.color;

     var my_option = document.createElement("OPTION");
     my_option.text=option_text;
     my_option.value=option_value;
     my_option.style.color=option_style_color;

     pos=document.form1.select2.options.length;
     document.form1.select1.options.add(my_option,pos);
     document.form1.select2.remove(i);
     i--;
  }
 }//for
}


function delete_left()
{
 for (i=0;i<document.form1.select1.options.length;i++)
 {
   if(document.form1.select1.options[i].selected)
   {
     option_text=document.form1.select1.options[i].text;
     option_value=document.form1.select1.options[i].value;

     var my_option = document.createElement("OPTION");
     my_option.text=option_text;
     my_option.value=option_value;

     if(option_text.indexOf("[必选]")>0)
        continue;//  return;
     pos=document.form1.select2.options.length;
     document.form1.select2.options.add(my_option,pos);
     document.form1.select1.remove(i);
     i--;
  }
 }//for
}



function insert_center()
{
 for (i=0; i<document.form1.select3.options.length; i++)
 {
   if(document.form1.select3.options[i].selected)
   {
     option_text=document.form1.select3.options[i].text;
     option_value=document.form1.select3.options[i].value;
     option_style_color=document.form1.select3.options[i].style.color;

     var my_option = document.createElement("OPTION");
     my_option.text=option_text;
     my_option.value=option_value;
     my_option.style.color=option_style_color;

     pos=document.form1.select3.options.length;
     document.form1.select2.options.add(my_option,pos);
     document.form1.select3.remove(i);
     i--;
  }
 }//for
}

function delete_center()
{
 for (i=0;i<document.form1.select2.options.length;i++)
 {
   if(document.form1.select2.options[i].selected)
   {
     option_text=document.form1.select2.options[i].text;
     option_value=document.form1.select2.options[i].value;

     var my_option = document.createElement("OPTION");
     my_option.text=option_text;
     my_option.value=option_value;

     if(option_text.indexOf("[必选]")>0)
        continue;//  return;
     pos=document.form1.select3.options.length;
     document.form1.select3.options.add(my_option,pos);
     document.form1.select2.remove(i);
     i--;
  }
 }//for
}

function func_select_all1()
{
 for (i=document.form1.select1.options.length-1; i>=0; i--)
   document.form1.select1.options[i].selected=true;
}

function func_select_all2()
{
 for (i=document.form1.select2.options.length-1; i>=0; i--)
   document.form1.select2.options[i].selected=true;
}

function func_select_all3()
{
 for (i=document.form1.select3.options.length-1; i>=0; i--)
   document.form1.select3.options[i].selected=true;
}

function func_up()
{
  sel_count=0;
  for (i=document.form1.select1.options.length-1; i>=0; i--)
  {
    if(document.form1.select1.options[i].selected)
       sel_count++;
  }

  if(sel_count==0)
  {
     alert("调整桌面模块的顺序时，请选择其中一项！");
     return;
  }
  else if(sel_count>1)
  {
     alert("调整桌面模块的顺序时，只能选择其中一项！");
     return;
  }

  i=document.form1.select1.selectedIndex;

  if(i!=0)
  {
    var my_option = document.createElement("OPTION");
    my_option.text=document.form1.select1.options[i].text;
    my_option.value=document.form1.select1.options[i].value;

    document.form1.select1.options.add(my_option,i-1);
    document.form1.select1.remove(i+1);
    document.form1.select1.options[i-1].selected=true;
  }
}

function func_down()
{
  sel_count=0;
  for (i=document.form1.select1.options.length-1; i>=0; i--)
  {
    if(document.form1.select1.options[i].selected)
       sel_count++;
  }

  if(sel_count==0)
  {
     alert("调整桌面模块的顺序时，请选择其中一项！");
     return;
  }
  else if(sel_count>1)
  {
     alert("调整桌面模块的顺序时，只能选择其中一项！");
     return;
  }

  i=document.form1.select1.selectedIndex;

  if(i!=document.form1.select1.options.length-1)
  {
    var my_option = document.createElement("OPTION");
    my_option.text=document.form1.select1.options[i].text;
    my_option.value=document.form1.select1.options[i].value;

    document.form1.select1.options.add(my_option,i+2);
    document.form1.select1.remove(i);
    document.form1.select1.options[i+1].selected=true;
  }
}

function mysubmit()
{
   sele1_str="";
   sele2_str="";
   sele3_str="";
   
   for (i=0; i< document.form1.select1.options.length; i++)
   {
      options_value=document.form1.select1.options[i].value;
      sele1_str+=options_value+",";
   }
   document.form1.selecta.value=sele1_str;
    
    for (i=0; i< document.form1.select2.options.length; i++)
   {
      options_value=document.form1.select2.options[i].value;
      sele2_str+=options_value+",";
   }
   document.form1.selectb.value=sele2_str;
    
    for (i=0; i< document.form1.select3.options.length; i++)
   {
      options_value=document.form1.select3.options[i].value;
      sele3_str+=options_value+",";
   }
   document.form1.selectc.value=sele3_str;
   document.form1.submit();
}
</script>
</head>

<body class="bodycolor">

<div class="conten_box">
  	<h4 class="title_intro"><span><img src="${root}/images/applicate.png"></span>首页自定义设置</h4>
	<form method="post" name="form1" action="${root}/system/diyindexset/saveindexset" style="margin:0;">
		<div class="mar_5">
			<table class="table table-bordered table-style">
			  <thead>
				  <tr class="TableHeader">
				    <th width="50">排序</th>
				    <th width="28%">左侧栏</th>
				    <th width="50">选择</th>
				    <th width="28%">中间栏</th>
				    <th width="50">选择</th>
				    <th>右侧栏</th>
				  </tr>
			  </thead>
			  <tbody>
				  <tr>
				    <td class="device_td_bg4" style="text-align:center;">
				      <input type="button" class="btn" value=" ↑ " onClick="func_up();">
				      <br><br>
				      <input type="button" class="btn" value=" ↓ " onClick="func_down();">
				    </td>
				    <td valign="top" style="text-align:center;">
					    <select  name="select1" MULTIPLE style="width:96%;height:400px;">
					    <c:forEach items="${modulListLeft}" var="modul">
		<option value="${modul.code}">${modul.name}</option>
                        </c:forEach>
					    </select>
					    <p class="mar_t5"><input type="button" value=" 全选 " onClick="func_select_all1();" class="btn" style="width:96%;"></p>
				    </td>
				    <td class="device_td_bg4" style="text-align:center;">
				      <input type="button" class="btn" value=" ← " onClick="insert_left();">
				      <br><br>
				      <input type="button" class="btn" value=" → " onClick="delete_left();">
				    </td>
				    <td style="text-align:center;">
					    <select  name="select2" MULTIPLE style="width:96%;height:400px;">
		                <c:forEach items="${modulListCenter}" var="modul">
		<option value="${modul.code}">${modul.name}</option>
                        </c:forEach>
					    </select>
					    <p class="mar_t5"><input type="button" value=" 全选 " onClick="func_select_all2();" class="btn" style="width:96%;"></p>
				    </td>
				    <td class="device_td_bg4" style="text-align:center;">
				      <input type="button" class="btn" value=" ← " onClick="insert_center();">
				      <br><br>
				      <input type="button" class="btn" value=" → " onClick="delete_center();">
				    </td>
				    <td style="text-align:center;">
					    <select  name="select3" MULTIPLE style="width:96%;height:400px;">
		                <c:forEach items="${modulListRight}" var="modul">
		<option value="${modul.code}">${modul.name}</option>
                        </c:forEach>
					    </select>
					    <p class="mar_t5"><input type="button" value=" 全选 " onClick="func_select_all3();" class="btn" style="width:96%;"></p>
				    </td>
				  </tr>
				  <tr>
				    <td class="device_td_bg4" colspan="6">
				    <input type="hidden" name="selecta">
				    <input type="hidden" name="selectb">
				    <input type="hidden" name="selectc">
				    点击条目时，可以组合CTRL或SHIFT键进行多选
				    </td>
				  </tr>
				</tbody>
			</table>
		</div>
		<div class="btn_line"><input class="btn btn-info BigButton" type="button" style="padding:4px 50px;" value="保存设置" onClick="mysubmit();"></div>
	</form>
</div>
</body>
</html>