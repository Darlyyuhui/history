MapFactory.Define("MapFactory/Geometry*",function(){
	return function(conf){
		var _conf = {
			type : "",
			points : ""
		};

		MapFactory.Extend(_conf,conf);

		this.type = _conf.type;

		this.points = _conf.points;

		this.toString = function(){
			return MapFactory.JSON.Stringify({
				type : this.type,
				points : this.points
			});
		}
	}
});