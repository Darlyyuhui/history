// JavaScript Document
/*左侧菜单上下展开闭合*/
$(function(){
    $(".a_part").click(function(){
        if($(this).next("ul").css("display")=="block")
        {
            $(this).css("background", "url(picone/nav_head_bg_h.png)");
            $(this).next("ul").hide(200);
        }
        else
        {
            $(this).css("background", "url(picone/nav_head_bg.png)");
            $(this).next("ul").show(400);
        }
    });
})  

//单行滚动最新消息
function scroll_news(){
         //alert("测试顶部告警");
}
var width=$(window).width();
var height=$(window).height();
var ww=width;
var hh=height;
function aa(id){
	//获取当前皮肤值
	var skin1=$("#skin-sel option:selected").val();
     window.childWindow=window.open(webroot+"cross/black/viewid/"+id+"/?page=1&skin1="+skin1,"child","height="+hh+" , width="+ww+",   toolbar = no ,   menubar= no, statusbar=no,  scrollbars=no,   resizable=no,   location=no,   status=no , top=0, left=0");
     window.childWindow.focus();
    
}

setInterval('scroll_news()',5000);


