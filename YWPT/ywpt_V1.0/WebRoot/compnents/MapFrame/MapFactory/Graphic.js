MapFactory.Define("MapFactory/Graphic*",function(){
	return function(geo,symbol,attributes,id){
		this.id = id;
		this.geo = geo;
		this.symbol = symbol;
		this.attributes = attributes;
	}
});