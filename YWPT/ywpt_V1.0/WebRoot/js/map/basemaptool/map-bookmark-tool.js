var bookmarkWidget = (function(){
	return function(){
		function showbookmarksTool(){
			var bookmarksDialog = new itmap.util.dialog();
			bookmarksDialog.conf({
				title:'书签',
				content:'<div id="bookmarksDIV"  style="width: 240px !important;"></div>',
				mask:false,
				zindex:100,
				right : 250,
				top : 50,
				closeCall : bookmarksDestroy,
				confirmButton :"删除所有书签",
				confirmButtonCall : clearBookmarks
			});
			bookmarksDialog.show();
			initbookmark();
		}

		function bookmarksDestroy(){
			bookmark.destroy();	     
		}

		//建立书签函数
		var bookmark,storageName = 'esrijsapi_mapmarks',useLocalStorage = supports_local_storage();	
		function initbookmark() {
			// 监听点击和删除 cookie
			// dojo.connect(dojo.byId('clear-storage'), 'onclick', clearBookmarks);
			// 创建书签控件
			bookmark = new esri.dijit.Bookmarks({
				map: itmap.arcgis.map,
				bookmarks: [],
				editable: true
			}, dojo.byId('bookmarksDIV'));

			// 把新书签保存在local storage中, 返回cookie
			dojo.connect(bookmark, 'onEdit', refreshBookmarks);
			dojo.connect(bookmark, 'onRemove', refreshBookmarks);

			// 查询本地保存的书签
			if (useLocalStorage) {
				var bmJSON = window.localStorage.getItem(storageName);
			} else {
				var bmJSON = dojo.cookie(storageName);
			}
			// 载入书签 ，当没有cookie是载入一个默认的西安市书签
			if (bmJSON && bmJSON != 'null' && bmJSON.length > 4) {
				var bmarks = dojo.fromJson(bmJSON);
				dojo.forEach(bmarks, function (b) {
					bookmark.addBookmark(b);
				});
			} else {
				var bookmarkPA = {
					"extent": {
						"spatialReference": {
							"wkid": 4326
						},
						"xmin": 108.77239489417006,
						"ymin": 34.2140827594021,
						"xmax": 109.0796427965479,
						"ymax": 34.3642235976401
					},
					"name": "西安市"
				}
				bookmark.addBookmark(bookmarkPA);
			}
		}
		
		function refreshBookmarks() {
			if (useLocalStorage) {
				window.localStorage.setItem(storageName, dojo.toJson(bookmark.toJson()));
			} else {
				var exp = 7; //  一个cookie本保留7天
				dojo.cookie(storageName, dojo.toJson(bookmark.toJson()), {
					expires: exp
				});
			}
		}
		
		function clearBookmarks() {
			var conf = confirm('是否删除所有书签？');
			if (conf) {
				if (useLocalStorage) {
		          // 从localStorage中删除书签
					window.localStorage.removeItem(storageName);
				} else {
		          // 删除 cookie
					dojo.cookie(storageName, null, { expires: -1 });
				}
				// 删除所有用户添加的书签
				//首先得到所有书签的名称
				var bmNames = dojo.map(bookmark.bookmarks, function (bm) {
					if (bm.name != '西安市') {
						return bm.name;
					}
				});
				// 调用removeBookmark函数根据name删除书签
				dojo.forEach(bmNames, function (bName) {
					bookmark.removeBookmark(bName);
				});
				alert('所有书签被删除！');
			}
		}
		
		// 支持local_storage数据源函数 
		// http://diveintohtml5.org/detect.html
		function supports_local_storage() {
			try {
				return 'localStorage' in window && window['localStorage'] !== null;
			} catch (e) {
				return false;
			}
		}
		
		return {showbookmarks:showbookmarksTool};
	}
})();