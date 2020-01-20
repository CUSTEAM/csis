<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-primary">
<div class="panel-heading">查詢條件</div>
<table class="table">
	<tr>
		<td>		
		<div class="input-group">
		  	<span class="input-group-addon" id="sizing-addon2">編號或名稱</span>
		  	<input id="Dtime_oid" name="Dtime_oid" class="form-control" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
		  	
		</div>	
		
		<div class="input-group">
		  	<span class="input-group-addon" id="sizing-addon2">開始日期</span>
		  	<input id="Dtime_oid" name="Dtime_oid" class="form-control" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
		</div>	
		
		<div class="input-group">
		  	<span class="input-group-addon" id="sizing-addon2">結束日期</span>
		  	<input id="Dtime_oid" name="Dtime_oid" class="form-control" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
		</div>	
		
		<button name="method:searchMix" class="btn btn-primary">查詢列表</button>
		</td>
	</tr>
	<tr>
		<td>		
		<%@ include file="/inc/jsp-kit/classSelector.jsp"%></td>
	</tr>
	
	<tr>
		<td nowrap>
		
			<div class="input-group">
			  	<span class="input-group-addon">授課教師</span>
			  	<input type="text" placeholder="教師完整姓名" class="form-control techid" name="techid" id="techid" value="${techid}" onClick="this.value='';" autocomplete="off" data-provide="typeahead"/>
			</div>
		
				
			<div class="input-group">
		  	<span class="input-group-addon">科目名稱</span>
				<input type="text" placeholder="課程名稱片段或完整課程代碼" class="form-control cscode" name="cscode" id="cscode" value="${cscode}" onClick="this.value='';" autocomplete="off" data-provide="typeahead"/>
			</div>
			
			<div class="input-group">
		  	<span class="input-group-addon">選別</span>
            <select name="opt" class="form-control">
				<option value=""></option>
				<option <c:if test="${opt eq'1'}">selected</c:if> value="1">必修</option>
				<option <c:if test="${opt eq'2'}">selected</c:if> value="2">選修</option>
				<option <c:if test="${opt eq'3'}">selected</c:if> value="3">通識</option>
			</select>
        </div>
        	
        	<div class="input-group">
			  	<span class="input-group-addon">教室</span>
			  	<input type="text" placeholder="教室代碼或名稱" class="form-control place" name="location" id="location" value="${location}" onClick="this.value='';" autocomplete="off" data-provide="typeahead"/>
			</div>
		</td>
	</tr>
	
</table>
<div class="panel-body">
	<button class="btn btn-default demo" name="method:add">新增課程</button>
		    <%@ include file="/jsp/course/CourseManager/print.jsp"%>
		    
		    <div class="btn-group">
		    <button class="btn btn-danger" name="method:search">查詢列表</button>
		    <a class="btn btn-default" href="javascript:window.location.replace(window.location)">重新設定條件</a>
</div>
</div>