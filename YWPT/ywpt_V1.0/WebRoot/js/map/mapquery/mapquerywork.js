/**
 * @author ZLT
 * @Date 2013-7-24
 */
var identifyClickEvent = function(){

	var i = new IdentifyTool();

	// 重复次数
	var retryTimes = 0;

	// setTimeout句柄
	var getResTime = null;

	// 点击句柄
	var clickHandler = null;
	
	// 是否已经给extent绑定了完成事件
	var _extentBinded = false;
	
	// 是否给地图单击事件绑定了完成事件
	var _clickBinded = false;

	var _map = itmap.arcgis.map;

	// 初始化控件
	i.setMap(_map).init().call(getI).url(baseServiceURL.basemapnew.url).setFieldRelation({
		name : 'NAME,MC,_TEXT',
		classify : 'source,category'
	});

	// 点击方式
	$("#identifyClickStyle").live('click',function(){
		i.clear();
		i.stopTool();
		if(!_clickBinded && !_extentBinded){
			clickHandler = dojo.connect(i.beginClick(),'onDrawEnd',function(e){
				showMsg("正在查询...");
				i.identify(e);
				i.stopTool();
			});
		}else{
			i.beginClick();
		}
		
	});

	// 清除
	$("#identifyClearM").live('click',function(){
		i.clear();
	});

	// 框选方式
	$("#identifyExtentStyle").die("click").live('click',function(){
		i.clear();
		if(clickHandler!=null){
			i.stopTool();
		}
		if(!_extentBinded && !_clickBinded){
			dojo.connect(i.beginExtent(),"onDrawEnd",function(){
				showMsg("正在查询...");
				i.identify(i.getExtentGeometry());
			});
			_extentBinded = true;
		}else{
			i.beginExtent();
		}
	});

	// 获取最终结果
	function getI(result){
		showMsg("查询完成！");
		setTimeout(clearMsg,2000);
		var _relation = {
			layerId:"图层ID",
			layerName:"图层名称",
			shape:"图层类型"
		}
		
		var _res = i.getIdentifyTextRes(result);
		for(var _index=0,len=_res.length;_index<len;_index++){
			_res[_index].func = (function(__indexs){
				return function(){
					_map.centerAt(_res[__indexs].data.point);
					itmap.arcgis.mapGraphicManager("mapPositionSign").clear();
					itmap.arcgis.mapGraphicManager("mapPositionSign").add(new esri.Graphic(_res[__indexs].data.point,itmap.arcgis.symbol.positionSymbolOffset()));
				}
			})(_index)
		}
		
		itmap.util.mapResultboxNew().init("mapResultC").addContent({content:_res,switchtab:true,relation:_relation});
		
		var data = [];
		var ids = [];
		for(var _index = 0,len=result.length;_index<len;_index++){
			var pos = inArray(result[_index].layerId,ids);
			if(pos==-1){
				ids.push(result[_index].layerId);
				data.push({name:result[_index].layerName,total:1});
			}else{
				data[pos].total++;
			}
		}
		
		function inArray(item,arr){
			for(var arrindex=0,len=arr.length;arrindex<len;arrindex++){
				if(item==arr[arrindex]){
					return arrindex;
				}
			}
			return -1;
		}
		
		itmap.util.chart().init({src:"mapOther",data:data});
		
		i.release();

	}
	
	function showMsg(msg){
		$("#mapGeoQueryMsg").html("<font color=red>"+msg+"</font>");
	}
	function clearMsg(){
		$("#mapGeoQueryMsg").html("");
	}
}