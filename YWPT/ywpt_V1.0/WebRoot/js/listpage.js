(function($) {
	$(function() {
		$(document).ready(function() {
			function changeColor(obj) {
				if (obj.checked == true) {
					obj.parentElement.parentElement.style.backgroundColor = '#FFFFE1';
				} else {
					var oldColor = "";
					var rowIndex = obj.parentElement.parentElement.rowIndex;
					if (rowIndex % 2 == 0) {
						oldColor = "#f5f5f5";
					} else {
						oldColor = "#f9f9f9";
					}
					obj.parentElement.parentElement.style.backgroundColor = oldColor;
				}
			}

			function showtable() {
				var overcolor = '#FFFFE1'; // 鼠标经过颜色
				var color1 = '#f5f5f5'; // 第一种颜色
				var color2 = '#f9f9f9'; // 第二种颜色
				var tr = $("#table tr");

				for (var i = 1; i < tr.length; i++) {
					tr[i].onmouseover = function() {
						this.style.backgroundColor = overcolor;
					}
					tr[i].onmouseout = function() {
						if (this.cells[0].children.length > 0) {
							var check = this.cells[0].children[0];
							if (check.checked == true) {
								this.style.backgroundColor = overcolor;
							} else {
								if (this.rowIndex % 2 == 0) {
									this.style.backgroundColor = color1;
								} else {
									this.style.backgroundColor = color2;
								}

							}
						} else {
							if (this.rowIndex % 2 == 0) {
								this.style.backgroundColor = color1;
							} else {
								this.style.backgroundColor = color2;
							}
						}

					}
					if (i % 2 == 0) {
						tr[i].className = "color1";
					} else {
						tr[i].className = "color2";
					}
				}
			}

			showtable();
		});
	});
})(jQuery);
