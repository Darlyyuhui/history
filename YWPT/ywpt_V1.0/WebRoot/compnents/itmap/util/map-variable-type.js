/**
 * 本类用于判断不同变量的类型
 * @author ZLT
 * @date 2013-10-17
 * 接口：
 * isObject 判断是否为对象
 * isEmptyObject 判断是否为空对象
 * isArray 判断是否为数组
 * isString 判断是否为字符串
 * isNumeric 判断是否为数字
 * isBoolean 判断是否为布尔类型
 * isReg 判断是否为正则
 * isDate 判断是否为日期
 * isFunc 判断是否为函数
 **/
itmap.util.variableTypes = {
		
	/**
	 * 系统变量类型
	 */
	_objTypes : {},
	
	/**
	 * 获取变量类型
	 */
	_type : function(){
		var typeName = ["Boolean","Number","String","Function","Array","Date","RegExp","Object"];
		var len = typeName.length;
		_objTypes = {};
		while(len--){
			this._objTypes["[object "+typeName[len]+"]"] = typeName[len].toLowerCase();
		}
		return typeName;
	},
	
	/**
	 * 获取变量类型
	 */
	_callType : function(obj){
		this._type();
		return obj===null ? String(obj) : this._objTypes[Object.prototype.toString.call(obj)] || "object";
	},
	
	/**
	 * 判断是否为对象类型
	 */
	isObject : function(obj){
		return this._callType(obj) === "object";
	},
	
	/**
	 * 判断是否为空对象
	 */
	isEmptyObject : function(obj){
		for(var elem in obj){
			return false;
		}
		return true;
	},
	
	/**
	 * 判断是否为数组
	 */
	isArray : function(obj){
		return this._callType(obj) === "array";
	},
	
	/**
	 * 判断是否为字符串
	 */
	isString : function(obj){
		return this._callType(obj) === "string";
	},
	
	/**
	 * 判断是否为数字
	 */
	isNumeric : function(obj){
		return this._callType(obj) === "number";
	},
	
	/**
	 * 判断是否为布尔类型
	 */
	isBoolean : function(obj){
		return this._callType(obj) === "boolean";
	},
	
	/**
	 * 判断是否为正则类型
	 */
	isReg : function(obj){
		return this._callType(obj) === "regexp";
	},
	
	/**
	 * 判断是否为日期类型
	 */
	isDate : function(obj){
		return this._callType(obj) === "date";
	},
	
	/**
	 * 判断是否为函数类型
	 */
	isFunc : function(obj){
		return this._callType(obj) === "function";
	}
}