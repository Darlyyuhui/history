<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<style>

.mian_white tr td{
  padding:3px 1px;
  border: #000 1px solid;
}
.mian_white tr th{
  padding:3px 1px;
  border: #000 1px solid;
}

.divContent{
 margin: 10px;
}


</style>

  <div class="mian_none divContent">
                    <table class="width-100 text-center mian_white">
                        <thead class="text-center">
                        <tr>
                            <th class="text-center">项目名称</th>
                            <th class="text-center">修复面积</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                         <c:forEach items="${rp}" var="item" varStatus="m">
							<tr>
								<td>${item.name}</td>
								<td>${item.area }</td>
								<td style="cursor:pointer;"><a href="<%=request.getContextPath() %>/repair/project/showProcess/${item.id}/">施工详情</a></td>
							</tr>
						</c:forEach>
                        </tbody>
                    </table>
                </div>