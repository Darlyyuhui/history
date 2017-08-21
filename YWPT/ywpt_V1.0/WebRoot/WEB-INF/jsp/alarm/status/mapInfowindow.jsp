<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="width: 300px; margin: 0px; padding: 0px; border: 0px;">
	<table class="commonResultTable" style="width: 300px; float: left;">

		<tr class="commonResultTableEvenRow">
			<td style="text-align: left;" width="75">
				资产名称：
			</td>
			<td style="text-align: left;" style="width:210px;">
				<span id="devicename">${info.assetname }</span>
			</td>
		</tr>
		<tr class="commonResultTableOddRow">
			<td style="text-align: left;">
				资产编号：
			</td>
			<td style="text-align: left;">
				${info.assetcode }
			</td>
		</tr>

		<tr class="commonResultTableEvenRow">
			<td style="text-align: left;" width="75">
				资产IP：
			</td>
			<td style="text-align: left;" style="width:210px;">
				<span id="devicename">${info.ip }</span>
			</td>
		</tr>
		<tr class="commonResultTableOddRow">
			<td style="text-align: left;">
				资产状态：
			</td>
			<td style="text-align: left;">
				<tags:xiangxuncache keyName="Dic" typeCode="assetstatus"
					id="${info.assetstatus }"></tags:xiangxuncache>
			</td>
		</tr>

		<tr class="commonResultTableEvenRow">
			<td style="text-align: left;" width="75">
				连接状态：
			</td>
			<td style="text-align: left;" style="width:210px;">
				<span id="devicename">
				<c:if test="${info.netStatus=='1'}">
				  正常
				</c:if>
				<c:if test="${info.netStatus!='1'}">
				  异常
				</c:if>
				</span>
			</td>
		</tr>
		<tr class="commonResultTableOddRow">
			<td style="text-align: left;">
				运行状态：
			</td>
			<td style="text-align: left;">
				<c:if test="${info.dataStatus=='1'}">
				  正常
				</c:if>
				<c:if test="${info.dataStatus!='1'}">
				  异常
				</c:if>
			</td>
		</tr>

		<tr class="commonResultTableEvenRow">
			<td style="text-align: left;" width="75">
				服务厂商：
			</td>
			<td style="text-align: left;" style="width:210px;">
				<span id="devicename"><tags:xiangxuncache
						keyName="FactoryInfo" id="${info.serviceid }"></tags:xiangxuncache>
				</span>
			</td>
		</tr>
	</table>
</div>
