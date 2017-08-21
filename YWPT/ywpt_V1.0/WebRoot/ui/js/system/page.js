var prepages;
var nxtpages;
$(document).ready(function (){
	prepages=$("#prepage").val();
	nxtpages=$("#nxtpage").val();
});
function setPage(currentPage,pageCount,leftPagemore,rightPagemore,prePage,nxtPage){
	var str1="";
	if(1==pageCount){
		str1 +="<span><a class='paginate_current'>"+currentPage+"</a></span>";
	}else if(0==pageCount){
		
	}else if(10>pageCount){
		if(1==currentPage){
			str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
			for(var m=2;m<=pageCount;m++){
				str1 +="<a class='paginate' name='"+m+"' onclick= 'gotoPage(this)'>"+m+"</a>";
			}
			str1 +="</span><a class='page_next' name='"+nxtPage+"' onclick= 'gotoPage(this)'>"+nxtpages+"</a>";
		}else if(currentPage==pageCount){
			str1 +="<a class='page_previous' name='"+prePage+"' onclick= 'gotoPage(this)'>"+prepages+"</a><span>";
			for(var m=1;m<pageCount;m++){
				str1 +="<a class='paginate' name='"+m+"' onclick= 'gotoPage(this)'>"+m+"</a>";
			}
			str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
		}else{
			str1 +="<a class='page_previous' name='"+prePage+"' onclick= 'gotoPage(this)'>"+prepages+"</a><span>";
			for(var m=1;m<=pageCount;m++){
				if(m==currentPage){
					str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
				}else{
					str1 +="<a class='paginate' name='"+m+"' onclick= 'gotoPage(this)'>"+m+"</a>";
				}
			}
			str1 +="</span><a class='page_next' name='"+nxtPage+"' onclick= 'gotoPage(this)'>"+nxtpages+"</a>";
		}
	}else{
		if(1==currentPage){
			str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
			for(var m=2;m<=5;m++){
				str1 +="<a class='paginate' name='"+m+"' onclick= 'gotoPage(this)'>"+m+"</a>";
			}
			str1 +="<a class='paginate' name='"+rightPagemore+"' onclick= 'gotoPage(this)'>...</a>";
			str1 +="<a class='paginate' name='"+pageCount+"' onclick= 'gotoPage(this)'>"+pageCount+"</a>";
			str1 +="</span><a class='page_next' name='"+nxtPage+"' onclick= 'gotoPage(this)'>"+nxtpages+"</a>";
		}else if(currentPage==pageCount){
			str1 +="<a class='page_previous' name='"+prePage+"' onclick= 'gotoPage(this)'>"+prepages+"</a><span>";
			str1 +="<a class='paginate' name='1' onclick= 'gotoPage(this)'>1</a>";
			str1 +="<a class='paginate' name='"+leftPagemore+"' onclick= 'gotoPage(this)'>...</a>";
			for(var m=pageCount-4;m<pageCount;m++){
				str1 +="<a class='paginate' name='"+m+"' onclick= 'gotoPage(this)'>"+m+"</a>";
			}
			str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
		}else if(currentPage<5){
			str1 +="<a class='page_previous' name='"+prePage+"' onclick= 'gotoPage(this)'>"+prepages+"</a><span>";
			for(var m=1;m<=5;m++){
				if(m==currentPage){
					str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
				}else{
					str1 +="<a class='paginate' name='"+m+"' onclick= 'gotoPage(this)'>"+m+"</a>";
				}
			}
			str1 +="<a class='paginate' name='"+rightPagemore+"' onclick= 'gotoPage(this)'>...</a>";
			str1 +="<a class='paginate' name='"+pageCount+"' onclick= 'gotoPage(this)'>"+pageCount+"</a>";
			str1 +="</span><a class='page_next' name='"+nxtPage+"' onclick= 'gotoPage(this)'>"+nxtpages+"</a>";
		}else if(currentPage>(pageCount-4)){
			str1 +="<a class='page_previous' name='"+prePage+"' onclick= 'gotoPage(this)'>"+prepages+"</a><span>";
			str1 +="<a class='paginate' name='1' onclick= 'gotoPage(this)'>1</a>";
			str1 +="<a class='paginate' name='"+leftPagemore+"' onclick= 'gotoPage(this)'>...</a>";
			for(var m=pageCount-4;m<=pageCount;m++){
				if(m==currentPage){
					str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
				}else{
					str1 +="<a class='paginate' name='"+m+"' onclick= 'gotoPage(this)'>"+m+"</a>";
				}
			}
			str1 +="</span><a class='page_next' name='"+nxtPage+"' onclick= 'gotoPage(this)'>"+nxtpages+"</a>";
		}else{
			str1 +="<a class='page_previous' name='"+prePage+"' onclick= 'gotoPage(this)'>"+prepages+"</a><span>";
			str1 +="<a class='paginate' name='1' onclick= 'gotoPage(this)'>1</a>";
			str1 +="<a class='paginate' name='"+leftPagemore+"' onclick= 'gotoPage(this)'>...</a>";
			str1 +="<a class='paginate' name='"+prePage+"' onclick= 'gotoPage(this)'>"+prePage+"</a>";
			str1 +="<span><a class='paginate_current'>"+currentPage+"</a>";
			str1 +="<a class='paginate' name='"+nxtPage+"' onclick= 'gotoPage(this)'>"+nxtPage+"</a>";
			str1 +="<a class='paginate' name='"+rightPagemore+"' onclick= 'gotoPage(this)'>...</a>";
			str1 +="<a class='paginate' name='"+pageCount+"' onclick= 'gotoPage(this)'>"+pageCount+"</a>";
			str1 +="</span><a class='page_next' name='"+nxtPage+"' onclick= 'gotoPage(this)'>"+nxtpages+"</a>";
		}
	}
	return str1;
}