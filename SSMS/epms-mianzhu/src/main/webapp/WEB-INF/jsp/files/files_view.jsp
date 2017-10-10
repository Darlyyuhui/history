<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script src="${root}/compnents/ace/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="${root}/compnents/jquery-adGallery/jquery.ad-gallery.css" />
<script src="${root}/compnents/jquery-adGallery/jquery.ad-gallery.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	var galleries = $('.ad-gallery').adGallery({
        width:${width},
        height:${height}
    });
	$('#switch-effect').change(function() {
		galleries[0].settings.effect = $(this).val();
		return false;
	});
	$('#toggle-slideshow').click(function() {
		galleries[0].slideshow.toggle();
		return false;
	});
	$('#toggle-description').click(function() {
		if (!galleries[0].settings.description_wrapper) {
			galleries[0].settings.description_wrapper = $('#descriptions');
		} else {
			galleries[0].settings.description_wrapper = false;
		}
		return false;
	});
});
</script>
<c:if test="${fileList == null || fn:length(fileList) == 0 }">
	<h5>无附件</h5>
</c:if>
<c:if test="${fileList != null && fn:length(fileList) > 0 }">
	<center>
		<div id="gallery" class="ad-gallery">
			<div class="ad-image-wrapper"></div>
			<div class="ad-controls"></div>
			<div class="ad-nav">
				<div class="ad-thumbs">
					<ul class="ad-thumb-list">
						<c:forEach items="${fileList }" var="item" varStatus="vs">
							<li>
								<p style="width:100px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">
								${item.fileName }
								</p>
								<c:if test="${item.fileType eq '1'}">
									<c:if test="${item.fileExists eq true }">
										<a href="${root }/${item.filePath }"><img
											src="${root }/${item.filePath }" width="100" height="100"
											title="${item.fileName }" ondblclick="showWindowImg('${item.fileName }','${root }/${item.filePath }')"></a>
									</c:if>
									<c:if test="${item.fileExists eq false }">
										<a href="${root }/images/files/error.png"><img
											src="${root }/images/files/error.png" width="100" height="100"
											title="${item.fileName }"></a>
									</c:if>
								</c:if>
								<c:if test="${item.fileType eq '2'}">
									<a href="${root }/images/files/doc.png"><img
										src="${root }/images/files/doc.png" width="100" height="100"
										onclick="onlineView('${item.id }')" title="${item.fileName }"></a>
								</c:if>
								<c:if test="${item.fileType eq '3'}">
									<a href="${root }/images/files/xls.png"><img
										src="${root }/images/files/xls.png" width="100" height="100"
										onclick="onlineView('${item.id }')" title="${item.fileName }"></a>
								</c:if>
								<c:if test="${item.fileType eq '4'}">
									<a href="${root }/images/files/ppt.png"><img
										src="${root }/images/files/ppt.png" width="100" height="100"
										onclick="onlineView('${item.id }')" title="${item.fileName }"></a>
								</c:if>
								<c:if test="${item.fileType eq '5'}">
									<a href="${root }/images/files/pdf.png"><img
										src="${root }/images/files/pdf.png" width="100" height="100"
										onclick="onlineView('${item.id }')" title="${item.fileName }"></a>
								</c:if>
								<c:if test="${item.fileType eq '6'}">
									<a href="${root }/images/files/txt.png"><img
										src="${root }/images/files/txt.png" width="100" height="100"
										onclick="onlineView('${item.id }')" title="${item.fileName }"></a>
								</c:if>
								<c:if test="${item.fileType eq '8'}">
									<a href="${root }/images/files/mp4.png"><img
										src="${root }/images/files/mp4.png" width="100" height="100"
										onclick="onlineVideo('${item.id }')" title="${item.fileName }"></a>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</center>
</c:if>