<%@ page language="java"  pageEncoding="utf-8"%>
<script>
	var index = location.href.indexOf("error");
	if(index!=-1){
		window.top.location.href="${root}/login?error=true";
	}else{
		window.top.location.href="${root}/login";
	}
</script>