/**
 * 业务层面自定义验证规则
 */

//经纬度验证
jQuery.validator.addMethod("isCoordinate", function(value, element) {
	var length = value.length;
	var mobile = /^\d{2,3}(\.\d{1,7})$/;
	return this.optional(element) || (length <= 11 && mobile.test(value));
}, "请填写正确的经纬度(保留7位小数)");

//2小数位数
jQuery.validator.addMethod("decimal2", function(value, element) {
	var length = value.length;
	var mobile = /^\d{0,}(\.\d{1,2})?$/;
	return this.optional(element) || (mobile.test(value));
}, "请填写正确的小数(保留2位小数)");

//4小数位数
jQuery.validator.addMethod("decimal4", function(value, element) {
	var length = value.length;
	var mobile = /^\d{0,}(\.\d{1,4})?$/;
	return this.optional(element) || (mobile.test(value));
}, "请填写正确的小数(保留4位小数)");
//生成指定小数位数
function makeDecimal(val, num) {
	val += "";
	if (val == "") {
		return "";
	}
	if (val == "0") {
		return val;
	}
	if (val.indexOf(".") == -1) {
		return val;
	}
	var strs = val.split(".");
	if (strs[1].length <= num) {
		return val;
	}
	return strs[0] + "." + strs[1].substr(0,num);
}
//土壤编号
//jQuery.validator.addMethod("trCode", function(value, element){
//	var code = /^TR\d{4}$/;
//	return this.optional(element) || (code.test(value));
//}, "土壤编号有误：TR0000");

//外业任务编号
//jQuery.validator.addMethod("rwCode", function(value, element){
//	var code = /^RW[1|2][0-9][0-9][0-9](0?[1-9]|1[0-2])\d{2}$/;
//	return this.optional(element) || (code.test(value));
//}, "任务编号有误：RW2017010000");
