<p class="sys_name"><span>系统模块图标</span>系统模块首页</p>
<div id="sonNav" style="padding:0;">
    <#list menus as sub>
	  <h4 class="nav_h4 nav_first"><span></span>${sub.name}</h4>
	  <ul class="nav_second">
	    <#list sub.sonResourceList as sonSub>
	    <!--<li id="${sonSub.id}" onmouseover="changeBC('${sonSub.id}')"><span></span>-->
	    <li id="${sonSub.id}" class="nav_li"><span></span>
	    	<a href="javascript:menuOnclick('${sonSub.id}','${sonSub.name}','${sub.name}','${sonSub.content}')" title="${sonSub.name}">${sonSub.name}</a></li>
	    </#list>
	  </ul>
    </#list>
</div>

<script type="text/javascript">
	$(function(){
		//点击h4，子菜单展开闭合效果切换
		$(".nav_h4").toggle(
			function(){
				$(this).removeClass("nav_first").addClass("nav_first_cur");
				$(this).next(".nav_second").slideUp();
			},function(){
				$(this).removeClass("nav_first_cur").addClass("nav_first");
				$(this).next(".nav_second").slideDown();
			}
		);
		
		//二级菜单点击添加突出样式，其它未点击菜单去掉突出样式
		$("#sonNav li").click(function(){
			//$(this).removeClass("nav_li").addClass("curent_nav").siblings("#sonNav li").removeClass("curent_nav").addClass("nav_li");
			$("#sonNav li").removeClass("curent_nav").addClass("nav_li");
			$(this).removeClass("nav_li").addClass("curent_nav");
			//alert($(this).text());
			//alert($(this).attr("class"));
		});
		
	});
</script>