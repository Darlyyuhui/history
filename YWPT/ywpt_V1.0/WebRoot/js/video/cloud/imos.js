

//工具函数---start
function CreateEmptyString(l) {
    var a = [];
    for (var i = 0; i < l; i++) {
        a[i] = '*';
    }
    return a.join('');
}


//向列表中添加表项
function AddList(sel, text, value){
    var option = document.createElement("option");
    option.appendChild(document.createTextNode(text));
    option.setAttribute("value",value); 
    sel.appendChild(option);
    return;
}


//去除时间格式内的HTTP关键字
function FormatTime(time){
    var tmp_string = time.split(' ');
    var tmp_string_cp = tmp_string[1].split(':');
    return tmp_string[0]+'%20'+tmp_string_cp[0]+'%3A'+tmp_string_cp[1]+'%3A'+ tmp_string_cp[2];
}



var IS_SHOW_DEBUG = 0;  //0-关闭, 1-开启
var gdownloadID = 1;   //下载编码
var gdownloadID2 = 1;

function DebugAlert(str){
    if(IS_SHOW_DEBUG == 1){
        alert(str);
    }
}

String.prototype.replaceAll = function(f, r) {
    var s = new String(this);
    while (s.indexOf(f) != -1) {
        s = s.replace(f, r);
    }
    return s.toString();
}

/**
 * 解析从控件返回的xml字符串
 */
function loadXML(xmlString){
     if(xmlString==''){
       alert("需要xml解析的字符为空！");
       return null;
     }
     if(!g_xmlActive){
         return;
     }
     g_xmlActive.loadXML(xmlString);
     if(0 == g_xmlActive.parseError.errorCode){
         return g_xmlActive;
     }
     else{
         alert("xml解析错误:" + g_xmlActive.parseError.reason);
         return null;
     } 
} 

function getElementById(dom, tagName){
    for(var i = 0; i < dom.childNodes.length; i++){
        var node = dom.childNodes[i];
        if(node.baseName == tagName){
            return node.nodeTypedValue;
        }
        else{
            if(node.childNodes.length != 0){
                getElementById(dom.childNodes[i], tagName);
            }
        }
    }
}

//工具函数---end

//全局变量---start

var g_UserLoginId = '';
var g_UserName = '';    //登录用户名
var g_PassWord = '';    //登录用户密码
var g_ServerIP = '';    //服务器IP地址
var g_imosActivePlayer = null;
var g_curFrameNum = '1';
var g_xmlActive = null;
var g_isLogin = 1;

//全局变量---end
	
//================>功能函数---start
//检查控件

function InitPage(){
   g_imosActivePlayer = document.all.h3c_IMOS_ActiveX;
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
    }
    var xmldoc;
    try{
        xmldoc = new ActiveXObject("Microsoft.XMLDOM");
        if(!xmldoc){
           xmldoc = new ActiveXObject("MSXML2.DOMDocument.3.0");  
        }
    } 
    catch(e){        
    }
    g_xmlActive = xmldoc;
    if(!g_xmlActive){
        alert("xml解析器获取错误，将导致某些功能不可用");
    }
    else{
        g_xmlActive.async = "false";  
    }
}


//登录    
function DoLoginInit(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }

    setXpInit();
    
    g_isLogin = g_imosActivePlayer.IMOSAX_InitOCX("60.12.249.168", "8800", "xiangxun", "123456", 1);
    //获取用户信息
    var retStr = g_imosActivePlayer.IMOSAX_GetUserLoginInfo();
    var userObj = loadXML(retStr);
    g_UserLoginId = userObj.documentElement.selectNodes("//LOGININFO/UserLoginIDInfo/UserLoginCode")[0].text;

}



