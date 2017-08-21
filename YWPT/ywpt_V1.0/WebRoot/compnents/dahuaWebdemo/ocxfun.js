// JavaScript Document
var gWndId = 0;
var nDirect=-1;
var nOper=-1;
var gXmlRecords;
var gRecordPath;

//javascript/js的ajax的POST请求：
/* 创建 XMLHttpRequest 对象 */ 
var xmlHttp; 
function GetXmlHttpObject(){ 
    if (window.XMLHttpRequest){ 
      // code for IE7+, Firefox, Chrome, Opera, Safari 
      xmlhttp=new XMLHttpRequest(); 
    }else{// code for IE6, IE5 
      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); 
    } 
    return xmlhttp; 
} 
// -----------ajax方法-----------// 
function getLabelsPost(){ 
//    xmlHttp=GetXmlHttpObject(); 
//    if (xmlHttp==null){ 
//        alert('您的浏览器不支持AJAX！'); 
//        return; 
//    } 
//    var url="http://www.lifefrom.com/t/"+Math.random(); 
//    xmlhttp.open("POST",url,true); 
//    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
//    xmlhttp.send(); 
//    xmlHttp.onreadystatechange=getLabelsOK;//发送事件后，收到信息了调用函数 
} 
function getOkPost(){ 
    if(xmlHttp.readyState==1||xmlHttp.readyState==2||xmlHttp.readyState==3){ 
        // 本地提示：加载中/处理中 
                                                  
    } 
    if (xmlHttp.readyState==4 && xmlHttp.status==200){ 
        var d=xmlHttp.responseText; // 返回值 
        // 处理返回值 
    } 
} 


function init(){
    ButtonCreateWnd_onclick();
    //加载设备树
    setTimeout("ButtonLogin_onclick()",5000);
}
function ShowCallRetInfo(nRet, strInfo) 
{
    if (nRet != 0)
    {
        var obj = document.getElementById("DPSDK_OCX");
         try{
         alert(strInfo + ": ErrorCode = "+obj.DPSDK_GetLastError());
         }catch(e){}
    }
}
function getDate(strDate) 
{
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
    function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
}

function ButtonCreateWnd_onclick() 
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    gWndId = obj.DPSDK_CreateSmartWnd(0, 0, 100, 100);
    var nWndCount = document.getElementById("textWndNum").value;
    //alert(nWndCount);
    obj.DPSDK_SetWndCount(gWndId, nWndCount); 
    obj.DPSDK_SetSelWnd(gWndId, 0); 
    
    }catch(e){}
}

function ButtonLogin_onclick() 
{
	debugger;
    var obj = document.getElementById("DPSDK_OCX");
     try{
    var szIp = document.getElementById("textIP").value;
    var nPort = document.getElementById("textPort").value;
    var szUsername = document.getElementById("textUser").value;
    var szPassword = document.getElementById("textPassword").value;
    
	ShowCallRetInfo(obj.DPSDK_Login(szIp, nPort, szUsername, szPassword), "登录");
    ButtonLoadDGroupInfo_onclick();
    
    }catch(e){
    	alert("浏览器未安装ocx插件，请在管理员下拉菜单中下载ocx插件，并按照文档提示，对IE浏览器进行相关设置！");
    }
}



//javascript/js的ajax的GET请求：
 


   function chg()
   {
    var value = document.getElementByIdx_x_x_x( 'sel' ).value;
    if( '' != value )
    {
     //alert( value );
     ajaxFunction( './get.php?data='+value );    // 注意在后缀.php之后加传值是先用?分隔再添加数据
    }
   }




function ButtonLogout_onclick() 
{
    var obj = document.getElementById("DPSDK_OCX");
      try{
       ShowCallRetInfo(obj.DPSDK_Logout(), "登出");
      }catch(e){}
   
}

