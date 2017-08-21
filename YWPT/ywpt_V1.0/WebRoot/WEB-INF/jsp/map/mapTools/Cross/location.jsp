<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	MapFactory.Require(
			["ItmsMap/Cross/Cross*"],
			function(cross){
				var api = cross();
				api.showAll();
				document.getElementById("crossLocation").onclick = function() {
					api.crossLocation(document.getElementById("crossLocationName").value);
				};
			});	
</script>
<table class="map_table mar_5" style="margin-right:-20px\0;#margin-right:-20px;_margin-right:-20px;">
  <tr>
    <td width="64">卡口名称：</td>
    <td><input id="crossLocationName" type="text" style="width:100px;height:28px;padding:4px;overflow:hidden"></td>
    <td><input id="crossLocation" type="button" class="btn btn-info" value="定位"></td>
  </tr>
</table>
