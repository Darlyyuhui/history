<div class="nav_title"><span><img src="../../images/picone/xtgncd_icon.gif"></span>系统功能菜单</div>
<div id="sidemenu" style="padding:0;">
    <#list menus as sub>
      <a data-toggle="collapse" href="javascript.html#colums_${sub.id}" class="menu_first">
	     <p class="a_part"><span><img src="../../images/picone/icon1.gif"></span>${sub.name}</p>
	  </a>
	  <div id="colums_${sub.id}" class="collapse in menu_sec">
		 <ul class="colums_two">
		    <#list sub.sonResourceList as sonSub>
		    <li id="${sonSub.id}" onmouseover="changeBC('${sonSub.id}')"><span></span>
		    	<a href="javascript:menuOnclick('${sonSub.id}','${sonSub.name}','${sub.name}','${sonSub.content}')" title="${sonSub.name}">${sonSub.name}</a></li>
		    </#list>
		 </ul>
	  </div>	 
	  <div class="clear"></div>
    </#list>
</div>
