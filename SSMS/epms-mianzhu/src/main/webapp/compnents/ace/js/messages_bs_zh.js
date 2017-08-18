/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "该记录已存在，请修正该数据",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请输入相同的值",
		minTo: jQuery.validator.format("请输入最大为 {0} 的值"),
		maxTo: "请输入最小为 {0} 的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入最多 {0} 位的字符串"),
		minlength: jQuery.validator.format("请输入最少 {0} 位的字符串"),
		rangelength: jQuery.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入最大为 {0} 的值"),
		min: jQuery.validator.format("请输入最小为 {0} 的值")
});

jQuery.extend(jQuery.validator.defaults, {
    errorElement: "span"
});


/**
"^(-?\\d+)(\\.\\d+)?$"　  //浮点数     
"^[A-Za-z]+$"　　          //由26个英文字母组成的字符串     
"^[A-Z]+$"　　             //由26个英文字母的大写组成的字符串     
"^[a-z]+$"　　             //由26个英文字母的小写组成的字符串     
"^[A-Za-z0-9]+$"　　      //由数字和26个英文字母组成的字符串     
"^\w+$"　　               //由数字、26个英文字母或者下划线组成的字符串  
***/
$.validator.addMethod("english", function (value, element, param) {
	var d = /^[A-Za-z]+$/;
	return d.test(value);
},"\u53ea\u80fd\u8f93\u5165\u82f1\u6587");

$.validator.addMethod("chinese", function (value, element, param) {
	var d = /^[\u0391-\uFFE5]+$/;
	return d.test(value);
},"\u53ea\u80fd\u8f93\u5165\u4e2d\u6587");

$.validator.addMethod("englishDigits", function (value, element, param) {
	var d = /^[A-Za-z0-9]+$/;
	return d.test(value);
},"\u53EA\u80FD\u8F93\u5165\u82F1\u6587\u548C\u6570\u5B57");

//法人代码验证
$.validator.addMethod("checkLegalCode", function (value, element, param) {
    if(value.length==0){
        return true;
    }
    var d = /^[A-Za-z0-9]{8}-[A-Za-z0-9]$/;
    return d.test(value);
},"请输入正确的法人代码。如:XXXXXXXX-X");


/***
 * 电话号码验证
 */
jQuery.validator.addMethod("telephone", function(value, element) {
    //电话号码格式010-12345678
    var telephoneReg = /^\d{3,4}-\d{7,8}$/;
    return this.optional(element) || (telephoneReg.test(value));
}, "请填写正确的电话号码，固话格式：区号-7到8位数字");
/***
 * 手机号码验证
 */