var MoveTest = {
    errorMsg : "放错了...请选择正确的类别！",
    curTarget : null,
    curTmpTarget : null,
    noSel : function() {
        try {
            window.getSelection
                    ? window.getSelection().removeAllRanges()
                    : document.selection.empty();
        } catch (e) {
        }
    },
    dragTree2Dom : function(treeId, treeNodes) {
        debugger;
        // 如果是单拖设备树，支持单拖
        if (treeId == 'dandevices') {
            return true;
        }

        // 按部门分组支持树节点第一级拖动
        // treebyNormal
        if (treeId == 'treebyNormal') {
            if (treeNodes[0].isParent == true && treeNodes[0].pId == null) {
                isdeviceFlag = "0";// 设置拖动节点标识，0是部门节点不是设备节点
                return true;
            }
            if (treeNodes[0].nodeType == 'device') {// 设备支持拖动
                isdeviceFlag = "1";// 设置拖动节点标识，1是设备节点
                return true;
            }
        }
        // alert(treeNodes[0].isParent+"=="+treeNodes[0].pId);
        // 其他不支持单拖
        if (treeNodes[0].isParent == true && treeNodes[0].pId != null) {
            isdeviceFlag = "0";// 设置拖动节点标识，0是道路节点不是设备节点
            return true;
        } else {
            if (treeNodes[0].nodeType == 'device') {// 设备支持拖动
                isdeviceFlag = "1";// 设置拖动节点标识，1是设备节点
                return true;
            } else {
                return false;
            }
        }
    },
    prevTree : function(treeId, treeNodes, targetNode) {
        return !targetNode.isParent
                && targetNode.parentTId == treeNodes[0].parentTId;
    },
    nextTree : function(treeId, treeNodes, targetNode) {
        return !targetNode.isParent
                && targetNode.parentTId == treeNodes[0].parentTId;
    },
    innerTree : function(treeId, treeNodes, targetNode) {
        return targetNode != null && targetNode.isParent
                && targetNode.tId == treeNodes[0].parentTId;
    },
    dropTree2Dom : function(e, treeId, treeNodes, targetNode, moveType) {
        $("#_left_up").unbind('click');
        $("#_right_down").unbind('click');
        if (screen_index == 1) {
            debugger;
            // 单拖设备处理
            if (treeId == "dandevices") {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                var code = zTree.getSelectedNodes()[0].code;
                var name = zTree.getSelectedNodes()[0].name;
                // 计算出x轴差值
                var xpoint = $("#pull-left-div").width();
                window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

            } else {
                var one_deviceNo = "";
                var domId = "dom_2";
                if (true) {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    var ddddd = zTree.getSelectedNodes()[0].tId;
                    // 节点属性是否是设备
                    var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
                    if (nodeTypeFlag == 'device') {
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        var code = zTree.getSelectedNodes()[0].code;
                        if (lastNodeMoveDeviceFlag == '0') {
                            // 清除当前屏的所有刷新
                            //window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var name = zTree.getSelectedNodes()[0].name;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        window.frames['dddddddddddddd'].xypoint(code, name, e,
                                xpoint);
                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');

                    } else {
                        // 存储不是设备下标索引数组
                        var index_data1 = new Array();
                        // 存储是设备下标索引数组
                        var index_device_data1 = new Array();

                        // 原来获取拖拽节点下的叶子节点
                        // var nodeList = $("#"+ddddd+" ul li
                        // a").find("span:first-child");
                        // 获取拖拽节点下的孩子节点
                        var liNodeList = $("#" + ddddd + ">ul>li");
                        for (var i = 0; i < liNodeList.length; i++) {
                            // alert(liNodeList[i].innerHTML);
                            // 判断孩子节点是否是叶子节点
                            if (liNodeList[i].lastChild.nodeName == "A") {
                                var id = liNodeList[i].lastChild.firstChild.id;
                                var style = $("#" + id).attr("style");
                                if (style == undefined || style == '') {
                                    // 将下标追加到数组最后
                                    index_data1.push(i);
                                } else {
                                    index_device_data1.push(i)
                                }

                            }
                        }
                        // 确定有几个设备
                        var deviceCount = index_device_data1.length;
                        if (deviceCount >= 1) {
                            // 判断当前刷新界面是否有屏在刷新，0是没有
                            if (oneScreenDevice.length != 0) {

                                // 注销上一次屏幕刷新设备编号
                                // window.frames['dddddddddddddd'].frames["oneScreenImg1"]
                                // .init(
                                // oneScreenDevice[0],
                                // treeNodes[0].children[index_device_data1[0]].code);
                                // 停止原来的
                                window.frames['dddddddddddddd'].stopvideo(
                                        oneScreenDevice[0], 1);
                                // 清除当前屏数所有的选中操作
                                window.frames['dddddddddddddd'].cleanSelected();
                                // 加载新拖的
                                window.frames['dddddddddddddd']
                                        .xypointMany(
                                                treeNodes[0].children[index_device_data1[0]].code,
                                                treeNodes[0].children[index_device_data1[0]].name);
                                $("#_right_down").attr('style', 'display:none');
                                $("#_left_up").attr('style', 'display:none');
                            } else {
                                // window.frames['dddddddddddddd'].frames["oneScreenImg1"]
                                // .init(
                                // null,
                                // treeNodes[0].children[index_device_data1[0]].code);
                                window.frames['dddddddddddddd']
                                        .xypointMany(
                                                treeNodes[0].children[index_device_data1[0]].code,
                                                treeNodes[0].children[index_device_data1[0]].name);
                            }
                            one_deviceNO = treeNodes[0].children[index_device_data1[0]].code;
                            oneScreenDevice[0] = treeNodes[0].children[index_device_data1[0]].code;
                        }

                        if (lastNodeMoveDeviceFlag == '1') {
                            // 清除全部所有屏
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
                        } 
//                        else {// 上一次操作时点击的视频设备
//                          // 清除全部所有屏
//                          window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
//                      }
                        lastNodeMoveDeviceFlag = isdeviceFlag;

                        // window.frames['dddddddddddddd'].frames["oneScreenImg1"].init(treeNodes[0].children[0].code);
                        // $("#_left_up").attr('style','display:inline');
                        var pno = 1;
                        $("#_left_up").click(function() {
                            pno--;
                            // var ptotalNum=treeNodes[0].children.length;
                            var up_ptotalNum = deviceCount;
                            left(pno, screen_index, up_ptotalNum, treeNodes,
                                    index_device_data1);
                        });
                        treeNode = treeNodes;
                        if (deviceCount > 1) {
                            $("#_right_down").attr('style', 'display:inline');
                        }

                        $("#_right_down").click(function() {
                            // aaa('${root}/cross/monitore/oneScreen','1');
                            pno++;
                            // var down_ptotalNum=treeNodes[0].children.length;
                            var down_ptotalNum = deviceCount;
                            // //alert(down_ptotalNum);
                            right(pno, screen_index, down_ptotalNum, treeNodes,
                                    index_device_data1);
                        });
                        // MoveTest.updateType();
                    }

                } else if ($(e.target).parents(".domBtnDiv").length > 0) {
                    // alert(MoveTest.errorMsg);
                }

            }
            // window.location.href='${root}/cross/monitore/init?screen=1&deviceCode=610122459602';
            // 系统启动时所有的设备信息jms主题，这里不需要请求后台
            // window.location.href='${root}/cross/monitore/init?screen=1&deviceCode='+one_deviceNO;
        } else if (screen_index == 2) {
            // 单拖设备处理
            if (treeId == "dandevices") {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                var code = zTree.getSelectedNodes()[0].code;
                var name = zTree.getSelectedNodes()[0].name;
                // 计算出x轴差值
                var xpoint = $("#pull-left-div").width();
                window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

            } else {
                $('#_right_down').unbind('click', function() {
                        });
                var two_deviceNO = "";
                var domId = "dom_2";
                if (true) {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    var ddddd = zTree.getSelectedNodes()[0].tId;
                    // 节点属性是否是设备
                    var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
                    if (nodeTypeFlag == 'device') {
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        var code = zTree.getSelectedNodes()[0].code;
                        if (lastNodeMoveDeviceFlag == '0') {
                            // 清除当前屏的所有刷新
//                          window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var name = zTree.getSelectedNodes()[0].name;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        window.frames['dddddddddddddd'].xypoint(code, name, e,
                                xpoint);
                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');

                    } else {
                        // 存储不是设备下标索引数组
                        var index_data2 = new Array();
                        // 存储是设备下标索引数组
                        var index_device_data2 = new Array();

                        // 获取拖拽节点下的孩子节点
                        var liNodeList = $("#" + ddddd + ">ul>li");
                        for (var i = 0; i < liNodeList.length; i++) {
                            // alert(liNodeList[i].innerHTML);
                            // 判断孩子节点是否是叶子节点
                            if (liNodeList[i].lastChild.nodeName == "A") {
                                var id = liNodeList[i].lastChild.firstChild.id;
                                var style = $("#" + id).attr("style");
                                if (style == undefined || style == '') {
                                    // 将下标追加到数组最后
                                    index_data2.push(i);
                                } else {
                                    index_device_data2.push(i)
                                }

                            }
                        }

                        // 确定有几个设备
                        var deviceCount = index_device_data2.length;
                        // 判断当前刷新界面是否有屏在刷新，0是没有
                        if (twoScreenDevice.length != 0) {

                            $("#_right_down").attr('style', 'display:none');
                            $("#_left_up").attr('style', 'display:none');
                            if (twoScreenDevice.length == 1) {
                                // window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
                                // .unlogout(twoScreenDevice[0]);
                                window.frames['dddddddddddddd'].stopvideo(
                                        twoScreenDevice[0], 1);
                            } else if (twoScreenDevice.length == 2) {
                                // // 注销上一次屏幕刷新设备编号
                                // window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
                                // .unlogout(twoScreenDevice[0]);
                                // window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
                                // .unlogout(twoScreenDevice[1]);
                                window.frames['dddddddddddddd'].stopvideo(
                                        twoScreenDevice[0], 1);
                                window.frames['dddddddddddddd'].stopvideo(
                                        twoScreenDevice[1], 2);
                            } else {
                            }
                            // 清除当前屏数所有的选中操作
                            window.frames['dddddddddddddd'].cleanSelected();
                        }
                        if (lastNodeMoveDeviceFlag == '1') {
                            // 清除全部所有屏
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
                        } 
//                        else {// 上一次操作时点击的视频设备
//                          // 清除全部所有屏
//                          window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
//                      }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        // 替换下面代码
                        if (deviceCount >= 2) {
                            var code = "";
                            var name = "";
                            for (var i = 0; i < 2; i++) {
                                if (i == 1) {
                                    two_deviceNO += treeNodes[0].children[index_device_data2[i]].code;
                                    code += treeNodes[0].children[index_device_data2[i]].code;
                                    name += treeNodes[0].children[index_device_data2[i]].name;
                                } else {
                                    two_deviceNO += treeNodes[0].children[index_device_data2[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data2[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data2[i]].name
                                            + ",";
                                }
                                twoScreenDevice[i] = treeNodes[0].children[index_device_data2[i]].code;
                            }
                            window.frames['dddddddddddddd'].xypointMany(code,
                                    name, e, xpoint);

                        } else {
                            if (deviceCount == 1) {

                                two_deviceNO = treeNodes[0].children[index_device_data2[0]].code;
                                var code = treeNodes[0].children[index_device_data2[0]].code;
                                var name = treeNodes[0].children[index_device_data2[0]].name;
                                twoScreenDevice[0] = treeNodes[0].children[index_device_data2[0]].code
                                window.frames['dddddddddddddd'].xypointMany(
                                        code, name, e, xpoint);
                            }
                        }
                        // $("#_left_up").attr('style','display:none');
                        var pno = 1;
                        $("#_left_up").click(function() {
                            pno--;
                            // var ptotalNum=treeNodes[0].children.length;
                            var up_ptotalNum = deviceCount;
                            left(pno, screen_index, up_ptotalNum, treeNodes,
                                    index_device_data2);
                        });
                        treeNode = treeNodes;

                        if (deviceCount > 2) {
                            // $("#_right_down").attr('style','display:inline');
                            $("#_right_down").attr('style', 'display:block');
                        }
                        // $("#_right_down_img").attr('alt','下一页')

                        $("#_right_down").click(function() {
                            // aaa('${root}/cross/monitore/oneScreen','1');
                            pno++;
                            // var ptotalNum=treeNodes[0].children.length;
                            var down_ptotalNum = deviceCount;
                            right(pno, screen_index, down_ptotalNum, treeNodes,
                                    index_device_data2);

                        });
                    }
                } else if ($(e.target).parents(".domBtnDiv").length > 0) {
                    // alert(MoveTest.errorMsg);
                }
            }
            // 系统启动时所有的设备信息jms主题，这里不需要请求后台
            // window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
        } else if (screen_index == 4) { 
             debugger;
            // 单拖设备处理
            if (treeId == "dandevices") {
                // window.frames['dddddddddddddd'].frames["fourScreenImg_"+(i+1)+"_iframe"].unlogout(fourScreenDevice[i]);
                // window.frames['dddddddddddddd'].document.getElementById("div1").innerHTML="888888888";
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                //zTree.removeNode(treeNodes[0]);
                var code = zTree.getSelectedNodes()[0].code;
                var name = zTree.getSelectedNodes()[0].name;
                var odiv1 = window.frames['nopage_iframe'].document
                        .getElementById("div1")
                var odiv2 = window.frames['nopage_iframe'].document
                        .getElementById("div2")
                var odiv3 = window.frames['nopage_iframe'].document
                        .getElementById("div3")
                var odiv4 = window.frames['nopage_iframe'].document
                        .getElementById("div4")
                // odiv1=document.getElementById('div1');
                // 左上角x\y坐标
                odiv1_x = odiv1.getBoundingClientRect().left;
                odiv1_y = odiv1.getBoundingClientRect().top;
                // 右下角x,y坐标
                odiv1_x1 = odiv1.getBoundingClientRect().right;
                odiv1_y1 = odiv1.getBoundingClientRect().bottom;
                // 左上角x\y坐标
                odiv2_x = odiv2.getBoundingClientRect().left;
                odiv2_y = odiv2.getBoundingClientRect().top;
                // 右下角x,y坐标
                odiv2_x1 = odiv2.getBoundingClientRect().right;
                odiv2_y1 = odiv2.getBoundingClientRect().bottom;
                // 左上角x\y坐标
                odiv3_x = odiv3.getBoundingClientRect().left;
                odiv3_y = odiv3.getBoundingClientRect().top;
                // 右下角x,y坐标
                odiv3_x1 = odiv3.getBoundingClientRect().right;
                odiv3_y1 = odiv3.getBoundingClientRect().bottom;
                // 左上角x\y坐标
                odiv4_x = odiv4.getBoundingClientRect().left;
                odiv4_y = odiv4.getBoundingClientRect().top;
                // 右下角x,y坐标
                odiv4_x1 = odiv4.getBoundingClientRect().right;
                odiv4_y1 = odiv4.getBoundingClientRect().bottom;
                /*
                 * //alert("("+odiv1_x+","+odiv1_y+")--("+odiv1_x1+","+odiv1_y1+")");
                 * //alert("("+odiv2_x+","+odiv2_y+")--("+odiv2_x1+","+odiv2_y1+")");
                 * //alert("("+odiv3_x+","+odiv3_y+")--("+odiv3_x1+","+odiv3_y1+")");
                 * //alert("("+odiv4_x+","+odiv4_y+")--("+odiv4_x1+","+odiv4_y1+")");
                 * //alert(e.pageX+","+e.pageY);
                 */
                // 计算出x轴差值
                var xpoint = $("#pull-left-div").width();
                window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

            } else {
                debugger;
                // 其他处理
                var four_deviceNO = "";
                // alert(treeNodes[0].getParentNode().id);
                // var domId = "dom_" + treeNodes[0].getParentNode().id;
                var domId = "dom_2";
                // if (moveType == null && (domId == e.target.id ||
                // $(e.target).parents("#" + domId).length > 0)) {
                if (true) {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    // zTree.removeNode(treeNodes[0]);
                    var ddddd = zTree.getSelectedNodes()[0].tId;
                    // 节点属性是否是设备
                    var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
                    if (nodeTypeFlag == 'device') {

                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        var code = zTree.getSelectedNodes()[0].code;
                        // alert("last2="+lastNodeMoveDeviceFlag+",isdevic2="+isdeviceFlag)
                        if (lastNodeMoveDeviceFlag == '0') {
                            // 清除当前屏的所有刷新
                            //window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var name = zTree.getSelectedNodes()[0].name;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        window.frames['dddddddddddddd'].xypoint(code, name, e,
                                xpoint);
                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');
                        // alert("last3="+lastNodeMoveDeviceFlag+",isdevic3="+isdeviceFlag)
                    } else {
                        // 存储不是设备下标索引数组
                        var index_data4 = new Array();
                        // 存储是设备下标索引数组
                        var index_device_data4 = new Array();
                        /*
                         * var nodeList = $("#"+ddddd+" ul li
                         * a").find("span:first-child"); for(var i=0;i<nodeList.length;i++) {
                         * var id =nodeList[i].id; var style =
                         * $("#"+id).attr('style'); if(style==undefined ||
                         * style=='') { //将下标追加到数组最后 index_data4.push(i); }else{
                         * index_device_data4.push(i) } }
                         */
                        // 获取拖拽节点下的孩子节点
                        var liNodeList = $("#" + ddddd + ">ul>li");
                        for (var i = 0; i < liNodeList.length; i++) {
                            // alert(liNodeList[i].innerHTML);
                            // 判断孩子节点是否是叶子节点
                            if (liNodeList[i].lastChild.nodeName == "A") {
                                var id = liNodeList[i].lastChild.firstChild.id;
                                var style = $("#" + id).attr("style");
                                if (style == undefined || style == '') {
                                    // 将下标追加到数组最后
                                    index_data4.push(i);
                                } else {
                                    index_device_data4.push(i)
                                }

                            }
                        }

                        // 确定有几个设备
                        var deviceCount = index_device_data4.length;
                        // zTree.removeNode(treeNodes[0]);
                        // alert(treeNodes[0].children.length);

                        // 判断当前刷新界面是否有屏在刷新，0是没有
                        if (fourScreenDevice.length != 0) {

                            $("#_right_down").attr('style', 'display:none');
                            $("#_left_up").attr('style', 'display:none');
                            for (var i = 0; i < fourScreenDevice.length; i++) {
                                // window.frames['dddddddddddddd'].frames["fourScreenImg_"
                                // + (i + 1) + "_iframe"]
                                // .unlogout(fourScreenDevice[i]);
                                window.frames['dddddddddddddd'].stopvideo(
                                        fourScreenDevice[i], i + 1);
                            }
                            // 清除当前屏数所有的选中操作
                            window.frames['dddddddddddddd'].cleanSelected();
                        }
                        if (lastNodeMoveDeviceFlag == '1') {
                            // 清除全部所有屏
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
                        }
                        // else{//上一次操作时点击的视频设备
                        // //清除全部所有屏
                        // window.frames['dddddddddddddd'].stopAll();//清除当前刷新页的所有刷新屏幕
                        // }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var odiv1 = window.frames['dddddddddddddd'].document
                                .getElementById("div1");
                        var odiv2 = window.frames['dddddddddddddd'].document
                                .getElementById("div2");
                        var odiv3 = window.frames['dddddddddddddd'].document
                                .getElementById("div3");
                        var odiv4 = window.frames['dddddddddddddd'].document
                                .getElementById("div4");
                        // odiv1=document.getElementById('div1');
                        // 左上角x\y坐标
                        odiv1_x = odiv1.getBoundingClientRect().left;
                        odiv1_y = odiv1.getBoundingClientRect().top;
                        // 右下角x,y坐标
                        odiv1_x1 = odiv1.getBoundingClientRect().right;
                        odiv1_y1 = odiv1.getBoundingClientRect().bottom;
                        // 左上角x\y坐标
                        odiv2_x = odiv2.getBoundingClientRect().left;
                        odiv2_y = odiv2.getBoundingClientRect().top;
                        // 右下角x,y坐标
                        odiv2_x1 = odiv2.getBoundingClientRect().right;
                        odiv2_y1 = odiv2.getBoundingClientRect().bottom;
                        // 左上角x\y坐标
                        odiv3_x = odiv3.getBoundingClientRect().left;
                        odiv3_y = odiv3.getBoundingClientRect().top;
                        // 右下角x,y坐标
                        odiv3_x1 = odiv3.getBoundingClientRect().right;
                        odiv3_y1 = odiv3.getBoundingClientRect().bottom;
                        // 左上角x\y坐标
                        odiv4_x = odiv4.getBoundingClientRect().left;
                        odiv4_y = odiv4.getBoundingClientRect().top;
                        // 右下角x,y坐标
                        odiv4_x1 = odiv4.getBoundingClientRect().right;
                        odiv4_y1 = odiv4.getBoundingClientRect().bottom;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        // 替换下面代码
                        if (deviceCount >= 4) {
                            var code = "";
                            var name = "";
                            for (var i = 0; i < 4; i++) {
                                if (i == 3) {
                                    four_deviceNO += treeNodes[0].children[index_device_data4[i]].code;
                                    code += treeNodes[0].children[index_device_data4[i]].code;
                                    name += treeNodes[0].children[index_device_data4[i]].name;
                                } else {
                                    four_deviceNO += treeNodes[0].children[index_device_data4[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data4[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data4[i]].name
                                            + ",";
                                }
                                fourScreenDevice[i] = treeNodes[0].children[index_device_data4[i]].code;
                            }
                            window.frames['dddddddddddddd'].xypointMany(code,
                                    name, e, xpoint);

                        } else {
                            if (deviceCount == 1) {
                                four_deviceNO = treeNodes[0].children[index_device_data4[0]].code;
                                fourScreenDevice[0] = treeNodes[0].children[index_device_data4[0]].code;
                                var code = treeNodes[0].children[index_device_data4[0]].code;
                                var name = treeNodes[0].children[index_device_data4[0]].name;
                                window.frames['dddddddddddddd'].xypointMany(
                                        code, name, e, xpoint);
                            }
                            if (deviceCount == 2) {
                                four_deviceNO = treeNodes[0].children[index_device_data4[0]].code
                                        + ","
                                        + treeNodes[0].children[index_device_data4[1]].code;
                                fourScreenDevice[0] = treeNodes[0].children[index_device_data4[0]].code;
                                fourScreenDevice[1] = treeNodes[0].children[index_device_data4[1]].code;
                                var code = treeNodes[0].children[index_device_data4[0]].code
                                        + ","
                                        + treeNodes[0].children[index_device_data4[1]].code;;
                                var name = treeNodes[0].children[index_device_data4[0]].name
                                        + ","
                                        + treeNodes[0].children[index_device_data4[1]].name;;
                                window.frames['dddddddddddddd'].xypointMany(
                                        code, name, e, xpoint);
                            }
                            if (deviceCount == 3) {
                                var code = ""
                                var name = "";
                                for (var i = 0; i < index_device_data4.length; i++) {
                                    if (i == 2) {
                                        four_deviceNO += treeNodes[0].children[index_device_data4[i]].code;
                                        code += treeNodes[0].children[index_device_data4[i]].code;
                                        name += treeNodes[0].children[index_device_data4[i]].name;
                                    } else {

                                        four_deviceNO += treeNodes[0].children[index_device_data4[i]].code
                                                + ",";
                                        code += treeNodes[0].children[index_device_data4[i]].code
                                                + ",";
                                        name += treeNodes[0].children[index_device_data4[i]].name
                                                + ",";
                                    }
                                    fourScreenDevice[i] = treeNodes[0].children[index_device_data4[i]].code;
                                }
                                window.frames['dddddddddddddd'].xypointMany(
                                        code, name, e, xpoint);
                            }
                        }

                        var pno = 1;
                        $("#_left_up").click(function() {
                            pno--;
                            // var ptotalNum=treeNodes[0].children.length;
                            var up_ptotalNum = deviceCount;
                            left(pno, screen_index, up_ptotalNum, treeNodes,
                                    index_device_data4);
                        });
                        treeNode = treeNodes;
                        if (deviceCount > 4) {
                            $("#_right_down").attr('style', 'display:inline');
                        }

                        $("#_right_down").click(function() {
                            // aaa('${root}/cross/monitore/oneScreen','1');
                            pno++;
                            // var ptotalNum=treeNodes[0].children.length;
                            var down_ptotalNum = deviceCount;
                            right(pno, screen_index, down_ptotalNum, treeNodes,
                                    index_device_data4);
                        });
                        // MoveTest.updateType();
                    }
                } else if ($(e.target).parents(".domBtnDiv").length > 0) {
                    // alert(MoveTest.errorMsg);
                }
            }
            // alert(four_deviceNO);
            // 系统启动时所有的设备信息jms主题，这里不需要请求后台
            // window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
        } else{
        
        }

    },
    dom2Tree : function(e, treeId, treeNode) {
        var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
        if (!target)
            return;
        var zTree = $.fn.zTree.getZTreeObj(treeId), parentNode;
        if (treeNode != null && treeNode.isParent
                && "dom_" + treeNode.id == target.parent().attr("id")) {
            parentNode = treeNode;
        } else if (treeNode != null
                && !treeNode.isParent
                && "dom_" + treeNode.getParentNode().id == target.parent()
                        .attr("id")) {
            parentNode = treeNode.getParentNode();
        }

        if (tmpTarget)
            tmpTarget.remove();
        if (!!parentNode) {
            var nodes = zTree.addNodes(parentNode, {
                        id : target.attr("domId"),
                        name : target.text()
                    });
            zTree.selectNode(nodes[0]);
        } else {
            target.removeClass("domBtn_Disabled");
            target.addClass("domBtn");
            // alert(MoveTest.errorMsg);
        }
        MoveTest.updateType();
        MoveTest.curTarget = null;
        MoveTest.curTmpTarget = null;
    },
    updateType : function() {
        // var zTree = $.fn.zTree.getZTreeObj("treebyNormal"),
        nodes = zTree.getNodes();
        for (var i = 0, l = nodes.length; i < l; i++) {
            var num = nodes[i].children ? nodes[i].children.length : 0;
            nodes[i].name = nodes[i].name.replace(/ \(.*\)/gi, "") + " (" + num
                    + ")";
            zTree.updateNode(nodes[i]);
        }
    },
    bindDom : function() {
        $(".domBtnDiv").bind("mousedown", MoveTest.bindMouseDown);
    },
    bindMouseDown : function(e) {
        var target = e.target;
        if (target != null && target.className == "domBtn") {
            var doc = $(document), target = $(target), docScrollTop = doc
                    .scrollTop(), docScrollLeft = doc.scrollLeft();
            target.addClass("domBtn_Disabled");
            target.removeClass("domBtn");
            curDom = $("<span class='dom_tmp domBtn'>" + target.text()
                    + "</span>");
            curDom.appendTo("body");

            curDom.css({
                        "top" : (e.clientY + docScrollTop + 3) + "px",
                        "left" : (e.clientX + docScrollLeft + 3) + "px"
                    });
            MoveTest.curTarget = target;
            MoveTest.curTmpTarget = curDom;

            doc.bind("mousemove", MoveTest.bindMouseMove);
            doc.bind("mouseup", MoveTest.bindMouseUp);
            doc.bind("selectstart", MoveTest.docSelect);
        }
        if (e.preventDefault) {
            e.preventDefault();
        }
    },
    bindMouseMove : function(e) {
        MoveTest.noSel();
        var doc = $(document), docScrollTop = doc.scrollTop(), docScrollLeft = doc
                .scrollLeft(), tmpTarget = MoveTest.curTmpTarget;
        if (tmpTarget) {
            tmpTarget.css({
                        "top" : (e.clientY + docScrollTop + 3) + "px",
                        "left" : (e.clientX + docScrollLeft + 3) + "px"
                    });
        }
        return false;
    },
    bindMouseUp : function(e) {
        var doc = $(document);
        doc.unbind("mousemove", MoveTest.bindMouseMove);
        doc.unbind("mouseup", MoveTest.bindMouseUp);
        doc.unbind("selectstart", MoveTest.docSelect);

        var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
        if (tmpTarget)
            tmpTarget.remove();

        if ($(e.target).parents("#" + treeId).length == 0) {
            if (target) {
                target.removeClass("domBtn_Disabled");
                target.addClass("domBtn");
            }
            MoveTest.curTarget = null;
            MoveTest.curTmpTarget = null;
        }
    },
    bindSelect : function() {
        return false;
    }
};

// 按照道路 设备树 设置
var byRoadSetting = {
    check : {
        enable : false
    },
    view : {
        dblClickExpand : false
    },
    data : {
        simpleData : {
            enable : true
        }
    },
    callback : {
       //beforeClick : beforeClickDevice,
       beforeClick : beforeClick,//大华视频点击方法
        onCheck : onCheck
//        ,
//        beforeDrag : MoveTest.dragTree2Dom,
//        onDrop : MoveTest.dropTree2Dom,
//        onMouseUp : MoveTest.dom2Tree
    },
    edit : {
        enable : true,
        showRemoveBtn : false,
        showRenameBtn : false,
        drag : {
            // prev: MoveTest.prevTree,
            // next: MoveTest.nextTree,
            // inner: MoveTest.innerTree
            prev : function() {
                return false;
            },
            next : function() {
                return false;
            },
            inner : function() {
                return false;
            }

        }
    }
};

// 按照道路 设备树 节点点击选择
function beforeClick(treeId, treeNode) {
    // alert(treeId);
    //alert("该节点不允许点击,请拖拽");
    /*
     * var zTree = $.fn.zTree.getZTreeObj("treebyNormal");
     * zTree.checkNode(treeNode, !treeNode.checked, null, true); return false;
     */
    if(treeNode.nodeType=='device'){
        document.getElementById("textCameraID").value=treeNode.id;
        var obj = document.getElementById("DPSDK_OCX");
        var szCameraId = treeNode.id;
        var nStreamType = treeNode.streamType;
        var nMediaType = treeNode.mediaType;
        var nTransType = treeNode.transType;
        var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
        ShowCallRetInfo(obj.DPSDK_StartRealplayByWndNo(gWndId, nWndNo, szCameraId, nStreamType, nMediaType, nTransType), "播放视频");
    }else{
        alert("请点击设备。");
    }
           

}




// 按照道路 设备树 节点点击选择
function beforeClickDevice(treeId, treeNode) {
//    alert($("#DPSDK_OCX").html());
    alert(treeNode.id);
    debugger;
    // var zTree = $.fn.zTree.getZTreeObj("treebyNormal");
    // zTree.checkNode(treeNode, !treeNode.checked, null, true); return false;
    // var zTree = $.fn.zTree.getZTreeObj(treeId);
    // var code = zTree.getSelectedNodes()[0].code;
    // var name = zTree.getSelectedNodes()[0].name;
    if ("dandevices" == treeId) {// 设备单拖
        // window.frames['nopage_iframe'].te();
        // alert(window.frames['nopage_iframe'].document.getElementById("imgpath1_code").value);
        iframeid = 'nopage_iframe';
    } else {
        // 按部门 按道路 自定义
        iframeid = 'dddddddddddddd';
    }
    var nWndCount = document.getElementById("textWndNum").value;
    screen_index=nWndCount;
    alert(screen_index);
    switch (parseInt(screen_index)) {
        case 1 :// 1屏
//            var one_imgpath_code_input = window.frames[iframeid].document
//                    .getElementById("imgpath1_code").value
//            if (one_imgpath_code_input == '') {
//                setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
//                        screen_index);
//            } else {
//                alert("请拖动到播放器内查看！");
//            }
             var obj = document.getElementById("DPSDK_OCX");
    
//    var szCameraId = document.getElementById("textCameraID").value;
//    var nStreamType = document.getElementById("selectStreamType").value;
//    var nMediaType = document.getElementById("selectMediaType").value;
//    var nTransType = document.getElementById("selectTransType").value;
    var szCameraId = treeNode.id;
    var nStreamType = treeNode.streamType;
    var nMediaType = treeNode.mediaType;
    var nTransType = treeNode.transType;
   alert(gWndId+"--窗口id");
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    alert(nWndNo);
    debugger;
    ShowCallRetInfo(obj.DPSDK_StartRealplayByWndNo(gWndId, nWndNo, szCameraId, nStreamType, nMediaType, nTransType), "播放视频");
    
            break;
        case 2 :
            var one_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath1_code").value;
            var two_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath2_code").value;
            if (one_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
                        screen_index);
            } else if (two_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
                        screen_index);
            } else {
                alert("请拖动到播放器内查看！");
            }

            break;
        case 4 :
            /*var one_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath1_code").value;
            var two_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath2_code").value;
            var three_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath3_code").value;
            var four_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath4_code").value;
            if (one_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
                        screen_index);
            } else if (two_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
                        screen_index);
            } else if (three_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
                        screen_index);
            } else if (four_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
                        screen_index);
            } else {
                alert("请拖动到播放器内查看！");
            }*/
        
        //getWallCount()
          var obj = document.getElementById("DPSDK_OCX");
      var szCameraId = treeNode.id;
    var nStreamType = treeNode.streamType;
    var nMediaType = treeNode.mediaType;
    var nTransType = treeNode.transType;
   alert(gWndId);
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    alert(nWndNo);
    ShowCallRetInfo(obj.DPSDK_StartRealplayByWndNo(gWndId, nWndNo, szCameraId, nStreamType, nMediaType, nTransType), "播放视频");
            break;
        case 6 :
            var one_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath1_code").value;
            var two_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath2_code").value;
            var three_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath3_code").value;
            var four_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath4_code").value;
            var five_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath5_code").value;
            var six_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath6_code").value;
            if (one_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
                        screen_index);
            } else if (two_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
                        screen_index);
            } else if (three_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
                        screen_index);
            } else if (four_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
                        screen_index);
            } else if (five_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '5', treeNode,
                        screen_index);
            } else if (six_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '6', treeNode,
                        screen_index);
            } else {
                alert("请拖动到播放器内查看！");
            }
            break;
        case 9 :
            var one_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath1_code").value;
            var two_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath2_code").value;
            var three_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath3_code").value;
            var four_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath4_code").value;
            var five_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath5_code").value;
            var six_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath6_code").value;
            var seven_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath7_code").value;
            var eight_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath8_code").value;
            var nine_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath9_code").value;
            if (one_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
                        screen_index);
            } else if (two_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
                        screen_index);
            } else if (three_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
                        screen_index);
            } else if (four_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
                        screen_index);
            } else if (five_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '5', treeNode,
                        screen_index);
            } else if (six_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '6', treeNode,
                        screen_index);
            } else if (seven_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '7', treeNode,
                        screen_index);
            } else if (eight_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '8', treeNode,
                        screen_index);
            } else if (nine_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '9', treeNode,
                        screen_index);
            } else {
                alert("请拖动到播放器内查看！");
            }
            break;
        case 16 :
            var one_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath1_code").value;
            var two_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath2_code").value;
            var three_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath3_code").value;
            var four_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath4_code").value;
            var five_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath5_code").value;
            var six_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath6_code").value;
            var seven_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath7_code").value;
            var eight_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath8_code").value;
            var nine_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath9_code").value;
            var ten_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath10_code").value;
            var eleven_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath11_code").value;
            var twelve_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath12_code").value;
            var thirteen_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath13_code").value;
            var fourteen_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath14_code").value;
            var fivteen_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath15_code").value;
            var sixteen_imgpath_code_input = window.frames[iframeid].document
                    .getElementById("imgpath16_code").value;
            if (one_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
                        screen_index);
            } else if (two_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
                        screen_index);
            } else if (three_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
                        screen_index);
            } else if (four_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
                        screen_index);
            } else if (five_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '5', treeNode,
                        screen_index);
            } else if (six_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '6', treeNode,
                        screen_index);
            } else if (seven_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '7', treeNode,
                        screen_index);
            } else if (eight_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '8', treeNode,
                        screen_index);
            } else if (nine_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '9', treeNode,
                        screen_index);
            } else if (ten_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '10', treeNode,
                        screen_index);
            } else if (eleven_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '11', treeNode,
                        screen_index);
            } else if (twelve_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '12', treeNode,
                        screen_index);
            } else if (thirteen_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '13', treeNode,
                        screen_index);
            } else if (fourteen_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '14', treeNode,
                        screen_index);
            } else if (fivteen_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '15', treeNode,
                        screen_index);
            } else if (sixteen_imgpath_code_input == '') {
                setImageCodeNameByIFrameIdInputId(iframeid, '', '16', treeNode,
                        screen_index);
            } else {
                alert("请拖动到播放器内查看！");
            }
            break;
    }

}
/**
 * 根据iframeid和inputid和第几屏,设置第几屏对应的视频设备code和name，执行播放视频
 * 
 * @param {}
 *            iframeid 分屏iframeid
 * @param {}
 *            inputid 分屏中设备编号inputid
 * @param {}
 *            pingshu 第几屏，设置第几屏的值
 * @param {}
 *            treeNode 点击设备树的树节点
 * @Param {}
 *            zongpingshu 总屏数
 */
