MapFactory.Define("MapFactory/Message*",function(){
	return function(conf){
		var _conf = {
            code : "",
            message : "",
            level : ""
        };

        MapFactory.Extend(_conf,conf);

        this.code = _conf.code;

        this.message = _conf.message;

        this.level = _conf.level;

        this.toString = function(){
            return MapFactory.JSON.Stringify({
                code : this.code,
                message : this.message,
                level : this.level
            });
        }
	}
});