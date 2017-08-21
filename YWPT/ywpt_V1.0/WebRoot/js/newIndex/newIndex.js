// JavaScript Document

	//浏览器自适应改变
	var MainH="";
	var MainW="";
	if($.browser.safari){
		//MainH = window.screen.availHeight-110-5-5-34;
		//$("#LeftBar").height(MainH-2);
		//$("#RightCon").height(MainH-2);
		//$("#mainbodyH").height(MainH);
		MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
		$("#LeftBar").height(MainH-2);
		$("#RightCon").height(MainH-2);
		$("#content-frame").height(MainH-12);
		$("#mainbodyH").height(MainH);
		$("#LeftNav").height(MainH-22);
		$("#submenu_frame").height(MainH-22);
		$("#Mainnav_ul").height(MainH-54);
		
		MainW = $(window).width();//浏览器时下窗口可视区域宽度
		var WidthRside = MainW-210-5-5-8;
		//$("#RightCon").width(WidthRside);
		//$("#content-frame").width(WidthRside-10);
		
	}else if($.browser.msie){
		//MainH = window.screen.availHeight-window.screenTop-50-5-5-36;
		//$("#LeftBar").height(MainH-2);
		//$("#RightCon").height(MainH-2);
		//$("#mainbodyH").height(MainH);
		MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
		$("#LeftBar").height(MainH-2);
		$("#RightCon").height(MainH-2);
		$("#content-frame").height(MainH-12);
		$("#mainbodyH").height(MainH);
		$("#LeftNav").height(MainH-22);
		$("#submenu_frame").height(MainH-22);
		$("#Mainnav_ul").height(MainH-54);
		
		MainW = $(window).width();//浏览器时下窗口可视区域宽度
		var WidthRside = MainW-210-5-5-8;
		//$("#RightCon").width(WidthRside);
		//$("#content-frame").width(WidthRside-10);
		
	}else if($.browser.mozilla){
		if($.browser.version=="11.0"){
			MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
			$("#LeftBar").height(MainH-2);
			$("#RightCon").height(MainH-2);
			$("#content-frame").height(MainH-12);
			$("#mainbodyH").height(MainH);
			$("#LeftNav").height(MainH-22);
			$("#submenu_frame").height(MainH-22);
			$("#Mainnav_ul").height(MainH-54);
			
			MainW = $(window).width();//浏览器时下窗口可视区域宽度
			var WidthRside = MainW-210-5-5-8;
			//$("#RightCon").width(WidthRside);
			//$("#content-frame").width(WidthRside-10);
			
		}else {
			MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
			$("#LeftBar").height(MainH-2);
			$("#RightCon").height(MainH-2);
			$("#content-frame").height(MainH-12);
			$("#mainbodyH").height(MainH);
			$("#LeftNav").height(MainH-22);
			$("#submenu_frame").height(MainH-22);
			$("#Mainnav_ul").height(MainH-54);
			
			MainW = $(window).width();//浏览器时下窗口可视区域宽度
			var WidthRside = MainW-210-5-5-8;
			//$("#RightCon").width(WidthRside);
			//$("#content-frame").width(WidthRside-10);
		
		}	
	}
	
	
	// 是否可以改变
	var isCanChange = true;
	
	// 每100毫秒允许改变一下
	setInterval("change()",100);
	function change(){
		isCanChange = true;
	}
	window.onresize=function(){
		if(isCanChange){
			var MainH="";
			var MainW="";
			//var Height="";
			if($.browser.safari){
				//MainH = window.screen.availHeight-110-5-5-34;
				//$("#LeftBar").height(MainH-2);
				//$("#RightCon").height(MainH-2);
				//$("#mainbodyH").height(MainH);
				MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
				$("#LeftBar").height(MainH-2);
				$("#RightCon").height(MainH-2);
				$("#content-frame").height(MainH-12);
				$("#mainbodyH").height(MainH);
				$("#LeftNav").height(MainH-22);
				$("#submenu_frame").height(MainH-22);
				$("#Mainnav_ul").height(MainH-54);
				
				MainW = $(window).width();//浏览器时下窗口可视区域宽度
				var WidthRside = MainW-210-5-5-8;
				//$("#RightCon").width(WidthRside);
				//$("#content-frame").width(WidthRside-10);
				
			}else if($.browser.msie){
				//MainH = window.screen.availHeight-window.screenTop-50-5-5-36;
				//$("#LeftBar").height(MainH-2);
				//$("#RightCon").height(MainH-2);
				//$("#mainbodyH").height(MainH);
				MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
				$("#LeftBar").height(MainH-2);
				$("#RightCon").height(MainH-2);
				$("#content-frame").height(MainH-12);
				$("#mainbodyH").height(MainH);
				$("#LeftNav").height(MainH-22);
				$("#submenu_frame").height(MainH-22);
				$("#Mainnav_ul").height(MainH-54);
		
				MainW = $(window).width();//浏览器时下窗口可视区域宽度
				var WidthRside = MainW-210-5-5-8;
				//$("#RightCon").width(WidthRside);
				//$("#content-frame").width(WidthRside-10);
				
			}else if($.browser.mozilla){
				if($.browser.version=="11.0"){
					MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
					$("#LeftBar").height(MainH-2);
					$("#RightCon").height(MainH-2);
					$("#content-frame").height(MainH-12);
					$("#mainbodyH").height(MainH);
					$("#LeftNav").height(MainH-22);
					$("#submenu_frame").height(MainH-22);
					$("#Mainnav_ul").height(MainH-54);
					
					MainW = $(window).width();//浏览器时下窗口可视区域宽度
					var WidthRside = MainW-210-5-5-8;
					//$("#RightCon").width(WidthRside);
					//$("#content-frame").width(WidthRside-10);
					
				}else {
					MainH=$(window).height()-50-5-5-32;//根据浏览器高度设置
					$("#LeftBar").height(MainH-2);
					$("#RightCon").height(MainH-2);
					$("#content-frame").height(MainH-12);
					$("#mainbodyH").height(MainH);
					$("#LeftNav").height(MainH-22);
					$("#submenu_frame").height(MainH-22);
					$("#Mainnav_ul").height(MainH-54);
					
					
					MainW = $(window).width();//浏览器时下窗口可视区域宽度
					var WidthRside = MainW-210-5-5-8;
					//$("#RightCon").width(WidthRside);
					//$("#content-frame").width(WidthRside-10);
				
				}	
			}
			
			isCanChange = false;
			//alert(isCanChange);
		}
		
	}
	
	//设置ul-li最后一行无下边框
	$(".alarm_ul li:last").css("border-bottom","0");
	$(".log_ul li:last").css("border-bottom","0");
	$(".ul_first li:last").css("border","0");
	
	//左侧菜单操作效果
	/*系统级菜单*/
	var $li = $(".mainnav_new li");
	
	$($li).click(function(){
		$(this).removeClass("sys_nav").addClass("active_nav").siblings().removeClass("active_nav").addClass("sys_nav");
	}).hover(function(){
		$(this).addClass("hover_nav");
	},function(){
		$(this).removeClass("hover_nav");
	});
	
	
	/*进入系统级菜单效果*/
	
	var $h4 = $(".nav_h4");
	$($h4).click(function(){
		$(this).removeClass("nav_first").addClass("nav_first_cur").siblings(".nav_h4").removeClass("nav_first_cur").addClass("nav_first");
		//$(this).next(".nav_second").css("display","block").siblings(".nav_second").css("display","none");
		$(this).next(".nav_second").slideDown().siblings(".nav_second").slideUp();
		return false;
	});
	
	
	
	
	
	
	
	
	