$(function() {
			$(".a_part").click(function() {
						if ($(this).next("ul").css("display") == "block") {
							$(this).css("background",
									"url(img/nav_head_bg.png)");
							$(this).next("ul").hide(200);
						} else {
							$(this).css("background",
									"url(img/nav_head_bg_h.png)");
							$(this).next("ul").show(400);
						}
					});
		})

// 单行滚动最新消息
function scroll_news() {

	var firstNode = $('.scroll-container li');

	firstNode.eq(0).fadeOut('fast', function() {
				$(this).clone().appendTo($(this).parent()).show('normal');
				$(this).remove();
			});
}