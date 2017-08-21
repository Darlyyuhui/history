<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div style="width:500px;margin:0;padding:0;border:0px;overflow:hidden;">
  <table class="commonResultTable">
    <tr class="commonResultTableTitleRow">
      <td style="text-align:left;">所属部门： <tags:xiangxuncache keyName="Department" id="${videoInfo.orgId }"></tags:xiangxuncache></td>
      <td style="text-align:left;">监控类型： <tags:xiangxuncache keyName="Dic" typeCode="monittype" id="${videoInfo.videotypeCode }"></tags:xiangxuncache></td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;">建设厂家： <tags:xiangxuncache keyName="DeviceCompanyInfo" id="${videoInfo.companyid }"></tags:xiangxuncache></td>
      <td style="text-align:left;">监控方向： <tags:xiangxuncache keyName="Dic" typeCode="direction" id="${videoInfo.directionCode}" ></tags:xiangxuncache></td>
    </tr>
    <tr class="commonResultTableTitleRow">
      <td style="text-align:left;">设备编号： ${videoInfo.code}</td>
      <td style="text-align:left;">所在道路： <tags:xiangxuncache keyName="RoadInfo" id="${videoInfo.roadinfoId }"></tags:xiangxuncache></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align:left;">
      	<iframe src="forward/map/mapTools/video/dialog/?videoCode=${videoInfo.code }&videoWidth=500&videoHeight=300" frameborder="0" scrolling="no" style="width:500px;height:300px;padding:0;margin:0;"></iframe>
      </td>
    </tr>
  </table>
</div>