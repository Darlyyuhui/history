var comet = {
    connection: false,
    iframediv: false,
    url: "/monitor.svl",
    initialize: function(){
        if (navigator.appVersion.indexOf("MSIE") != -1) {
            comet.connection = new ActiveXObject("htmlfile");
            comet.connection.open();
            comet.connection.write("<html>");
            comet.connection.write("<script>document.domain = '" + document.domain + "'");
            comet.connection.write("</html>");
            comet.connection.close();
            comet.iframediv = comet.connection.createElement("div");
            try {
                comet.connection.appendChild(comet.iframediv);
            }catch(e) {
                if (comet.iframediv.outerHTML) {
                    comet.connection.innerHTML =comet.iframediv.outerHTML;
                }
                else {
                    console.log('not working');
                }
            }
            comet.connection.parentWindow.comet = comet;
            comet.iframediv.innerHTML = "<iframe id='comet_iframe' src='" + comet.url + "'></iframe>";
        }
        else {
            if (navigator.appVersion.indexOf("KHTML") != -1) {
                comet.connection = document.createElement('iframe');
                comet.connection.setAttribute('id', 'comet_iframe');
                comet.connection.setAttribute('src', comet.url);

                with (comet.connection.style) {
                    position = "absolute";
                    left = top = "-100px";
                    height = width = "1px";
                    visibility = "hidden";
                }
                document.body.appendChild(comet.connection);

            }
            else {
                comet.connection = document.createElement('iframe');
                comet.connection.setAttribute('id', 'comet_iframe');
                with (comet.connection.style) {
                    left = top = "-100px";
                    height = width = "1px";
                    visibility = "hidden";
                    display = 'none';
                }
                comet.iframediv = document.createElement('iframe');
                comet.iframediv.setAttribute('src', comet.url);
                comet.connection.appendChild(comet.iframediv);
                document.body.appendChild(comet.connection);
            }
        }
    },
    //添加公共消息
    sendMessage: function(data){
        try {
            var content = data.split(":");
            var dev = content[0];
            var u = content[1];
            var cs = $("#msg_"+dev);
            if (u.length > 0) {
                if (cs.html().length > 20) {
                    cs.html("");
                }
                //$("#msg_"+dev+" li").css("background", "#000");
                cs.show()
                    .append("<li style='color:#000000;list-style:none'>"+data+"</li>")
                    .scrollTop(document.getElementById("msg_"+dev).scrollHeight);

                $("#dev_time").html(data.split("，")[0].split("：")[1]);
            }
        } 
        catch (e) {
            //alert(e);
        }
    },
    //退出
    onUnload: function(){
        if (comet.connection) {
            comet.connection = false;
        }
        $.ajax({
            type: "POST",
            url:  comet.url+"&m=remove",
            data:"name=<%=request.getSession().getId()%>"
        });
    }
};
