/**
 * 分页器
 * @author ZLT
 * @since 2014-08-21
 */
MapFactory.Define("ItmsMap/Util/Pager*",function(){
	return function(conf){

		/**
		 * 配置信息
		 * @param parentSrc String 放置分页器的容器
		 * @param current int 当前页面
		 * @param pageSize int 每一页显示个数
		 * @param totalNum int 数据总数
		 * @param clickEvt Function 点击事件
		 * @param pageOffset int 页码左右偏移量
		 */
		var _conf = {
			parentSrc : "",
			currentPage : 1,
			pageSize : 10,
			totalNum : 1,
			clickEvt : function(){},
			pageOffset : 5,
			enableJump : false,
			enableStatistic : false
		};

		MapFactory.Extend(_conf,conf);

		var _dom = MapFactory.Dom;

		var _events = MapFactory.Events;

		var _pageNum = 0;

		var _currentPage = 1;

		var _pageSize = 10;

		var _pagerContainer = null;

		var _btnSelected = "selected";

		var _btnHover = "hover";

		var _disableSelect = "disableSelect";

		var _prePageBtn = null;

		var _nextPageBtn = null;

		var _pageOffset = 5;

		var _parentContainer = null;

		var _attachDiv = null;

		function _init(){
			if(!_conf.parentSrc){
				return;
			}
			_parentContainer = _dom.getById(_conf.parentSrc);
			if(!_parentContainer){
				return;
			}
			_currentPage = _conf.currentPage;
			_pageSize = _conf.pageSize;
			_currentPage = _conf.currentPage;
			_pageOffset = _conf.pageOffset;
			_pageNum = Math.ceil(_conf.totalNum / _pageSize);
			if(_pageNum < 1){
				_pageNum = 1;
			}
			if(_currentPage > _pageNum){
				_currentPage = 1;
			}
			_setPagerContainer();
			_dom.append(_dom.getById(_conf.parentSrc),_pagerContainer);

			_setPageNumBtn();

			if(_conf.enableJump){
				_setJumpBox();
			}

			if(_conf.enableStatistic){
				_setStatisticBox();
			}
		}
		_init();

		function _setPagerContainer(){
			_pagerContainer = _dom.createElem("ul");
			_pagerContainer.id = "resultPager";
			_dom.addClass(_pagerContainer,"resultPager");
		}

		function _setPageNumBtn(){
			_dom.empty(_pagerContainer);
			_addPrePageBtn();
			
			if(_conf.enableJump && _conf.enableStatistic) {
				var _pageBtn = _dom.createElem("li");
				_dom.html(_pageBtn,"第" + _currentPage + "/" + _pageNum + "页");
				_dom.addClass(_pageBtn,"pagerNumClass showPagerNumClass");
				_dom.append(_pagerContainer,_pageBtn);
			}else {
				if(_pageNum > _pageOffset * 2){
					var _startPage;
					if(_currentPage + _pageOffset > _pageNum){
						_startPage = _pageNum - _pageOffset * 2;
						_addPageNumBtn(_startPage,_pageNum);
					}else if(_currentPage == _pageNum){
						_startPage = _currentPage - _pageOffset * 2;
						_addPageNumBtn(_startPage,_currentPage);
					}else{
						_startPage = _currentPage - _pageOffset > 0 ? _currentPage - _pageOffset : 1;
						_addPageNumBtn(_startPage,_startPage + _pageOffset * 2);
					}
				}else{
					_addPageNumBtn(1,_pageNum);
				}
			}
			
			_addNextPageBtn();
		}

		function _addPageNumBtn(start,end){
			var _pageBtn;
			if(start > 1){
				_pageBtn = _dom.createElem("li");
				_dom.html(_pageBtn,"...");
				_bindHoverEvt(_pageBtn);
				_dom.addClass(_pageBtn,"pagerNumClass");
				_dom.append(_pagerContainer,_pageBtn);
				_pageBtn.onclick = function(){
					_setPage(1);
				}
			}
			for(var i=start;i<=end;i++){
				_pageBtn = _dom.createElem("li");
				_dom.html(_pageBtn,i);
				if(i == _currentPage){
					_dom.addClass(_pageBtn,_btnSelected);
				}
				_bindHoverEvt(_pageBtn);
				_bindPageBtnClickEvt(_pageBtn);
				_dom.addClass(_pageBtn,"pagerNumClass");
				_dom.append(_pagerContainer,_pageBtn);
			}
			if(end < _pageNum){
				_pageBtn = _dom.createElem("li");
				_dom.html(_pageBtn,"...");
				_bindHoverEvt(_pageBtn);
				_dom.addClass(_pageBtn,"pagerNumClass");
				_dom.append(_pagerContainer,_pageBtn);
				_pageBtn.onclick = function(){
					_setPage(_pageNum);
				}
			}
		}

		function _addPrePageBtn(){
			_prePageBtn = _dom.createElem("li");
			_dom.html(_prePageBtn,"上一页");
			_bindHoverEvt(_prePageBtn);
			_bindPreBtnClickEvt(_prePageBtn);
			_dom.addClass(_prePageBtn,"pagerNumClass");
			_dom.append(_pagerContainer,_prePageBtn);

			if(_currentPage <= 1){
				_disablePreBtn();
			}
		}

		function _addNextPageBtn(){
			_nextPageBtn = _dom.createElem("li");
			_dom.html(_nextPageBtn,"下一页");
			_bindHoverEvt(_nextPageBtn);
			_bindNextBtnClickEvt(_nextPageBtn);
			_dom.addClass(_nextPageBtn,"pagerNumClass");
			_dom.append(_pagerContainer,_nextPageBtn);

			if(_currentPage >= _pageNum){
				_disableNextBtn();
			}
		}

		function _bindHoverEvt(elem){
			elem.onmouseover = function(){
				_dom.addClass(this,_btnHover);
			}
			elem.onmouseout = function(){
				_dom.removeClass(this,_btnHover);
			}
		}

		function _bindPageBtnClickEvt(elem){
			elem.onclick = function(){
				var _page = parseInt(_dom.html(elem));
				_setPage(_page);
			}
		}

		function _bindPreBtnClickEvt(elem){
			elem.onclick = function(){
				_setPage(--_currentPage);
			}
		}

		function _bindNextBtnClickEvt(elem){
			elem.onclick = function(){
				_setPage(++_currentPage);
			}
		}

		function _enablePreBtn(){
			if(_dom.hasClass(_prePageBtn,_disableSelect)){
				_dom.removeClass(_prePageBtn,_disableSelect);
				_bindPreBtnClickEvt(_prePageBtn);
				_bindHoverEvt(_prePageBtn);
			}
		}

		function _enableNextBtn(){
			if(_dom.hasClass(_nextPageBtn,_disableSelect)){
				_dom.removeClass(_nextPageBtn,_disableSelect);
				_bindNextBtnClickEvt(_nextPageBtn);
				_bindHoverEvt(_nextPageBtn);
			}
		}

		function _disablePreBtn(){
			_prePageBtn.onclick = function(){};
			_prePageBtn.onmouseover = function(){};
			_prePageBtn.onmouseout = function(){};
			if(_dom.hasClass(_prePageBtn,_btnHover)){
				_dom.removeClass(_prePageBtn,_btnHover);
			}
			_dom.addClass(_prePageBtn,_disableSelect);
		}

		function _disableNextBtn(){
			_nextPageBtn.onclick = function(){};
			_nextPageBtn.onmouseover = function(){};
			_nextPageBtn.onmouseout = function(){};
			if(_dom.hasClass(_nextPageBtn,_btnHover)){
				_dom.removeClass(_nextPageBtn,_btnHover);
			}
			_dom.addClass(_nextPageBtn,_disableSelect);
		}

		function _setPage(page){
			var _children = _dom.children(_pagerContainer);
			for(var i=0,len=_children.length;i<len;i++){
				var _child = _children[i];
				if(_dom.hasClass(_child,_btnSelected)){
					_dom.removeClass(_child,_btnSelected);
				}
				if(page == i){
					_dom.addClass(_child,_btnSelected);
				}
			}

			_currentPage = page;

			_setPageNumBtn();

			if(_currentPage <= 1){
				_disablePreBtn();
			}else{
				_enablePreBtn();
			}

			if(_currentPage >= _pageNum){
				_disableNextBtn();
			}else{
				_enableNextBtn();
			}

			_conf.clickEvt({
				currentPage : _currentPage
			});
		}
		
		function _setAttachBox(){
			_attachDiv = _dom.createElem("div");
			_dom.append(_parentContainer,_attachDiv);
			_dom.addClass(_attachDiv,"pagerAttachBox");
		}

		function _setJumpBox(){
			if(!_attachDiv){
				_setAttachBox();
			}
			var _html = "<input id='pagerNumInputBox' class='pagerNumInputBox' type='text' style='float:left;width:60px;height:18px;line-height:18px;' />" +
					"<span id='pagerJumpBtn' class='pagerNumClass' style=''>跳转</div>";
			var _jumpBox = _dom.createElem("div");
			var _inputBox = _dom.createElem("input");
			var _jumpBtn = _dom.createElem("input");
			_dom.attr(_inputBox,"type","text");
			_dom.attr(_inputBox,"id","pagerNumInputBox");
			_dom.addClass(_inputBox,"pagerNumInputBox");
			_dom.addClass(_jumpBox,"pagerJumpBox");
			_dom.attr(_jumpBtn,"value","跳转");
			_dom.attr(_jumpBtn,"type","button");

			//_dom.append(_jumpBox,_inputBox);
			//_dom.append(_jumpBox,_jumpBtn);
			_dom.html(_jumpBox,_html);
			_dom.append(_attachDiv,_jumpBox);
			_events.bindEvent(_dom.getById("pagerJumpBtn"),"click",function(){
				var _page = _dom.attr(_dom.getById("pagerNumInputBox"),"value");
				if(!_page){
					return;
				}
				_page = parseInt(_page);
				if(!_page || (_page > _pageNum || _page <= 0)){
					return;
				}
				_setPage(_page);
			});
		}

		function _setStatisticBox(){
			if(!_attachDiv){
				_setAttachBox();
			}
			var _statisticBox = _dom.createElem("span");
			_dom.html(_statisticBox,"共"+ _conf.totalNum +"条");
			_dom.addClass(_statisticBox,"pagerStatisticBox");
			_dom.append(_attachDiv,_statisticBox);
		}
	}
});