//登录    
function DoLogin(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var serverIP = document.getElementById("ServerIPText").value;
    var userName = document.getElementById("UserNameText").value;
    var passWd = document.getElementById("PassWordText").value.toString();
    if(passWd == ""){
        passWd = "";
    }
    
    setXpInit();
    
    g_isLogin = g_imosActivePlayer.IMOSAX_InitOCX(serverIP, "8800", userName, passWd, 1);
    //获取用户信息
    var retStr = g_imosActivePlayer.IMOSAX_GetUserLoginInfo();
    var userObj = loadXML(retStr);
    g_UserLoginId = userObj.documentElement.selectNodes("//LOGININFO/UserLoginIDInfo/UserLoginCode")[0].text;

}

//设置播放器属性
function setXpInit(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var pcParamxml = "<?xml version='1.0'?><data><LayoutNum>4</LayoutNum><BkRectColor>16744448</BkRectColor><SelRectColor>16744448</SelRectColor></data>";
    var flag = g_imosActivePlayer.IMOSAX_SetAttributeParam(pcParamxml);
    if(0 != flag){
      alert("设置播放器属性出错！");
    }
}

//退出登录
function DoLogout(){
    if(g_imosActivePlayer){
        var flag = g_imosActivePlayer.IMOSAX_UnregOCX();
        if(0 != flag){
           alert("用户注销失败，返回："+flag);
        }
    }
     parent.loaded = false;
}

//云台控制方面
function ButtonPtzDirection_onclick(ptzCmdStr){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    var cameraId = document.getElementById("video_code_"+frameNum).value;
    //这里方便测试 直接写死摄像机ID
    //var cameraId = 'IPC40';
    var PtzCmd = parseInt(ptzCmdStr, 16);
    var ptzSpeed = parent.document.getElementById("selectPtzDirectionStep").options[parent.document.getElementById("selectPtzDirectionStep").selectedIndex].text;
    var flag = g_imosActivePlayer.IMOSAX_SendPtzCtrlCommand(cameraId, PtzCmd, ptzSpeed, ptzSpeed, 0);
    if(0 != flag){
        alert("云台控制出错，错误码：" + flag);
        showerror(flag);
    }
}

function ButtonPtzCameraOperation_onclick(ptzCmdStr){
   if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    var cameraId = document.getElementById("video_code_"+frameNum).value;
    //这里方便测试 直接写死摄像机ID
    //var cameraId = 'IPC40';
    var PtzCmd = parseInt(ptzCmdStr, 16);
    var ptzSpeed = parent.document.getElementById("selectCameraStep").options[parent.document.getElementById("selectCameraStep").selectedIndex].text;
    //var ptzSpeed = 6;
    var flag = g_imosActivePlayer.IMOSAX_SendPtzCtrlCommand(cameraId, PtzCmd, ptzSpeed, ptzSpeed, 0);
    if(0 != flag){
        alert("云台控制出错，错误码：" + flag);
        showerror(flag);
    }
}


function ButtonAddPrePoint_onclick(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    //var cameraId = 'IPC40';
    var frameNum = g_curFrameNum;
    var cameraId = document.getElementById("video_code_"+frameNum).value;
    var nPrePointNum = parent.document.getElementById("textPrePointNum").value;
    var strPrePointName = parent.document.getElementById("textPrePointName").value;
    if(nPrePointNum==''){
       alert("预置点编号不能为空！");
       return;
    }
    nPrePointNum = parseInt(nPrePointNum, 10);
    var flag = g_imosActivePlayer.IMOSAX_AddPtzPreset(cameraId, nPrePointNum, strPrePointName);
    if(0 != flag){
        alert("云台控制出错，错误码：" + flag);
        showerror(flag);
    }else{
        alert("预置点添加成功！");
    }
}

