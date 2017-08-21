//>>built
define("dgrid/editor",["dojo/_base/kernel","dojo/_base/lang","dojo/_base/array","dojo/_base/Deferred","dojo/on","dojo/aspect","dojo/has","./Grid","put-selector/put","dojo/_base/sniff"],function(_1,_2,_3,_4,on,_5,_6,_7,_8){var _9,_a,_b;function _c(_d,_e){_d.value=_e;if(_d.type=="radio"||_d.type=="checkbox"){_d.checked=!!_e;}};function _f(_10,_11){if(typeof _11=="number"){_10=isNaN(_10)?_10:parseFloat(_10);}else{if(typeof _11=="boolean"){_10=_10=="true"?true:_10=="false"?false:_10;}else{if(_11 instanceof Date){var _12=new Date(_10);_10=isNaN(_12.getTime())?_10:_12;}}}return _10;};function _13(_14,cmp){if(typeof cmp.get=="function"){return _f(cmp.get("value"));}else{return _f(cmp[cmp.type=="checkbox"||cmp.type=="radio"?"checked":"value"]);}};function _15(_16,_17,_18,_19,_1a){var _1b,row,_1c,_1d;if(!(_18>=_19&&_18<=_19)){_1b=_16.cell(_17);row=_1b.row;_1c=_1b.column;if(_1c.field&&row){_1d={grid:_16,cell:_1b,rowId:row.id,oldValue:_18,value:_19,bubbles:true,cancelable:true};if(_1a&&_1a.type){_1d.parentType=_1a.type;}if(on.emit(_17,"dgrid-datachange",_1d)){if(_16.updateDirty){_16.updateDirty(row.id,_1c.field,_19);_1c.autoSave&&setTimeout(function(){_16._trackError("save");},0);}}else{var cmp;if(cmp=_17.widget){cmp._dgridIgnoreChange=true;cmp.set("value",_18);setTimeout(function(){cmp._dgridIgnoreChange=false;},0);}else{if(cmp=_17.input){_c(cmp,_18);}}return _18;}}}return _19;};function _1e(_1f,_20,cmp,_21){var _22;if(!cmp.isValid||cmp.isValid()){_22=_15(_1f,(cmp.domNode||cmp).parentNode,_9?_a:cmp._dgridLastValue,_13(_20,cmp),_21);if(_9){_a=_22;}else{cmp._dgridLastValue=_22;}}};function _23(_24){var _25=_24.editor,_26=_24.editOn,_27=_24.grid,_28=typeof _25!="string",_29,cmp,_2a,_2b,_2c;_29=_24.editorArgs||{};if(typeof _29=="function"){_29=_29.call(_27,_24);}if(_28){cmp=new _25(_29);_2a=cmp.focusNode||cmp.domNode;_2a.className+=" dgrid-input";cmp.connect(cmp,_26?"onBlur":"onChange",function(){if(!cmp._dgridIgnoreChange){_1e(_27,_24,this,{type:"widget"});}});}else{_2c=function(evt){var _2d=evt.target;if("_dgridLastValue" in _2d&&_2d.className.indexOf("dgrid-input")>-1){_1e(_27,_24,_2d,evt);}};if(!_24.grid._hasInputListener){_27._hasInputListener=true;_27.on("change",function(evt){_2c(evt);});}_2b=_25=="textarea"?"textarea":"input[type="+_25+"]";cmp=_2a=_8(_2b+".dgrid-input",_2.mixin({name:_24.field,tabIndex:isNaN(_24.tabIndex)?-1:_24.tabIndex},_29));if(_6("ie")<9||(_6("ie")&&_6("quirks"))){if(_25=="radio"||_25=="checkbox"){on(cmp,"click",function(evt){_2c(evt);});}else{on(cmp,"change",function(evt){_2c(evt);});}}}on(_2a,"mousedown",function(evt){evt.stopPropagation();});return cmp;};function _2e(_2f,_30){var cmp=_23(_2f),_31=cmp.domNode,_32=cmp.domNode||cmp,_33=cmp.focusNode||_32,_34=_31?function(){cmp.set("value",cmp._dgridLastValue);}:function(){_c(cmp,cmp._dgridLastValue);_1e(_2f.grid,_2f,cmp);},_35;function _36(){var _37=_32.parentNode,_38=_2f.grid.cell(_32),i=_37.children.length-1,_39={alreadyHooked:true},_3a;_37.removeChild(_32);while(i--){_8(_37.firstChild,"!");}_7.appendIfNode(_37,_2f.renderCell(_2f.grid.row(_37).data,_a,_37,_b?_2.delegate(_39,_b):_39));_9=_a=_b=null;_2f._editorBlurHandle.pause();};function _3b(evt){var key=evt.keyCode||evt.which;if(key==27){_34();_a=cmp._dgridLastValue;_33.blur();}else{if(key==13&&_2f.dismissOnEnter!==false){_33.blur();}}};_35=on(_33,"keydown",_3b);(_2f._editorBlurHandle=on.pausable(cmp,"blur",_36)).pause();return cmp;};function _3c(cmp,_3d,_3e,_3f){var _40=_3d.grid,_41=_3d.editor,_42=cmp.domNode;if(!_42){_c(cmp,_3f);}_3e.innerHTML="";_8(_3e,cmp.domNode||cmp);if(_42){if(!cmp._started){cmp.startup();}cmp._dgridIgnoreChange=true;cmp.set("value",_3f);setTimeout(function(){cmp._dgridIgnoreChange=false;},0);}cmp._dgridLastValue=_3f;if(_9){_a=_3f;}};function _43(_44){var row,_45,_46,_47,_48,_49,cmp,dfd;if(!_44.column){_44=this.cell(_44);}_45=_44.column;_48=_45.field;_46=_44.element.contents||_44.element;if((cmp=_45.editorInstance)){if(_9!=_46&&(!_45.canEdit||_45.canEdit(_44.row.data,_49))){_9=_46;row=_44.row;_47=this.dirty&&this.dirty[row.id];_49=(_47&&_48 in _47)?_47[_48]:_45.get?_45.get(row.data):row.data[_48];_3c(_45.editorInstance,_45,_46,_49);dfd=new _4();setTimeout(function(){if(cmp.focus){cmp.focus();}if(_45._editorBlurHandle){_45._editorBlurHandle.resume();}dfd.resolve(cmp);},0);return dfd.promise;}}else{if(_45.editor){cmp=_46.widget||_46.input;if(cmp){dfd=new _4();if(cmp.focus){cmp.focus();}dfd.resolve(cmp);return dfd.promise;}}}return null;};_2.getObject("dgrid.editor",true);return (dgrid.editor=function(_4a,_4b,_4c){var _4d=_4a.renderCell||_7.defaultRenderCell,_4e=[],_4f;if(!_4a){_4a={};}_4a.editor=_4b=_4b||_4a.editor||"text";_4a.editOn=_4c=_4c||_4a.editOn;_4f=typeof _4b!="string";if(_4a.widgetArgs){_1.deprecated("column.widgetArgs","use column.editorArgs instead","dgrid 1.0");_4a.editorArgs=_4a.widgetArgs;}_5.after(_4a,"init",_4c?function(){var _50=_4a.grid;if(!_50.edit){_50.edit=_43;}_4a.editorInstance=_2e(_4a,_4d);}:function(){var _51=_4a.grid;if(!_51.edit){_51.edit=_43;}if(_4f){_4e.push(_5.before(_51,"removeRow",function(_52){var _53=_51.cell(_52,_4a.id).element,_54=(_53.contents||_53).widget;if(_54){_54.destroyRecursive();}}));}});_5.after(_4a,"destroy",function(){_3.forEach(_4e,function(l){l.remove();});if(_4a._editorBlurHandle){_4a._editorBlurHandle.remove();}if(_4c&&_4f){_4a.editorInstance.destroyRecursive();}});_4a.renderCell=_4c?function(_55,_56,_57,_58){if(!_58||!_58.alreadyHooked){on(_57.tagName=="TD"?_57:_57.parentNode,_4c,function(){_b=_58;_4a.grid.edit(this);});}return _4d.call(_4a,_55,_56,_57,_58);}:function(_59,_5a,_5b,_5c){if(!_4a.canEdit||_4a.canEdit(_59,_5a)){var cmp=_23(_4a);_3c(cmp,_4a,_5b,_5a);_5b[_4f?"widget":"input"]=cmp;}else{return _4d.call(_4a,_59,_5a,_5b,_5c);}};return _4a;});});