MapFactory.Define("MapFactory/Query",[
	"MapFactory/QueryAPI*",
	"MapFactory/Message*"
],function(api,messageClass){
	return function(url){

		var _url = "",
			_geometry,
			_geometryPrecision = 1,
			_relationship = "intersect",
			_where;

		function setUrl(url){
			_url = url;
		}

		function setGeometry(geometry){
			_geometry = geometry;
		}

		function setGeometryPrecision(precision){
			_geometryPrecision = precision ? precision : _geometryPrecision;
		}

		function setSpatialRelationShip(relationship){
			_relationship = relationship ? relationship : _relationship;
		}

		function setCondition(obj){
			var _condition = "",
			_conReg = /[=|>|<]/g,
			_conReg2 = /\s+IN\b/g,
			_numReg = /\b\d+\b/g,
			_conReg3 = /like/g,
			i = 0;
			for(var elem in obj){
				i++;
				var _match = elem.match(_conReg),
					_match2 = elem.match(_conReg2),
					_numMatch = obj[elem].match(_numReg),
					_match3 = elem.match(_conReg3),
					_quoteStr = "'";
				if(i > 1){
					_condition += " And ";
				}
				if(!_match){
					if(!_match2){
						if(_match3){
							_condition += elem.replace(_conReg3,"") + " like " + _quoteStr + "%" + obj[elem] + "%" + _quoteStr;
						}else{
							_condition += elem + " = " + _quoteStr + obj[elem] + _quoteStr;
						}
						
					}else{
						var _fields = elem.replace(_conReg2,""),
							_values = obj[elem].split(","),
							_valueFlag = 0;
						for(var _vIndex=0,_vLen=_values.length;_vIndex<_vLen;_vIndex++){
							_valueFlag++;
							if(_valueFlag > 1){
								_condition += " OR ";
							}
							_condition += _fields + " = " + _quoteStr + _values[_vIndex] + _quoteStr;
						}
					}
				}else{
					_condition += elem.replace(_conReg,"") + _match.toString() + obj[elem];
				}
			}
			_where = _condition;
		}

		function execute(successFunc,failureFunc){
			if(!_url){
				if(failureFunc){
					failureFunc(new messageClass({
						message : "服务地址未设置"
					}));
				}
				return;
			}
			var _data = {
				geometry : "",
				geometryPrecision : "",
				spatialRelationship : "",
				where : ""
			};
			if(_geometry){
				if(MapFactory.VariableTypes.isObject){
					_data["geometry"] = MapFactory.JSON.Stringify(_geometry);
				}else{
					_data["geometry"] = _geometry;
				}
				_data["geometryPrecision"] = _geometryPrecision+"";
				_data["spatialRelationship"] = _relationship;
			}
			if(_where){
				_data["where"] = _where;
			}
			if(!_where && !_geometry){
				_data["where"] = "1=1";
			}
			MapFactory.XHR.Post(_url,_data,function(_res){
				successFunc(_res);
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});