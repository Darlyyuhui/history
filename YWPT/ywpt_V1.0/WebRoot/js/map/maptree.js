/*-----------------------------
 * @author LHF
 * Date : 2013-8-13
 * 需要加载类库有：
 *  "jquery-1.4.4.min.js"
 * 	"jquery.ztree.core-3.5.min.js"
 * 
 -----------------------------*/

//定义菜单树的闭包
var mapTree = (function(){
     return function(){
    	 
    	//设置菜单生成需要的参数
 		var settings= {
 				data: {
 		                simpleData: {
 		                    enable: true,
 		                    idKey:"id",
 		                    pIdKey:"pid" 		            
 		                    }
 		                },
 				callback:{
 		                	onClick:treeOnclick
 		                }
 				
 	    		 }
    	 
    	  
         var api={
             init:init
         };
 		
 		//初始化菜单
 		function init(domObj){ 			
 			$.ajax({
 				url:"map/home/createTree/",
 				type:"post",
 				dataType:"json",
 				async: true,
 				success:function(json){
										
					//初始化菜单
					try{
						createTree(domObj,json,settings);
					}catch(err){
						alert(err.name+":"+err.message);
					}				
					
				}
 			});	
 		}
 		
// 		//默认节点单击事件
// 		function treeOnclick(event, treeId, treeNode){
//		 	console.log("默认方法");
//		 } 
 
         function createTree(domObj,json,settings){
        	 
        	 //变量
        	 var num = 0;
        	 var jsonArray;//存储次级菜单json的数组
        	 var topMenuP;//顶级菜单p标签
        	 var menuText;//顶级菜单文本
        	 var topMenuSpan;//顶级菜单span标签
        	 var menuDiv;//次级菜单容器DIV
        	 var menuUl;//次级菜单ul
        	 var j;

        	 while (num<json.length){
        		 if (json[num].level == "2"){
        			 
        			 //创建顶级菜单
        			 topMenuP = document.createElement("p");
        			 topMenuP.setAttribute("class","menu_head");
        			 topMenuP.setAttribute("id", json[num].id);
        			 topMenuP.setAttribute("name", json[num].name);
        			 
        			 menuText = document.createTextNode(json[num].name);//创建顶级菜单文本        			 
        			 topMenuSpan = document.createElement("span");
        			 topMenuSpan.appendChild(menuText);
        			 topMenuP.appendChild(topMenuSpan);
        			 
        			 domObj.appendChild(topMenuP);
        			 
        			 //创建次级菜单
        			 menuDiv = document.createElement("div");
//        			 menuDiv.setAttribute("id", json[num].id);
        			 menuDiv.setAttribute("class","menu_body");
        			 
        			 menuUl = document.createElement("ul");
        			 menuUl.setAttribute("id", json[num+1].id);
					 menuUl.setAttribute("class","ztree");
					 
					 menuDiv.appendChild(menuUl);
					 domObj.appendChild(menuDiv);
					 
					 if (json[num].childCount){
						 
						 //读出次级菜单
						 jsonArray = new Array();
						 for (j=1;j<=json[num].childCount;j++){
							 jsonArray.push(json[num+j]);
						 }
						 
						 $.fn.zTree.init($("#"+json[num+1].id), settings, jsonArray);//生成次级菜单
						 
						 num+=json[num].childCount+1;					 
					 }else{
						 
						 num++;
					 }							 
        		 }else{
        			 num++;
        		 } 
        		 
        	 }
        	 
        	 $(".menu_head").click(topMenuClick);
        	 
        	 $(".menu_head").first().next("div.menu_body").slideDown(500);
        	 $(".menu_head").click(function(){
        		 $(this).next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
        	 });
         }
         return api;
     }
 })();

	//初始化菜单
	$(function(){
//		console.log("init");
		var map = new mapTree();
		map.init(document.getElementById("lefttree"));
		
	});
	
	
	//在此添加树节点（次级菜单）单击事件
	function treeOnclick(event, treeId, treeNode){
		
		mapToolsFrame.addUrl(treeNode.rest,{menuid:treeId,name:treeNode.name});
		
		// 修改工具台名字
		if(!$("#mapWorkSpace span")[0]){
			$("#mapWorkSpace").append("<span></span>");
		}
		$("#mapWorkSpace span").text(" > "+treeNode.name+"");
	 }
	
	//在此添加顶级菜单单击事件
	function topMenuClick(event){
//		if ($(this).attr("name")=="专题图层")
//		{
//			console.log("专题图层");
//		}
	}
	