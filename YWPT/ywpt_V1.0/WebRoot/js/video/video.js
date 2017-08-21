/**
 * 视频监控模块js
 */

var vedio2 = {
    /**
     * videoPlayer播放器的播放方法
     * 1屏视频加载方法
     * 
     * @param {}
     *            root 系统路径
     * @param {}
     *            devicecode 设备编号
     * @param {}
     *            devicename 设备名称
     * @param {}
     *            command 视频厂家
     * @param {}
     *            screen_index 需要加载当前页面的第几屏
     * @param {}
     *            pingshu 每个页面的总屏数
     */
    loadvideoPlayer : function(root, devicecode, devicename, command, screen_index,
            pingshu) {
        var deviceIp = "193.169.100.249";// 设备的ip地址
        // var rtspName = "mp4:" + devicecode;// 设备
        var rtspName = devicecode;// 设备
        $.ajax({
            type : "POST",
            url : root + "/vedio/index/sendCommandInfo",
            dataType : "json",
            data : {
                command : command,
                // command : 2,
                deviceIp : deviceIp,
                rtspName : rtspName
            },
            success : function(dd) {
                // alert(dd[1]);
                var serverIp = dd[1];
                rtspName = "mp4:" + devicecode;// 设备
                $("#videoPlayer" + screen_index).remove();
                /*
                 * var height = ""; // 网页正文部分上，y轴坐标点 window.screenTop; //
                 * 网页正文部分左，x轴坐标点 window.screenLeft; // 屏幕高度
                 * window.screen.height; // 屏幕可用工作区高度 window.screen.availHeight;
                 * var videoHeight = ""; if ($.browser.safari) { height =
                 * window.screen.availHeight - 185; videoHeight = height - 110;
                 * $("#videoPlayer" + screen_index + "s").height(videoHeight);
                 * $("#videoPlayer" + screen_index).height(videoHeight); } else
                 * if ($.browser.msie) { height = window.screen.availHeight -
                 * window.screenTop - 30; videoHeight = height - 110;
                 * $("#videoPlayer" + screen_index).height(videoHeight);
                 * $("#videoPlayer" + screen_index + "s").height(videoHeight); }
                 * else if ($.browser.mozilla) { height =
                 * window.screen.availHeight - window.mozInnerScreenY - 5; //
                 * 左侧设备树自适应高度 videoHeight = height - 110; $("#videoPlayer" +
                 * screen_index + "s").height(videoHeight); $("#videoPlayer" +
                 * screen_index).height(videoHeight);; }
                 */
                var videoHeight = vedio2.height(pingshu);
//              alert(videoHeight+"--"+screen_index);
                // $("#div" + screen_index)
                // .append("<object width='100%' height="
                // + videoHeight
                // + " id='videoPlayer"
                // + screen_index
                // + "' name='videoPlayer' type='application/x-shockwave-flash'
                // classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'><param
                // name='movie' value='swfs/videoPlayer.swf' />"
                // + "<param name='quality' value='high' />"
                // + "<param name='bgcolor' value='#000000' />"
                // + "<param name='allowfullscreen' value='true' />"
                // + "<param name='flashvars' id='flashvars'
                // value='&videoWidth=0&videoHeight=0&dsControl=manual&dsSensitivity=100&serverURL=rtmp://193.169.100.250/live/"
                // + rtspName
                // + "&DS_Status=true&streamType=live&autoStart=true' />"
                // + "<embed width='100%' height='"+videoHeight+"'
                // src='swfs/videoPlayer.swf' id='videoPlayer"
                // + screen_index
                // + "s' quality='high' bgcolor='#000000' name='videoPlayer'
                // allowfullscreen='true'
                // pluginspage='http://www.adobe.com/go/getflashplayer'
                // flashvars='&videoWidth=0&videoHeight=0&dsControl=manual&dsSensitivity=100&serverURL=rtmp://193.169.100.250/live/"
                // + rtspName
                // + "&DS_Status=true&streamType=live&autoStart=true'
                // type='application/x-shockwave-flash'>"
                // + "</embed></object>");
                $("#div" + screen_index)
                        .append("<object width='100%' height="
                                + videoHeight
                                + " id='videoPlayer"
                                + screen_index
                                + "' name='videoPlayer' type='application/x-shockwave-flash' classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'><param name='movie' value='swfs/videoPlayer.swf' />"
                                + "<param name='quality' value='high' />"
                                + "<param name='bgcolor' value='#000000' />"
                                + "<param name='allowfullscreen' value='true' />"
                                + "<param name='flashvars' id='flashvars' value='&videoWidth=0&videoHeight=0&dsControl=manual&dsSensitivity=100&serverURL=rtmp://"
                                + serverIp
                                + "/live/"
                                + rtspName
                                + "&DS_Status=true&streamType=live&autoStart=true' />"
                                + "<embed width='100%' height='"
                                + videoHeight
                                + "' src='swfs/videoPlayer.swf' id='videoPlayer"
                                + screen_index
                                + "s' quality='high' bgcolor='#000000' name='videoPlayer' allowfullscreen='true' pluginspage='http://www.adobe.com/go/getflashplayer' flashvars='&videoWidth=0&videoHeight=0&dsControl=manual&dsSensitivity=100&serverURL=rtmp://"
                                + serverIp
                                + "/live/"
                                + rtspName
                                + "&DS_Status=true&streamType=live&autoStart=true' type='application/x-shockwave-flash'>"
                                + "</embed></object>");
//                alert("rtmp://" + serverIp+ "/live/" + rtspName);
//              alert($("#div" + screen_index).html());
            },
            error : function() {
            }
        });

    },
    /**
     * CkPlayer播放器
     * 1屏视频加载方法
     * 
     * @param {}
     *            root 系统路径
     * @param {}
     *            devicecode 设备编号
     * @param {}
     *            devicename 设备名称
     * @param {}
     *            command 视频厂家
     * @param {}
     *            screen_index 需要加载当前页面的第几屏
     * @param {}
     *            pingshu 每个页面的总屏数
     * @param{}
     *            lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
     */
    load : function(root, devicecode, devicename, command, screen_index,
            pingshu,lx) {
        var typelx='';
        if("internet"==lx){
            typelx='internet';
        }else{
            typelx="defalut";
        }
        var deviceIp = "193.169.100.249";// 设备的ip地址
        // var rtspName = "mp4:" + devicecode;// 设备
        var rtspName = devicecode;// 设备
        $.ajax({
            type : "POST",
            url : root + "/vedio/index/sendCommandInfo/"+typelx+"/",
            dataType : "json",
            data : {
                command : command,
                deviceIp : deviceIp,
                rtspName : rtspName
            },
            success : function(dd) {
                //debugger;
                var serverIp = dd[1];
                rtspName = "mp4:" + devicecode;// 设备
                $("#videoPlayer" + screen_index).remove();
                var videoHeight = vedio2.height(pingshu);
                if('ip'==serverIp){
                    $("#div" + screen_index).append("<div id='videoPlayer"+screen_index+"' style=\"background:#000;width:100%;height:"+videoHeight+"px\"></div>");
                }else{
                    /*$("#div" + screen_index)
                        .append("<object width='100%' height="
                                + videoHeight
                                + " id='videoPlayer"
                                + screen_index
//                              + "' name='videoPlayer' type='application/x-shockwave-flash' classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'><param name='movie' value='swfs/videoPlayer.swf' />"
                                + "' name='videoPlayer' type='application/x-shockwave-flash' classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'><param name='movie' value='ckplayer6.3_all/ckplayer/ckplayer.swf' />"
                                + "<param name='quality' value='high' />"
                                + "<param name='bgcolor' value='#000000' />"
                                + "<param name='allowfullscreen' value='true' />"
                                + "<param name='flashvars' id='flashvars' value='f=rtmp://"
                                + serverIp
                                + "/live/"
                                + rtspName
                                + "' />"
                                + "<embed width='100%' height='"
                                + videoHeight
                                + "' src='ckplayer6.3_all/ckplayer/ckplayer.swf' id='videoPlayer"
                                + screen_index
                                + "s' quality='high' bgcolor='#000000' name='videoPlayer' allowfullscreen='true'  flashvars='f=rtmp://"
                                + serverIp
                                + "/live/"
                                + rtspName
                                + "' type='application/x-shockwave-flash'>"
                                + "</embed></object>");*/
                    $("#div" + screen_index).append("<div id='videoPlayer"+screen_index+"' style=\"background:#000;width:100%;height:"+videoHeight+"px\"></div>");
//                    alert($("#videoPlayer"+screen_index).html());
                    var width_div=$("#videoPlayer"+screen_index).width();
                    var swfVersionStr = "10.0.0";
            swfobject.embedSWF(
                root+"/swfs/videoplayer/videoPlayer.swf?videourl=rtmp://"+serverIp+"/live/"+rtspName+"&width="+width_div+"&height="+videoHeight, "videoPlayer" + screen_index, 
                width_div, videoHeight, swfVersionStr, "", {}, {allowfullscreen: true}, {"id": "videoPlayer"+screen_index});
                }
//                alert($("#videoPlayer"+screen_index).html());
              //alert($("#div" + screen_index).html());
                                //alert("rtmp://" + serverIp+ "/live/" + rtspName);
            },
            error : function() {
            }
        });

    },
    /**
     * 单屏视频停止方法
     * 
     * @param {}
     *            root 系统路径
     * @param {}
     *            devicecode 设备编号
     * @param {}
     *            devicename 设备名称
     * @param {}
     *            command 控制命令
     * @param {}
     *            screen_index 总共屏中的第几屏
     * @param {}
     *            pingshu 当前页面总共几屏
     * @param{}
     *            lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
     */
    stop : function(root, devicecode, devicename, command, screen_index,
            pingshu,lx) {
        if("internet"==lx){
        
        }else{
            lx="defalut";
        }
        var deviceIp = "193.169.100.249";// 设备的ip地址
        var rtspName =  devicecode;// 设备
        $.ajax({
            type : "POST",
            url : root + "/vedio/index/sendCommandInfo/"+lx+"/",
            dataType : "json",
            data : {
                command : command,
                deviceIp : deviceIp,
                rtspName : rtspName
            },
            success : function(dd) {
                rtspName = "mp4:" + devicecode+"1";// 设备
                $("#videoPlayer" + screen_index).remove();
                var videoHeight = vedio2.height(pingshu);
//                $("#div" + screen_index)
//                        .append("<object width='100%' height="
//                                + videoHeight
//                                + " id='videoPlayer"
//                                + screen_index
//                                + "' name='videoPlayer' type='application/x-shockwave-flash' classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'><param name='movie' value='ckplayer6.3_all/ckplayer/ckplayer.swf' />"
//                                + "<param name='quality' value='high' />"
//                                + "<param name='bgcolor' value='#000000' />"
//                                + "<param name='allowfullscreen' value='true' />"
//                                + "<param name='flashvars' id='flashvars' value='f=rtmp://"+dd[1]+"/live/"
//                                + rtspName
//                                + "' />"
//                                + "<embed width='100%' height='"
//                                + videoHeight
//                                + "' src='ckplayer6.3_all/ckplayer/ckplayer.swf' id='videoPlayer"
//                                + screen_index
//                                + "s' quality='high' bgcolor='#000000' name='videoPlayer' allowfullscreen='true' flashvars='f=rtmp://"+dd[1]+"/live/"
//                                + rtspName
//                                + "' type='application/x-shockwave-flash'>"
//                                + "</embed></object>");
                                //alert($("#div"+screen_index).html());
                 $("#div" + screen_index).append("<div id='videoPlayer"+screen_index+"' style=\"background:#000;width:100%;height:"+videoHeight+"px\"></div>");
            },
            error : function() {
            }
        });

    },
    /**
     * 多个屏的后台请求
     * 
     * @param {}
     *            root 项目路径
     * @param {}
     *            devicecode_name_data 选中的所有设备编号和设备名称，格式为：设备编号:设备名称,设备编号:设备名称
     * @param {}
     *            command 控制命令
     * @param {}
     *            screen_index 选中的所有操作屏，格式为 1,2,3,5
     * @param {}
     *            pingshu 当前页面的屏数
     *@param {}
     *            lx 播放视频类型
     */
    loadAll : function(root, devicecode_name_data, command, screen_index,pingshu,lx) {
        // 把选中的操作屏格式转换成数组，分别单个进行后台ajax请求
        var arrayscreen = screen_index.split(",");
        var arraydevice = devicecode_name_data.split(",");
        for (var i = 0; i < arrayscreen.length; i++) {
            var code_name = arraydevice[i].split(":");// 格式为： 设备编号:设备名称
            vedio2
                    .load(root, code_name[0], code_name[1], command,
                            arrayscreen[i],pingshu,lx)
        }
    },
    /**
     * 多个屏的后台请求
     * 
     * @param {}
     *            root 项目路径
     * @param {}
     *            devicecode_name_data 选中的所有设备编号和设备名称，格式为：设备编号:设备名称,设备编号:设备名称
     * @param {}
     *            command 控制命令
     * @param {}
     *            screen_index 选中的所有操作屏，格式为 1,2,3,5
     * @param {}
     *            pingshu 当前页面总共几屏
     * @param{}
     *            lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
     */
    stopAll : function(root, devicecode_name_data, command, screen_index,
            pingshu,lx) {
        // 把选中的操作屏格式转换成数组，分别单个进行后台ajax请求
        var arrayscreen = screen_index.split(",");
        var arraydevice = devicecode_name_data.split(",");
        for (var i = 0; i < arrayscreen.length; i++) {
            var code_name = arraydevice[i].split(":");// 格式为： 设备编号:设备名称
            vedio2.stop(root, code_name[0], code_name[1], command,
                    arrayscreen[i], pingshu,lx);
            setTimeout('',2000);
        }
    },
    /**
     * 不用选中操作屏，停止屏幕上播放的全部视频。视频首页左侧，有
     * @param {} root 项目路径
     * @param {} command 控制命令
     * @param {} pingshu 当前页面总共几屏,总屏数
     * @param{}
     *            lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
     */
    stopAllnoSelect:function(root,command,pingshu,lx){
        for(var i=1;i<=pingshu;i++){
            var code = $("#imgpath"+i+"_code").val();
            var name = $("#imgpath"+i+"_name").html();
            if(null!=code||undefined!=code)
            {
//	            vedio2.stop(root, code, name, command,
//	                    i, pingshu,lx);
	            vedio2.stopLocal(root,code,name,command,
	                    i, pingshu,lx);
                        
            }
            //setTimeout('',200);
        }
    },
    stopdrapAll:function(root,command,pingshu,lx){
        //debugger;
        for(var i=1;i<=pingshu;i++){
            var code = $("#imgpath"+i+"_code").val();
            var name = $("#imgpath"+i+"_name").html();
            if(null!=code||undefined!=code)
            {
//              vedio2.stop(root, code, name, command,
//                      i, pingshu,lx);
                vedio2.stopLocalDragDevice(root,code,name,command,
                        i, pingshu,lx);
                        
            }
            //setTimeout('',200);
        }
    },
     /**
     * 单屏视频停止方法,页面直接删除播放器页面元素，
     * 不请求后台，没有关闭播放的视频流,按部门，按道路，自定义分组时调用
     * 
     * @param {}
     *            root 系统路径
     * @param {}
     *            devicecode 设备编号
     * @param {}
     *            devicename 设备名称
     * @param {}
     *            command 控制命令
     * @param {}
     *            screen_index 总共屏中的第几屏
     * @param {}
     *            pingshu 当前页面总共几屏
     * @param{}
     *            lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
     */
    stopLocalDragDevice : function(root, devicecode, devicename, command, screen_index,
            pingshu,lx) {
//        $("#imgpath"+screen_index+"_code").val('');
//        $("#imgpath"+screen_index+"_name").html('');
        vedio2.stop(root, code, name, command,
                      i, pingshu,lx);
    },
      /**
     * 单屏视频停止方法,页面直接删除播放器页面元素，
     * 不请求后台，没有关闭播放的视频流
     * 
     * @param {}
     *            root 系统路径
     * @param {}
     *            devicecode 设备编号
     * @param {}
     *            devicename 设备名称
     * @param {}
     *            command 控制命令
     * @param {}
     *            screen_index 总共屏中的第几屏
     * @param {}
     *            pingshu 当前页面总共几屏
     * @param{}
     *            lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
     */
    stopLocal : function(root, devicecode, devicename, command, screen_index,
            pingshu,lx) {
        $("#imgpath"+screen_index+"_code").val('');
        $("#imgpath"+screen_index+"_name").html('');
        var height= $("#videoPlayer" + screen_index).height();
        var width= $("#videoPlayer" + screen_index).width();
        $("#videoPlayer" + screen_index).remove();
//        var videoHeight = vedio2.height(pingshu);
        $("#div" + screen_index).append("<div id='videoPlayer"+screen_index+"' style=\"background:#000;width:"+width+"px;height:"+height+"px\"></div>");
    },
    /**
     * 录像方法
     */
    lx : function(screen) {
        switch (screen) {
            case 1 :// 1屏
                // yasuo.one();
                yasuo.test(1);
                break;
            case 2 :// 2屏
                // yasuo.two();
                yasuo.test(2);
                break;
            case 4 :// 4屏
                // yasuo.four();
                yasuo.test(4);
                break;
            case 6 :// 6屏
                // yasuo.six();
                yasuo.test(6);
                break;
            case 9 :// 9屏
                // yasuo.nine();
                yasuo.test(9);
                break;
            case 16 :// 16屏
                // yasuo.sixteen();
                yasuo.test(16);
                break;
            default :
                break;
        }
    },
    /**
     * 压缩方法
     * 
     * @param {}
     *            screen 几屏
     */
    ys : function(screen) {
        //debugger;
        switch (screen) {
            case 1 :// 1屏
                yasuo.test(1);
                break;
            case 2 :// 2屏
                yasuo.test(2);
                break;
            case 4 :// 4屏
                yasuo.test(4);
                break;
            case 6 :// 6屏
                yasuo.test(6);
                break;
            case 9 :// 9屏
                yasuo.test(9);
                break;
            case 16 :// 16屏
                yasuo.test(16);
                break;
            default :
                break;
        }

    },
    /**
     * 根据屏数设置页面视频播放高度
     * 
     * @param {}
     *            screen 屏数
     */
    heightOld : function(screen) {
        var height = "";
        // 网页正文部分上，y轴坐标点
        window.screenTop;
        // 网页正文部分左，x轴坐标点
        window.screenLeft;
        // 屏幕高度
        window.screen.height;
        // 屏幕可用工作区高度
        window.screen.availHeight;
        var videoHeight = "";
        if ($.browser.safari) {

            height = window.screen.availHeight - 185;

            videoHeight = height - 110;
        } else if ($.browser.msie) {
            height = window.screen.availHeight - window.screenTop - 30;
            videoHeight = height - 110;
        } else if ($.browser.mozilla) {
            height = window.screen.availHeight - window.mozInnerScreenY - 5;
            // 左侧设备树自适应高度
            videoHeight = height - 110;
        }
        if (screen == 1 || screen == 2) {// 1屏 ,2屏
            return videoHeight;
        } else if (screen == 4 || screen == 6 || screen == 16) {// 4屏 6屏 16屏
            return videoHeight / 2 - 26;
        } else if (screen == 9) {// 9屏
            return videoHeight / 3 - 26;
        } else {// 1屏
            return videoHeight;
        }
    },
    /**
     * 根据屏数设置页面视频播放高度
     * 
     * @param {}
     *            screen 屏数
     */
    height : function(screen) {
        // 获取父页面的高,没有上一页和下一页的父页面高度
        var paraentHeight = window.parent.document
                .getElementById("nopage_iframe").clientHeight;
        if (paraentHeight == '' || paraentHeight == undefined
                || paraentHeight == 0) {
            // 获取有上一页下一页的父页面高度
            paraentHeight = window.parent.document
                    .getElementById("dddddddddddddd").clientHeight;
        }
        paraentHeight = paraentHeight - 50;
//      alert(paraentHeight);
        var img_4h = paraentHeight / 2 - 26;// 4,6,16屏高度
        var img_9h = paraentHeight / 3 - 26;// 9屏高度
        var height = "";
        // 网页正文部分上，y轴坐标点
        window.screenTop;
        // 网页正文部分左，x轴坐标点
        window.screenLeft;
        // 屏幕高度
        window.screen.height;
        // 屏幕可用工作区高度
        window.screen.availHeight;
        var videoHeight;
        if ($.browser.safari) {
            height = window.screen.availHeight - 185;
//          var videoHeight = height - 110;

        } else if ($.browser.msie) {
            height = window.screen.availHeight - window.screenTop - 30;
//          videoHeight = height - 110;
            videoHeight = height;

        } else if ($.browser.mozilla) {

            if ($.browser.version == "11.0") {// ie11浏览器处理
                height = window.screen.availHeight - window.screenTop - 30;
//              videoHeight = height - 110;
                videoHeight = height;

            } else {
                height = window.screen.availHeight - window.mozInnerScreenY - 5;
                // 左侧设备树自适应高度
//              videoHeight = height - 110;
                videoHeight = height;

            }
        }
         if (screen==1||screen==2) {// 1屏 ,2屏
               return paraentHeight-26; 
        }
        else if (screen==4||screen==6||screen==16) {// 4屏 6屏 16屏
               return img_4h; 
        }
        else if (screen==9) {// 9屏
               return img_9h; 
        }
        else {// 1屏
               return paraentHeight; 
        }
    },
    /**
     * 停止视频播放
     * @param {} screen 当前页面屏数
     */
    stopVideo : function(screen) {
        switch (screen) {
            case 1 :// 1屏
                // yasuo.one();
                yasuo.test(1);
                break;
            case 2 :// 2屏
                // yasuo.two();
                yasuo.test(2);
                break;
            case 4 :// 4屏
                // yasuo.four();
                yasuo.test(4);
                break;
            case 6 :// 6屏
                // yasuo.six();
                yasuo.test(6);
                break;
            case 9 :// 9屏
                // yasuo.nine();
                yasuo.test(9);
                break;
            case 16 :// 16屏
                // yasuo.sixteen();
                yasuo.test(16);
                break;
            default :
                break;
        }
    },
    /**
     * 启动视频播放
     * @param {} screen 几屏
     */
    startPlay : function(screen) {
        switch (screen) {
            case 1 :// 1屏
                // yasuo.one();
                yasuo.test(1);
                break;
            case 2 :// 2屏
                // yasuo.two();
                yasuo.test(2);
                break;
            case 4 :// 4屏
                // yasuo.four();
                yasuo.test(4);
                break;
            case 6 :// 6屏
                // yasuo.six();
                yasuo.test(6);
                break;
            case 9 :// 9屏
                // yasuo.nine();
                yasuo.test(9);
                break;
            case 16 :// 16屏
                // yasuo.sixteen();
                yasuo.test(16);
                break;
            default :
                break;
        }
    },
    /**
     * 播放器按钮功能处理
     * 
     * @param {}
     *            root 系统路径
     * @param {}
     *            command 视频控制命令
     * @param {}
     *            screen_index 页面屏数,录像和停止用
     * @param{}
     *            lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
     */
    vedioOp : function(root, command, screen_index,lx) {
        // 压缩用到设备编号:设备名称,设备编号:设备名称这种格式
        var device_code_name = $("#vedio_device_name_input").val();
        // 获取操作屏的字符串格式 1,2,4这种格式
        var srceen_val = $("#srceen_val").val();
        if (command == '0') {// 停止
            vedio2.stopVideo(screen_index);
            // 停止用到设备编号:设备名称,设备编号:设备名称这种格式
            var device_code_name = $("#vedio_device_name_input").val();
            // 获取操作屏的字符串格式 1,2,4这种格式
            var srceen_val = $("#srceen_val").val();
            if (device_code_name == '' || srceen_val == '') {
                alert('请选择操作屏');
                return;
            }

            // $("#img_lx").css("display", "none");//隐藏录像图片
            // $("#img_now_lx").css("display","block");//正在录像显示
            // $("#img_tz_lx").css("display","block");//显示停止录像按钮
            $("#CompValue").css("display", "none");// 隐藏压缩方式div
            vedio2.stopAll(root, device_code_name, command, srceen_val,
                    screen_index,lx);
            // $("#img1").attr("title","停止");
            // $("#img1").attr("src","${pageContext.request.contextPath}/images/videostop.png");
        } else if (command == '1') {// 启动播放
        } else if (command == '2') {// 重新启动播放

            // $("#img1").attr("title","重新启动");
            // $("#img1").attr("src","${pageContext.request.contextPath}/images/videostop.png");
            vedio2.startPlay(screen_index);
            // 启动播放用到设备编号:设备名称,设备编号:设备名称这种格式
            var device_code_name = $("#vedio_device_name_input").val();
            // 获取操作屏的字符串格式 1,2,4这种格式
            var srceen_val = $("#srceen_val").val();
            $("#CompValue").css("display", "none");// 隐藏压缩方式div
            if (device_code_name == '' || srceen_val == '') {
                alert('请选择操作屏');
                return;
            }
            vedio2.loadAll(root, device_code_name, command, srceen_val,screen_index,lx);
             $("#CompBtn").css("display", "block");// 隐藏压缩图片
            $("#rar_now").css("display", "none");// 显示正在压缩图片
            $("#rar_tz").css("display", "none");// 显示停止压缩图片
        } else if (command == '5') {// 录像
            // luxiang_now.png正在录像图片
            /*
             * var select_span = $('#img_select_span').css('display'); if
             * (select_span == 'block') { if (devicecode == '' || devicecode ==
             * undefined) { alert("请选择对应的设备"); return; } if (devicename == '' ||
             * devicename == undefined) { alert("请选择对应的设备"); return; } //
             * 设置录像图片为正在录像图片 $("#img_lx").attr("src", root +
             * "/images/luxiang_now.png"); vedio2.loadAll(root,
             * device_code_name, command, srceen_val); } else { alert("请选择操作屏");
             * return; }
             */
            // $("#img1").attr("title","录像");
            // $("#img1").attr("src","${pageContext.request.contextPath}/images/videostop.png");
            vedio2.lx(screen_index);
            // 压缩用到设备编号:设备名称,设备编号:设备名称这种格式
            var device_code_name = $("#vedio_device_name_input").val();
            // 获取操作屏的字符串格式 1,2,4这种格式
            var srceen_val = $("#srceen_val").val();
            if (device_code_name == '' || srceen_val == '') {
                alert('请选择操作屏');
                return;
            }

            $("#img_lx").css("display", "none");// 隐藏录像图片
            $("#img_now_lx").css("display", "block");// 正在录像显示
            $("#img_tz_lx").css("display", "block");// 显示停止录像按钮
            $("#CompValue").css("display", "none");// 隐藏压缩方式div
            vedio2.loadAll(root, device_code_name, command, srceen_val,screen_index,lx);
        } else if (command == '6') {// 压缩
            // 压缩用到设备编号:设备名称,设备编号:设备名称这种格式
            var device_code_name = $("#vedio_device_name_input").val();
            // 获取操作屏的字符串格式 1,2,4这种格式
            var srceen_val = $("#srceen_val").val();
            var radioValue = $('input[name="ys"]:checked').val();
            if (radioValue == undefined) {
                alert("请选择压缩方式");
                return;
            } else {
                // 设置压缩方式层隐藏
                $("#CompValue").css("display", "none");
                // 设置压缩图片为正在压缩图片
                // $("#CompBtn").attr("src", root + "/images/rar_curent.png");
                $("#CompBtn").css("display", "none");// 隐藏压缩图片
                $("#rar_now").css("display", "block");// 显示正在压缩图片
                $("#rar_tz").css("display", "block");// 显示停止压缩图片
                // 设置CompBtn操作op属性值为no
                $("#CompBtn").attr("op", "no");
                
                //停止
//                vedio2.stopAll(root, device_code_name, command, srceen_val,
//                    screen_index,lx);
                
                // CIF:352×288,D1:704×576,压缩
                vedio2.loadAll(root, device_code_name, command + ":"
                                + radioValue, srceen_val,screen_index,lx);
                //播放
//                vedio2.loadAll(root, device_code_name, command, srceen_val,screen_index);                
            }
        } else if (command == '15') {// 停止录像

            // 隐藏录像图片
            $("#img_lx").css("display", "block");
            $("#img_now_lx").css("display", "none");// 正在录像显示
            $("#img_tz_lx").css("display", "none");// 显示停止录像按钮
            $("#img_xz_lx").css("display", "block");// 显示停止录像按钮
            $("#CompValue").css("display", "none");// 隐藏压缩方式div

        }else if (command == '10') {
            //alert("999"+root);
//            D:\apache-tomcat-6.0.33\apache-tomcat-6.0.33\webapps\itms\ckplayer6.3_all\1.mp4
            var pathhttp='http://192.168.6.7:8099/atms1//20140527/610101200500/02/3/610101200500_02_3_20140527123315955_0.jpg';
//            var pathhttp='http://localhost:8080/itms/ckplayer6.3_all/1.mp4';
            //debugger;
            window.location.href=root+"/vedio/index/downLoadVideo/?path="+pathhttp;
        }
        else if (command == '11') {
        } else if (command == '12') {
        } else if (command == '13') {// 停止压缩按钮
            //设置压缩方式层隐藏
            $("#CompValue").css("display", "none");
            $("#CompBtn").css("display", "block");// 显示压缩图片
            $("#CompBtn").attr("op", "yes");
            $("#rar_now").css("display", "none");// 显示正在压缩图片
            $("#rar_tz").css("display", "none");// 显示停止压缩图片
            // 压缩用到设备编号:设备名称,设备编号:设备名称这种格式
            var device_code_name = $("#vedio_device_name_input").val();
            // 获取操作屏的字符串格式 1,2,4这种格式
            var srceen_val = $("#srceen_val").val();
            vedio2.loadAll(root, device_code_name, 2, srceen_val,screen_index,lx);
        }

        else {
        }
    }
};