function setImageCodeNameByIFrameIdInputId(iframeid, inputid, pingshu,
        treeNode, zongpingshu) {
            debugger;
    window.frames[iframeid].document.getElementById("imgpath" + pingshu
            + "_code").value = treeNode.code;
    window.frames[iframeid].document.getElementById("imgpath" + pingshu
            + "_name").innerHTML = treeNode.name;
    if(shizheng_poject_type=='internet'){
        //市政项目视频参数配置，加载视频用到，神州鹰视频
    
      videoPlay.load(root, iframeid, treeNode.code, treeNode.name, '2', pingshu,
            zongpingshu,shizheng_poject_type);  
    }else{//普通视频加载
        videoPlay.load(root, iframeid, treeNode.code, treeNode.name, '2', pingshu,
            zongpingshu);
    }
    
}
// 按照道路 设备树
function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree-org"), nodes = zTree
            .getCheckedNodes(true), v = "";
    var h = "";
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].name + ",";
        h += nodes[i].id + ",";
    }
}

//登陆方法
function dl(){
    debugger;
     $.ajax({
            type : "POST",
            url : "/itms/vedio/index/dahua",
            dataType : "json",
            data : {
                d : '1',
                deviceIp : '2',
                rtspName :'3'
            },
            success : function(dd) {
                debugger;
//                var dr = deviceByRoadjsonArray;
//              alert(dr);
//                var deviceByRoadNode = eval("(" + dd.msg + ")");
//                var deviceByRoadNode =dd.msg ;
                alert(document.getElementById("DPSDK_OCX").innerHTML);
                var deviceByRoadNode =dd.msg ;
                deviceTreeByRoad = $.fn.zTree.init($("#treeByRoad"), byRoadSetting,
                        deviceByRoadNode);
                deviceTreeByRoad.expandAll(false);
                
            },
            error : function() {
            }
        });

}

