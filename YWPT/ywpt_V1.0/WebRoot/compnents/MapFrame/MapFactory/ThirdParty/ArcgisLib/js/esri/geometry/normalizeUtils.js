/*
 COPYRIGHT 2009 ESRI

 TRADE SECRETS: ESRI PROPRIETARY AND CONFIDENTIAL
 Unpublished material - all rights reserved under the
 Copyright Laws of the United States and applicable international
 laws, treaties, and conventions.

 For additional information, contact:
 Environmental Systems Research Institute, Inc.
 Attn: Contracts and Legal Services Department
 380 New York Street
 Redlands, California, 92373
 USA

 email: contracts@esri.com
 */
//>>built
define("esri/geometry/normalizeUtils",["dojo/_base/array","dojo/_base/lang","dojo/_base/Deferred","dojo/has","esri/kernel","esri/config","esri/deferredUtils","esri/geometry/Polyline","esri/geometry/Polygon","esri/geometry/webMercatorUtils","esri/geometry/jsonUtils"],function(_1,_2,_3,_4,_5,_6,_7,_8,_9,_a,_b){function _c(_d,_e){return Math.ceil((_d-_e)/(_e*2));};function _f(_10,_11){var _12=_10.paths||_10.rings,i,j,il=_12.length,jl;for(i=0;i<il;i++){var _13=_12[i];jl=_13.length;for(j=0;j<jl;j++){var _14=_10.getPoint(i,j);_10.setPoint(i,j,_14.offset(_11,0));}}return _10;};function _15(_16,_17){if(!(_16 instanceof _8||_16 instanceof _9)){var msg="_straightLineDensify: the input geometry is neither polyline nor polygon";console.error(msg);throw new Error(msg);}var _18=_16 instanceof _8,_19=_18?_16.paths:_16.rings,_1a=[],_1b;_1.forEach(_19,function(_1c){_1a.push(_1b=[]);_1b.push([_1c[0][0],_1c[0][1]]);var x1,y1,x2,y2;var i,j,_1d,_1e,_1f,_20,xj,yj;for(i=0;i<_1c.length-1;i++){x1=_1c[i][0];y1=_1c[i][1];x2=_1c[i+1][0];y2=_1c[i+1][1];_1d=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));_1e=(y2-y1)/_1d;_1f=(x2-x1)/_1d;_20=_1d/_17;if(_20>1){for(j=1;j<=_20-1;j++){var _21=j*_17;xj=_1f*_21+x1;yj=_1e*_21+y1;_1b.push([xj,yj]);}var _22=(_1d+Math.floor(_20-1)*_17)/2;xj=_1f*_22+x1;yj=_1e*_22+y1;_1b.push([xj,yj]);}_1b.push([x2,y2]);}});if(_18){return new _8({paths:_1a,spatialReference:_16.spatialReference});}else{return new _9({rings:_1a,spatialReference:_16.spatialReference});}};function _23(_24,_25,_26){var _27=1000000;if(_25){var _28=_15(_24,_27);_24=_a.webMercatorToGeographic(_28,true);}if(_26){_24=_f(_24,_26);}return _24;};function _29(_2a,_2b,_2c){var _2d=_2a.x||_2a[0];var _2e;if(_2d>_2b){_2e=_c(_2d,_2b);if(_2a.x){_2a=_2a.offset(_2e*(-2*_2b),0);}else{_2a[0]=_2d+(_2e*(-2*_2b));}}else{if(_2d<_2c){_2e=_c(_2d,_2c);if(_2a.x){_2a=_2a.offset(_2e*(-2*_2c),0);}else{_2a[0]=_2d+(_2e*(-2*_2c));}}}return _2a;};function _2f(_30,_31){var _32=-1;_1.forEach(_31.cutIndexes,function(_33,i){var _34=_31.geometries[i];var _35=_34.rings||_34.paths;_1.forEach(_35,function(_36,_37){_1.some(_36,function(_38){if(_38[0]<180){return true;}else{var _39=0,j,jl=_36.length,ptX;for(j=0;j<jl;j++){ptX=_36[j][0];_39=ptX>_39?ptX:_39;}var _3a=_c(_39,180),_3b=_3a*-360,_3c,_3d=_36.length;for(_3c=0;_3c<_3d;_3c++){var _3e=_34.getPoint(_37,_3c);_34.setPoint(_37,_3c,_3e.offset(_3b,0));}return true;}});});if(_33===_32){if(_34.rings){_1.forEach(_34.rings,function(_3f,j){_30[_33]=_30[_33].addRing(_3f);});}else{_1.forEach(_34.paths,function(_40,j){_30[_33]=_30[_33].addPath(_40);});}}else{_32=_33;_30[_33]=_34;}});return _30;};function _41(_42,_43,_44,_45){var dfd=new _3();dfd.addCallbacks(_44,_45);var _46=[],_47=[],_48,_49,_4a,_4b,_4c,_4d,_4e,_4f,_50=0;_1.forEach(_42,function(_51){if(!_51){_46.push(_51);return;}if(!_48){_48=_51.spatialReference;_49=_48._getInfo();_4a=_48._isWebMercator();_4b=_4a?20037508.342788905:180;_4c=_4a?-20037508.342788905:-180;_4d=_4a?102100:4326;_4e=new _8({"paths":[[[_4b,_4c],[_4b,_4b]]],"spatialReference":{"wkid":_4d}});_4f=new _8({"paths":[[[_4c,_4c],[_4c,_4b]]],"spatialReference":{"wkid":_4d}});}if(!_49){_46.push(_51);return;}var _52=_b.fromJson(_51.toJson()),_53=_51.getExtent();if(_51.type==="point"){_46.push(_29(_52,_4b,_4c));}else{if(_51.type==="multipoint"){_52.points=_1.map(_52.points,function(_54){return _29(_54,_4b,_4c);});_46.push(_52);}else{if(_51.type==="extent"){var _55=_53._normalize(null,null,_49);_46.push(_55.rings?new _9(_55):_55);}else{var _56=_c(_53.xmin,_4c),_57=_56*(2*_4b);_52=(_57===0)?_52:_f(_52,_57);_53=_53.offset(_57,0);if(_53.intersects(_4e)&&(_53.xmax!==_4b)){_50=(_53.xmax>_50)?_53.xmax:_50;_52=_23(_52,_4a);_47.push(_52);_46.push("cut");}else{if(_53.intersects(_4f)&&(_53.xmin!==_4c)){_50=(_53.xmax*(2*_4b)>_50)?_53.xmax*(2*_4b):_50;_52=_23(_52,_4a,360);_47.push(_52);_46.push("cut");}else{_46.push(_52);}}}}}});var _58=new _8(),_59=_c(_50,_4b),_5a=-90,_5b=_59;while(_59>0){var _5c=-180+(360*_59);_58.addPath([[_5c,_5a],[_5c,_5a*-1]]);_5a=_5a*-1;_59--;}if(_47.length>0&&_5b>0){if(_43){_43.cut(_47,_58,function(_5d){_47=_2f(_47,_5d);var _5e=[];_1.forEach(_46,function(_5f,i){if(_5f==="cut"){var _60=_47.shift();if((_42[i].rings)&&(_42[i].rings.length>1)&&(_60.rings.length>=_42[i].rings.length)){_46[i]="simplify";_5e.push(_60);}else{_46[i]=(_4a===true)?_a.geographicToWebMercator(_60):_60;}}});if(_5e.length>0){_43.simplify(_5e,function(_61){_1.forEach(_46,function(_62,i){if(_62==="simplify"){_46[i]=(_4a===true)?_a.geographicToWebMercator(_61.shift()):_61.shift();}});dfd.callback(_46);},function(_63){dfd.errback(_63);});}else{dfd.callback(_46);}},function(_64){dfd.errback(_64);});}else{dfd.errback(new Error("esri.geometry.normalizeCentralMeridian: 'geometryService' argument is missing."));}}else{_1.forEach(_46,function(_65,i){if(_65==="cut"){var _66=_47.shift();_46[i]=(_4a===true)?_a.geographicToWebMercator(_66):_66;}});dfd.callback(_46);}return dfd;};function _67(_68,_69,_6a,_6b){var _6c=false,_6d;if(_2.isObject(_68)&&_68){if(_2.isArray(_68)){if(_68.length){_6d=_68[0]&&_68[0].declaredClass;if(_6d&&_6d.indexOf("Graphic")!==-1){_68=_1.map(_68,function(_6e){return _6e.geometry;});_6c=_68.length?true:false;}else{if(_6d&&_6d.indexOf("esri.geometry.")!==-1){_6c=true;}}}}else{_6d=_68.declaredClass;if(_6d&&_6d.indexOf("FeatureSet")!==-1){_68=_1.map(_68.features||[],function(_6f){return _6f.geometry;});_6c=_68.length?true:false;}else{if(_6d&&_6d.indexOf("esri.geometry.")!==-1){_6c=true;}}}}if(_6c){_69.push({index:_6a,property:_6b,value:_68});}};function _70(_71,_72){var _73=[];_1.forEach(_72,function(_74){var _75=_74.i,arg=_71[_75],_76=_74.p,_77;if(!_2.isObject(arg)||!arg){return;}if(_76){if(_76[0]==="*"){for(_77 in arg){if(arg.hasOwnProperty(_77)){_67(arg[_77],_73,_75,_77);}}}else{_1.forEach(_76,function(_78){_67(_2.getObject(_78,false,arg),_73,_75,_78);});}}else{_67(arg,_73,_75);}});return _73;};function _79(_7a,_7b){var idx=0,_7c={};_1.forEach(_7b,function(_7d){var _7e=_7d.index,_7f=_7d.property,_80=_7d.value,len=_80.length||1;var _81=_7a.slice(idx,idx+len);if(!_2.isArray(_80)){_81=_81[0];}idx+=len;delete _7d.value;if(_7f){_7c[_7e]=_7c[_7e]||{};_7c[_7e][_7f]=_81;}else{_7c[_7e]=_81;}});return _7c;};function _82(_83){var _84=(_2.isObject(_83))?_83.prototype:_2.getObject(_83+".prototype");_1.forEach(_84.__msigns,function(sig){var _85=_84[sig.n];_84[sig.n]=function(){var _86=this,_87=[],i,_88=new _3(_7._dfdCanceller);if(sig.f){_7._fixDfd(_88);}for(i=0;i<sig.c;i++){_87[i]=arguments[i];}var _89={dfd:_88};_87.push(_89);var _8a,_8b=[],_8c;if(_86.normalization&&!_86._isTable){_8a=_70(_87,sig.a);_1.forEach(_8a,function(_8d){_8b=_8b.concat(_8d.value);});if(_8b.length){_8c=_41(_8b,_6.defaults.geometryService);}}if(_8c){_88._pendingDfd=_8c;_8c.addCallbacks(function(_8e){if(_88.canceled){return;}_89.assembly=_79(_8e,_8a);_88._pendingDfd=_85.apply(_86,_87);},function(err){var _8f=_86.declaredClass;if(_8f&&_8f.indexOf("FeatureLayer")!==-1){_86._resolve([err],null,_87[sig.e],_88,true);}else{_86._errorHandler(err,_87[sig.e],_88);}});}else{_88._pendingDfd=_85.apply(_86,_87);}return _88;};});};var _90={normalizeCentralMeridian:_41,_foldCutResults:_2f,_prepareGeometryForCut:_23,_offsetMagnitude:_c,_pointNormalization:_29,_updatePolyGeometry:_f,_straightLineDensify:_15,_createWrappers:_82,_disassemble:_70,_addToBucket:_67,_reassemble:_79};if(_4("extend-esri")){_2.mixin(_2.getObject("geometry",true,_5),_90);}return _90;});