MapFactory.Define("ItmsMap/Util/DateFormat*",function(){
	return {
		"dateFormartFull":function(date, dateSplit, timeSplit){
			if(typeof dateSplit == "undefined")dateSplit = "-";
			if(typeof timeSplit == "undefined")timeSplit = ":";
			var d;
			if(typeof date == "object")d = date;
			else if(typeof date == "number") {
				if(date < 0)return "0000"+dateSplit+"00"+dateSplit+"00 00"+timeSplit+"00"+timeSplit+"00";
				d = new Date(date);
			}
			else d = new Date();
			d.setHours(d.getHours()-8);//北京时间为东8区，所以需要减去8小时
			return d.getFullYear()+dateSplit+
					(d.getMonth()<9?("0"+(d.getMonth()+1).toString()):(d.getMonth()+1))+dateSplit+
					(d.getDate()<10?("0"+d.getDate().toString()):d.getDate())+" "+
					(d.getHours()<10?("0"+d.getHours().toString()):d.getHours())+timeSplit+
					(d.getMinutes()<10?("0"+d.getMinutes().toString()):d.getMinutes())+timeSplit+
					(d.getSeconds()<10?("0"+d.getSeconds().toString()):d.getSeconds());
		},
		"dateFormart":function(date, dateSplit){
			if(typeof dateSplit == "undefined")dateSplit = "-";
			var d;
			if(typeof date == "object")d = date;
			else if(typeof date == "number") {
				if(date < 0)return "0000"+dateSplit+"00"+dateSplit+"00";
				d = new Date(date);
			}
			else d = new Date();
			d.setHours(d.getHours()-8);
			return d.getFullYear()+dateSplit+
					(d.getMonth()<9?("0"+(d.getMonth()+1).toString()):(d.getMonth()+1))+dateSplit+
					(d.getDate()<10?("0"+d.getDate().toString()):d.getDate());
		},
		"timeFormart":function(date, timeSplit){
			if(typeof timeSplit == "undefined")timeSplit = ":";
			var d;
			if(typeof date == "object")d = date;
			else if(typeof date == "number") {
				if(date < 0)return "00"+timeSplit+"00"+timeSplit+"00";
				d = new Date(date);
			}
			else d = new Date();
			d.setHours(d.getHours()-8);
			return  (d.getHours()<10?("0"+d.getHours().toString()):d.getHours())+timeSplit+
					(d.getMinutes()<10?("0"+d.getMinutes().toString()):d.getMinutes())+timeSplit+
					(d.getSeconds()<10?("0"+d.getSeconds().toString()):d.getSeconds());
		},
		
		/**
		 *使用说明
		 * format(new Date(), "yyyy-MM-dd hh:mm:ss")
		 **/
		format: function(date, fmt) {
			var first;
			var o = {
				"M+": date.getMonth() + 1, //月
				"d+": date.getDate(),//日
				"h+": date.getHours(),//小时
				"m+": date.getMinutes(),//分钟
				"s+": date.getSeconds(),//秒
				"q+": Math.floor((date.getMonth() + 3)/3),//季度
				"S": date.getMilliseconds()//毫秒数
			};
			
			//年可以不区分大小写
			if (/(y+)/ig.test(fmt)) {
				first = RegExp.$1;
				//以年份替换格式符
				fmt = fmt.replace(first, (date.getFullYear() + "").substr(4 - first.length));
				
				for (var key in o) {
					if (new RegExp("(" + key + ")").test(fmt))
					{
						first = RegExp.$1;
						fmt = fmt.replace(first, ("0" + o[key]).substr(("" + o[key]).length - 1));
					}
				}
			}
			
			return fmt;
		}
	}
});