function ButtonDelPrePoint_onclick(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    //var cameraId = 'IPC40';
    var frameNum = g_curFrameNum;
    var cameraId = document.getElementById("video_code_"+frameNum).value;
    var nPrePointNum = parent.document.getElementById("FileList").value;
    if(nPrePointNum==''){
       alert("请选择要删除的预置点！");
       return;
    }
    alert(nPrePointNum);
    nPrePointNum = parseInt(nPrePointNum, 10);
    var flag = g_imosActivePlayer.IMOSAX_DelPtzPreset(cameraId, nPrePointNum);
     alert(flag);
    if(0 != flag){
        alert("云台控制出错，错误码：" + flag);
        showerror(flag);
    }
}
function ButtonSeekPrePoint_onclick(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    //var cameraId = 'IPC40';
    var frameNum = g_curFrameNum;
    var cameraId = document.getElementById("video_code_"+frameNum).value;
    var nPrePointNum = parent.document.getElementById("FileList").value;
    if(nPrePointNum==''){
       alert("请选择要定位的预置点！");
       return;
    }
    nPrePointNum = parseInt(nPrePointNum, 10);
    var flag = g_imosActivePlayer.IMOSAX_UsePtzPreset(cameraId, nPrePointNum);
    if(0 != flag){
        alert("云台控制出错，错误码：" + flag);
        showerror(flag);
    }
}
function ButtonQueryPrePoint_onclick(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    //var cameraId = 'IPC40';
    var frameNum = g_curFrameNum;
    var cameraId = document.getElementById("video_code_"+frameNum).value;
    var retStr = g_imosActivePlayer.IMOSAX_QueryPtzPresetList(cameraId);
    //测试添加
    if(retStr==''){
      alert("当前监控还没有配预置点！");
    }
    var recordListObj = loadXML(retStr);
    var RecordObjArray = recordListObj.documentElement.selectNodes("//PresetList/item");
    var FileListObj = parent.document.all.FileList;
    FileListObj.innerHTML = '';
    for(i = 0; i < RecordObjArray.length; i++){ 
        var value = RecordObjArray[i].selectSingleNode("PresetValue").text;
        var name = RecordObjArray[i].selectSingleNode("PresetDesc").text;
        AddList(FileListObj, name,value);
    }
}


//部门查询 v
function queryOrgInfo(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    
    var orgxml = g_imosActivePlayer.IMOSAX_QueryOrgInfo('iccsid');
    alert("组织信息：" + orgxml);
}

//添加轮切资源 v
function addSwitch(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    
    var switchxml = '<?xml version="1.0" ?><data><SwitchBaseInfo><SwitchName>yantaotest</SwitchName><SwitchDesc /></SwitchBaseInfo><SwitchResNum>2</SwitchResNum>'+
'<SwitchUnitList count="2"><item><CameraCode>IPC40</CameraCode><CameraName>宇视球机测试</CameraName><Sequence>1</Sequence><Interval>10</Interval></item>'+
'<item><CameraCode>banqiu</CameraCode><CameraName>宇视摄像机测试</CameraName><Sequence>2</Sequence><Interval>10</Interval></item></SwitchUnitList></data>';
    
    var code = g_imosActivePlayer.IMOSAX_AddSwitchResource('iccsid', switchxml);
    alert("轮切编码为：" + flag);
}


//查询资源 IMOSAX_QueryOrgResList
//参数: 
//[IN] strOrgCode 资源父组织编码
//[IN] ulResType 资源类型
//[IN] ulResSubType 资源子类型
//[IN] ulLowStart 分页查询的起始行
//[IN] ulPageSize 每页的记录个数
//资源类型:
//IMOS_TYPE_CAMERA（1001）摄像机
//IMOS_TYPE_GUARD_TOUR_RESOURCE（2001）轮切资源
//IMOS_TYPE_GUARD_TOUR_PLAN（2002）轮切计划
//IMOS_TYPE_GROUP_SWITCH_RESOURCE（3010）组轮巡资源
//IMOS_TYPE_GROUP_SWITCH_PLAN（3011）组轮巡计划资源
//IMOS_TYPE_SALVO_RESOURCE（3004）组显示资源

function QueryOrgResList(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var xml = g_imosActivePlayer.IMOSAX_QueryOrgResList('iccsid',3,0,0,0);
    alert("部门编码为："+'iccsid'+"的资源有：" + xml);
}


