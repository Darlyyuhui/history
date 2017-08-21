/**
 * 日期转换类
 */
itmap.util.DateFromat = {
	"dateFormartFull":function(date, dateSplit, timeSplit){
		if(typeof dateSplit == "undefined")dateSplit = "-";
		if(typeof timeSplit == "undefined")timeSplit = ":";
		var d;
		if(typeof date == "object")d = date;
		else if(typeof date == "number")d = new Date(date);
		else d = new Date();
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
		else if(typeof date == "number")d = new Date(date);
		else d = new Date();
		return d.getFullYear()+dateSplit+
				(d.getMonth()<9?("0"+(d.getMonth()+1).toString()):(d.getMonth()+1))+dateSplit+
				(d.getDate()<10?("0"+d.getDate().toString()):d.getDate());
	}
}