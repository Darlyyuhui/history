/**
 * 右侧快速操作
 * kongge@office.weiphone.com
 * 2012.06.07
*/
jQuery(function($){
	//创建DOM
	var 
	quickHTML = '<div class="quick_links_panel"><div id="quick_links" class="quick_links"><a href="#top" class=""><span>返回顶部</span></a><i class=""></i><a href="#" class="message_list" ><i class="message"></i><span>告警信息</span><em class="num" style="display:none"></em></a></div><div class="quick_toggle"><a href="javascript:;" class="toggle" title="展开/收起">×</a></div></div><div id="quick_links_pop" class="quick_links_pop hide"></div>',
	quickShell = $(document.createElement('div')).html(quickHTML).addClass('quick_links_wrap'),
	quickLinks = quickShell.find('.quick_links');
	quickPanel = quickLinks.parent();
	quickShell.appendTo('body');
	
	//具体数据操作 
	var 
	quickPopXHR,
	loadingTmpl = '<div class="loading" style="padding:30px 80px"><i></i><span>Loading...</span></div>',
	popTmpl = '<div class="title"><h3><i></i>告警信息详情</h3></div><div class="pop_panel"><div class="wrap"><div class="dowebok"><ul><li>1、<a href="http://www.dowebok.com/140.html" target="_blank">jQuery.eraser – jQuery橡皮擦插件</a></li><li>2、<a href="http://www.dowebok.com/139.html" target="_blank">让IE7 IE8支持CSS3 background-size属性</a></li><li>3、<a href="http://www.dowebok.com/138.html" target="_blank">制作大小可调整的导航菜单</a></li><li>4、<a href="http://www.dowebok.com/134.html" target="_blank">scrollReveal.js – 页面滚动显示动画JS</a></li><li>5、<a href="http://www.dowebok.com/133.html" target="_blank">AutoScroll.js – jQuery页面自动滚动插件</a></li><li>6、<a href="http://www.dowebok.com/132.html" target="_blank">Owl Carousel制作百度百科2014新首页幻灯片</a></li><li>7、<a href="http://www.dowebok.com/131.html" target="_blank">WOW.js – 让页面滚动更有趣</a></li><li>8、<a href="http://www.dowebok.com/127.html" target="_blank">multiscroll.js – jQuery左右垂直反向滚动插</a></li><li>9、<a href="http://www.dowebok.com/126.html" target="_blank">fullPage.js制作网易邮箱大师页面</a></li><li>10、<a href="http://www.dowebok.com/125.html" target="_blank">FlexSlider制作新浪2014成都车展幻灯片</a></li></ul></div></div></div>',
	historyListTmpl = '<ul><%for(var i=0,len=items.length; i<5&&i<len; i++){%><li><a href="<%=items[i].productUrl%>" target="_blank" class="pic"><img alt="<%=items[i].productName%>" src="<%=items[i].productImage%>" width="60" height="60"/></a><a href="<%=items[i].productUrl%>" title="<%=items[i].productName%>" target="_blank" class="tit"><%=items[i].productName%></a><div class="price" title="单价"><em>&yen;<%=items[i].productPrice%></em></div></li><%}%></ul>',
	newMsgTmpl = '<ul><li><a href="'+account_comment_url+'"><span class="tips">新回复<em class="num"><b><%=items.commentNewReply%></b></em></span>商品评价/晒单</a></li><li><a href="'+account_consult_url+'"><span class="tips">新回复<em class="num"><b><%=items.consultNewReply%></b></em></span>商品咨询</a></li><li><a href="'+account_message_url+'"><span class="tips">新回复<em class="num"><b><%=items.messageNewReply%></b></em></span>我的留言</a></li><li><a href="'+account_arrival_url+'"><span class="tips">新通知<em class="num"><b><%=items.arrivalNewNotice%></b></em></span>到货通知</a></li><li><a href="'+account_reduce_url+'"><span class="tips">新通知<em class="num"><b><%=items.reduceNewNotice%></b></em></span>降价提醒</a></li></ul>',
	quickPop = quickShell.find('#quick_links_pop'),
	quickDataFns = {
		//个人中心
		
		
		//站内消息
		message_list: {
			title: '站内消息',
			content: loadingTmpl,
			init: function(ops){
			}
		}
	};
	
	//showQuickPop
	var 
	prevPopType,
	prevTrigger,
	doc = $(document),
	popDisplayed = false,
	hideQuickPop = function(){
		if(prevTrigger){
			prevTrigger.removeClass('current');
		}
		popDisplayed = false;
		prevPopType = '';
		quickPop.hide();
	},
	showQuickPop = function(type){
		popTmpl = "";
		$.ajax({
            type : "POST",
            url : "../../alarm/log/newAlarmAll?temp="+Math.random(),
            dataType : "json",
            async: false,
            success : function(msg) {
            if(msg.success){
               if(msg != undefined){
            	   var vendorJson = eval(msg.data);
            	   //头
            	   popTmpl += '<div class="title"><h3><i></i>告警信息详情</h3></div><div class="pop_panel"><div class="wrap"><div class="dowebok"><ul>';
            	   //循环<li>2、<a href="#" target="_blank">2</a></li>
            	   
            	   for(var i=0; i<vendorJson.length; i++){
            		   //popTmpl += '<li><a href="javascript:showAlarmLogDialog('+vendorJson[i].id+')"><span style="width:20%">'+vendorJson[i].deviceName+'</span>&nbsp;&nbsp;<span style="width:40%">'+vendorJson[i].eventTypeName+'</span>&nbsp;&nbsp;"+"&nbsp;&nbsp;<span style="width:40%">'+vendorJson[i].alarmTimeStr+'</span>&nbsp;&nbsp;</a></li>';
            		   popTmpl += '<li><a href="javascript:showAlarmLogDialog(\''+vendorJson[i].id+'\')"><span style="float:left">'+vendorJson[i].deviceName+'</span>&nbsp;&nbsp;<span style="color:'+vendorJson[i].alarmColor+';">'+vendorJson[i].eventTypeName+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="float:right">'+vendorJson[i].alarmTimeStr+'</span>&nbsp;&nbsp;</a></li>';
            	   }
            	   
            	   //尾
            	   popTmpl += '</ul></div></div></div>';
                  
                  
               }else{
            	   $("#notice_ul").html("");
               }
            }
            },error:function() {
              popTmpl = '<div class="title"><h3><i></i>告警信息详情</h3></div><div class="pop_panel"><div class="wrap"><div class="dowebok"><ul><li>无数据</a></li></ul></div></div></div>';
            }
		});
		
		if(quickPopXHR && quickPopXHR.abort){
			quickPopXHR.abort();
		}
		if(type !== prevPopType){
			var fn = quickDataFns[type];
			quickPop.html(ds.tmpl(popTmpl, fn));
			fn.init.call(this, fn);
		}
		doc.unbind('click.quick_links').one('click.quick_links', hideQuickPop);

		quickPop[0].className = 'quick_links_pop quick_' + type;
		popDisplayed = true;
		prevPopType = type;
		quickPop.show();
	};
	quickShell.bind('click.quick_links', function(e){
		e.stopPropagation();
	});

	//通用事件处理
	var 
	view = $(window),
	quickLinkCollapsed = !!ds.getCookie('ql_collapse'),
	getHandlerType = function(className){
		return className.replace(/current/g, '').replace(/\s+/, '');
	},
	showPopFn = function(){
		var type = getHandlerType(this.className);
		if(popDisplayed && type === prevPopType){
			return hideQuickPop();
		}
		showQuickPop(this.className);
		if(prevTrigger){
			prevTrigger.removeClass('current');
		}
		prevTrigger = $(this).addClass('current');
		//田勃加载新闻滚动事件
		aa();
	},
	quickHandlers = {
		//购物车，最近浏览，商品咨询
		my_qlinks: showPopFn,
		message_list: showPopFn,
		history_list: showPopFn,
		leave_message: showPopFn,
		//返回顶部
		return_top: function(){
			ds.scrollTo(0, 0);
			hideReturnTop();
		},
		toggle: function(){
			quickLinkCollapsed = !quickLinkCollapsed;
			
			quickShell[quickLinkCollapsed ? 'addClass' : 'removeClass']('quick_links_min');
			ds.setCookie('ql_collapse', quickLinkCollapsed ? '1' : '', 30);
		}
	};
	quickShell.delegate('a', 'click', function(e){
		var type = getHandlerType(this.className);
		if(type && quickHandlers[type]){
			quickHandlers[type].call(this);
			e.preventDefault();
		}
	});
	
	//Return top
	var scrollTimer, resizeTimer, minWidth = 1350;

	function resizeHandler(){
		clearTimeout(scrollTimer);
		scrollTimer = setTimeout(checkScroll, 160);
	}
	function checkResize(){
		quickShell[view.width() > 1340 ? 'removeClass' : 'addClass']('quick_links_dockright');
	}
	function scrollHandler(){
		clearTimeout(resizeTimer);
		resizeTimer = setTimeout(checkResize, 160);
	}
	function checkScroll(){
		view.scrollTop()>100 ? showReturnTop() : hideReturnTop();
	}
	function hideReturnTop(){
		quickPanel.removeClass('quick_links_allow_gotop');
	}

	view.bind('scroll.go_top', resizeHandler).bind('resize.quick_links', scrollHandler);
	quickLinkCollapsed && quickShell.addClass('quick_links_min');
	resizeHandler();
	scrollHandler();
	

	//校验商品咨询表单
	function  checkMessageForm(){
		var content = $("#msg");
		if(content.val()==""){
			ds.dialog({
				   title : '消息',
				   content : "请填写咨询内容！",
				   onyes : function(){
						this.close();
				   },
				   width : 200,
				   lock : true
			});
			return false;
		}

		var checkcode = $("#token_txt").val();
		if(checkcode=="" || checkcode=="点击获取"){
			ds.dialog({
				   title : '消息',
				   content : "验证码不能为空，请输入验证码！",
				   onyes : function(){
						this.close();
				   },
				   width : 200,
				   lock : true
			});
			return false;
		}
		return true;
	}

	//获取验证码
	function getValidateCode(){
		this.value="";
		var validateCodeUrl = validateCode_url+'?t='+Math.random();
		$("#code_img").html('<img id="validate_code_img_id_1" src="' + validateCodeUrl + '" height="20" width="80" alt="验证码" />');
		return;
	}
});
function aa(){
	$('.dowebok').vTicker({
		showItems: 10,
		pause: 2000
	});
}
//首次加载站内消息总数
jQuery(function($){
	var shell = $('#quick_links a.message_list');
	if(shell.find("em").length){
		
		$.ajax({
			url: unreadNewMsgUrl,
			dataType: 'json',
			cache: false,
			success: function(data){
				if(1 == data.success){
					if(0 == data.msgtotal){
						shell.find('em').remove();
					}else{
						var num = data.msgtotal;
						//消息总数最大只显示 99
						$(".dowebok").val("1111");
					}
				}
			}
		});
	}
});