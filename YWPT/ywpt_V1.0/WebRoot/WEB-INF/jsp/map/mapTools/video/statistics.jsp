<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
MapFactory.Require([
	"ItmsMap/Video/Video*"
],function(Video){
	Video().statistic("videoStatistics");
});
</script>
<div id="videoStatistics"></div>