jQuery.validator.addMethod("mobilephone", function(value, element) {
    //电话号码格式13589299392
    var mobilephoneReg = /^(((1[3-9]{1}[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (mobilephoneReg.test(value));
}, "请填写正确的手机号码");
/***
 *验证手机号，支持11位
 * /^1[3-9]\d{9}$/
 */
$.validator.addMethod("phone", function (value, element, param) {
	var d = /^(1[3-9]\d{9})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
	return this.optional(element) || d.test(value);
},"请输入正确的电话号码");

/***
 *验证电话号码，包括手机号和固定电话
 */
$.validator.addMethod("teletest", function (value, element, param) {
	var phone1 = /^0{1}\d{2,3}-\d{7,8}$/;
	var phone2 = /^(((1[3-9]{1}[0-9]{1}))+\d{8})$/;
	return this.optional(element) || phone1.test(value) || phone2.test(value);
},"请输入正确的座机号或手机号，固话格式：区号-7到8位数字"); 

$.validator.addMethod("regValueEx", function (value, element, param) {
	var regValue = element.reg;
	var objExp = new RegExp(regValue); 
	return objExp.test(value);
},"您输入的数据不正确");

/**;
*/
$.validator.addMethod("ip", function (value, element, param) {
	var pattern=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	return pattern.test(value);
},"请输入正确的ip地址");

//非空验证
$.validator.addMethod(
    "notnull",
    function (value, element, regexp)
    {
        if (!value) return true;
        return !$(element).hasClass("l-text-field-null");
    },
    "不能为空"
);

//颜色验证 add by kouyunhao 2013-10-22
$.validator.addMethod("color", function (value, element) {
	var pattern=/^([0-9]|[1-9][0-9]|1[0-9]{1,2}|2[0-5]{1,2})\,([0-9]|[1-9][0-9]|1[0-9]{1,2}|2[0-5]{1,2})\,([0-9]|[1-9][0-9]|1[0-9]{1,2}|2[0-5]{1,2})$/;
	return this.optional(element) || pattern.test(value);
},"请输入合法的颜色");

//特殊字符验证 add by kouyunhao 2013-10-21
$.validator.addMethod("specialcharfilter", function (value, element, param) {
	var pattern = "`~!@#$%^&*()-=+|{}':;',\[\\].<>/?\"·～！￥……×（）——『』【】《》‘；：”“'。，、？"; 
	var flag = true;
	for(var i = 0; i < pattern.length; i++){
		patt = pattern.charAt(i);
		if(value.indexOf(patt) != -1){
			flag = false;
			break;
		}
	}
	return flag;
},"不包含特殊字符");

//密码合法性验证 add by kouyunhao 2013-07-22
$.validator.addMethod("password", function (value, element) {
	var pattern=/^\w+$/;
	return this.optional(element) || pattern.test(value);
},"请输入合法的密码");

//邮箱验证 add by kouyunhao 2013-10-12
/**$.validator.addMethod("checkEmail", function (value, element, param) {
	var pattern=/^\w+([-\.]\w+)*@\w+([\.-]\w+)*\.\w{2,4}$/;
	return pattern.test(value);
},"请输入正确的邮箱地址");*/

//身份证号码合法性验证 add by kouyunhao 2013-10-12
$.validator.addMethod("idcard", function (value, element) {
	//身份证正则表达式(15位) 
	//var isIDCard1=/^[1-9]\d{2}([1][9]|[2][0])\d{2}((0[1-9])|(1[0-2]))(([0|1|2][1-9])|3[0-1])\d{4}$/; 
	//身份证正则表达式(18位) 
	var isIDCard2=/^[1-9]\d{5}([1][9]|[2][0])\d{2}((0[1-9])|(1[0-2]))(([0|1|2][1-9])|3[0-1]|([1|2]0))\d{3}(\d|X|x)$/; 
	return this.optional(element) || isIDCard2.test(value);
},"请输入正确的身份证号码");

//车牌号验证 add by kouyunhao 2013-07-23
$.validator.addMethod("platenum", function (value, element) {
	//var pattern=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5,8}$/;
	//普通号牌
	var pattern=/^[\u4e00-\u9fa5]{1}[A-HJ-NP-Za-hj-np-z]{1}[A-HJ-NP-Za-hj-np-z0-9]{4,7}([A-HJ-NP-Za-hj-np-z0-9]|[\u4e00-\u9fa5]){1}$/;
	//var pattern=/^[\u4e00-\u9fa5]{1}[A-HJ-NP-Za-hj-np-z]{1}[A-HJ-NP-Za-hj-np-z0-9]{4,7}([A-HJ-NP-Za-hj-np-z0-9]|[警]){1}$/;
	//var pattern=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{4,7}([A-Z0-9]|[警]){1}$/;
	//武警号牌
	var wjplate=/^WJ(0[1-9]|[1-2][0-9]|3[0-2])-[\u4e00-\u9fa50-9]{1}[0-9]{3}[1-9]$/;
	return this.optional(element) || pattern.test(value) || wjplate.test(value);
},"请输入正确的号牌号码");

//异步验证道路名 YANTAO ADD 2013.07.15
 jQuery.validator.addMethod("checkRoadName", function(value, element){
        var result = false;
        // 设置同步
        $.ajaxSetup({
            async: false
        });
        var param = {
            name: value
        };
        $.post("/atms/device/roadinfo/roadNameExist/", param, function(data){
            result = (0 != data);
        });
        // 恢复异步
        $.ajaxSetup({
            async: true
        });
        return result;
    }, "道路名称输入与GIS已有道路库有偏差!");

    
    //异步验证设备编号 YANTAO ADD 2013.08.03
 jQuery.validator.addMethod("checkDeviceCode", function(value, element){
        var result = false;
        // 设置同步
        $.ajaxSetup({
            async: false
        });
        var param = {
            name: value
        };
        $.post("/atms/device/deviceinfo/deviceCodeExist/", param, function(data){
            result = (0 != data);
        });
        // 恢复异步
        $.ajaxSetup({
            async: true
        });
        return result;
    }, "设备编号已存在!");
 
$.validator.addMethod("minCompare", function (value, element) {
		var v1 = this.optional(element);
		var v2 = $(value).val();
		return  v1>=v2;
},"请输入合法的最小值");
 
    