// 视频压缩类
var yasuo = {
    one : function() {
        // 获取视频设备和视频名称值
        var devicecode = $("#imgpath1_code").val();
        var devicename = $("#imgpath1_name").html();
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();
        // 获取播放视频是否选中
        var select_span = $('#img_select_span').css('display');
        // 选中操作屏
        if (select_span == 'block') {
            // 未选中视频设备进行播放
            if (devicecode == '' || devicecode == undefined) {
                alert("请选择对应的设备");
                return;
            }
            // 未选中视频设备进行播放
            if (devicename == '' || devicename == undefined) {
                alert("请选择对应的设备");
                return;
            }
            device_name_array.push(devicecode + ":" + devicename);
            screen_data_array.push(1);
        } else {
            alert("请选择操作屏");
            return;
        }

        setDeviceSrceenValue(device_name_array, screen_data_array);
    },
    two : function() {
        // 获取视频设备和视频名称值
        var devicecode = $("#imgpath1_code").val();
        var devicename = $("#imgpath1_name").html();
        var devicecode2 = $("#imgpath2_code").val();
        var devicename2 = $("#imgpath2_name").html();
        // 获取播放视频1屏是否选中
        var select_span = $('#img_select_span').css('display');
        // 获取播放视频2屏是否选中
        var select_span2 = $('#img_select_span2').css('display');
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();

        // 选中操作屏
        if (select_span == 'block' || select_span2 == 'block') {

            if (select_span == 'block') {
                // 未选中视频设备进行播放
                if (devicecode == '' || devicecode == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename == '' || devicename == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode + ":" + devicename);
                screen_data_array.push(1);
            }
            if (select_span2 == 'block') {
                // 未选中视频设备进行播放
                if (devicecode2 == '' || devicecode2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename2 == '' || devicename2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode2 + ":" + devicename2);
                screen_data_array.push(2);
            }

        } else {
            alert("请选择操作屏");
            return;
        }
        setDeviceSrceenValue(device_name_array, screen_data_array);
    },
    four : function() {
        // 获取视频设备和视频名称值
        var devicecode = $("#imgpath1_code").val();
        var devicename = $("#imgpath1_name").html();
        var devicecode2 = $("#imgpath2_code").val();
        var devicename2 = $("#imgpath2_name").html();
        var devicecode3 = $("#imgpath3_code").val();
        var devicename3 = $("#imgpath3_name").html();
        var devicecode4 = $("#imgpath4_code").val();
        var devicename4 = $("#imgpath4_name").html();
        // 获取播放视频1屏是否选中
        var select_span = $('#img_select_span').css('display');
        // 获取播放视频2屏是否选中
        var select_span2 = $('#img_select_span2').css('display');
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();

        // 选中操作屏
        if (select_span == 'block' || select_span2 == 'block') {

            if (select_span == 'block') {
                // 未选中视频设备进行播放
                if (devicecode == '' || devicecode == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename == '' || devicename == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode + ":" + devicename);
                screen_data_array.push(1);
            }
            if (select_span2 == 'block') {
                // 未选中视频设备进行播放
                if (devicecode2 == '' || devicecode2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename2 == '' || devicename2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode2 + ":" + devicename2);
                screen_data_array.push(2);
            }

        } else {
            alert("请选择操作屏");
            return;
        }
        setDeviceSrceenValue(device_name_array, screen_data_array);
    },
    six : function() {
        // 获取视频设备和视频名称值
        var devicecode = $("#imgpath1_code").val();
        var devicename = $("#imgpath1_name").html();
        var devicecode2 = $("#imgpath2_code").val();
        var devicename2 = $("#imgpath2_name").html();
        // 获取播放视频1屏是否选中
        var select_span = $('#img_select_span').css('display');
        // 获取播放视频2屏是否选中
        var select_span2 = $('#img_select_span2').css('display');
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();

        // 选中操作屏
        if (select_span == 'block' || select_span2 == 'block') {

            if (select_span == 'block') {
                // 未选中视频设备进行播放
                if (devicecode == '' || devicecode == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename == '' || devicename == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode + ":" + devicename);
                screen_data_array.push(1);
            }
            if (select_span2 == 'block') {
                // 未选中视频设备进行播放
                if (devicecode2 == '' || devicecode2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename2 == '' || devicename2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode2 + ":" + devicename2);
                screen_data_array.push(2);
            }

        } else {
            alert("请选择操作屏");
            return;
        }
        setDeviceSrceenValue(device_name_array, screen_data_array);
    },
    nine : function() {
        // 获取视频设备和视频名称值
        var devicecode = $("#imgpath1_code").val();
        var devicename = $("#imgpath1_name").html();
        var devicecode2 = $("#imgpath2_code").val();
        var devicename2 = $("#imgpath2_name").html();
        // 获取播放视频1屏是否选中
        var select_span = $('#img_select_span').css('display');
        // 获取播放视频2屏是否选中
        var select_span2 = $('#img_select_span2').css('display');
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();

        // 选中操作屏
        if (select_span == 'block' || select_span2 == 'block') {

            if (select_span == 'block') {
                // 未选中视频设备进行播放
                if (devicecode == '' || devicecode == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename == '' || devicename == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode + ":" + devicename);
                screen_data_array.push(1);
            }
            if (select_span2 == 'block') {
                // 未选中视频设备进行播放
                if (devicecode2 == '' || devicecode2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename2 == '' || devicename2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode2 + ":" + devicename2);
                screen_data_array.push(2);
            }

        } else {
            alert("请选择操作屏");
            return;
        }
        setDeviceSrceenValue(device_name_array, screen_data_array);
    },
    sixteen : function() {
        // 获取视频设备和视频名称值
        var devicecode = $("#imgpath1_code").val();
        var devicename = $("#imgpath1_name").html();
        var devicecode2 = $("#imgpath2_code").val();
        var devicename2 = $("#imgpath2_name").html();
        // 获取播放视频1屏是否选中
        var select_span = $('#img_select_span').css('display');
        // 获取播放视频2屏是否选中
        var select_span2 = $('#img_select_span2').css('display');
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();

        // 选中操作屏
        if (select_span == 'block' || select_span2 == 'block') {

            if (select_span == 'block') {
                // 未选中视频设备进行播放
                if (devicecode == '' || devicecode == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename == '' || devicename == undefined) {
                    alert("请选择1屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode + ":" + devicename);
                screen_data_array.push(1);
            }
            if (select_span2 == 'block') {
                // 未选中视频设备进行播放
                if (devicecode2 == '' || devicecode2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                // 未选中视频设备进行播放
                if (devicename2 == '' || devicename2 == undefined) {
                    alert("请选择2屏对应的设备");
                    return;
                }
                device_name_array.push(devicecode2 + ":" + devicename2);
                screen_data_array.push(2);
            }

        } else {
            alert("请选择操作屏");
            return;
        }
        setDeviceSrceenValue(device_name_array, screen_data_array);
    },
    /**
     * 设置设备编号和选中的操作屏
     * 
     * @param {}
     *            device_name_array 设备编号值，格式为设备编号:设备名称,设备编号:设备名称
     * @param {}
     *            screen_data_array 选中操作屏, 格式为1,2,3
     */
    setDeviceSrceenValue : function(device_name_array, screen_data_array) {
        // op属性判断是否显示选择压缩方式弹出层
        var op = $("#CompBtn").attr("op");
        if (op == "yes") {
            var isShow = $("#CompValue").css("display");
            if (isShow == 'none') {
                $("#CompValue").css("display", "block");
                // 给隐含域赋值，设置选中操作屏的设备编号:设备名称,设备编号:设备名称这种格式
                $("#vedio_device_name_input").val(device_name_array.join());
                // 给隐含域赋值，设置选中操作屏 1,2,5 这种格式
                $("#srceen_val").val(screen_data_array.join());
            } else {
                $("#CompValue").css("display", "none");
            }
        } else {
        }
    },
    /**
     * 根据几屏显示操作几屏
     * 
     * @param {}
     *            screen 几屏
     */
    test : function(screen) {
        //debugger;
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();
        // 存放选中的屏的下标
        var index_array = new Array();
        var isrun = false;
        for (var i = 1; i < screen + 1; i++) {
            var flag = 0;
            var value = yasuo.selectedvedio(i);
            // alert(value);
            var valuearray = value.split(":");
            if (valuearray[1] == "block") {
                index_array.push(valuearray[0]);
                if (valuearray[2] == "##") {
                    alert("请选择" + i + "屏操作屏");
                    flag = 1;
                } else {

                }
            } else {
                if (valuearray[2] == "##") {

                } else {
//                    alert("请选择" + i + "屏操作屏");
//                    flag = 1;
                }

            }
            if (flag == 1) {
                return;
            }
        }
        if (index_array.length == 0) {
             alert("请选择有视频播放的操作屏");
            return;
        }
        for (var i = 0; i < index_array.length; i++) {

            var flag = yasuo.selectScreen(index_array[i], device_name_array,
                    screen_data_array);
            if (flag) {
                isrun = true;
                continue;
            } else {
                isrun = false;
                break;
            }
        }
        if (isrun) {
            // alert(device_name_array+"---"+screen_data_array.join()+"---");
            yasuo.setDeviceSrceenValue(device_name_array, screen_data_array);
        }

    },
    selectScreen : function(index, device_name_array, screen_data_array) {
        // 获取视频设备和视频名称值
        var devicecode = $("#imgpath" + index + "_code").val();
        var devicename = $("#imgpath" + index + "_name").html();
        // 获取播放视频是否选中
        var select_span = $('#img_select_span' + index).css('display');
        // 选中操作屏
        if (select_span == 'block') {
            // 未选中视频设备进行播放
            if (devicecode == '' || devicecode == undefined) {
                alert("请选择" + index + "屏对应的设备");
                return false;
            }
            // 未选中视频设备进行播放
            if (devicename == '' || devicename == undefined) {
                alert("请选择" + index + "屏对应的设备");
                return false;
            }
            device_name_array.push(devicecode + ":" + devicename);
            screen_data_array.push(index);
            return true;
        }
        // else {
        // alert("请选择操作屏");
        // return false;
        // }
    },
    selectedvedio : function(i) {
        //debugger;
        var select_span = $('#img_select_span' + i).css('display');
        var imgpath_code = $("#imgpath" + i + "_code").val();
        if (imgpath_code == '' || imgpath_code == undefined
                || imgpath_code == null) {
            imgpath_code = '##';
        }
        if (select_span == 'block') {
            return i + ":block:" + imgpath_code;
        } else {
            return i + ":none:" + imgpath_code;
        }
    }

};

/**
 * 录像类
 * @type 
 */
var luxiang = {
    /**
     * 根据几屏显示操作几屏
     * 
     * @param {}
     *            screen 几屏
     */
    test : function(screen) {
        // 存放选中的设备编号和名称数据
        var device_name_array = new Array();
        // 存放选中操作屏的值
        var screen_data_array = new Array();
        // 存放选中的屏的下标
        var index_array = new Array();
        var isrun = false;
        for (var i = 1; i < screen + 1; i++) {

            var value = yasuo.selectedvedio(i);
            var valuearray = value.split(":");
            if (valuearray[1] == "block") {
                index_array.push(valuearray[0]);
            }
        }
        if (index_array.length == 0) {
            alert("请选择操作屏");
            return;
        }
        for (var i = 0; i < index_array.length; i++) {
            var flag = yasuo.selectScreen(index_array[i], device_name_array,
                    screen_data_array);
            if (flag) {
                isrun = true;
                continue;
            } else {
                isrun = false;
                break;
            }
        }
        if (isrun) {
            yasuo.setDeviceSrceenValue(device_name_array, screen_data_array);
        }

    }
};
// function selectedvedio(i){
// var select_span = $('#img_select_span'+index).css('display');
// if (select_span == 'block') {
// return "i:block";
// }else{
// return "i:none";
// }
// }
