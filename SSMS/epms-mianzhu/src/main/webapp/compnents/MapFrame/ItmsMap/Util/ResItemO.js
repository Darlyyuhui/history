/**
 * 结果集盒子 辅助对象
 * 结果条目
 * @param name 结果条目名称，也可以叫label
 * @param func 结果条目点击后的回调函数，若没有，则传空字符串 ""
 * @param attribute 结果条目属性
 * @param data 需要传出的额外数据
 */
MapFactory.Define("ItmsMap/Util/ResItemO*",function(){
	return function(name,func,attribute,data){
	   this.name = name;
	   this.func = func;
	   this.attribute = attribute;
	   this.data = data;
	}
});