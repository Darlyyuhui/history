/**
 * Created by Administrator on 2016/12/1.
 */
/**
 * 地址搜索框
 * 结果条目
 * @param    isBlock    判断时候显示的  true显示  false不显示
 * @param    wrapBox    放置外部容器的搜索框
 * @param    width      搜索框的宽度
 * @param    left       相对外部容器的左边距离
 * @param    top        相对外部容器的上边距离
 */
(function($){
    $.selectMap = {
        //初始化方法
        init:function(parme){
            var parme =  $.extend({},$._default,parme);
            if(parme.isBlock && parme.wrapBox){
                this.setFrom(parme);
            };

            this.clickButton();
            this.inputEvent();
            this.inputChange();
            this.upDown();         ;
        },
        //搜索框对象
        setFrom:function(parme){
            var $form = $("<form class='form-search' style='position:absolute;"+parme.left+";top:"+parme.top+";'>"
                            +"<span class='input-icon align-middle'>"
                            +"<i class='ace-icon fa fa-search'></i>"
                            +"<input type='text' autofocus autocomplete='off' class='search-query' placeholder='请输入搜索企业名称' style='width: "+parme.width+"px;' id='inp'>"
                            +"</span>"
                            +"<button class='btn btn-sm btn-primary' type='button' id='button'>搜索</button>"
                            +"<ul style=' width:"+parme.width+"px; margin:0;z-index:100' id='select'></ul>"
                            +"</form>");
            //添加到容器中
            $form.appendTo(parme.wrapBox)
        },
        //点击button获取数据的方法
        clickButton:function(){
            $("#button").click(function(){
                getData();
            });
        },
        //给input绑定enter事件
       inputEvent:function(){
           var that = this;
            $(document).keydown(function(event){
                var event = event || window.event;
                if(event.keyCode == "13"){
                    $("#select").empty();
                    that.index = -1;
                    getData();
                    return false;
                }else if(event.keyCode == "8"){
                    $("#select").empty();

                }
            });
        },
        //聚焦事件搜索框
        inputChange:function(){

            $("#inp").on("focus",function(){
                $("#select").empty();
            });
            $("#inp").on("click",function(){
                $("#select").empty();
            });
        },
        //updown事件
        index:-1,
        upDown:function(){
            var that= this;
            $(document).keydown(function(event){
                var event = event || window.event;
                if(event.keyCode == "38"){
                    that.index--;
                    if(that.index<0){that.index = that.index%$("#select li").length+$("#select li").length}
                    that.index = that.index%$("#select li").length;
                    $("#select li").eq(that.index).css({color:"red",background:"gray"}).siblings().css({color:"black",background:"#fff"});
                    $("#inp").val($("#select li").eq(that.index).html());
                };
                if(event.keyCode == "40"){
                    that.index++;
                    if(that.index<0){that.index = that.index%$("#select li").length+$("#select li").length}
                    that.index = that.index%$("#select li").length;
                    $("#select li").eq(that.index).css({color:"red",background:"gray"}).siblings().css({color:"black",background:"#fff"});
                    $("#inp").val($("#select li").eq(that.index).html());
                }
            });
        }
    };
    //默认的参数
    $._default = {
        width:400,
        left:0,
        top:0

    };
    function getData(){
        var $select = $("#select");
        var val = $("#inp").val();
        $.get( "http://localhost/ea/enter/get/nature/list/",{search_enterName:val},
            function(data){
                if(data.result.length == 0 && $select.children().length == 0){$("<li>").appendTo($select).html("未找到搜索地点").css({
                    listStyle:"none",
                    padding:"3px 23px",
                    color:"black",
                    cursor: "pointer",
                    background:"#fff",
                    border:"1px solid #666",
                    color:"red"
                })};
                if($select.children().length>0){
                    return;
                }else{
                    for(var i=0;i<data.result.length;i++){
                        $("<li>").appendTo($select).html(data.result[i].enterid).css({
                            listStyle:"none",
                            padding:"3px 23px",
                            color:"black",
                            cursor: "pointer",
                            background:"#fff",
                            border:"1px solid #666",
                            borderTop:0
                        }).hover(function(){
                            $(this).css({color:"red",background:"gray"})
                        },function(){
                            $(this).css({color:"black",background:"#fff"})
                        }).attr({
                            longitude:data.result[i].longitude+"",
                            latitude:data.result[i].latitude+""

                        }).click(function(){
                            $(this).parent().empty();
                            $("#inp").val($(this).html())
                        })
                    };

                }
            });

    };
})(jQuery);
