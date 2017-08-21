<%@ page language="java" pageEncoding="UTF-8"%>
    <div id="controls">
     <button onclick="initEdit()">初始化绘制</button>
        <ul id="controlToggle">    
            <li>
                <input type="radio" name="type" value="point" id="pointToggle" onclick="drewToggleControl(this);" style="float:left; margin-right:3px;"/>
                <label for="pointToggle">绘制点</label>
            </li>
            <li>
                <input type="radio" name="type" value="line" id="lineToggle" onclick="drewToggleControl(this);"  style="float:left; margin-right:3px;"/>
                <label for="lineToggle">绘制线</label>
            </li>
            <li>
                <input type="radio" name="type" value="polygon" id="polygonToggle" onclick="drewToggleControl(this);"  style="float:left; margin-right:3px;"/>
                <label for="polygonToggle">绘制面</label>
            </li>
                 </ul>
    </div>
    
    <div id="buffer" title="缓冲">
       <button onclick="bufferGEO()">缓冲</button>     
    </div>
    
    <div  title="查询">
          <input type="text"  id="keyname"/>
          <button  onclick="showQuery()">属性查询</button>
          <button  onclick="identity()">空间查询</button>
    </div>
    
    <div title="editToolBar" >
        <button onclick="addFeature()"> 添加要素</button>
        <button onclick="deleteFeature()">删除要素</button>
        <button onclick="editFeature()">编辑要素</button>
        <button onclick="saveEdit()">保存编辑</button>
        <div  id="editTools"></div>
         <table id="tbody" style="width:100%;" class="table table-striped table-bordered  table-condensed table-style" border="0">
		   	 <tr>
			   <td  style="background:#f9f9f9;width:62px">设备名称</td> 
			   <td><input type="text" id="deviceNAME" class="input" style="width:90%; height:15px; margin:0;" value=""></td>		     
			 </tr>			
			 <tr>
			    <td style="vertical-align:middle;background:#f9f9f9;">设备类型</td>
			    <td><input type="text" id="deviceTYPE" class="input" style="width:90%; height:15px; margin:0;" value=""></td>
			 </tr>
			 <tr>
			    <td style="vertical-align:middle;background:#f5f5f5;">建设长家</td>
			    <td><input type="text" id="deviceCOMPANY" class="input" style="width:90%; height:15px; margin:0;" value=""></td>			
			 </tr>
			 <tr>
			    <td style="vertical-align:middle;background:#f5f5f5;">设备编号</td>
			    <td><input type="text" id="deviceCODE" class="input" style="width:90%; height:15px; margin:0;" value=""></td>
			 </tr>
			<tr>
			    <td style="vertical-align:middle;background:#f9f9f9;">设备方向</td>
			    <td><input type="text" id="deviceDIRECT" class="input" style="width:90%; height:15px; margin:0;" value=""></td>			
		   </tr>
	       <tr>
			   <td style="vertical-align:middle;background:#f9f9f9;">要素ID</td>
			   <td><input type="text" id="deviceOBJECTID" class="input" style="width:90%; height:15px; margin:0;" value=""></td>
		  </tr>
	   </table>			   
    </div>

