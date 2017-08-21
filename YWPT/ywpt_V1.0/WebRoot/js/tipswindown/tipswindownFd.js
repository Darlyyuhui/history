///-------------------------------------------------------------------------
//jQuery弹出窗口 By Await [2009-11-22]
//--------------------------------------------------------------------------
/*参数：[可选参数在调用时可写可不写,其他为必写]
----------------------------------------------------------------------------
    title:  窗口标题
  content:  内容(可选内容为){ text | id | img | url | iframe }
    width:  内容宽度
   height:  内容高度
     drag:  是否可以拖动(ture为是,false为否)
     time:  自动关闭等待的时间，为空是则不自动关闭
   showbg:  [可选参数]设置是否显示遮罩层(0为不显示,1为显示)
  cssName:  [可选参数]附加class名称,
  left:弹出框的距离左边象数值
  top:弹出框的距离顶部象数值
 ------------------------------------------------------------------------*/
 //示例:
 //------------------------------------------------------------------------
 //simpleWindown("例子","text:例子","500","400","true","3000","0","exa",'10','5')
 //------------------------------------------------------------------------
var showWindown = true;
var templateSrc = "http://leotheme.cn/wp-content/themes/Dreamy"; //设置loading.gif路径
function tipsWindown(title,content,width,height,drag,time,showbg,cssName,left,top) {
       //设置最大窗口宽度
    var width = width;        //设置最大窗口宽度
    var height = height>= 527?this.height=527:this.height=height;  //设置最大窗口高度
    if(showWindown == true) {

        var simpleWindown_html = new String;
//            simpleWindown_html = "<div id=\"windownbg\" style=\"height:"+$(document).height()+"px;filter:alpha(opacity=0);opacity:0;z-index: 999901\"></div>";
            simpleWindown_html = "<div id=\"windownbg\" style=\"height:837px;filter:alpha(opacity=0);opacity:0;z-index: 999901\"></div>";
            simpleWindown_html += "<div id=\"windown-box\">";
            simpleWindown_html += "<div id=\"windown-title\"><h2 style='padding:0;margin:0'></h2><span id=\"windown-close\">关闭</span></div>";
            simpleWindown_html += "<div id=\"windown-content-border\" style=\"padding:0;margin:0;\"><div id=\"windown-content\" style=\"width:1020px;height:430px;padding:0;overflow:hidden;background:#ddd;\"></div></div>"; 
            simpleWindown_html += "</div>";
            //simpleWindown_html +="<iframe id=\"DivShim\" src=\"javascript:false;\" scrolling=\"no\" frameborder=\"0\" style=\"height:"+$(document).height()+"px; display:none;\"></iframe>";
            $("body").append(simpleWindown_html);
            show=false;
            //show = false;
    }
  //DivSetVisible(true);
    contentType = content.substring(0,content.indexOf(":"));
    content = content.substring(content.indexOf(":")+1,content.length);
    switch(contentType) {
        case "text":
        $("#windown-content").html(content);
        break;
        case "id":
        $("#windown-content").html($("#"+content+"").html());
        break;
        case "img":
        $("#windown-content").ajaxStart(function() {
//          $(this).html("<img src='"+templateSrc+"/images/loading.gif' class='loading' />");
        });
        $.ajax({
            error:function(){
                $("#windown-content").html("<p class='windown-error'>加载数据出错...</p>");
            },
            success:function(html){
                $("#windown-content").html("<img src="+content+" alt='' />");
            }
        });
        break;
        case "url":
        var content_array=content.split("?");
        $("#windown-content").ajaxStart(function(){
//          $(this).html("<img src='"+templateSrc+"/images/loading.gif' class='loading' />");
        });
        $.ajax({
            type:content_array[0],
            url:content_array[1],
            data:content_array[2],
            dataType : "json",
            error:function(){
                $("#windown-content").html("<p class='windown-error'>加载数据出错...</p>");
            },
            success:function(html){
                if(html!=""){
                    var table=$("<table border='0'></table>")
                var tr= $("<tr height=\"60\"></tr>");
                var tr1=    $("<tr height=\"60\"></tr>")
                var tr2=    $("<tr height=\"60\"></tr>")
                var tr3=    $("<tr height=\"60\"></tr>")
                for(var i=0;i<html.length;i++){
                        if(i<5){
                        var td=$("<td width=\"80px\"><a  href='#' onclick=\"choose('"+html[i][2]+"')\">"+html[i][2]+"</a></td>")
                        tr.append(td);
                        table.append(tr);
                        $("#windown-content").append(table);
                        }else if(i>=5&&i<10){
                            var td=$("<td width=\"80px\"><a  href='#' onclick=\"choose('"+html[i][2]+"')\">"+html[i][2]+"</a></td>")
                        tr1.append(td);
                        table.append(tr1);
                        $("#windown-content").append(table);
                        }else if(i>=10&&i<15){
                            var td=$("<td width=\"80px\"><a  href='#' onclick=\"choose('"+html[i][2]+"')\">"+html[i][2]+"</a></td>")
                        tr2.append(td);
                        table.append(tr2);
                        $("#windown-content").append(table);
                        }else if(i>=15&&i<20){
                        var td=$("<td width=\"80px\"><a  href='#' onclick=\"choose('"+html[i][2]+"')\">"+html[i][2]+"</a></td>")
                        tr3.append(td);
                        table.append(tr3);
                        $("#windown-content").append(table);}else{}
                }
                //得到年度
                var teamfenyeniandu=$("#teamfenyeniandu").val();
                //获取总页数
                var pageCountNumber=$("#pageCountNumber").val();
                //获取当前页
                var pageCurrent=$("#pageCurrent").val();
                //每页显示的条数
                var currentNumber=$("#currentNumber").val();
                
                
                $("#windown-content").append("<a href=\"#\" style=\"font-size: 12\" onClick=\"upShouye('"+teamfenyeniandu+"','"+1+"','"+currentNumber+"')\">首页</a>");
                if(parseInt(pageCurrent)>1){
                    var nn=parseInt(pageCurrent)-1;
                    $("#windown-content").append("<a href=\"#\" style=\"font-size: 12\" onClick=\"upPage('"+teamfenyeniandu+"','"+nn+"','"+currentNumber+"')\">上一页</a>");
                }
                if(parseInt(pageCurrent)<pageCountNumber){
                //得到下一页的页数
                var n=parseInt(pageCurrent)+1;
                //把页数写到页面上
                
                $("#windown-content").append("<a href=\"#\" style=\"font-size: 12\" onClick=\"upPage('"+teamfenyeniandu+"','"+n+"','"+currentNumber+"')\">下一页</a>");
                }
                $("#windown-content").append("<a href=\"#\" style=\"font-size: 12\" onClick=\"upmoye('"+teamfenyeniandu+"','"+pageCountNumber+"','"+currentNumber+"')\">末页</a>");
                
                
                }else{}
//              $("#windown-content").html(html);
            }
        });
        break;
        case "iframe":
        $("#windown-content").html("<iframe  src='"+content+"' width='100%' height='"+parseInt(height)+"' scrolling='no' frameborder='0' style='margin:0;padding:0;'></iframe>");
    }
    $("#windown-title h2").html(title);
    //遮盖层是否弹出
    if(showbg == "true") {$("#windownbg").show();}else {$("#windownbg").remove();};
    $("#windownbg").animate({opacity:"0.8"},"normal");//设置透明度
   
    $("#windown-box").show();
    if( height >= 527 ) {
        $("#windown-title").css({width:(parseInt(width)+22)+"px"});
        //$("#windown-content").css({width:(parseInt(width)+17)+"px",height:height+"px"});
        $("#windown-content").css("width","1020px");
    }else {
        //$("#windown-title").css({width:(parseInt(width)+10)+"px"});
        $("#windown-title").css("width","1020px");
        //$("#windown-content").css({width:width+"px",height:height+"px"});
        $("#windown-content").css("width","1020px");
    }

    var cw = document.documentElement.clientWidth,ch = document.documentElement.clientHeight,est = document.documentElement.scrollTop; 
    var _version = $.browser.version;
    if(top==''&&top==''){
          if ( _version == 6.0 ) {
            $("#windown-box").css({left:"50%",top:(parseInt((ch)/2)+est)+"px",marginTop: -((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "999999"});
        }else {
            $("#windown-box").css({left:"50%",top:"50%",marginTop:-((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "999999"});
        };
    }else{
       
        $("#windown-box").css({left:left+"px",top:top+"px",zIndex: "999999"});
         $("#windownbg").animate({opacity:"0"},"normal");//设置透明度
    }
   
     
    var Drag_ID = document.getElementById("windown-box"),DragHead = document.getElementById("windown-title");
        
    var moveX = 0,moveY = 0,moveTop,moveLeft = 0,moveable = false;
        if ( _version == 6.0 ) {
            moveTop = est;
        }else {
            moveTop = 0;
        }
    var sw = Drag_ID.scrollWidth,sh = Drag_ID.scrollHeight;
        DragHead.onmouseover = function(e) {
            if(drag == "true"){DragHead.style.cursor = "move";}else{DragHead.style.cursor = "default";}
        };
        DragHead.onmousedown = function(e) {
        if(drag == "true"){moveable = true;}else{moveable = false;}
        e = window.event?window.event:e;
        var ol = Drag_ID.offsetLeft, ot = Drag_ID.offsetTop-moveTop;
        moveX = e.clientX-ol;
        moveY = e.clientY-ot;
        document.onmousemove = function(e) {
                if (moveable) {
                e = window.event?window.event:e;
                var x = e.clientX - moveX;
                var y = e.clientY - moveY;
                    if ( x > 0 &&( x + sw < cw) && y > 0 && (y + sh < ch) ) {
                        Drag_ID.style.left = x + "px";
                        Drag_ID.style.top = parseInt(y+moveTop) + "px";
                        Drag_ID.style.margin = "auto";
                        }
                    }
                }
        document.onmouseup = function () {moveable = false;};
        Drag_ID.onselectstart = function(e){return false;}
    }
    $("#windown-content").attr("class","windown-"+cssName);
    var closeWindown = function() {
        $("#windownbg").remove();
        $("#windown-box").fadeOut("slow",function(){$(this).remove();});
    }
    if( time == "" || typeof(time) == "undefined") {
        $("#windown-close").click(function() {
            $("#windownbg").remove();
            $("#windown-box").fadeOut("slow",function(){$(this).remove();});
        });
    }else { 
        setTimeout(closeWindown,time);
    }
}
/**
 * 分页函数
 * @param {} niandu  年度
 * @param {} currentPage  分页的页数
 * @param {} currentNumber 每页显示多少条
 */
function upPage(niandu,currentPage,currentNumber){
//  DivSetVisible(true);
        //把页数写到页面上
                $("#pageCurrent").val(currentPage)
tipsWindown('队伍名称','url:post?/cqyt/DictDataServlet?h=1&niandufenye='+niandu+'&fenyePage='+currentPage+'&fenyePageCurrent='+currentNumber+'','400','300','true','','true','text');
}
/**
 * 首页方法
 * @param {} niandu
 * @param {} currentPage
 * @param {} currentNumber
 */
function upShouye(niandu,currentPage,currentNumber){
//  DivSetVisible(true);
    $("#pageCurrent").val(currentPage);
tipsWindown('队伍名称','url:post?/cqyt/DictDataServlet?h=1&niandufenye='+niandu+'&fenyePage='+currentPage+'&fenyePageCurrent='+currentNumber+'','400','300','true','','true','text');
}
/**
 *末页方法
 * @param {} niandu
 * @param {} currentPage
 * @param {} currentNumber
 */
function upmoye(niandu,currentPage,currentNumber){
//  DivSetVisible(true);
    $("#pageCurrent").val(currentPage);
tipsWindown('队伍名称','url:post?/cqyt/DictDataServlet?h=1&niandufenye='+niandu+'&fenyePage='+currentPage+'&fenyePageCurrent='+currentNumber+'','400','300','true','','true','text');
}
function getPageCountNumber(niandu,currentNumber){
//  DivSetVisible(true);
    tipsWindown('队伍名称','url:post?/cqyt/DictDataServlet?h=1&niandufenye=2010&currentNumber='+currentNumber+'','400','300','true','','true','text');
}
function choose(teamName){
//项目组
    $("#text2").val(teamName);
    $("#windownbg").remove();
            $("#windown-box").fadeOut("slow",function(){$(this).remove();});
//      opener.window.document.all.text2.value=teamName;
//   $("#windownbgu").toggle();
//  $("#windown-box").toggle();
 
}
/**
 * 显示层时先调用iframevar win = new Ext.Window({});
 * @param {} state
 */
function DivSetVisible(state){
  var DivRef = document.getElementById('windownbg');
  var IfrRef = document.getElementById('DivShim');
  if(state){
  DivRef.style.display = "block";
  IfrRef.style.width = DivRef.offsetWidth;
  IfrRef.style.height = DivRef.offsetHeight;
  IfrRef.style.top = DivRef.style.top;
  IfrRef.style.left = DivRef.style.left;
  IfrRef.style.zIndex = DivRef.style.zIndex - 1;
  IfrRef.style.display = "block";
  }else{
  DivRef.style.display = "none";
  IfrRef.style.display = "none";
  }
  }
