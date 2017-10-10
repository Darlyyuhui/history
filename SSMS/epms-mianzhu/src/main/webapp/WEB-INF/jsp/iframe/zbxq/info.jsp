<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<style>
.mian_white {
	background: #FFF;
	color: #000;
	margin-left: 5px;
	 border: #000 1px solid;
}
.mian_white tr td{
  padding:3px 1px;
  border: #000 1px solid;
}
.mian_white tr th{
  padding:3px 1px;
  border: #000 1px solid;
}
.apb-TabMargin{
  padding-top: 0px;
  height: 100%;
  padding-bottom: 5px;
}
.apb-td-word-size{
  font-weight: bold;
  width: 30%;
}
.divContent{
 margin: 10px;
}
.ststicitemlabel{
 font-size: 14px;
 font-weight: bold;
}
.regTotalLabel{
  font-size: 18px;
}
</style>

 <div class="mian_none divContent">
                    <div class="ststicitemlabel">全市土壤普查采样点:&nbsp;&nbsp;<span class="regTotalLabel">${lc.regTotal}个</span></div>
                    <div class="ststicitemlabel">全市土壤样品分析情况:</div>
                    <table class="width-100 text-center mian_white">
                        <thead class="text-center">
                        <tr>
                            <th class="text-center">指标项</th>
                            <th class="text-center">最小值</th>
                            <th class="text-center">平均值</th>
                            <th class="text-center">最大值</th>
                            <th class="text-center">标准差</th>
                            <th class="text-center">变异系数</th>
                            <th class="text-center">超标率</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="region-lcLevel">ph</td>
                            <td>${lc.minPh}</td>
                            <td>${lc.avgPh}</td>
                            <td>${lc.maxPh}</td>
                            <td>${lc.stdevPh}</td>
                            <td>${lc.byPh}</td>
                            <td  rowspan=3>${lc.cbCd}</td>
                        </tr>
                         <tr>
                            <td class="region-lcLevel">镉</td>
                            <td>${lc.minCd}</td>
                            <td>${lc.avgCd}</td>
                            <td>${lc.maxCd}</td>
                            <td>${lc.stdevCd}</td>
                            <td>${lc.byCd}</td>
                        </tr>
                         <tr>
                            <td class="region-lcLevel">有效态镉</td>
                            <td>${lc.minACd}</td>
                            <td>${lc.avgACd}</td>
                            <td>${lc.maxACd}</td>
                            <td>${lc.stdevACd}</td>
                            <td>${lc.byACd}</td>
                        </tr>
                        <tr></tr>
                         <tr></tr>
                          <tr></tr>
                        </tbody>
                    </table>
                </div>