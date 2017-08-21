/**
 * @author ZLT
 * @date : 2013-7-15
 * 用法：var d = new Dialog();
 * 
 * 属性（均有默认值，宽高为自适应）：
 * isMove Boolean 是否可以移动，默认true
 * title  String  对话框标题
 * titleHeight Number 对话框标题高度
 * closeButton Boolean 是否需要添加关闭按钮
 * content String 对话框内容,可以为html
 * left Number 左定位
 * top Number 上定位
 * right Number 右定位
 * bottom Number 下定位
 * zindex 层级，默认1000
 * mask Boolean 是否需要背景遮罩层
 * maskbg String 背景层颜色，默认为#000
 * maskClick Boolean 背景遮罩层是否可点
 * buttonDisplay Object
 * ++ confirmButton Boolean 是否要显示确认按钮 
 * ++ cancelButton Boolean 是否要显示取消按钮
 * confirmButton String 确认按钮显示文字
 * cancelButton String 取消按钮显示文字
 * confirmButtonCall function 确认按钮点击后的回调函数
 * cancelButtonCall function 取消按钮点击后的回调函数
 * closeCall function 关闭对话框后的回调函数
 * 
 * 方法：
 * conf(Object) 配置对话框
 * show() 显示对话框
 * hide() 隐藏对话框
 * setDialogTitle() 设置对话框标题
 * setDialogContent() 设置对话框内容
 * setPosition() 设置对话框位置
 * getWidth() getWidth 获取对话框的宽
 * getHeight() getHeight 获取对话框的高
 */
