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
require({cache:{"url:esri/dijit/templates/RenderingRule.html":"<div data-dojo-attach-point=\"_renderingRuleContainer\" class=\"esriRenderingRuleContainer\">\r\n  <table class=\"esriRenderingRuleTable\">\r\n    <tbody>\r\n      <col class=\"esriRenderingRuleRenderer\"/>\r\n      <col class=\"esriRenderingRuleBandCombination\"/>\r\n      <tr>\r\n        <td>\r\n          <label data-dojo-attach-point=\"rendererLabel\">${_i18n.widgets.renderingRule.rendererLabelTitle}</label>\r\n        </td>\r\n        <td>\r\n          <div data-dojo-attach-point=\"rasterFunctionList\" data-dojo-type=\"dijit/form/FilteringSelect\" class=\"esriRenderingRuleRasterFunctionList\">\r\n          </div>\r\n        </td>\r\n      </tr>\r\n      <tr>\r\n        <td>\r\n          <label data-dojo-attach-point=\"bandIdsLabel\">${_i18n.widgets.renderingRule.bandCombinationLabelTitle}</label>\r\n        </td>\r\n        <td>\r\n          <div data-dojo-attach-point=\"bandIdsRedList\" data-dojo-type=\"dijit/form/FilteringSelect\" class=\"esriRenderingRuleBandIdsRedList\">\r\n          </div>\r\n          <div data-dojo-attach-point=\"bandIdsGreenList\" data-dojo-type=\"dijit/form/FilteringSelect\" class=\"esriRenderingRuleBandIdsGreenList\">\r\n          </div>\r\n          <div data-dojo-attach-point=\"bandIdsBlueList\" data-dojo-type=\"dijit/form/FilteringSelect\" class=\"esriRenderingRuleBandIdsBlueList\">\r\n          </div>         \r\n        </td>\r\n      </tr>\r\n      <tr>\r\n        <td/>\r\n        <td>\r\n          <div data-dojo-attach-point=\"msgLabel\" class=\"esriRenderingRuleMsgLabel\"></div>\r\n        </td>\r\n      </tr>\r\n    </tbody>\r\n  </table>\r\n</div>\r\n"}});define("esri/dijit/RenderingRule",["dojo/_base/declare","dojo/_base/lang","dojo/_base/connect","dojo/_base/array","dojo/i18n!esri/nls/jsapi","dojo/text!esri/dijit/templates/RenderingRule.html","dojo/store/Memory","dojo/has","esri/kernel","esri/layers/RasterFunction","esri/geometry/Extent","dijit/_WidgetBase","dijit/_TemplatedMixin","dijit/_WidgetsInTemplateMixin","dijit/form/FilteringSelect"],function(_1,_2,_3,_4,_5,_6,_7,_8,_9,_a,_b,_c,_d,_e){var _f=_1([_c,_d,_e],{declaredClass:"esri.dijit.RenderingRule",templateString:_6,widgetsInTemplate:true,layer:null,map:null,_renderingRuleObject:null,_rasterFunctionData:[],_rasterFunctionStore:null,_cachedFunctionList:[],_cachedkeyProperties:{},_pendingDfds:{},_renderingRuleChanged:false,_redBandIdStore:null,_greenBandIdStore:null,_blueBandIdStore:null,_donotSaveChanges:false,_resetBandCombination:false,_serviceBandCount:3,_defaultBandCombinationFncName:"User Defined RGB Renderer",_firstFncInRenderingRuleList:null,constructor:function(_10){_1.safeMixin(this,_10);this._i18n=_5;this._renderingRuleObject=new _a();},startup:function(){this.inherited(arguments);_3.connect(this.rasterFunctionList,"onChange",_2.hitch(this,"_onRasterFunctionChange"));_3.connect(this.bandIdsRedList,"onChange",_2.hitch(this,"_onBandIdsChange"));_3.connect(this.bandIdsGreenList,"onChange",_2.hitch(this,"_onBandIdsChange"));_3.connect(this.bandIdsBlueList,"onChange",_2.hitch(this,"_onBandIdsChange"));},destroy:function(){this._pendingDfds=null;this.inherited(arguments);},_setLayerAttr:function(_11){if(!_11){return;}this.inherited(arguments);var _12=false;if(this.layer===_11){_12=true;}this.layer=_11;this._donotSaveChanges=true;var _13=_2.hitch(this,"_setupDefaults");if(this.layer.loaded){this._setupDefaults();}else{_3.connect(this.layer,"onLoad",_13);}this._donotSaveChanges=false;},_setupDefaults:function(){this._setupBandIdDefaults();this._setupRenderingRuleDefaults();},_setupRenderingRuleDefaults:function(){if(!this.layer){return;}this._rasterFunctionData=[];var i;for(i=0;i<this._cachedFunctionList.length;i++){var _14=this._cachedFunctionList[i];if(_14&&this.layer===_14.layer){this._rasterFunctionData=_14.data;this._setupFunctionStore();return;}}this._fillRasterFunctionList(this.layer);},_setupFunctionStore:function(){if(!this.layer){console.log("Could not populate renderers as the layer does not exists");return;}this._rasterFunctionStore=new _7({data:this._rasterFunctionData,idProperty:"name"});this.rasterFunctionList.set("store",this._rasterFunctionStore);this.rasterFunctionList.set("labelAttr","label");this.rasterFunctionList.set("labelType","html");var _15="";if(this.layer.renderingRule&&this.layer.renderingRule.functionName){_15=this.layer.renderingRule.functionName;}else{if(this.layer.bandCount>2){_15=this._defaultBandCombinationFncName;}else{if(this._firstFncInRenderingRuleList){_15=this._firstFncInRenderingRuleList;}}}if(_15&&this._rasterFunctionStore.get(_15)){this.rasterFunctionList.set("value",_15);this._onRasterFunctionChange();}return;},_fillRasterFunctionList:function(_16){if(!this.layer){return;}this._rasterFunctionData=[];if(_16===null||_16.extent===null){return;}var _17=new _b(_16.extent.xmin,_16.extent.ymin,_16.extent.xmax,_16.extent.ymax,_16.extent.spatialReference);var _18=_17.getWidth();var _19=_17.getHeight();if((_18/_19>=2)||(_19/_18>=2)){var _1a=(Math.min(_18,_19))/2;var _1b=_17.getCenter();_17.update(_1b.x-_1a,_1b.y-_1a,_1b.x+_1a,_1b.y+_1a,_16.extent.spatialReference);}var _1c=_17.xmin+","+_17.ymin+","+_17.xmax+","+_17.ymax;var _1d=this.layer.url+"/exportImage?bbox="+_1c+"&imageSize=400,400&f=image&renderingRule=";if(this.layer.bandCount>=3){var _1e=this.layer.bandIds;var _1f=_1d;if(_1e&&_1e.length>=3){_1f=_1d+"&bandIds="+_1e[0]+","+_1e[1]+","+_1e[2];}this._addFunctionItemToList(this._defaultBandCombinationFncName,this._defaultBandCombinationFncName,"The default RGB renderer",_1f,"");}if(_16.rasterFunctionInfos&&_16.rasterFunctionInfos.length>0){_4.forEach(_16.rasterFunctionInfos,_2.hitch(this,function(_20){var _21="{\"rasterFunction\":\""+_20.name+"\"}";this._addFunctionItemToList(_20.name,_20.name,_20.description,_1d,_21);if(this._firstFncInRenderingRuleList===null){this._firstFncInRenderingRuleList=_20.name;}}));}var _22={};_22.layer=this.layer;_22.data=this._rasterFunctionData;this._cachedFunctionList.push(_22);this._setupFunctionStore();return;},_addFunctionItemToList:function(_23,id,_24,_25,_26){var _27={};_27.name=_23;_27.id=id;var _28=_24;if(_28.length>200){_28=_28.substring(0,200)+"...";}_27.description=_28;_27.label="<html><body><section><h4>"+_23+":</h4><table cellspacing='5'><tr><td><img src='"+_25+_26+"' height='100' width='100'></td><td><p style='white-space:pre-wrap;width:40ex'><i>"+_28+"</i></p></td></tr></table></section></body></html>";this._rasterFunctionData.push(_27);},_setupBandIdDefaults:function(){if(!this.layer){return;}var _29=3;_29=this.layer.bandCount;var _2a=this.layer.id;var _2b=this._cachedkeyProperties[_2a];if(!_2b&&_29>1){this.msgLabel.style.display="";this.msgLabel.innerHTML="<i>"+this._i18n.widgets.renderingRule.bandNamesRequestMsg+"</i>";var dfd=this.layer.getKeyProperties();this._pendingDfds[_2a]=1;dfd.addBoth(_2.partial(this._fillBandIdList,this,this.layer));}else{this._fillBandIdList(this,this.layer,_2b);}if(_29<3){this._hideBandIds();}else{this._showBandIds();}},_fillBandIdList:function(_2c,_2d,_2e){if(!_2c.layer||_2c.layer!==_2d){return;}var _2f=_2c._pendingDfds,_30=_2c.layer.id;if(_2f&&_2f[_30]){delete _2f[_30];}_2c.msgLabel.style.display="none";_2c.msgLabel.innerHTML="";var _31=3;_31=_2c.layer.bandCount;var _32;if(_2e&&_2e.BandProperties&&_2e.BandProperties.length>0){_32=_2e.BandProperties;}var _33=_2c._getBandIdList(_31,_32,"");_2c._redBandIdStore=new _7({data:_33,idProperty:"name"});_2c.bandIdsRedList.set("store",_2c._redBandIdStore);_2c.bandIdsRedList.set("labelAttr","label");_2c.bandIdsRedList.set("labelType","html");var _34=_2c._getBandIdList(_31,_32,"");_2c._greenBandIdStore=new _7({data:_34,idProperty:"name"});_2c.bandIdsGreenList.set("store",_2c._greenBandIdStore);_2c.bandIdsGreenList.set("labelAttr","label");_2c.bandIdsGreenList.set("labelType","html");var _35=_2c._getBandIdList(_31,_32,"");_2c._blueBandIdStore=new _7({data:_35,idProperty:"name"});_2c.bandIdsBlueList.set("store",_2c._blueBandIdStore);_2c.bandIdsBlueList.set("labelAttr","label");_2c.bandIdsBlueList.set("labelType","html");var _36=_2c.layer.bandIds;if(_36&&_36.length>2){_2c.bandIdsRedList.set("value",_2c._getBandName(_2c._redBandIdStore,_36[0]));_2c.bandIdsGreenList.set("value",_2c._getBandName(_2c._greenBandIdStore,_36[1]));_2c.bandIdsBlueList.set("value",_2c._getBandName(_2c._blueBandIdStore,_36[2]));}else{if(_2c._redBandIdStore.data.length>0&&_2c._greenBandIdStore.data.length>1&&_2c._blueBandIdStore.data.length>2){_2c.bandIdsRedList.set("value",_2c._redBandIdStore.data[0].name);_2c.bandIdsGreenList.set("value",_2c._greenBandIdStore.data[1].name);_2c.bandIdsBlueList.set("value",_2c._blueBandIdStore.data[2].name);}}_2c._cachedkeyProperties[_30]=_2e;var _37=_2c.rasterFunctionList.get("value");if(_37===_2c._defaultBandCombinationFncName){_2c._enableBandIds();}return;},_getBandIdList:function(_38,_39,_3a){if(!this.layer){return;}var _3b=[];if(!_3a){_3a="Black";}var _3c=false;if(_39&&_38===_39.length){_3c=true;}var i;for(i=0;i<_38;i++){var _3d=i;var _3e=i;if(_3c&&_39[i]&&_39[i].BandName){_3d=_39[i].BandName;}else{_3d++;}var _3f={};_3f.name=_3d;_3f.index=_3e;_3f.label="<html><body><span value="+_3e+"><font color="+_3a+">"+_3d+"</font></span></body></html>";_3b.push(_3f);}return _3b;},_getBandName:function(_40,_41){if(!_40||!_40.data){return;}var i;for(i=0;i<_40.data.length;i++){var _42=_40.data[i];if(_42.index===_41){return _42.name;}}return "";},_onRasterFunctionChange:function(){var _43=this.rasterFunctionList.get("value");if(_43){var _44=this._rasterFunctionStore.get(_43).description;this.rasterFunctionList.set("title",_44);var _45=this.layer.id;if(_43===this._defaultBandCombinationFncName&&!this._pendingDfds[_45]){this._enableBandIds();}else{this._disableBandIds();}}if(_43!==this._defaultBandCombinationFncName){this._onRasterFunctionApply();}else{this._onBandIdsApply();}this._renderingRuleChanged=true;},_onBandIdsChange:function(){var _46=this.rasterFunctionList.get("value");if(_46===this._defaultBandCombinationFncName){this._onBandIdsApply();this._renderingRuleChanged=true;}},_onRasterFunctionApply:function(){if(this._donotSaveChanges){return;}if(!this.layer){return;}var _47=this.rasterFunctionList.get("value");var _48=new _a();_48.functionName=_47;var _49=[];this.layer.setBandIds(_49,true);this.layer.setRenderingRule(_48);},_onBandIdsApply:function(){if(this._donotSaveChanges){return;}if(!this.layer){return;}if(!this._redBandIdStore||!this.bandIdsGreenList||!this.bandIdsBlueList){return;}var _4a=[];var _4b=this._redBandIdStore.get(this.bandIdsRedList.value);var _4c=this._greenBandIdStore.get(this.bandIdsGreenList.value);var _4d=this._blueBandIdStore.get(this.bandIdsBlueList.value);if(_4b&&_4c&&_4d){_4a.push(_4b.index);_4a.push(_4c.index);_4a.push(_4d.index);}var _4e=new _a();this.layer.setRenderingRule(_4e,true);this.layer.setBandIds(_4a);},_disableBandIds:function(){this.bandIdsRedList.set("disabled",true);this.bandIdsGreenList.set("disabled",true);this.bandIdsBlueList.set("disabled",true);this.bandIdsLabel.style.color="Gray";},_enableBandIds:function(){this.bandIdsRedList.set("disabled",false);this.bandIdsGreenList.set("disabled",false);this.bandIdsBlueList.set("disabled",false);if(this.bandIdsRedList.value===""){this.bandIdsRedList.set("value","1");}if(this.bandIdsGreenList.value===""){this.bandIdsGreenList.set("value","2");}if(this.bandIdsBlueList.value===""){this.bandIdsBlueList.set("value","3");}this.bandIdsLabel.style.color="Black";},_showBandIds:function(){this.bandIdsRedList.domNode.style.display="";this.bandIdsGreenList.domNode.style.display="";this.bandIdsBlueList.domNode.style.display="";this.bandIdsLabel.style.display="";},_hideBandIds:function(){this.bandIdsRedList.domNode.style.display="none";this.bandIdsGreenList.domNode.style.display="none";this.bandIdsBlueList.domNode.style.display="none";this.bandIdsLabel.style.display="none";},_getDefaultRedBandIndex:function(){var _4f;if(this._redBandIdStore){_4f=this._redBandIdStore.get("Red");}if(!_4f){_4f=1;}return _4f;}});if(_8("extend-esri")){_2.setObject("dijit.RenderingRule",_f,_9);}return _f;});