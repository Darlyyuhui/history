/**
 * 自定义验证规则
 */
String.prototype.getBytes = function() {
	var cArr = this.match(/[^x00-xff]/ig);
	return this.length + (cArr == null ? 0 : cArr.length);
}
function countChars(obj, outObjId){
	document.getElementById(outObjId).innerHTML = "当前字符数：" + obj.value.getBytes();
}
//中文字两个字节
jQuery.validator.addMethod(
    "textLength", 
    function(value, element, param) {
        return this.optional(element) || (value.getBytes() <= param);   
    }, 
    $.validator.format("请确保输入的值在{0}个字符之内(一个中文字算2个字符)")
);
//验证联系电话
jQuery.validator.addMethod("isTel", function(value, element){
	var tel = /^(\d{3,4})-(\d{7,9})$/g;
	return this.optional(element) || (tel.test(value));
}, "电话号码格式错误xxxx-xxxxxxxxx");

//手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^1[34578]\d{9}$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请填写正确的手机号码");

//联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function(value,element) {
	var length = value.length;
	var mobile = /^1[34578]\d{9}$/;
	var tel = /^(\d{3,4})-(\d{7,9})$/g;
	return this.optional(element) || (tel.test(value) || mobile.test(value));
}, "填写正确的联系电话");

//IP地址验证
jQuery.validator.addMethod("ipAddr", function(value, element) { 
	var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
	return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "Ip地址格式错误");