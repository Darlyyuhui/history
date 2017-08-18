/***
 * 表格全选
 * @param {} a 全选input
 * @param {} tableid  所在表格的id
 */
function selectAll(a){
   var checked = $('#selectAll').attr("checked");
   $("#table :checkbox").each(function(){
   	    if((checked=="checked") || checked){
  			$(this).attr("checked",checked);
   	    }else{
   	    	$(this).removeAttr("checked");
   	    }
   });
}

/****
 * 获得所有选中的checkbox的值
 * 
 * @return {}
 */
function getSelectedValue() {
	var checkeds = [];
	var checks = $("#table :checkbox:checked");
	for (var i = 0; i < checks.length; i++) {
		var sigle = checks[i];
		var v = $(sigle).val();
		if (v != "-1") {
			checkeds.push(v);
		}
	}
	checkeds.join(",");
	return checkeds;
}

/***
 * 关闭提示层
 */    
function closeAlert(){
	 $("#alert-div").hide("slow");
}

/***
 * 关闭提示层
 */    
function closesucAlert(){
	 $("#alertsuc-div").hide("slow");
}

/***
 * 显示提示信息
 * @param {} msg
 */
function showMessage(msg){
	$("#alert-content").html("").html(msg);
	$("#alert-div").show();
	setTimeout('closeAlert()',1300);
}

/***
 * 显示提示信息
 * @param {} msg
 */
function showSucMessage(msg){
	$("#alertsuc-content").html("").html(msg);
	$("#alertsuc-div").show();
	setTimeout('closesucAlert()',1300);
}

/***
 * 行单击事件
 */
function rowOnclick(tr){
}

function resize(){


}

$(document).ready(function(){
	$("#alert-div").hide();
	resize();

    $('input, textarea').placeholder();

});

$("#table tbody tr").click(function (e) {
	if ($(e.target).attr("type") != "checkbox") {
		$(this).find(":checkbox").attr("checked", !$(this).find(":checkbox").attr("checked"));
	}
});

function killErrors() {
	return true;
}
//window.onerror = killErrors;


$(document).on('click', 'th input:checkbox' , function(){
    var that = this;
    $(this).closest('table').find('tr > td:first-child input:checkbox')
        .each(function(){
            this.checked = that.checked;
            $(this).closest('tr').toggleClass('selected');
        });
});

//重置所有查询框
function resetSearchValue() {
	$("input[name^='search_'],textarea[name^='search_'],select[name^='search_']").each(function(){
		this.value = "";
	});
}





