<#list menus as sub>
  <div id="colums_${sub.id}" class="collapse in">
	 <ul class="colums_two">
	    <#list sub.sonResourceList as sonSub>
	    <li><span></span><a href="javascript:menuOnclick('${sonSub.id}','${sonSub.name}','${sub.name}','${sonSub.content}')">${sonSub.name}</a></li>
	    </#list>
	 </ul>
  </div>	 
  <div class="clear"></div>
</#list>