//查询轮切资源  v
function querySwitch(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var xml = g_imosActivePlayer.IMOSAX_QuerySwitchResource('3424896008042053633');
    alert("轮切资源：" + xml);
}

//暂停轮切
function ztSwitch(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    
    var flag = g_imosActivePlayer.IMOSAX_AdjustFrameSwitch(frameNum,0);
    alert("暂停轮切：" + flag);
}

//恢复轮切
function hfSwitch(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    
    var flag = g_imosActivePlayer.IMOSAX_AdjustFrameSwitch(frameNum,1);
    alert("恢复轮切：" + flag);
}

//启动轮切
function DoStartSwitch(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var cameraId = '3424896008042053633';
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartFrameSwitch (frameNum, cameraId);
    if(0 == flag){
        alert("启动轮切成功");
    }
    else{
        alert("停止轮切失败，错误码：" + flag);
    }
}

function DoStopSwitch(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopFrameSwitch(frameNum);
    if(0 == flag){
        alert("停止轮切成功");
    }
    else{
        alert("停止轮切失败，错误码：" + flag);
    }
}

//启动实况
function DoStartPlay(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var cameraId = document.getElementById("video_code_"+g_curFrameNum).value;
   // var cameraId = document.getElementById("imgpath1_code").value;
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    //cameraId = 'IPC40';
    var flag = g_imosActivePlayer.IMOSAX_StartFrameLive (frameNum, cameraId);
    
    $("#img_tz_bofang").css("display", "block");
    $("#img_jx_bofang").css("display", "none");
    
    if(0 == flag){
        //alert("实况播放成功");
    }
    else{
        showerror(flag);
    }
}

function DoStartPlayByDrag(frameNum,cameraId){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    //cameraId = 'banqiu';
    //cameraId = 'HIK_xiangxun_1_1';
    //cameraId = 'IPC40';
    //IE9 加载D3D效果
    var nn = g_imosActivePlayer.IMOSAX_SetSingleCfgParam("RenderMode","0");
    var flag = g_imosActivePlayer.IMOSAX_StartFrameLive (frameNum, cameraId);
    if(0 == flag){
        //alert("实况播放成功");
    }
    else{
        showerror(flag);
    }
}
//释放实况
function DoStopPlay(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopFramelive(frameNum);
    
    $("#img_tz_bofang").css("display", "none");
    $("#img_jx_bofang").css("display", "block");
    
    if(0 == flag){
        alert("停止实况成功");
    }
    else{
        showerror(flag);
    }
}
function DoStopPlayByDrag(frameNum){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopFrameLive(frameNum);
    if(0 == flag){
        alert("停止实况成功");
    }
    else{
        showerror(flag);
    }
}

//启动文件
function DoStartFile(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var cameraId = document.getElementById("FileIDText").value;
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartLocalReplay (frameNum, cameraId);
    if(0 == flag){
        alert("播放文件成功");
    }
    else{
       showerror(flag);
    }
}

//停止文件
//释放本地文件
function DoStopFile(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartLocalReplay(frameNum);
    if(0 == flag){
        alert("停止文件成功");
    }
    else{
        showerror(flag);
    }
}

//单步 
function DoStepFile(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_OneStepPlay(frameNum);
    if(0 != flag){
        alert("单步文件失败，错误码：" + flag);
    }
}


//抓拍
//抓拍图片将保存在指定路径下, 以当天日期为名称的文件夹内
function DoSnatch(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_SnatchOnce(frameNum);
    if(0 != flag){
	     showerror(flag);
	}
}

//连续抓拍
function StartSnatchSeries(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartSnatchSeries(frameNum);
    $("#img_zhuapai").css("display", "none");
    $("#img_now_zhuapai").css("display", "block");
    $("#img_tz_zhuapai").css("display", "block");
    if(0 != flag){
	     showerror(flag);
	}
}

