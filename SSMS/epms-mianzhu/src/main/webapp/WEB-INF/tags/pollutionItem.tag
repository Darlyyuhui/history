<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="polIndex" required="true" type="java.lang.String"%>
<%@ attribute name="polElementId" required="true" type="java.lang.String"%>
<style>

.tag-pollution-color {
	margin: 0px auto;
	width: 70px;
	height: 45px;
	background-color: white;
	border: white 5px solid;
	float: left;
	line-height: 35px;
}

#tag-pollution-label {
	width: 60px;
	height: 35px;
	float: left;
	line-height: 35px;
	text-align: center;
}

</style>

<div>
	<div id="${polElementId}" class="tag-pollution-color"></div>
	<div  id="tag-pollution-label">${polIndex}</div>
</div>
<script type="text/javascript">

    	   var polColor;
    	   var colorDiv=this.document.getElementById('${polElementId}');
    	   polValue='${polIndex}'.replace("%","");
    	   polValue=parseFloat(polValue);
    	   polValue=polValue/100;
    		   if(polValue<=0.25){
    			   polColor="#104E8B";
    		   }else if(polValue<=0.5){
    			   polColor="#FFD700";
    		   }else if(polValue<=0.75){
    			   polColor="#FF7F00";
    		   }else{
    			   polColor="#EE0000";
    		   }
    		colorDiv.style.backgroundColor=polColor; 
     
</script>
