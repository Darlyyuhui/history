MapFactory.Define("ItmsMap/UserLayers/DataController*",["ItmsMap/UserLayers/MPointConfig*"],function(MPointConfig){
	//统计数据
	var countList=[];
	//菜单数据
	var menuList=[];
	//所有采样点
	var cydPointList=[];
	//获取目前点击行政区的id
	var reginId="";
	//存储当前选中采样点信息
	var cydPoint={};
	return function(){
		
		var api={
				getCountData:getCountData,
				getMenuData:getMenuData,
				setMenuData:setMenuData,
				setCountData:setCountData,
				getRegionCountByMenus:getRegionCountByMenus,
				setPointList:setPointList,
				getPointList:getPointList,
				getPointListByMenu:getPointListByMenu,
				setReginId:setReginId,
				getRegionId:getRegionId,
				setcydPoint:setcydPoint,
				getcydPoint:getcydPoint
				};
		function setcydPoint(cydPointObj){
			cydPoint=cydPointObj;
		}
		function getcydPoint(){
			return cydPoint;
		}
		function setReginId(regId){
			reginId=regId;
		}
		function getRegionId(){
			return reginId;
		}
		function setPointList(list){
			cydPointList=list;
		}
		function getPointList(){
			return cydPointList;
		}
		
		function setCountData(list){
			 countList=list;
		}
		function setMenuData(list){
			menuList=list;
		}
		function getCountData(){
			return countList;
		}
		function getMenuData(){
			return menuList;
		}
		//根据菜单获取各个行政区域的统计信息
		function getRegionCountByMenus(Menus){
			var newCountList=[];
			for(var id in countList){
				var countObjByType={
						  undergroundWaterNum:0, //地下水监测点
					      surfaceWaterNum:0, //地表灌溉水监测点
					      dirtNum:0, //底泥监测点
					      airNum:0,  //大气沉降采样点
					      backNum:0,  //背景土壤监测点
					      landNum:0,  //农田土壤监测点
					      farmNum:0,  //农作物监测点
					      manureNum:0
				},count={};
				
				var countObj=countList[id];
				
				var cydCountNum=0;
				for(var j=0;j<Menus.length;j++){
				    var elementValue=parseInt(countObj[MPointConfig[Menus[j].id]]);
					cydCountNum=cydCountNum+elementValue;
					countObjByType[MPointConfig[Menus[j].id]]=elementValue;
				}
				count.countObjByType=countObjByType;
				count.cydCountNum=cydCountNum;
				count.countObj=countObj;
				count.id=id;
				count.townName=getTownNameByid(id);
				newCountList.push(count);
			}
			return newCountList;
		}
		//根据行政区域id获取行政区域名称
		function getTownNameByid(id){
			for(var i=0;i<menuList.length;i++){
				var menu=menuList[i];
				if(menu.name=="行政区域"){
				    var childrenMenu=menu.children;
				    for(var j=0;i<childrenMenu.length;j++){
				    	var cm=childrenMenu[j];
				    	if(cm.id==id){
				    		return cm.name;
				    	}
				    }
				}
			}
		}
		/**
		 * 根据菜单选择获取对应的采样点位
		 */
		function getPointListByMenu(Menus,_regionMenu){
			var points=[];
			for(var i=0;i<Menus.length;i++){
				var pointArr=cydPointList[Menus[i].id];
				var newPoint=[];
				for(var j=0;j<pointArr.length;j++){
					var pointObj=pointArr[j];
					if(pointIncludeRegion(pointObj,_regionMenu)){
						newPoint.push(pointObj);
					}
				}
				if(newPoint && newPoint.length>0){
					var obj={};
					obj.dataType=Menus[i].id+"Symbol";
					obj.pointTypeName=Menus[i].name;
					obj.pointTypeTag=Menus[i].id;
					obj.dataList=newPoint;
					points.push(obj);
				}
			}
			return points;
		}
		/**
		 * 判断某个点是否属于某个行政区
		 */
		function pointIncludeRegion(pointObj,_regionMenu){
			for(var i=0;i<_regionMenu.length;i++){
				var region=_regionMenu[i];
				if(region.name==pointObj.regionId){
					return true;
				}
			}
			return false;
		}
		return api;
	}
	
});