function StopSnatchSeries(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    $("#img_zhuapai").css("display", "block");
    $("#img_now_zhuapai").css("display", "none");
    $("#img_tz_zhuapai").css("display", "none");
    var flag = g_imosActivePlayer.IMOSAX_StopSnatchSeries(frameNum);
    if(0 != flag){
	     showerror(flag);
	}
}

//录像
function DoLocalStorage(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartLocalStorage(frameNum);
    
    $("#img_lx").css("display", "none");
    $("#img_now_lx").css("display", "block");
    $("#img_tz_lx").css("display", "block");
    $("#img_xz_lx").css("display", "none");

    if(0 != flag){
	     showerror(flag);
	}
}

function StopLocalStorage(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopLocalStorage(frameNum);
    
    $("#img_lx").css("display", "block");
    $("#img_now_lx").css("display", "none");
    $("#img_tz_lx").css("display", "none");
    $("#img_xz_lx").css("display", "block");
    
    if(0 != flag){
	     showerror(flag);
	}
}


//全屏
function setFull(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_FullScreen();
    if(0 != flag){
	     showerror(flag);
	}
}

//================>功能函数---end






//================>回放功能函数---start

//获得摄像机列表
function DoGetCamList(){
var strXmlQueryCondition = "";
strXmlQueryCondition =  '<?xml version="1.0" ?>'+
'<data>'+
  '<ItemNum>4</ItemNum>'+                   //总共有四个查询条件
'<QueryConditionList count="4">'+
'<item>'+                                   // 不查询子域
      '<QueryType>257</QueryType> '+
      '<LogicFlag>0</LogicFlag> '+
      '<QueryData>0</QueryData> '+
    '</item>'+
  '<item>'+                                //查询的资源类型是摄像头
      '<QueryType>256</QueryType>'+
      '<LogicFlag>0</LogicFlag>'+
      '<QueryData>1001</QueryData>'+
    '</item>'+
  '<item>'+                               // 查询的资源子类型是固定摄像头
      '<QueryType>11</QueryType>'+
      '<LogicFlag>0</LogicFlag>'+
      '<QueryData>1</QueryData>'+ 
    '</item>'+
  '<item>'+                               // 查询结果按照名称的升序排序
      '<QueryType>1</QueryType> '+
      '<LogicFlag>6</LogicFlag>'+ 
      '<QueryData /> '+
   '</item>'+
   '</QueryConditionList>'+
  '</data>';
 
var strXmlQueryPageInfo = '<?xml version="1.0" ?> '+
'<data>'+
  '<PageRowNum>100</PageRowNum>'+                     //最多返回100个记录
  '<PageFirstRowNumber>0</PageFirstRowNumber>'+       //从第0个记录开始返回
  '<QueryCount>1</QueryCount>'+                       //还需要返回总记录数
'</data>';
 
    var retStr = "";
    retStr = g_imosActivePlayer.IMOSAX_QueryOrgResListEx('iccsid', strXmlQueryCondition, strXmlQueryPageInfo);
 
    var cameraListObj = loadXML(retStr);
    if(!cameraListObj){
	    return;
	}
	var firstCameraCode = cameraListObj.documentElement.selectNodes("//result/ResList/item/ResItemV1/ResCode");//参见文档xml结构进行解析
	firstCameraCode = firstCameraCode[0].text;
	alert("检索到的第一个摄像机的编码是：" + firstCameraCode);
}
 
function AddQueryList(StartTime , EndTime, FileName){
    $("#FileList").html(""); 
    $("#FileList").append("<tr><td width=120>"+StartTime+"</td><td width=120>"+EndTime+"</td><td width=39><a href=javascript:DoPlayRecord('"+FileName+"') ><i class=icon-edit></i>回放</a><input id='"+FileName+"' type='hidden' value='"+StartTime+"~"+EndTime+"' ><a href=javascript:DoDownRecord('"+FileName+"') ><i class=icon-edit></i>下载</a></td></tr>"); 
    return;
}

