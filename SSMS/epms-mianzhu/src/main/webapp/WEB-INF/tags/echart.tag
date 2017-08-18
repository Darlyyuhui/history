<%@ tag import="java.util.Random"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="divId" type="java.lang.String" required="true"%>
<%@ attribute name="option" type="java.lang.String" required="true"%>
<%@ attribute name="theme" type="java.lang.String" required="false"%>
<%
	String time = System.nanoTime()+"";
	int random = Integer.parseInt(time.substring(time.length()-3));
	Random r = new Random();
	int num = r.nextInt(random);
	String charName = "chart"+num;
	String charOptName = "chartOpt"+num;
%>
<script type="text/javascript">
var <%=charName%>;
if ("${theme}" == "") {
	<%=charName%> = echarts.init(document.getElementById("${divId}"));
} else {
	<%=charName%> = echarts.init(document.getElementById("${divId}"), "${theme}");
}
var <%=charOptName%> = ${option};
<%=charName%>.setOption(<%=charOptName%>);
</script>