function ButtonLoadDGroupInfo_onclick() 
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
      ShowCallRetInfo(obj.DPSDK_LoadDGroupInfo(), "加载组织结构");
    //alert(obj.DPSDK_GetDGroupStr());
    document.getElementById("DGroupInfo").innerText = obj.DPSDK_GetDGroupStr();
   
//      xmlHttp=GetXmlHttpObject(); 
//      //alert(xmlHttp);
//    if (xmlHttp==null){ 
//        alert('您的浏览器不支持AJAX！'); 
//        return; 
//    } 
//    var url="http://localhost:8080/itms/vedio/index/dahua?d=1"; 
//        window.location.href="www.baidu.com";
//    var url="http://193.169.100.205:8080/itms/vedio/index/sendCommandInfo/1/"; 
//    xmlhttp.open("POST",url,true); 
//    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
//    xmlhttp.send(); 
//    xmlHttp.onreadystatechange=getLabelsOK;//发送事件后，收到信息了调用函数 
       $.ajax({
            type : "POST",
            url : "/itms/vedio/index/dahua",
            dataType : "json",
            data : {
                d : obj.DPSDK_GetDGroupStr(),
//                d : '1',
                deviceIp : '2',
                rtspName :'3'
            },
            success : function(dd) {
                debugger;
//                var dr = deviceByRoadjsonArray;
//              alert(dr);
//                var deviceByRoadNode = eval("(" + dd.msg + ")");
                var deviceByRoadNode =dd.msg ;
                deviceTreeByRoad = $.fn.zTree.init($("#treeByRoad"), byRoadSetting,
                        deviceByRoadNode);
                deviceTreeByRoad.expandAll(false);
                
            },
            error : function() {
            }
        });
     

    
      }catch(e){}
    
}