function DoQueryRecord(){
    var cameraId = document.getElementById("CamIDText").value;
    var startQueryTime = document.getElementById("StartTimeText").value;
    var endQueryTime = document.getElementById("EndTimeText").value;
    var retStr = g_imosActivePlayer.IMOSAX_QueryRecord(cameraId, startQueryTime, endQueryTime);
    if(retStr==''){
       alert("回放视频列表查询返回结果为空！");
       return;
    }else{
       alert("返回结果为："+retStr);
    }
    var recordListObj = loadXML(retStr);
    var RecordObjArray = recordListObj.documentElement.selectNodes("//RESLIST/item");
    
    for(i = 0; i < RecordObjArray.length; i++){ 
        var FileName = RecordObjArray[i].selectSingleNode("FileName").text;
        var StartTime = RecordObjArray[i].selectSingleNode("StartTime").text;
        var EndTime = RecordObjArray[i].selectSingleNode("EndTime").text;
        AddQueryList(StartTime , EndTime, FileName);
    }
}

function DoQueryRecord1(){
    var cameraId = document.getElementById("CamIDText").value;
    var startQueryTime = document.getElementById("StartTimeText1").value;
    var endQueryTime = document.getElementById("EndTimeText1").value;
    var retStr = g_imosActivePlayer.IMOSAX_QueryBakRecord(cameraId, startQueryTime, endQueryTime);
    alert(retStr);
    
    var recordListObj = loadXML(retStr);
    var RecordObjArray = recordListObj.documentElement.selectNodes("//RESLIST/item");
    
    var FileListObj = document.all.FileList;
    FileListObj.innerHTML = '';
    for(i = 0; i < RecordObjArray.length; i++){ 
        var FileName = RecordObjArray[i].selectSingleNode("BakFilePath").text;
        var StartTime = RecordObjArray[i].selectSingleNode("BeginTime").text;
        var EndTime = RecordObjArray[i].selectSingleNode("EndTime").text;
        AddList(FileListObj, StartTime + '~' + EndTime, FileName);
    }
}
 
function DoQueryRecord2(){
    var cameraId = document.getElementById("CamIDText").value;
    var startQueryTime = document.getElementById("StartTimeText2").value;
    var endQueryTime = document.getElementById("EndTimeText2").value;
    var retStr = g_imosActivePlayer.IMOSAX_QuerySlaveRecord(cameraId, startQueryTime, endQueryTime);
    alert(retStr);
    var recordListObj = loadXML(retStr);
    var RecordObjArray = recordListObj.documentElement.selectNodes("//RESLIST/item");
    
    var FileListObj = document.all.FileList;
    FileListObj.innerHTML = '';
    for(i = 0; i < RecordObjArray.length; i++){ 
        var FileName = RecordObjArray[i].selectSingleNode("FileName").text;
        var StartTime = RecordObjArray[i].selectSingleNode("StartTime").text;
        var EndTime = RecordObjArray[i].selectSingleNode("EndTime").text;
        AddList(FileListObj, StartTime + '~' + EndTime, FileName);
    }
}



// 录像下载
function DoDownRecord(filename){
   
	var cameraId = document.getElementById("CamIDText").value;
	   
    //var FileListObj = document.all.FileList;
    //var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    var stet = document.getElementById(filename).value;
    time_array = stet.split("~");
    if("" == time_array){
	    return;
	}
    start_time = time_array[0];
    stop_time = time_array[1];
	//alert(frameNum + "--" + cameraId);
    var downloadcode = g_imosActivePlayer.IMOSAX_StartDownMediaStream(cameraId, start_time, stop_time);
    alert(downloadcode);
	if(0 != downloadcode){
	     showerror(downloadcode);
	}
}