MapFactory.Define("MapFactory/Util/Dialog*",function(){
	return function(conf) {

		/**
		 * 配置参数信息
		 */
		var _conf = {
			mutiDialog : false, // 是否开启多对话框模式
			mutiDialogSeed : "", //若开启多对话框模式，需要添加独立的标识字符串来与其他对话框进行区别
			isMove : true, // 是否可以移动
			title : '提示框', // 标题
			titleHeight : 35, // 标题高
			footerHeight : 40, // 底部高
			content : 'No content',
			width : '300', // 对话框宽,  废弃！
			height : '200', // 对话框高，  废弃！
			maxHeight : '500',
			left : '',
			top : '',
			right : '',
			bottom : '',
			zindex : '3000', // 盒子层序
			mask : true, //是否需要背景遮罩层
			maskbg : '#000', // 遮罩层背景
			maskClick : false, // 是否设置背景点击事件
			buttonDisplay : {
				'confirmButton' : true,
				'cancelButton' : true
			}, // 是否显示按钮
			confirmButton : '确认', // 按钮文字
			confirmButtonCall : '', // 确认按钮触发函数
			cancelButton : '取消', // 按钮文字
			cancelButtonCall : '', // 取消按钮触发函数
			closeCall : '' // 关闭对话框触发函数
		}

		/**
		 * 对外API
		 */
		var api = {
			conf : configDialog, // 配置对话框
			show : showDialog, // 显示
			hide : hideDialog,  // 隐藏
			setDialogTitle : setTitle, // 设置标题
			setDialogContent :setContent, // 设置内容
			setPosition : setPosition, // 设置对话框位置
			setAutoPosition : setAutoPosition, // 自动设置对话框位置
			getWidth : getWidth, // 获取对话框的宽
			getHeight : getHeight, // 获取对话框的高
			minimize : minimizeDialog, // 最小化对话框
			maximize : maximizeDialog,// 最大化对话框
			setRelatePosition:setRelatePosition
		}
		
		/**
		 * 各组件ID
		 */
		var maskID = "",
			DialogBoxID ="",
			DialogOuterBoxID = "",
			DialogBoxTitleID = "",
			DialogBoxContentID = "",
			DialogBoxFooterID = "",
			DialogBoxConfirmButtonID = "",
			DialogBoxCancelButtonID = "",
			DialogCloseButtonID = "",
			DialogMinimizeButtonID = "",
			DialogMaximizeButtonID = "";

		/**
		 * 背景遮罩
		 */
		var mask = null;

		/**
		 * 弹出框
		 */
		var DialogBox = null;

		/**
		 * 对话框外部容器
		 */
		var DialogOuterBox = null;

		/**
		 * 弹出框标题
		 */
		var DialogBoxTitle = null;
		
		/**
		 * 关闭按钮
		 */
		var DialogCloseButton = null;
		
		/**
		 * 最小化按钮
		 */
		var DialogMinimizeButton = null;
		
		/**
		 * 最大化按钮
		 */
		var DialogMaximizeButton = null;

		/**
		 * 弹出框内容
		 */
		var DialogBoxContent = null;

		/**
		 * 弹出框页底
		 */
		var DialogBoxFooter = null;

		/**
		 * 按钮一
		 */
		var DialogBoxConfirmButton = null;

		/**
		 * 按钮二
		 */
		var DialogBoxCancelButton = null;

		/**
		 * 移动状态
		 */
		var _dragEable = false;

		/**
		 * 鼠标在对象中的偏移量
		 */
		var _offsetX = 0, _offsetY = 0;

		/**
		 * 鼠标初始位置
		 */
		var _initX = 0,_initY = 0;

		/**
		 * 标记是否有参数修改
		 */
		var _isParamsChange = false;

		/**
		 * 初始化构造函数
		 */
		function _init(){
			configDialog(conf);
			_initDialogLoader();
			_initOuterDialog();
			_initDialogTitle();
			_initDialogBox();
			_initDialogContent();
			_initDialogFooter();
			_setParams();
		}
		
		_init();

		/**
		 * 初始化对话框读取
		 */
		function _initDialogLoader(){
			var _dialogSeed = "";
			if(_conf.mutiDialog){
				_dialogSeed = "_"+_conf.mutiDialogSeed;
			}
			maskID = "DialogMaskBackground" + _dialogSeed;
			DialogBoxID ="DialogInnerBox" + _dialogSeed;
			DialogOuterBoxID = "DialogOuterBox" + _dialogSeed;
			DialogBoxTitleID = "DialogBoxTitle" + _dialogSeed;
			DialogBoxContentID = "DialogBoxContent" + _dialogSeed;
			DialogBoxFooterID = "DialogBoxFooter" + _dialogSeed;
			DialogBoxConfirmButtonID = "DialogBoxConfirmButton" + _dialogSeed;
			DialogBoxCancelButtonID = "DialogBoxCancelButton" + _dialogSeed;
			DialogCloseButtonID = "DialogCloseButton" + _dialogSeed;
			DialogMinimizeButtonID = "DialogMinimizeButton" + _dialogSeed;
			DialogMaximizeButtonID = "DialogMaximizeButton" + _dialogSeed;
			
			mask = document.getElementById(maskID);
			DialogBox = document.getElementById(DialogBoxID);
			DialogOuterBox = document.getElementById(DialogOuterBoxID);
			DialogBoxTitle = document.getElementById(DialogBoxTitleID);
			DialogBoxContent = document.getElementById(DialogBoxContentID);
			DialogBoxFooter = document.getElementById(DialogBoxFooterID);
			DialogBoxConfirmButton = document.getElementById(DialogBoxConfirmButtonID);
			DialogBoxCancelButton = document.getElementById(DialogBoxCancelButtonID);
			DialogCloseButton = document.getElementById(DialogCloseButtonID);
			DialogMinimizeButton = document.getElementById(DialogMinimizeButtonID);
			DialogMaximizeButton = document.getElementById(DialogMaximizeButtonID);
		}

		/**
		 * 初始化外部对话框
		 */
		function _initOuterDialog() {
			if(DialogBox == null || typeof DialogBox == "undefined"){
				
				// 创建对话框外部容器
				DialogOuterBox = document.createElement('div');
				DialogOuterBox.id = DialogOuterBoxID;
				DialogOuterBox.className = 'DialogOuterBox';

				document.body.appendChild(DialogOuterBox);
			}
		}

		/**
		 * 显示对话框
		 */
		function _showDialog(){
			if(DialogOuterBox != null){
				DialogOuterBox.style.display = "block";
			}else{
				_initOuterDialog();
			}
			return api;
		}

		/**
		 * 初始化内部对话框
		 */
		function _initDialogBox(){
			// 创建对话框
			if(DialogBox == null || typeof DialogBox == 'undefined'){
				DialogBox = document.createElement('div');
				DialogBox.id = DialogBoxID;
				DialogBox.className = 'DialogInnerBox';
				DialogBox.onclick = function(e) {
					_stopBubble(e);
				}
				DialogOuterBox.appendChild(DialogBox);
			}
		}

		/**
		 * 初始化遮罩层
		 */
		function _initMask(){
			// 创建遮罩
			if(mask == null || typeof mask == "undefined"){
				mask = document.createElement('div');
				mask.id = maskID;
				mask.className = 'DialogMaskBackground';
				mask.style.width = 100 + "%";
				mask.style.height = 100 + "%";
				mask.style.display = "block";
				mask.style.zIndex = _conf.zindex;
				if(_conf.maskClick) {
					mask.onclick = hideDialog;
				}
				document.body.appendChild(mask);
			}
		}

		/**
		 * 显示遮罩层
		 */
		function _showMask(){
			if(mask != null){
				mask.style.display = "block";
			}else{
				_initMask();
			}
		}

		/**
		 * 隐藏遮罩层
		 */
		function _hideMask(){
			if(mask){
				mask.style.display = "none";
			}
		}

		/**
		 * 初始化标题
		 */
		function _initDialogTitle(){
			if(DialogBoxTitle == null || typeof DialogBoxTitle == "undefined"){
				// 创建对话框标题
				DialogBoxTitle = document.createElement('div');
				DialogBoxTitle.id = DialogBoxTitleID;
				DialogBoxTitle.className = 'DialogBoxTitle';
				DialogOuterBox.appendChild(DialogBoxTitle);
				_initDialogOperationBtn();
				DialogBoxTitle.appendChild(document.createElement('b'));
			}
			_hideMaximizeBtn();
			_showMinimizeBtn();
		}

		/**
		 * 显示对话框标题
		 */
		function _showDialogTitle(){
			if(DialogBoxTitle == null){
				_initDialogTitle();
			}else{
				DialogBoxTitle.style.display = "block";
			}
		}

		/**
		 * 隐藏对话框标题
		 */
		function _hideDialogTitle(){
			if(DialogBoxTitle != null){
				DialogBoxTitle.style.display = "none";
			}
		}
		
		/**
		 * 初始化对话框操作按钮
		 */
		function _initDialogOperationBtn(){
			if(DialogCloseButton == null || typeof DialogCloseButton =="undefined"){
				DialogCloseButton = document.createElement('span');
				DialogCloseButton.id = DialogCloseButtonID;
				DialogCloseButton.className = 'dialogOperationButton';
				DialogCloseButton.alt = '关闭';
				DialogBoxTitle.appendChild(DialogCloseButton);
				DialogCloseButton.innerHTML = '×';
				DialogCloseButton.onclick = hideDialog;
			}
			if(DialogMaximizeButton == null || typeof DialogMaximizeButton == "undefined"){
				DialogMaximizeButton = document.createElement('span');
				DialogMaximizeButton.id = DialogMaximizeButtonID;
				DialogMaximizeButton.className = 'dialogOperationButton';
				DialogMaximizeButton.innerHTML = '+';
				DialogMaximizeButton.alt = '最大化';
				DialogBoxTitle.appendChild(DialogMaximizeButton);
				DialogMaximizeButton.onclick = maximizeDialog;
			}
			if(DialogMinimizeButton == null || typeof DialogMinimizeButton == "undefined"){
				DialogMinimizeButton = document.createElement('span');
				DialogMinimizeButton.id = DialogMinimizeButtonID;
				DialogMinimizeButton.className = 'dialogOperationButton';
				DialogMinimizeButton.innerHTML = '-';
				DialogMinimizeButton.alt = '最小化';
				DialogBoxTitle.appendChild(DialogMinimizeButton);
				DialogMinimizeButton.onclick = minimizeDialog;
			}
		}

		/**
		 * 显示最小化按钮
		 */
		function _showMinimizeBtn(){
			if(DialogMinimizeButton){
				DialogMinimizeButton.style.display = "block";
			}
		}

		/**
		 * 隐藏最小化按钮
		 */
		function _hideMinimizeBtn(){
			if(DialogMinimizeButton){
				DialogMinimizeButton.style.display = "none";
			}
		}
		
		/**
		 * 显示最大化按钮
		 */
		function _showMaximizeBtn(){
			if(DialogMaximizeButton){
				DialogMaximizeButton.style.display = "block";
			}
		}

		/**
		 * 隐藏最大化按钮
		 */
		function _hideMaximizeBtn(){
			if(DialogMaximizeButton){
				DialogMaximizeButton.style.display = "none";
			}
		}

		/**
		 * 初始化对话框内容
		 */
		function _initDialogContent(){
			// 创建内容框
			if(DialogBoxContent == null || typeof DialogBoxContent == "undefined"){
				DialogBoxContent = document.createElement('div');
				DialogBoxContent.id = DialogBoxContentID;
				DialogBoxContent.className = 'DialogBoxContent';
				DialogBox.appendChild(DialogBoxContent);
			}
		}

		/**
		 * 显示对话框内容
		 */
		function _showDialogContent(){
			if(DialogBoxContent == null){
				_initDialogContent();
			}else{
				DialogBoxContent.style.display = "block";
			}
		}
		
		/**
		 * 隐藏对话框内容
		 */
		function _hideDialogContent(){
			if(DialogBoxContent != null){
				DialogBoxContent.style.display = "none";
			}
		}

		/**
		 * 初始化对话框底部
		 */
		function _initDialogFooter(){
			// 初始化对话框底部
			if(DialogBoxFooter == null || typeof DialogBoxFooter == "undefined"){
				DialogBoxFooter = document.createElement('div');
				DialogBoxFooter.id = DialogBoxFooterID;
				DialogBoxFooter.className = 'DialogBoxFooter';
				DialogBoxFooter.style.height = _conf.footerHeight + "px";
				DialogBoxFooter.style.lineHeight = _conf.footerHeight + "px";
				DialogOuterBox.appendChild(DialogBoxFooter);
			}

			// 初始化对话框按钮一
			if(DialogBoxConfirmButton == null || typeof DialogBoxConfirmButton == "undefined"){
				DialogBoxConfirmButton = document.createElement('span');
				DialogBoxConfirmButton.id = DialogBoxConfirmButtonID;
				DialogBoxConfirmButton.className = 'DialogBoxConfirmButton DialogBoxButton';
				DialogBoxFooter.appendChild(DialogBoxConfirmButton);
			}

			// 初始化对话框按钮二
			if(DialogBoxCancelButton == null || typeof DialogBoxCancelButton == "undefined"){
				DialogBoxCancelButton = document.createElement('span');
				DialogBoxCancelButton.id = DialogBoxCancelButtonID;
				DialogBoxCancelButton.className = 'DialogBoxCancelButton DialogBoxButton';
				DialogBoxFooter.appendChild(DialogBoxCancelButton);
			}
		}

		/**
		 * 显示对话框底部
		 */
		function _showDialogFooter(){
			if(DialogBoxFooter != null){
				if(_conf.buttonDisplay.confirmButton || _conf.buttonDisplay.cancelButton || typeof _conf.buttonDisplay.confirmButton == "undefined" || typeof _conf.buttonDisplay.cancelButton == "undefined") {
					DialogBoxFooter.style.display = "block";
				}
			}else{
				_initDialogFooter();
			}
		}

		/**
		 * 隐藏对话框底部
		 */
		function _hideDialogFooter(){
			if(DialogBoxFooter != null){
				DialogBoxFooter.style.display = "none";
			}
		}

		/**
		 * 设置对话框参数
		 */
		function _setParams() {
			DialogOuterBox.style.zIndex = _conf.zindex + 1;
			// 设置标题
			if(_conf.title !== ""){
				_showDialogTitle();
				DialogBoxTitle.style.height = _conf.titleHeight + "px";
				DialogBoxTitle.style.lineHeight =  _conf.titleHeight + "px";
				setTitle(_conf.title);
			}else{
				_hideDialogTitle();
			}

			// 设置内容
			_showDialogContent();
			setContent(_conf.content);

			// 底部
			if(_conf.buttonDisplay.confirmButton || _conf.buttonDisplay.cancelButton || typeof _conf.buttonDisplay.confirmButton == "undefined" || typeof _conf.buttonDisplay.cancelButton == "undefined") {
				_showDialogFooter();
			}else{
				_hideDialogFooter();
			}

			// 设置按钮一
			if(_conf.buttonDisplay.confirmButton || typeof _conf.buttonDisplay.confirmButton == "undefined") {
				DialogBoxConfirmButton.style.display = "inline";
				DialogBoxConfirmButton.innerHTML = _conf.confirmButton;
				if(_conf.confirmButtonCall != '') {
					DialogBoxConfirmButton.onclick = function(e) {
						//_hideDialog();
						_conf.confirmButtonCall();
					}
				} else {
					DialogBoxConfirmButton.onclick = function(e) {
						hideDialog();
					}
				}
			} else {
				DialogBoxConfirmButton.style.display = "none";
			}

			// 设置按钮二
			if(_conf.buttonDisplay.cancelButton || typeof _conf.buttonDisplay.cancelButton == "undefined") {
				DialogBoxCancelButton.style.display = "inline";
				DialogBoxCancelButton.innerHTML = _conf.cancelButton;
				if(_conf.cancelButtonCall != '') {
					DialogBoxCancelButton.onclick = function(e) {
						//_hideDialog();
						_conf.cancelButtonCall();
					}
				} else {
					DialogBoxCancelButton.onclick = function(e) {
						hideDialog();
					}
				}
			} else {
				DialogBoxCancelButton.style.display = "none";
			}

		}

		/**
		 * 设置对话框位置
		 */
		function _setDialogPosition(){
			if(_conf.left !== ""){
				DialogOuterBox.style.left = _conf.left + "px";
				DialogOuterBox.style.right = "";
			}else if(_conf.right !== ""){
				DialogOuterBox.style.right = _conf.right + "px";
				DialogOuterBox.style.left = "";
			}else{
				DialogOuterBox.style.right = "";
				DialogOuterBox.style.left = _getLeft() + "px";
			}

			if(_conf.top !== ""){
				DialogOuterBox.style.top = _conf.top + "px";
				DialogOuterBox.style.bottom = "";
			}else if(_conf.bottom !== ""){
				DialogOuterBox.style.bottom = _conf.bottom + "px";
				DialogOuterBox.style.top = "";
			}else{
				DialogOuterBox.style.top = _getTop() + "px";
				DialogOuterBox.style.bottom = "";
			}
		}

		/**
		 * 获取对话框左边位置
		 */
		function _getLeft() {
			return Math.max(document.body.clientWidth,document.documentElement.clientWidth) / 2 - DialogOuterBox.clientWidth / 2;
		}

		/**
		 * 获取对话框顶部位置
		 */
		function _getTop() {
			return Math.max(document.body.clientHeight,document.documentElement.clientHeight) / 2 - DialogOuterBox.clientHeight / 2;
		}

		/**
		 * 阻止程序冒泡
		 */
		function _stopBubble(e) {
			if(e && e.stopPropagation) {
				e.stopPropagation();
			} else {
				window.event.cancelBubble = true;
			}
		}
		
		/**
		 * 是否绑定或者取消拖动函数
		 */
		function _isMoveAble(){
			if(DialogBoxTitle != null){
				if(_conf.isMove){
					DialogBoxTitle.onmousedown = _dragEnable;
					DialogBoxTitle.onmouseup = _dragEnd;
				}else{
					DialogBoxTitle.onmousedown = function(){};
					DialogBoxTitle.onmouseup = function(){};
				}
			}
		}
		
		/**
		 * 允许拖动并初始化
		 */
		function _dragEnable(_event){
			var e = _event || window.event;
			var p = _getTargetPosition(DialogOuterBox);
			var scrollLeft = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
			var scrollTop = Math.max(document.body.scrollTop,document.documentElement.scrollTop);

			if(DialogOuterBox.style.right != ''){
				DialogOuterBox.style.right = '';
				p.left = Math.max(document.body.clientWidth,document.documentElement.clientWidth) - DialogOuterBox.clientWidth - p.right;
				DialogOuterBox.style.left = p.left + 'px';
			}

			if(DialogOuterBox.style.bottom != ''){
				DialogOuterBox.style.bottom = '';
				p.top = Math.max(document.body.clientHeight,document.documentElement.clientHeight) - DialogOuterBox.clientHeight - p.bottom;
				DialogOuterBox.style.left = p.top + 'px';
			}

			_offsetX = scrollLeft + e.clientX - p.left;
			_offsetY = scrollTop + e.clientY - p.top;
			_dragEable = true;

			// 设置移动时的鼠标样式
			DialogBoxTitle.style.cursor = "move";
			if(DialogBoxTitle.children[0]){
				DialogBoxTitle.children[0].style.cursor = "move";
			}

			document.body.onmousemove = _move;
			document.body.onmouseup = _dragEnd;
		}

		/**
		 * 拖动
		 */
		function _move(_event){
			var e = _event || window.event;
			var scrollLeft = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
			var scrollTop = Math.max(document.body.scrollTop,document.documentElement.scrollTop);

			if(_dragEable){
				DialogOuterBox.style.left = scrollLeft + e.clientX - _offsetX +"px";
				DialogOuterBox.style.top = scrollTop +e.clientY - _offsetY + "px";
			}
		}
		
		/**
		 * 拖动结束
		 */
		function _dragEnd(){
			document.body.onmousemove = null;
			document.body.onmouseup = null;
			// 设置移动时的鼠标样式
			DialogBoxTitle.style.cursor = "auto";
			if(DialogBoxTitle.children[0]){
				DialogBoxTitle.children[0].style.cursor = "default";
			}
			_dragEable = false;
		}

		/**
		 * 获取元素位置
		 */
		function _getTargetPosition(o){
			if(typeof o != "object" || o == null){
				return 0;
			}
			var left=0,right=0,top=0,bottom=0;
			left = o.style.left;
			top = o.style.top;
			right = o.style.right;
			bottom = o.style.bottom;
			if(left!=''){
				left = left.substr(0,left.indexOf('p'));
			}
			if(top!=''){
				top = top.substr(0,top.indexOf('p'));
			}
			if(right!=''){
				right = right.substr(0,right.indexOf('p'));
			}
			if(bottom!=''){
				bottom = bottom.substr(0,bottom.indexOf('p'));
			}
			return {
				left : left,
				right : right,
				top : top,
				bottom : bottom
			};
		}

		/**
		 * 配置对话框
		 */
		function configDialog(confObj){
			// 设置参数
			if( typeof confObj != "undefined") {
				for(elem in _conf) {
					if( typeof confObj[elem] != "undefined"){ //&& confObj[elem] !== "") {
						_conf[elem] = confObj[elem];
					}
				}
				confObj = null;
				_isParamsChange = true;
			}
			return api;
		}

		/**
		 * 显示对话框
		 */
		function showDialog() {
			if(_conf.mask){
				_showMask();
			}else{
				_hideMask();
			}
			// 判断对话框是否允许移动
			_showDialog();
			_isMoveAble();
			if(_isParamsChange){
				_setParams();
				_isParamsChange = false;
			}
			return api;
		}

		/**
		 * 隐藏对话框
		 */
		function hideDialog(isCallFunc) {
			if(mask && mask != null) {
				mask.style.display = "none";
			}
			if(DialogOuterBox && DialogOuterBox != null) {
				DialogOuterBox.style.display = "none";
			}
			_isCallFunc = true;
			if(typeof isCallFunc == "boolean"){
				_isCallFunc = isCallFunc;
			}
			if(_conf.closeCall !== "" && _isCallFunc){
				_conf.closeCall();
			}
			return api;
		}

		/**
		 * 设置标题
		 */
		function setTitle(titleString){
			_conf.title = titleString;
			DialogBoxTitle.children[3].innerHTML = titleString;
			_isParamsChange = true;
			return api;
		}

		/**
		 * 设置内容
		 */
		function setContent(content){
			_conf.content = content;
			DialogBoxContent.innerHTML = content;
			if(DialogBoxContent.clientHeight > _conf.maxHeight){
				DialogBoxContent.style.height = _conf.maxHeight + "px";
				DialogBoxContent.style.overflowY = "scroll";
			}else{
				DialogBoxContent.style.height = "";
				DialogBoxContent.style.overflowY = "hidden";
			}
			DialogOuterBox.style.width = "";
			if(DialogBoxContent.children[0]){
				DialogOuterBox.style.width = DialogBoxContent.children[0].offsetWidth + 20 + "px";
			}else{
				DialogOuterBox.style.width = _conf.width + 20 + "px";
			}
			_setDialogPosition();
			_isParamsChange = true;
			return api;
		}

		/**
		 * 设置对话框位置
		 */
		function setPosition(x,y,r,b){
			if(x){
				_conf.left = x;	
				DialogOuterBox.style.left = x + "px";
			}
			if(y){
				_conf.top = y;
				DialogOuterBox.style.top = y + "px";
			}
			if(r){
				_conf.right = r;
				DialogOuterBox.style.right = r + "px";
			}
			if(b){
				_conf.bottom = b;
				DialogOuterBox.style.bottom = b + "px";	
			}
			return api;
		}
		
        function setRelatePosition(position){       	
        	if(position.left !== ""){
				DialogOuterBox.style.left = position.left + "px";
				DialogOuterBox.style.right = "";
				_conf.left = position.left;
			}
        	if(position.right !== ""){
				DialogOuterBox.style.right = position.right + "px";
				DialogOuterBox.style.left = "";
				_conf.right = position.right;
			}

			if(position.top !== ""){
				DialogOuterBox.style.top = position.top + "px";
				DialogOuterBox.style.bottom = "";
				_conf.top = position.top;
			} 
			if(position.bottom !== ""){
				DialogOuterBox.style.bottom = position.bottom + "px";
				DialogOuterBox.style.top = "";
				_conf.bottom = position.bottom;
			}   
			return api;
        }
		
		
		
		/**
		 * 设置自动居中格式
		 */
		function setAutoPosition(){
			_conf.left = "";
			_conf.right = "";
			_conf.top = "";
			_conf.bottom = "";
			_setDialogPosition();
		}

		/**
		 * 获取对话框的宽度
		 */
		function getWidth(){
			return DialogOuterBox.clientWidth;
		}

		/**
		 * 获取对话框的高度
		 */
		function getHeight(){
			return DialogOuterBox.clientHeight;
		}

		/**
		 * 最小化对话框 
		 */
		function minimizeDialog(){
			_hideDialogContent();
			_hideDialogFooter();
			_hideMinimizeBtn();
			_showMaximizeBtn();
		}

		/**
		 * 最大化对话框
		 */
		function maximizeDialog(){
			_showDialogContent();
			_showDialogFooter();
			_showMinimizeBtn();
			_hideMaximizeBtn();
		}

		return api;
	}
});