function ButtonStartRealplayByWndNo_onclick() 
{
    var obj = document.getElementById("DPSDK_OCX");
    
    var szCameraId = document.getElementById("textCameraID").value;
    var nStreamType = document.getElementById("selectStreamType").value;
    var nMediaType = document.getElementById("selectMediaType").value;
    var nTransType = document.getElementById("selectTransType").value;
    try{
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_StartRealplayByWndNo(gWndId, nWndNo, szCameraId, nStreamType, nMediaType, nTransType), "播放视频");
     }catch(e){}
}

function ButtonStopRealplayByWndNo_onclick() 
{
    var obj = document.getElementById("DPSDK_OCX");
     try{
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
     ShowCallRetInfo(obj.DPSDK_StopRealplayByWndNo(gWndId, nWndNo), "播放视频");
     }catch(e){}
}

function ButtonStartRecordByWndNo_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_StartRealRecordByWndNo(gWndId, nWndNo,"C:\\123.dav"), "实时视频录制");
    }catch(e){}
}

function ButtonStopRecordByWndNo_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_StopRealRecordByWndNo(gWndId, nWndNo), "停止实时视频录制");
    }catch(e){}
}

function ButtonSetOsdTxtByWndNo_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var strOSD = document.getElementById("text7").value;
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_SetOsdTxtByWndNo(gWndId, nWndNo,strOSD), "OSD文本信息下发");
    }catch(e){}
}

