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
require({cache:{"url:esri/dijit/analysis/templates/FindHotSpots.html":"<div class=\"esriAnalysis\">\r\n  <div data-dojo-type=\"dijit/layout/ContentPane\" style=\"margin-top:0.5em; margin-bottom: 0.5em;\">\r\n    <div data-dojo-attach-point=\"_hotspotsToolContentTitle\" class=\"analysisTitle\">\r\n         <table class=\"esriFormTable\" > \r\n            <tr>\r\n              <td><div class=\"findHotSpotsIcon\"></div></td>\r\n              <td>${i18n.findHotSpots}</td>\r\n              <td>\r\n                <div class=\"esriFloatTrailing\" style=\"padding:0;\">\r\n                  <a href=\"#\" class='esriFloatLeading helpIcon' esriHelpTopic=\"toolDescription\"></a>\r\n                  <a href=\"#\" data-dojo-attach-point=\"_closeBtn\" title=\"${i18n.close}\" class=\"closeIcon\">              \r\n                  <img data-dojo-attach-point=\"_closeImg\" border=\"0\"/></a>            \r\n                </div>\r\n              </td>\r\n            </tr>\r\n         </table>\r\n    </div>\r\n    <div style=\"clear:both; border-bottom: #333 thin solid; height:1px;width:100%;\"></div>\r\n  </div>\r\n  <div data-dojo-type=\"dijit/form/Form\" data-dojo-attach-point=\"_form\" readOnly=\"true\">\r\n     <table class=\"esriFormTable\"  data-dojo-attach-point=\"_hotspotsTable\"> \r\n       <tbody>\r\n        <tr>\r\n          <td  colspan=\"3\" class=\"sectionHeader\" data-dojo-attach-point=\"_hotspotsToolDescription\" ></td>\r\n        </tr>\r\n        <!--<tr data-dojo-attach-point=\"_pointFieldTd\">\r\n           <td colspan=\"3\">\r\n             <label class=\"esriFloatLeading esriTrailingMargin025\">${i18n.oneLabel}</label>\r\n             <label class=\"\">${i18n.chooseAttributeLabel}</label>\r\n           </td>\r\n        </tr>-->\r\n        <tr>\r\n          <td colspan=\"2\">\r\n            <label data-dojo-attach-point=\"_labelOne\" class=\"esriFloatLeading esriTrailingMargin025\">${i18n.oneLabel}</label>\r\n            <label data-dojo-attach-point=\"_polylabel\" class=\"\">${i18n.chooseAttributeLabel}</label>\r\n            <select class=\"longTextInput\"  style=\"margin-top:10px;\" data-dojo-type=\"dijit/form/Select\" data-dojo-attach-point=\"_analysFieldSelect\" data-dojo-attach-event=\"onChange:_handleFieldChange\"></select>\r\n          </td>\r\n          <td class=\"shortTextInput\">\r\n            <a href=\"#\" class='esriFloatTrailing helpIcon' data-dojo-attach-point=\"_analysisFieldHelpLink\" esriHelpTopic=\"AnalysisFieldPoly\"></a> \r\n          </td> \r\n        </tr>\r\n        <tr data-dojo-attach-point=\"_optionsRow\">\r\n          <td colspan=\"3\">\r\n            <div class=\"optionsClose\" style=\"width:99%\" data-dojo-attach-point=\"_optionsDiv\">\r\n              <div class=\"dijitTreeExpando\" data-dojo-attach-event=\"onclick:_handleOptionsBtnClick\"><label class=\"esriLeadingMargin2\">${i18n.Options}</label></div>\r\n              <table class=\"esriFormTable optionsTable\">\r\n                <tbody>\r\n                  <tr>\r\n                    <td colspan=\"2\">\r\n                        <label class=\"longTextInput\">${i18n.defineBoundingLabel}</label>\r\n                    </td>                    \r\n                  </tr>\r\n                  <tr>\r\n                    <td style=\"width:95%\">\r\n                      <select class=\"longTextInput esriMediumlabel\"  style=\"width:100%;table-layout:fixed;\" data-dojo-type=\"dijit/form/Select\" data-dojo-attach-point=\"_boundingAreaSelect\" data-dojo-attach-event=\"onChange:_handleBoundingSelectChange\"></select>                      \r\n                    </td>\r\n                    <td style=\"width:8%\">\r\n                      <button data-dojo-type=\"dijit/form/ToggleButton\" class=\"esriboundingButton\" data-dojo-props=\"showLabel:false,iconClass:'toolbarIcon polygonIcon',style:'width:16px;'\" data-dojo-attach-event=\"onClick:_handleBoundingBtnClick\"></button>\r\n                    </td> \r\n                    <td class=\"shortTextInput\">\r\n                       <a href=\"#\" class='esriFloatTrailing helpIcon' esriHelpTopic=\"BoundingPolygonLayer\"></a> \r\n                    </td> \r\n                   \r\n                  </tr>      \r\n                  <tr>\r\n                    <td colspan=\"2\">\r\n                        <label class=\"longTextInput\">${i18n.provideAggLabel}</label>\r\n                    </td>                    \r\n                  </tr>\r\n                  <tr>\r\n                    <td colspan=\"2\">\r\n                        <select class=\"longTextInput\"  data-dojo-type=\"dijit/form/Select\" data-dojo-attach-point=\"_aggAreaSelect\" data-dojo-attach-event=\"onChange:_handleAggAreaSelectChange\"></select>\r\n                    </td>   \r\n                    <td class=\"shortTextInput\">\r\n                      <a href=\"#\" class='esriFloatTrailing helpIcon' esriHelpTopic=\"AggregationPolygonLayer\"></a> \r\n                    </td> \r\n                 \r\n                  </tr>                               \r\n                </tbody>\r\n              </table>\r\n            </div>\r\n          </td>\r\n        </tr>\r\n        <tr>\r\n          <td colspan=\"2\">\r\n            <label class=\"esriFloatLeading esriTrailingMargin025\">${i18n.twoLabel}</label>\r\n            <label class=\"longTextInput\">${i18n.outputLayerLabel}</label>\r\n          </td>\r\n          <td class=\"shortTextInput\">\r\n            <a href=\"#\" class='esriFloatTrailing helpIcon' esriHelpTopic=\"OutputLayerName\"></a> \r\n          </td>             \r\n        </tr>\r\n        <tr>\r\n          <td colspan=\"3\">\r\n            <input type=\"text\" data-dojo-type=\"dijit/form/ValidationTextBox\" data-dojo-props=\"required:true\" class=\"longTextInput esriLeadingMargin05\" data-dojo-attach-point=\"_outputLayerInput\" value=\"${i18n.hotspots}\"></input>\r\n          </td>                \r\n        </tr>\r\n        <tr>\r\n          <td colspan=\"3\">\r\n             <div data-dojo-attach-point=\"_chooseFolderRow\">\r\n               <label style=\"width:9px;font-size:smaller;\">${i18n.saveResultIn}</label>\r\n               <input class=\"longInput\" dojoAttachPoint=\"_webMapFolderSelect\" dojotype=\"dijit/form/ComboBox\" trim=\"true\" style=\"width:60%;height:auto\"></input>\r\n             </div>              \r\n          </td>\r\n        </tr>                                              \r\n      </tbody>         \r\n     </table>\r\n   </div>\r\n  <div id=\"hotspotsToolContentButtons\" style=\"padding:5px;margin-top:5px;border-top:solid 1px #BBB;\">\r\n    <div data-dojo-attach-point=\"_chooseExtentDiv\" style=\"width:100%;padding:0.5em 0 0.5em 0\">\r\n     <label class=\"esriSelectLabel\" style=\"font-size:smaller;\">\r\n       <input type=\"radio\" data-dojo-attach-point=\"_useExtentCheck\" data-dojo-type=\"dijit/form/CheckBox\" data-dojo-props=\"checked:true\" name=\"extent\" value=\"true\"/>\r\n         ${i18n.useMapExtent}\r\n     </label>    \r\n    </div>      \r\n    <button data-dojo-type=\"dijit/form/Button\" type=\"submit\" data-dojo-attach-point=\"_saveBtn\" class=\"esriLeadingMargin4\" data-dojo-attach-event=\"onClick:_handleSaveBtnClick\">\r\n        ${i18n.runAnalysis}\r\n    </button>\r\n  </div>\r\n</div>\r\n"}});define("esri/dijit/analysis/FindHotSpots",["require","dojo/_base/declare","dojo/_base/lang","dojo/_base/array","dojo/_base/connect","dojo/_base/Color","dojo/_base/json","dojo/has","dojo/i18n","dojo/json","dojo/string","dojo/dom-style","dojo/dom-attr","dojo/dom-construct","dojo/query","dojo/dom-class","dijit/_WidgetBase","dijit/_TemplatedMixin","dijit/_WidgetsInTemplateMixin","dijit/_OnDijitClickMixin","dijit/_FocusMixin","dijit/registry","dijit/form/Button","dijit/form/CheckBox","dijit/form/Form","dijit/form/Select","dijit/form/TextBox","dijit/form/ToggleButton","dijit/form/ValidationTextBox","dijit/layout/ContentPane","dijit/form/ComboBox","esri/kernel","esri/dijit/analysis/AnalysisBase","esri/toolbars/draw","esri/dijit/PopupTemplate","esri/layers/FeatureLayer","esri/map","esri/dijit/analysis/utils","dojo/text!esri/dijit/analysis/templates/FindHotSpots.html"],function(_1,_2,_3,_4,_5,_6,_7,_8,_9,_a,_b,_c,_d,_e,_f,_10,_11,_12,_13,_14,_15,_16,_17,_18,_19,_1a,_1b,_1c,_1d,_1e,_1f,_20,_21,_22,_23,_24,Map,_25,_26){var _27=_2([_11,_12,_13,_14,_15,_21],{declaredClass:"esri.dijit.analysis.FindHotSpots",templateString:_26,basePath:_1.toUrl("esri/dijit/analysis"),widgetsInTemplate:true,analysisLayer:null,analysisField:null,aggregationPolygonLayer:null,boundingPolygonLayer:null,outputLayerName:null,showSelectFolder:false,showChooseExtent:true,i18n:null,map:null,toolName:"FindHotSpots",helpFileName:"FindHotSpots",resultParameter:"HotSpotsResultLayer",constructor:function(_28,_29){this.inherited(arguments);this._pbConnects=[];if(_28.containerNode){this.container=_28.containerNode;}},destroy:function(){this.inherited(arguments);_4.forEach(this._pbConnects,_5.disconnect);delete this._pbConnects;},postMixInProperties:function(){this.inherited(arguments);_3.mixin(this.i18n,_9.getLocalization("esri","jsapi").findHotSpotsTool);this.set("drawLayerName",this.i18n.blayerName);},postCreate:function(){this.inherited(arguments);_10.add(this._form.domNode,"esriSimpleForm");_d.set(this._closeImg,"src",this.basePath+"/images/close.gif");this._outputLayerInput.set("validator",_3.hitch(this,this.validateServiceName));this._buildUI();},startup:function(){},_onClose:function(_2a){if(_2a){if(this._featureLayer){this.map.removeLayer(this._featureLayer);_4.forEach(this.boundingPolygonLayers,function(lyr,_2b){if(lyr==this._featureLayer){this._boundingAreaSelect.removeOption({value:_2b+1,label:this._featureLayer.name});this.boundingPolygonLayers.splice(_2b,1);return;}},this);}}this._toolbar.deactivate();this.onClose();},_handleSaveBtnClick:function(e){if(!this._form.validate()){return;}this._saveBtn.set("disabled",true);var _2c={},_2d={};_2c.AnalysisLayer=_7.toJson(_25.constructAnalysisInputLyrObj(this.analysisLayer));if(this._analysFieldSelect.get("value")!=="0"){_2c.AnalysisField=this._analysFieldSelect.get("value");}if(this._isPoint&&this._analysFieldSelect.get("value")==="0"){if(this._boundingAreaSelect.get("value")!=="-1"){var _2e=this.boundingPolygonLayers[this._boundingAreaSelect.get("value")-1];_2c.BoundingPolygonLayer=_7.toJson(_25.constructAnalysisInputLyrObj(_2e));}if(this._aggAreaSelect.get("value")!=="-1"){var _2f=this.aggregationPolygonLayers[this._aggAreaSelect.get("value")-1];_2c.AggregationPolygonLayer=_7.toJson(_25.constructAnalysisInputLyrObj(_2f));}}_2c.OutputName=_7.toJson({serviceProperties:{name:this._outputLayerInput.get("value")}});if(this.showChooseExtent){if(this._useExtentCheck.get("checked")){_2c.context=_7.toJson({extent:this.map.extent});}}_2d.jobParams=_2c;_2d.itemParams={"description":this.i18n.itemDescription,"tags":_b.substitute(this.i18n.itemTags,{layername:this.analysisLayer.name,fieldname:(!_2c.AnalysisField)?"":_2c.AnalysisField}),"snippet":this.i18n.itemSnippet};if(this.showSelectFolder){_2d.itemParams.folder=this._webMapFolderSelect.item?this.folderStore.getValue(this._webMapFolderSelect.item,"id"):"";}this.execute(_2d);},_save:function(){},_buildUI:function(){this._loadConnections();_25.initHelpLinks(this.domNode);if(this.analysisLayer){if(this.analysisLayer.geometryType==="esriGeometryPolygon"){this._isPoint=false;_d.set(this._hotspotsToolDescription,"innerHTML",_b.substitute(this.i18n.hotspotsPolyDefine,{layername:this.analysisLayer.name}));_c.set(this._optionsRow,"display","none");_d.set(this._analysisFieldHelpLink,"esriHelpTopic","AnalysisFieldPoly");}else{if(this.analysisLayer.geometryType==="esriGeometryPoint"||this.analysisLayer.geometryType==="esriGeometryMultipoint"){this._isPoint=true;_d.set(this._hotspotsToolDescription,"innerHTML",_b.substitute(this.i18n.hotspotsPointDefine,{layername:this.analysisLayer.name}));_10.add(this._analysFieldSelect.domNode,"esriLeadingMargin1");_c.set(this._optionsRow,"display","");_d.set(this._analysisFieldHelpLink,"esriHelpTopic","AnalysisFieldPoint");this._outputLayerInput.set("value",_b.substitute(this.i18n.outputLayerName,{layername:this.analysisLayer.name}));}}this.set("AnalyisFields",this.analysisLayer);}if(this.outputLayerName){this._outputLayerInput.set("value",this.outputLayerName);}if(this.boundingPolygonLayers){this._boundingAreaSelect.addOption({value:"-1",label:this.i18n.defaultBoundingOption,selected:true});_4.forEach(this.boundingPolygonLayers,function(_30,_31){if(_30.geometryType==="esriGeometryPolygon"){this._boundingAreaSelect.addOption({value:_31+1,label:_30.name,selected:false});}},this);}if(this.aggregationPolygonLayers){this._aggAreaSelect.addOption({value:"-1",label:this.i18n.defaultAggregationOption,selected:true});_4.forEach(this.aggregationPolygonLayers,function(_32,_33){if(_32.geometryType==="esriGeometryPolygon"){this._aggAreaSelect.addOption({value:_33+1,label:_32.name,selected:false});}},this);}_c.set(this._chooseFolderRow,"display",this.showSelectFolder===true?"block":"none");if(this.showSelectFolder){this.getFolderStore().then(_3.hitch(this,function(_34){this.folderStore=_34;this._webMapFolderSelect.set("store",_34);this._webMapFolderSelect.set("value",this.portalUser.username);}));}_c.set(this._chooseExtentDiv,"display",this.showChooseExtent===true?"block":"none");},_handleFieldChange:function(_35){if(this._analysFieldSelect.get("value")==="0"){this._outputLayerInput.set("value",_b.substitute(this.i18n.outputLayerName,{layername:this.analysisLayer.name}));if(this._isPoint){_10.remove(this._optionsDiv,"disabled");}}else{this._outputLayerInput.set("value",_b.substitute(this.i18n.outputLayerName,{layername:this._analysFieldSelect.getOptions(_35).label}));if(this._isPoint){_10.add(this._optionsDiv,"disabled");if(_10.contains(this._optionsDiv,"optionsOpen")){_10.remove(this._optionsDiv,"optionsOpen");_10.add(this._optionsDiv,"optionsClose");}}}},_handleOptionsBtnClick:function(){if(_10.contains(this._optionsDiv,"disabled")){return;}else{if(_10.contains(this._optionsDiv,"optionsClose")){_10.remove(this._optionsDiv,"optionsClose");_10.add(this._optionsDiv,"optionsOpen");}else{if(_10.contains(this._optionsDiv,"optionsOpen")){_10.remove(this._optionsDiv,"optionsOpen");_10.add(this._optionsDiv,"optionsClose");}}}},_handleBoundingSelectChange:function(_36){this._aggAreaSelect.set("disabled",this._boundingAreaSelect.get("value")!=="-1");},_handleAggAreaSelectChange:function(_37){this._boundingAreaSelect.set("disabled",this._aggAreaSelect.get("value")!=="-1");},_handleBoundingBtnClick:function(e){e.preventDefault();this.onDrawToolActivate();if(!this._featureLayer){this._createBoundingPolyFeatColl();}this._toolbar.activate(_22.POLYGON);},_loadConnections:function(){this._connect(this,"onExecute",_3.hitch(this,"_onClose",false));this._connect(this._closeBtn,"onclick",_3.hitch(this,"_onClose",true));},_createBoundingPolyFeatColl:function(){var _38={"layerDefinition":null,"featureSet":{"features":[],"geometryType":"esriGeometryPolygon"}};_38.layerDefinition={"currentVersion":10.11,"copyrightText":"","defaultVisibility":true,"relationships":[],"isDataVersioned":false,"supportsRollbackOnFailureParameter":true,"supportsStatistics":true,"supportsAdvancedQueries":true,"geometryType":"esriGeometryPolygon","minScale":0,"maxScale":0,"objectIdField":"OBJECTID","templates":[],"type":"Feature Layer","displayField":"TITLE","visibilityField":"VISIBLE","name":this.drawLayerName,"hasAttachments":false,"typeIdField":"TYPEID","capabilities":"Query","allowGeometryUpdates":true,"htmlPopupType":"","hasM":false,"hasZ":false,"globalIdField":"","typeIdField":"","supportedQueryFormats":"JSON","hasStaticData":false,"maxRecordCount":-1,"indexes":[],"types":[],"drawingInfo":{"renderer":{"type":"simple","symbol":{"color":[0,0,0,255],"outline":{"color":[0,0,0,255],"width":3,"type":"esriSLS","style":"esriSLSSolid"},"type":"esriSFS","style":"esriSFSNull"},"label":"","description":""},"transparency":0,"labelingInfo":null,"fixedSymbols":true},"fields":[{"alias":"OBJECTID","name":"OBJECTID","type":"esriFieldTypeOID","editable":false},{"alias":"Title","name":"TITLE","length":50,"type":"esriFieldTypeString","editable":true},{"alias":"Visible","name":"VISIBLE","type":"esriFieldTypeInteger","editable":true},{"alias":"Description","name":"DESCRIPTION","length":1073741822,"type":"esriFieldTypeString","editable":true},{"alias":"Type ID","name":"TYPEID","type":"esriFieldTypeInteger","editable":true}]};var _39=new _23({title:"{title}",description:"{description}"});this._featureLayer=new _24(_38,{id:this.drawLayerName});this.map.addLayer(this._featureLayer);_5.connect(this._featureLayer,"onClick",_3.hitch(this,function(evt){this.map.infoWindow.setFeatures([evt.graphic]);}));},_addFeatures:function(_3a){this.onDrawToolDeactivate();this._toolbar.deactivate();var _3b=[];var _3c={};var _3d=new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_NULL,new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new _6([0,0,0]),4));var _3e=new esri.Graphic(_3a,_3d);this.map.graphics.add(_3e);_3c["description"]="blayer desc";_3c["title"]="blayer";_3e.setAttributes(_3c);_3b.push(_3e);this._featureLayer.applyEdits(_3b,null,null);if(this.boundingPolygonLayers.length===0||this.boundingPolygonLayers[this.boundingPolygonLayers.length-1]!==this._featureLayer){var _3f=this.boundingPolygonLayers.push(this._featureLayer);var _40=this._boundingAreaSelect.getOptions();this._boundingAreaSelect.removeOption(_40);_40=_4.map(_40,function(_41){_41.selected=false;return _41;});this._boundingAreaSelect.addOption({value:_3f,label:this._featureLayer.name,selected:true});this._boundingAreaSelect.addOption(_40);}},_setAnalysisGpServerAttr:function(url){this.analysisGpServer=url;this.set("toolServiceUrl",this.analysisGpServer+"/"+this.toolName);},_setAnalysisLayerAttr:function(_42){this.analysisLayer=_42;},_setAnalyisFieldsAttr:function(_43){var _44=_43.fields;if(this._isPoint){this._analysFieldSelect.addOption({value:"0",label:this.i18n.noAnalysisField});}_4.forEach(_44,function(_45,_46){if(_4.indexOf(["esriFieldTypeSmallInteger","esriFieldTypeInteger","esriFieldTypeSingle","esriFieldTypeDouble"],_45.type)!==-1){this._analysFieldSelect.addOption({value:_45.name,label:_45.name});}},this);},_setMapAttr:function(map){this.map=map;this._toolbar=new _22(this.map);_5.connect(this._toolbar,"onDrawEnd",_3.hitch(this,this._addFeatures));},_getMapAttr:function(){return this.map;},_setDrawLayerNameAttr:function(_47){this.drawLayerName=_47;},_getDrawLayerNameAttr:function(){return this._featureLayer.name;},_getDrawLayerAttr:function(){return this._featureLayer;},_getDrawToolbarAttr:function(){return this._toolbar;},_setDisableRunAnalysisAttr:function(_48){this._saveBtn.set("disabled",_48);},validateServiceName:function(_49){var _4a=/(:|&|<|>|%|#|\?|\\|\"|\/|\+)/.test(_49);if(_49.length===0||(_49.trim().length===0)){this._outputLayerInput.set("invalidMessage",this.i18n.requiredValue);return false;}if(_4a){this._outputLayerInput.set("invalidMessage",this.i18n.invalidServiceName);return false;}if(_49.length>128){this._outputLayerInput.set("invalidMessage",this.i18n.invalidServiceNameLength);return false;}return true;},_setShowSelectFolderAttr:function(_4b){this.showSelectFolder=_4b;},_getShowSelectFolderAttr:function(){return this.showSelectFolder;},_setShowChooseExtentAttr:function(_4c){this.showChooseExtent=_4c;},_getShowChooseExtentAttr:function(){return this.showChooseExtent;},_connect:function(_4d,evt,_4e){this._pbConnects.push(_5.connect(_4d,evt,_4e));},onDrawToolActivate:function(){},onDrawToolDeactivate:function(){},onSave:function(){},onClose:function(){}});if(_8("extend-esri")){_3.setObject("dijit.analysis.FindHotSpots",_27,_20);}return _27;});