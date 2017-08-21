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
require({cache:{"url:esri/dijit/templates/AttributeInspector.html":"<div class=\"esriAttributeInspector\">\r\n    <div class=\"atiLayerName\" dojoAttachPoint=\"layerName\"></div>\r\n    <div class=\"atiAttributes\" dojoAttachPoint=\"attributeTable\"></div>\r\n    <div dojoAttachPoint=\"attachmentEditor\"></div>\r\n    <div class=\"atiEditorTrackingInfo\" dojoAttachPoint=\"editorTrackingInfoDiv\"></div>\r\n    <div class=\"atiButtons\" dojoAttachPoint=\"editButtons\">\r\n        <button  dojoType=\"dijit.form.Button\" class=\"atiButton atiDeleteButton\"  dojoAttachPoint=\"deleteBtn\" dojoAttachEvent=\"onClick: onDeleteBtn\" showLabel=\"true\" type=\"button\">${NLS_deleteFeature}</button>\r\n        <div class=\"atiNavButtons\" dojoAttachPoint=\"navButtons\">\r\n            <div class=\"atiNavMessage\" dojoAttachPoint=\"navMessage\"></div>\r\n            <button  dojoType=\"dijit.form.Button\" iconClass=\"atiButton atiFirstIcon\" dojoAttachPoint=\"firstFeatureButton\" dojoAttachEvent=\"onClick: onFirstFeature\" showLabel=\"false\" type=\"button\">${NLS_first}</button>\r\n            <button  dojoType=\"dijit.form.Button\" iconClass=\"atiButton atiPrevIcon\" dojoAttachPoint=\"prevFeatureButton\" dojoAttachEvent=\"onClick: onPreviousFeature\" showLabel=\"false\" type=\"button\">${NLS_previous}</button>\r\n            <button  dojoType=\"dijit.form.Button\" iconClass=\"atiButton atiNextIcon\" dojoAttachPoint=\"nextFeatureButton\" dojoAttachEvent=\"onClick: onNextFeature\" showLabel=\"false\" type=\"button\">${NLS_next}</button>\r\n            <button  dojoType=\"dijit.form.Button\" iconClass=\"atiButton atiLastIcon\" dojoAttachPoint=\"lastFeatureButton\" dojoAttachEvent=\"onClick: onLastFeature\" showLabel=\"false\" type=\"button\">${NLS_last}</button>\r\n        </div>\r\n    </div>\r\n</div>\r\n"}});define("esri/dijit/AttributeInspector",["dojo/_base/declare","dojo/_base/lang","dojo/_base/array","dojo/_base/connect","dojo/_base/sniff","dojo/_base/kernel","dojo/has","dojo/dom-style","dojo/dom-construct","esri/kernel","esri/lang","esri/domUtils","esri/layers/InheritedDomain","esri/layers/FeatureLayer","dojo/i18n!esri/nls/jsapi","dojo/fx","dojox/gfx","dijit/_Widget","dijit/_Templated","dijit/Editor","dijit/_editor/plugins/LinkDialog","dijit/_editor/plugins/TextColor","esri/dijit/editing/AttachmentEditor","esri/dijit/editing/Util","esri/tasks/query","dijit/form/DateTextBox","dijit/form/TextBox","dijit/form/NumberTextBox","dijit/form/FilteringSelect","dijit/form/NumberSpinner","dijit/form/Button","dijit/form/SimpleTextarea","dijit/Tooltip","dojo/data/ItemFileReadStore","dojox/date/islamic","dojox/date/islamic/Date","dojox/date/islamic/locale","dojo/text!esri/dijit/templates/AttributeInspector.html"],function(_1,_2,_3,_4,_5,_6,_7,_8,_9,_a,_b,_c,_d,_e,_f,fx,gfx,_10,_11,_12,_13,_14,_15,_16,_17,_18,_19,_1a,_1b,_1c,_1d,_1e,_1f,_20,_21,_22,_23,_24){var ATI=_1([_10,_11],{declaredClass:"esri.dijit.AttributeInspector",widgetsInTemplate:true,templateString:_24,_navMessage:"( ${idx} ${of} ${numFeatures} )",onUpdate:function(){},onDelete:function(){},onAttributeChange:function(){},onNext:function(){},onReset:function(){},onCancel:function(){},constructor:function(_25,_26){_2.mixin(this,_f.widgets.attributeInspector);_25=_25||{};if(!_25.featureLayer&&!_25.layerInfos){console.error("esri.AttributeInspector: please provide correct parameter in the constructor");}this._userIds={};if(_25.featureLayer&&_25.featureLayer.credential){var _27=_25.featureLayer.id;this._userIds[_27]=_25.featureLayer.credential.userId;}else{if(_25.layerInfos){var _28=_25.layerInfos;_3.forEach(_28,function(_29){if(_29.featureLayer){var _2a=_29.featureLayer.id;if(_29.featureLayer.credential){this._userIds[_2a]=_29.featureLayer.credential.userId;}if(_29.userId){this._userIds[_2a]=_29.userId;}}},this);}}this._datePackage=this._getDatePackage(_25);this._layerInfos=_25.layerInfos||[{featureLayer:_25.featureLayer,options:_25.options||[]}];this._aiConnects=[];this._selection=[];this._toolTips=[];this._numFeatures=0;this._featureIdx=0;this._currentLInfo=null;this._currentFeature=null;this._hideNavButtons=_25.hideNavButtons||false;},postCreate:function(){this._initLayerInfos();this._createAttachmentEditor();this.onFirstFeature();},destroy:function(){this._destroyAttributeTable();_3.forEach(this._aiConnects,_4.disconnect);delete this._aiConnects;if(this._attachmentEditor){this._attachmentEditor.destroy();delete this._attachmentEditor;}delete this._layerInfos;this._selection=this._currentFeature=this._currentLInfo=this._attributes=this._layerInfos=null;this.inherited(arguments);},refresh:function(){this._updateSelection();},first:function(){this.onFirstFeature();},last:function(){this.onLastFeature();},next:function(){this.onNextFeature();},previous:function(){this.onPreviousFeature();},showFeature:function(_2b,_2c){if(_2c){this._createOnlyFirstTime=true;}this._updateSelection([_2b],_2c);this._updateUI();},onLayerSelectionChange:function(_2d,_2e,_2f){this._createOnlyFirstTime=false;this._featureIdx=(_2f===_e.SELECTION_NEW)?0:this._featureIdx;this._updateSelection();this._updateUI();},onLayerSelectionClear:function(){if(!this._selection||this._selection.length<=0){return;}this._numFeatures=0;this._featureIdx=0;this._selection=[];this._currentFeature=null;this._currentLInfo=null;this._updateUI();},onLayerEditsComplete:function(_30,_31,_32,_33){_33=_33||[];if(_33.length){var _34=this._selection;var _35=_30.featureLayer.objectIdField;_3.forEach(_33,_2.hitch(this,function(del){_3.some(_34,_2.hitch(this,function(_36,idx){if(_36.attributes[_35]!==del.objectId){return false;}this._selection.splice(idx,1);return true;}));}));}_31=_31||[];if(_31.length){this._selection=_16.findFeatures(_31,_30.featureLayer);this._featureIdx=0;}var _37=this._numFeatures=this._selection?this._selection.length:0;if(_31.length){var _38=_37?this._selection[this._featureIdx]:null;if(_38){var _39=_38.getLayer();var _3a=_39.getEditCapabilities();if(!(_3a.canCreate&&!_3a.canUpdate)){this._showFeature(_38);}}}this._updateUI();},onFieldValueChange:function(_3b,_3c){_3c=(typeof _3c==="undefined")?null:_3c;var _3d=_3b.field;if(_3d.type==="esriFieldTypeDate"){_3c=(_3c&&_3c.getTime)?_3c.getTime():(_3c&&_3c.toGregorian?_3c.toGregorian().getTime():_3c);}if(this._currentFeature.attributes[_3d.name]===_3c){return;}var _3e=this._currentLInfo;var _3f=this._currentFeature;var _40=_3d.name;if(_40===_3e.typeIdField){var _41=this._findFirst(_3e.types,"id",_3c);var _42=_3e.fieldInfos;_3.forEach(_42,function(_43){_3d=_43.field;if(!_3d||_3d.name===_3e.typeIdField){return;}var _44=_43.dijit;var _45=this._setFieldDomain(_44,_41,_3d);if(_45&&_44){this._setValue(_44,_3f.attributes[_3d.name]+"");if(_44.isValid()===false){this._setValue(_44,null);}}},this);}this.onAttributeChange(_3f,_40,_3c);},onDeleteBtn:function(evt){this._deleteFeature();},onNextFeature:function(evt){this._onNextFeature(1);},onPreviousFeature:function(evt){this._onNextFeature(-1);},onFirstFeature:function(evt){this._onNextFeature(this._featureIdx*-1);},onLastFeature:function(evt){this._onNextFeature((this._numFeatures-1)-this._featureIdx);},_initLayerInfos:function(){var _46=this._layerInfos;this._editorTrackingInfos={};_3.forEach(_46,this._initLayerInfo,this);},_initLayerInfo:function(_47){var _48=_47.featureLayer,_49,_4a;this._connect(_48,"onSelectionComplete",_2.hitch(this,"onLayerSelectionChange",_47));this._connect(_48,"onSelectionClear",_2.hitch(this,"onLayerSelectionClear",_47));this._connect(_48,"onEditsComplete",_2.hitch(this,"onLayerEditsComplete",_47));_47.showAttachments=_48.hasAttachments?(_b.isDefined(_47.showAttachments)?_47.showAttachments:true):false;_47.hideFields=_47.hideFields||[];_47.htmlFields=_47.htmlFields||[];_47.isEditable=_48.isEditable()?(_b.isDefined(_47.isEditable)?_47.isEditable:true):false;_47.typeIdField=_48.typeIdField;_47.layerId=_48.id;_47.types=_48.types;if(_48.globalIdField){_49=this._findFirst(_47.fieldInfos,"fieldName",_48.globalIdField);if(!_49&&!_47.showGlobalID){_47.hideFields.push(_48.globalIdField);}}_4a=this._findFirst(_47.fieldInfos,"fieldName",_48.objectIdField);if(!_4a&&!_47.showObjectID){_47.hideFields.push(_48.objectIdField);}var _4b=this._getFields(_47.featureLayer);if(!_4b){return;}var _4c=_47.fieldInfos||[];_4c=_3.map(_4c,function(_4d){return _2.mixin({},_4d);});if(!_4c.length){_4b=_3.filter(_4b,_2.hitch(this,function(_4e){return !this._isInFields(_4e.name,_47.hideFields);}));_47.fieldInfos=_3.map(_4b,_2.hitch(this,function(_4f){var _50=(this._isInFields(_4f.name,_47.htmlFields)?ATI.STRING_FIELD_OPTION_RICHTEXT:ATI.STRING_FIELD_OPTION_TEXTBOX);return {"fieldName":_4f.name,"field":_4f,"stringFieldOption":_50};}));}else{_47.fieldInfos=_3.filter(_3.map(_4c,_2.hitch(this,function(_51){var _52=_51.stringFieldOption||(this._isInFields(_51.fieldName,_47.htmlFields)?ATI.STRING_FIELD_OPTION_RICHTEXT:ATI.STRING_FIELD_OPTION_TEXTBOX);return _2.mixin(_51,{"field":this._findFirst(_4b,"name",_51.fieldName),"stringFieldOption":_52});})),"return item.field;");}if(_47.showGlobalID&&!_49){_4c.push(this._findFirst(_4b,"name",_48.globalIdField));}if(_47.showObjectID&&!_4a){_4c.push(this._findFirst(_4b,"name",_48.objectIdField));}var _53=[];if(_48.editFieldsInfo){if(_48.editFieldsInfo.creatorField){_53.push(_48.editFieldsInfo.creatorField);}if(_48.editFieldsInfo.creationDateField){_53.push(_48.editFieldsInfo.creationDateField);}if(_48.editFieldsInfo.editorField){_53.push(_48.editFieldsInfo.editorField);}if(_48.editFieldsInfo.editDateField){_53.push(_48.editFieldsInfo.editDateField);}}this._editorTrackingInfos[_48.id]=_53;},_createAttachmentEditor:function(){this._attachmentEditor=null;var _54=this._layerInfos;var _55=_3.filter(_54,function(_56){return _56.showAttachments;});if(!_55||!_55.length){return;}this._attachmentEditor=new _15({"class":"atiAttachmentEditor"},this.attachmentEditor);this._attachmentEditor.startup();},_setCurrentLInfo:function(_57){var _58=this._currentLInfo?this._currentLInfo.featureLayer:null;var _59=_57.featureLayer;if(_58&&_58.id===_59.id&&!_58.ownershipBasedAccessControlForFeatures){var _5a=_59.getEditCapabilities();if(!(_5a.canCreate&&!_5a.canUpdate)){return;}}this._currentLInfo=_57;this._createTable();},_updateSelection:function(_5b,_5c){this._selection=_5b||[];var _5d=this._layerInfos;_3.forEach(_5d,this._getSelection,this);var _5e=this._numFeatures=this._selection.length;var _5f=_5e?this._selection[this._featureIdx]:null;this._showFeature(_5f,_5c);},_getSelection:function(_60){var _61=_60.featureLayer.getSelectedFeatures();this._selection=this._selection.concat(_61);},_updateUI:function(){var _62=this._numFeatures;var _63=this._currentLInfo;this.layerName.innerHTML=(!_63||_62===0)?this.NLS_noFeaturesSelected:(_63.featureLayer?_63.featureLayer.name:"");_8.set(this.attributeTable,"display",_62?"":"none");_8.set(this.editButtons,"display",_62?"":"none");_8.set(this.navButtons,"display",(!this._hideNavButtons&&(_62>1)?"":"none"));this.navMessage.innerHTML=_b.substitute({idx:this._featureIdx+1,of:this.NLS_of,numFeatures:this._numFeatures},this._navMessage);if(this._attachmentEditor){_8.set(this._attachmentEditor.domNode,"display",((_63&&_63.showAttachments)&&_62)?"":"none");}var _64=((_63&&_63.showDeleteButton===false)||!this._canDelete)?false:true;_8.set(this.deleteBtn.domNode,"display",_64?"":"none");if(this.domNode.parentNode&&this.domNode.parentNode.scrollTop>0){this.domNode.parentNode.scrollTop=0;}},_onNextFeature:function(_65){this._featureIdx+=_65;if(this._featureIdx<0){this._featureIdx=this._numFeatures-1;}else{if(this._featureIdx>=this._numFeatures){this._featureIdx=0;}}var _66=this._selection.length?this._selection[this._featureIdx]:null;this._showFeature(_66);this._updateUI();this.onNext(_66);},_deleteFeature:function(){this.onDelete(this._currentFeature);},_showFeature:function(_67,_68){if(!_67){return;}this._currentFeature=_67;var _69=_68?_68:_67.getLayer();var _6a=_69.getEditCapabilities({feature:_67,userId:this._userIds[_69.id]});this._canUpdate=_6a.canUpdate;this._canDelete=_6a.canDelete;var _6b=this._getLInfoFromFeatureLayer(_69);if(!_6b){return;}this._setCurrentLInfo(_6b);var _6c=_67.attributes;var _6d=this._findFirst(_6b.types,"id",_6c[_6b.typeIdField]);var _6e,_6f=null;var _70=_6b.fieldInfos;_3.forEach(_70,function(_71){_6f=_71.field;_6e=_71.dijit||null;if(!_6e){return;}var _72=this._setFieldDomain(_6e,_6d,_6f);var _73=_6c[_6f.name];_73=(_73&&_72&&_72.codedValues&&_72.codedValues.length)?(_72.codedValues[_73]?_72.codedValues[_73].name:_73):_73;if(!_b.isDefined(_73)){_73="";}if(_6e.declaredClass==="dijit.form.DateTextBox"){_73=(_73==="")?null:new Date(_73);}else{if(_6e.declaredClass==="dijit.form.FilteringSelect"){_6e._lastValueReported=null;_73=_6c[_6f.name]+"";}}try{this._setValue(_6e,_73);if(_6e.declaredClass==="dijit.form.FilteringSelect"&&_6e.isValid()===false){this._setValue(_6e,null);}}catch(error){_6e.set("displayedValue",this.NLS_errorInvalid,false);}},this);if(this._attachmentEditor&&_6b.showAttachments){this._attachmentEditor.showAttachments(this._currentFeature);}var _74=_69.getEditSummary(_67);if(_74){this.editorTrackingInfoDiv.innerHTML=_74;_c.show(this.editorTrackingInfoDiv);}else{_c.hide(this.editorTrackingInfoDiv);}},_setFieldDomain:function(_75,_76,_77){if(!_75){return null;}var _78=_77.domain;if(_76&&_76.domains){if(_76.domains[_77.name]&&_76.domains[_77.name] instanceof _d===false){_78=_76.domains[_77.name];}}if(!_78){return null;}if(_78.codedValues&&_78.codedValues.length>0){_75.set("store",this._toStore(_3.map(_78.codedValues,function(_79){return {id:_79.code+="",name:_79.name};})));this._setValue(_75,_78.codedValues[0].code);}else{_75.constraints={min:_b.isDefined(_78.minValue)?_78.minValue:Number.MIN_VALUE,max:_b.isDefined(_78.maxValue)?_78.maxValue:Number.MAX_VALUE};this._setValue(_75,_75.constraints.min);}return _78;},_setValue:function(_7a,_7b){if(!_7a.set){return;}_7a._onChangeActive=false;_7a.set("value",_7b,true);_7a._onChangeActive=true;},_getFields:function(_7c){var _7d=_7c._getOutFields();if(!_7d){return null;}var _7e=_7c.fields;return (_7d=="*")?_7e:_3.filter(_3.map(_7d,_2.hitch(this,"_findFirst",_7e,"name")),_b.isDefined);},_isInFields:function(_7f,_80){if(!_7f||!_80&&!_80.length){return false;}return _3.some(_80,function(_81){return _81.toLowerCase()===_7f.toLowerCase();});},_findFirst:function(_82,_83,_84){var _85=_3.filter(_82,function(_86){return _86.hasOwnProperty(_83)&&_86[_83]===_84;});return (_85&&_85.length)?_85[0]:null;},_getLInfoFromFeatureLayer:function(_87){var _88=_87?_87.id:null;return this._findFirst(this._layerInfos,"layerId",_88);},_createTable:function(){this._destroyAttributeTable();this.attributeTable.innerHTML="";this._attributes=_9.create("table",{cellspacing:"0",cellpadding:"0"},this.attributeTable);var _89=_9.create("tbody",null,this._attributes);var _8a=this._currentFeature;var _8b=this._currentLInfo;var _8c=this._findFirst(_8b.types,"id",_8a.attributes[_8b.typeIdField]);var _8d=_8b.fieldInfos;_3.forEach(_8d,_2.hitch(this,"_createField",_8c,_89),this);this._createOnlyFirstTime=false;},_createField:function(_8e,_8f,_90){var _91=this._currentLInfo;var _92=_90.field;if(this._isInFields(_92.name,_91.hideFields)){return;}if(this._isInFields(_92.name,this._editorTrackingInfos[_91.featureLayer.id])){return;}var _93=_9.create("tr",null,_8f);var td=_9.create("td",{innerHTML:_90.label||_92.alias||_92.name,"class":"atiLabel"},_93);td=_9.create("td",null,_93);var _94=null;var _95=false;if(_90.customField){_9.place(_90.customField.domNode||_90.customField,_9.create("div",null,td),"first");_94=_90.customField;}else{if(_91.isEditable===false||_92.editable===false||_90.isEditable===false||_92.type==="esriFieldTypeOID"||_92.type==="esriFieldTypeGlobalID"||(!this._canUpdate&&!this._createOnlyFirstTime)){_95=true;}}if(!_94&&(_91.typeIdField&&_92.name.toLowerCase()==_91.typeIdField.toLowerCase())){_94=this._createTypeField(_92,_90,td);}else{if(!_94){_94=this._createDomainField(_92,_90,_8e,td);}}if(!_94){switch(_92.type){case "esriFieldTypeString":_94=this._createStringField(_92,_90,td);break;case "esriFieldTypeDate":_94=this._createDateField(_92,_90,td);break;case "esriFieldTypeInteger":case "esriFieldTypeSmallInteger":_94=this._createIntField(_92,_90,td);break;case "esriFieldTypeSingle":case "esriFieldTypeDouble":_94=this._createFltField(_92,_90,td);break;default:_94=this._createStringField(_92,_90,td);break;}}if(_90.tooltip&&_90.tooltip.length){this._toolTips.push(new _1f({connectId:[_94.id],label:_90.tooltip}));}_94.onChange=_2.hitch(this,"onFieldValueChange",_90);_94.set("disabled",_95);_90.dijit=_94;},_createTypeField:function(_96,_97,_98){return new _1b({"class":"atiField",name:_96.alias||_96.name,store:this._toStore(_3.map(this._currentLInfo.types,function(_99){return {id:_99.id,name:_99.name};})),searchAttr:"name"},_9.create("div",null,_98));},_createDomainField:function(_9a,_9b,_9c,_9d){var _9e=_9a.domain;if(_9c&&_9c.domains){if(_9c.domains[_9a.name]&&_9c.domains[_9a.name] instanceof _d===false){_9e=_9c.domains[_9a.name];}}if(!_9e){return null;}if(_9e.codedValues){return new _1b({"class":"atiField",name:_9a.alias||_9a.name,searchAttr:"name",required:_9a.nullable||false},_9.create("div",null,_9d));}else{return new _1c({"class":"atiField"},_9.create("div",null,_9d));}},_createStringField:function(_9f,_a0,_a1){var _a2={"class":"atiField",trim:true,maxLength:_9f.length};if(_a0.stringFieldOption===ATI.STRING_FIELD_OPTION_TEXTAREA){_a2["class"]+=" atiTextAreaField";return new _1e(_a2,_9.create("div",null,_a1));}else{if(_a0.stringFieldOption===ATI.STRING_FIELD_OPTION_RICHTEXT){_a2["class"]+=" atiRichTextField";_a2.height="100%";_a2.width="100%";_a2.plugins=_a0.richTextPlugins||["bold","italic","underline","foreColor","hiliteColor","|","justifyLeft","justifyCenter","justifyRight","justifyFull","|","insertOrderedList","insertUnorderedList","indent","outdent","|","createLink"];return new _12(_a2,_9.create("div",null,_a1));}else{return new _19(_a2,_9.create("div",null,_a1));}}},_createDateField:function(_a3,_a4,_a5){var _a6={"class":"atiField","trim":true};if(this._datePackage){_a6.datePackage=this._datePackage;}return new _18(_a6,_9.create("div",null,_a5));},_createIntField:function(_a7,_a8,_a9){return new _1a({"class":"atiField",constraints:{places:0},invalidMessage:this.NLS_validationInt,trim:true},_9.create("div",null,_a9));},_createFltField:function(_aa,_ab,_ac){return new _1a({"class":"atiField",trim:true,invalidMessage:this.NLS_validationFlt},_9.create("div",null,_ac));},_toStore:function(_ad){return new _20({data:{identifier:"id",label:"name",items:_ad}});},_connect:function(_ae,evt,_af){this._aiConnects.push(_4.connect(_ae,evt,_af));},_getDatePackage:function(_b0){if(_b0.datePackage===null){return null;}else{if(_b0.datePackage){return _b0.datePackage;}else{if(_6.locale==="ar"){return "dojox.date.islamic";}}}return null;},_destroyAttributeTable:function(){var _b1=this._layerInfos;_3.forEach(_b1,function(_b2){var _b3=_b2.fieldInfos;_3.forEach(_b3,function(_b4){var _b5=_b4.dijit;if(_b5){_b5._onChangeHandle=null;if(_b4.customField){return;}if(_b5.destroyRecursive){_b5.destroyRecursive();}else{if(_b5.destroy){_b5.destroy();}}}_b4.dijit=null;},this);},this);var _b6=this._toolTips;_3.forEach(_b6,function(_b7){_b7.destroy();delete _b7;});this._toolTips=[];if(this._attributes){_9.destroy(this._attributes);}}});_2.mixin(ATI,{STRING_FIELD_OPTION_RICHTEXT:"richtext",STRING_FIELD_OPTION_TEXTAREA:"textarea",STRING_FIELD_OPTION_TEXTBOX:"textbox"});if(_7("extend-esri")){_2.setObject("dijit.AttributeInspector",ATI,_a);}return ATI;});