function ButtonCleanUpOsdInfoByWndNo_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_CleanUpOsdInfoByWndNo(gWndId, nWndNo), "清除osd信息");
    }catch(e){}
}

function ButtonCapturePictureByWndNo_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    var mydate=new Date();
    var reg=new RegExp(":","g");
    var path="c:\\dahuaphoto\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".bmp";
    alert("存储路径："+path)
    ShowCallRetInfo(obj.DPSDK_CapturePictureByWndNo(gWndId, nWndNo,path), "抓图");
    }catch(e){}
}

function ButtonStopRealplayByCameraId_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var strCameraID = document.getElementById("textCameraID").value;
    ShowCallRetInfo(obj.DPSDK_StopRealplayByCameraId(strCameraID), "关闭CAM");
    }catch(e){}
}

function ButtonPtzDirection_onclick(nDirects)
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var szCameraId = document.getElementById("textCameraID").value;
    nDirect = nDirects;
    var nStep = document.getElementById("selectPtzDirectionStep").value;
    ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, nDirect, nStep, 0), "方向控制");
    }catch(e){}
}

function ButtonPtzDirection_onclickStop(bStop)
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var szCameraId = document.getElementById("textCameraID").value;
    var nStep = document.getElementById("selectPtzDirectionStep").value;
    ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, nDirect, nStep, bStop), "方向控制");
    }catch(e){}
}