function DoPlayRecord(filename){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格"); 
        return;
    }
	var cameraId = document.getElementById("CamIDText").value; 
	   
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    var stet = document.getElementById(filename).value;
    time_array = stet.split("~");
    if("" == time_array){
	    return;
	}
    start_time = time_array[0];
    stop_time = time_array[1];
	//alert(frameNum + "--" + cameraId);
    var flag = g_imosActivePlayer.IMOSAX_StartVodReplay(frameNum, cameraId, start_time, stop_time);
	if(0 != flag){
	     showerror(flag);
	}
}

function DoPlayRecord1(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
	var cameraId = document.getElementById("CamIDText").value;    
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    for(var i = 0; i < FileListObj.options.length; i++){
        if(FileListObj.options[i].value == FileListObj.value){
            time_array = FileListObj.options[i].innerText.split("~");
            start_time = time_array[0];
            stop_time = time_array[1];
            DebugAlert(start_time + ":" + stop_time);
            break;
        }
    }
	if("" == time_array){
	    return;
	}
    var flag = g_imosActivePlayer.IMOSAX_StartBakVodReplay(frameNum, cameraId, start_time, stop_time);
	if(0 != flag){
	     showerror(flag);
	}
}

function DoPlayRecord2(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格"); 
        return;
    }
	var cameraId = document.getElementById("CamIDText").value;    
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    for(var i = 0; i < FileListObj.options.length; i++){
        if(FileListObj.options[i].value == FileListObj.value){
            time_array = FileListObj.options[i].innerText.split("~");
            start_time = time_array[0];
            stop_time = time_array[1];
            DebugAlert(start_time + ":" + stop_time);
            break;
        }
    }
	if("" == time_array){
	    return;
	}
    var flag = g_imosActivePlayer.IMOSAX_StartSlaveVodReplay(frameNum, cameraId, start_time, stop_time);
	if(0 != flag){
	     showerror(flag);
	}
}
//停止录像回放
function DoStopPlayRecord(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopReplay(frameNum);
    
    if(0 != flag){
	     showerror(flag);
	}
    
}

//暂停回放
function PauseReplay(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_PauseReplay(frameNum);
    
    $("#img_zt_huifang").css("display", "none");
    $("#img_jx_huifang").css("display", "block");
    
    if(0 != flag){
	     showerror(flag);
	}
    
}

function SetPos(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
	var pos = document.all.PosText.value;   //时间格式为：yyyy-mm-dd hh:mm:ss
	if(pos==''){
	  alert("请输入跳转位置！");
	}
    var flag = g_imosActivePlayer.IMOSAX_DragPlay(frameNum, pos)
	if(0 != flag){
	     showerror(flag);
	}
}

//继续回放
function ResumeReplay(){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_ResumeReplay(frameNum);
    
    $("#img_zt_huifang").css("display", "block");
    $("#img_jx_huifang").css("display", "none");
    
	if(0 != flag){
	     showerror(flag);
	}
    
}

 
//设置播放速度
function SetPlaySpeed(speed){
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);    
    if(isNaN(frameNum) || frameNum < 1 || frameNum > 25){
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_SetPlaySpeed(frameNum, speed);
    if(0 != flag){
	     showerror(flag);
	}
}

 
//查询摄像机信息
function DoQueryCamInfo(){
    if(!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    
    var cameraId = document.getElementById("CameraID").value;
    var retStr = g_imosActivePlayer.IMOSAX_QueryCameraInfo(cameraId);
    alert(retStr); 
}


//================>功能函数---end




//================>事件处理函数---start

/**
 * 窗格单击事件的处理函数
 */
function dealEventClickFrame(ulFrameNum, pcFrameInfo){
    //当前窗格
    g_curFrameNum = ulFrameNum;
    pcFrameInfo = pcFrameInfo.replaceAll("\"", "\'");
    var tmpXmlDoc = loadXML(pcFrameInfo);
    if(!tmpXmlDoc){
        return;
    }
    //将需要的数据从xml中获取，方便后续使用
    var cameraID = tmpXmlDoc.selectNodes("//FrameInfo//CameraCode")[0];
}
//================>事件处理函数--end