function ButtonPtzCameraOperation_onclick(nOpers)
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var szCameraId = document.getElementById("textCameraID").value;
    nOper = nOpers;
    var nStep = document.getElementById("selectCameraStep").value;
    ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nOper, nStep, 0), "镜头控制");
    }catch(e){}
}

function ButtonPtzCameraOperation_onclickStop(bStop)
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var szCameraId = document.getElementById("textCameraID").value;
    var nStep = document.getElementById("selectCameraStep").value;
    ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nOper, nStep, bStop), "镜头控制");
    }catch(e){}
}

function ButtonPtzSit_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    try{
    var szCameraId = document.getElementById("textCameraID").value; 
    var nXPosition = document.getElementById("textXPosition").value;
    var nYPosition = document.getElementById("textYPosition").value;
    var nStep = document.getElementById("selectPtzSitStep").value;
   
    ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nXPosition, nYPosition), "三维定位");
    }catch(e){}
}
function ButtonAddPrePoint_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var szCameraId = document.getElementById("textCameraID").value; 
    var nPrePointNum = document.getElementById("textPrePointNum").value;
    var strPrePointName = document.getElementById("textPrePointName").value;
    ShowCallRetInfo(obj.DPSDK_PtzPrePointOperation(szCameraId, nPrePointNum, strPrePointName,2), "增加预置点");
    }catch(e){}
}

function ButtonDelPrePoint_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
 
    var szCameraId = document.getElementById("textCameraID").value; 
    var nPrePointNum = document.getElementById("textPrePointNum").value;
    var strPrePointName = document.getElementById("textPrePointName").value;
   
    ShowCallRetInfo(obj.DPSDK_PtzPrePointOperation(szCameraId, nPrePointNum, strPrePointName,3), "删除预置点");
    }catch(e){}
}

function ButtonQueryPrePoint_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
 
    var szCameraId = document.getElementById("textCameraID").value; 
   
    alert(obj.DPSDK_QueryPrePoint(szCameraId));
    }catch(e){}
}

function ButtonSeekPrePoint_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
 
    var szCameraId = document.getElementById("textCameraID").value; 
    var nPrePointNum = document.getElementById("textPrePointNum").value;
    var strPrePointName = document.getElementById("textPrePointName").value;
   
    ShowCallRetInfo(obj.DPSDK_PtzPrePointOperation(szCameraId, nPrePointNum, strPrePointName,1), "定位预置点");
    }catch(e){}
}
function getDate(strDate) 
{
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
    function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
}

function ButtonQueryRecord_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var szCameraId = document.getElementById("textCameraID").value; 
    var nRecordSource = document.getElementById("selectRecordSource").value;
    var nRecordType = document.getElementById("selectRecordType").value;
    var strStartTime = document.getElementById("textStartTime").value;
    var strEndTime = document.getElementById("textEndTime").value;  
    var nStartTime = getDate(strStartTime).getTime()/1000;
//  alert(nStartTime);
    var nEndTime = getDate(strEndTime).getTime()/1000;
//  alert(nEndTime);
        
//    ShowCallRetInfo(obj.DPSDK_QueryRecordInfo(szCameraId, nRecordSource, nRecordType, nStartTime, nEndTime), "查询录像");

//  gXmlRecords = obj.DPSDK_QueryRecordInfo(szCameraId, nRecordSource, nRecordType, nStartTime, nEndTime);
    alert(obj.DPSDK_QueryRecordInfo(szCameraId, nRecordSource, nRecordType, nStartTime, nEndTime));
    }catch(e){}
}

function ButtonStartFilePlaybackByWndNo_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_StartFilePlaybackByWndNo(gWndId, nWndNo,0), "按文件回放");
    }catch(e){}
}

function ButtonStartTimePlaybackByWndNo_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    var szCameraId = document.getElementById("textCameraID").value; 
    var nRecordSource = document.getElementById("selectRecordSource").value;
 //   var nRecordType = document.getElementById("selectRecordType").value;
    var strStartTime = document.getElementById("textStartTime").value;
    var strEndTime = document.getElementById("textEndTime").value;  
    var nStartTime = getDate(strStartTime).getTime()/1000;
    var nEndTime = getDate(strEndTime).getTime()/1000;

    ShowCallRetInfo(obj.DPSDK_StartTimePlaybackByWndNo(gWndId, nWndNo, szCameraId, nRecordSource, nStartTime, nEndTime), "按时间回放");
    }catch(e){}
}

function ButtonPausePlaybackByWndNo_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_PausePlaybackByWndNo(gWndId, nWndNo), "暂停回放");
    }catch(e){}
}

function ButtonResumePlaybackByWndNo_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_ResumePlaybackByWndNo(gWndId, nWndNo), "继续回放");
    }catch(e){}
}

function ButtonStopPlaybackByWndNo_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_StopPlaybackByWndNo(gWndId, nWndNo), "停止回放");
    }catch(e){}
}

function ButtonSetPlaybackSpeedByWndNo_onclick(nPlaybackRate)
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    //var nPlaybackRate = document.getElementById("selectPlaybackRate").value;
    alert(nPlaybackRate);
    ShowCallRetInfo(obj.DPSDK_SetPlaybackSpeedByWndNo(gWndId, nWndNo, nPlaybackRate), "回放速度");
    }catch(e){}
}

function ButtonDownloadRecordByFile_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    var mydate=new Date();
    var reg=new RegExp(":","g");
    gRecordPath="c:\\dahuarecord\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".dav";
    alert("存储路径："+gRecordPath);
    ShowCallRetInfo(obj.DPSDK_DownloadRecordByFile(gRecordPath, 0), "按文件下载");
    }catch(e){}
}

function ButtonDownloadRecordByTime_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var szCameraId = document.getElementById("textCameraID").value; 
    var nRecordSource = document.getElementById("selectRecordSource").value;
 //   var nRecordType = document.getElementById("selectRecordType").value;
    var strStartTime = document.getElementById("textStartTime").value;
    var strEndTime = document.getElementById("textEndTime").value;  
    var nStartTime = getDate(strStartTime).getTime()/1000;
    var nEndTime = getDate(strEndTime).getTime()/1000;
    var mydate=new Date();
    var reg=new RegExp(":","g");
    gRecordPath="c:\\dahuarecord\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".dav";
    alert("存储路径："+gRecordPath);
    ShowCallRetInfo(obj.DPSDK_DownloadRecordByTime(gRecordPath, szCameraId, nRecordSource, nStartTime, nEndTime), "按时间下载");
    }catch(e){}
}

function ButtonPauseDownloadRecord_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var mydate=new Date();
    var reg=new RegExp(":","g");
    //var path="c:\\dahuarecord\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".dav";
    //alert("存储路径："+path);
    ShowCallRetInfo(obj.DPSDK_PauseDownloadRecord(gRecordPath), "暂停下载");
    }catch(e){}
}

function ButtonResumeDownloadRecord_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var mydate=new Date();
    var reg=new RegExp(":","g");
    //var path="c:\\dahuarecord\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".dav";
    //alert("存储路径："+path);
    ShowCallRetInfo(obj.DPSDK_ResumeDownloadRecord(gRecordPath), "继续下载");
    }catch(e){}
}

function ButtonStopDownloadRecord_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var mydate=new Date();
    var reg=new RegExp(":","g");
    //var path="c:\\dahuarecord\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".dav";
    //alert("存储路径："+path);
    ShowCallRetInfo(obj.DPSDK_StopDownloadRecord(gRecordPath), "停止下载");
    }catch(e){}
}

function ButtonPlaybackCapture_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    var mydate=new Date();
    var reg=new RegExp(":","g");
    var path="c:\\dahuaphoto\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".bmp";
    alert("存储路径："+path);
    ShowCallRetInfo(obj.DPSDK_CapturePictureByWndNo(gWndId, nWndNo,path), "抓图");
    }catch(e){}
}

function ButtonEnableAlarm_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var szDevID = document.getElementById("textDeviceID").value; 
    var nVideoChannel = document.getElementById("textVideoChan").value; 
    var nAlarmChannel = document.getElementById("textAlarmChan").value; 
    var nAlarmType = document.getElementById("selectAlarmType").value;
    
    ShowCallRetInfo(obj.DPSDK_EnableAlarm(szDevID, nVideoChannel, nAlarmChannel, nAlarmType), "布控");
    }catch(e){}
}

function ButtonDisableAlarm_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");
    
    ShowCallRetInfo(obj.DPSDK_DisableAlarm(), "撤控");
    }catch(e){}
}

function ButtonQueryAlarm_onclick()
{
    try{
    var obj = document.getElementById("DPSDK_OCX");

    var szCameraId = document.getElementById("textCameraID").value; 
    var nAlarmType = document.getElementById("selectAlarmType").value;
    var strStartTime = document.getElementById("textAlarmStartTime").value;
    var strEndTime = document.getElementById("textAlarmEndTime").value; 
    var nStartTime = getDate(strStartTime).getTime()/1000;
    var nEndTime = getDate(strEndTime).getTime()/1000;
    
    var nCount = obj.DPSDK_QueryAlarmCount(szCameraId, nAlarmType, nStartTime, nEndTime);
    
    if(nCount > 0)
    {
        var nIndex;
        var strAlarmInfo = obj.DPSDK_QueryAlarmInfo(szCameraId, nAlarmType, nStartTime, nEndTime, nIndex,500);
        alert(strAlarmInfo);
    }
    }catch(e){}
}


//电视墙操作方法
function getWallCount(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var count=obj.DPSDK_QueryTvWallList();
    writewall("获取电视墙总数:"+count);
    alert("获取电视墙总数:"+count);
    }catch(e){}
}
function writewall(info){
    document.getElementById("wallinfoxml").innerText=info;
}
function getWallInfo(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var allstr=obj.DPSDK_GetTvWallList();
    writewall("获取电视墙信息:"+allstr);
    var xmlDoc=loadXML(allstr);
    var elements = xmlDoc.getElementsByTagName("TvWall");
    var objSelect=document.getElementById("wallid");
    objSelect.options.length = 0;  
    for (var i = 0; i < elements.length; i++) {
        var tvWallId = elements[i].getAttribute("tvWallId");
        var name = elements[i].getAttribute("name");
        //var tvWallId = elements[i].getElementsByTagName("TvWall").getAttribute("tvWallId").value;
        //var name = elements[i].getElementsByTagName("name")[0].firstChild.nodeValue;
        //alert(tvWallId+"----"+name);
        var varItem = new Option(name, tvWallId);      
        objSelect.options.add(varItem); 
    }

    }catch(e){}
    
}

function getWallLayout(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
     var wallid = document.getElementById("wallid").value;
     alert(wallid);
    var allstr=obj.DPSDK_QueryTvWallLayout(wallid);
    writewall("获取电视墙布局:"+allstr);
    }catch(e){}
}

function getOneWallLayout(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var wallstr=obj.DPSDK_GetTvWallLayout(51);
    writewall("得到电视墙布局"+wallstr);
      }catch(e){}
}

function CutWall(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var statue=obj.DPSDK_SetTvWallScreenSplit(51,489,4);
    alert("分割:"+statue);
    }catch(e){}
}

function wallset(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var statue=obj.DPSDK_SetTvWallScreenWindowSource(51,489,1,'1000007$1$0$2',1,30);
    alert("设置"+statue);
    }catch(e){}
}


function wallclose(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var statue=obj.DPSDK_CloseTvWallScreenWindowSource(51,489,1);
    alert("关闭"+statue);
    }catch(e){}
}


function WallClean(){
    try{
    var obj = document.getElementById("DPSDK_OCX");
    var statue=obj.DPSDK_ClearTvWallScreen(51);
    alert("清屏"+statue);
    }catch(e){}
}

loadXML = function(xmlString){
    try{
        var xmlDoc=null;
        //判断浏览器的类型
        //支持IE浏览器 
        if(!window.DOMParser && window.ActiveXObject){   //window.DOMParser 判断是否是非ie浏览器
            var xmlDomVersions = ['MSXML.2.DOMDocument.6.0','MSXML.2.DOMDocument.3.0','Microsoft.XMLDOM'];
            for(var i=0;i<xmlDomVersions.length;i++){
                try{
                    xmlDoc = new ActiveXObject(xmlDomVersions[i]);
                    xmlDoc.async = false;
                    xmlDoc.loadXML(xmlString); //loadXML方法载入xml字符串
                    break;
                }catch(e){
                }
            }
        }
        //支持Mozilla浏览器
        else if(window.DOMParser && document.implementation && document.implementation.createDocument){
            try{
                /* DOMParser 对象解析 XML 文本并返回一个 XML Document 对象。
                 * 要使用 DOMParser，使用不带参数的构造函数来实例化它，然后调用其 parseFromString() 方法
                 * parseFromString(text, contentType) 参数text:要解析的 XML 标记 参数contentType文本的内容类型
                 * 可能是 "text/xml" 、"application/xml" 或 "application/xhtml+xml" 中的一个。注意，不支持 "text/html"。
                 */
                domParser = new  DOMParser();
                xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
            }catch(e){
            }
        }
        else{
            return null;
        }

        return xmlDoc;
        }catch(e){}
}


function aaa(value){
    document.getElementById("textWndNum").value=value;
    ButtonCreateWnd_onclick();
}

