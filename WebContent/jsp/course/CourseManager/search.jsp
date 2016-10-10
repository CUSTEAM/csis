<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-primary">
<div class="panel-heading">查詢條件</div>
<table class="table">
	<tr>
		<td>		
		<div class="input-group">
		  	<span class="input-group-addon" id="sizing-addon2">快速查詢</span>
		  	<input id="Dtime_oid" name="Dtime_oid" class="form-control" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
		  	<span class="input-group-btn"><button name="method:searchMix" class="btn btn-default">查詢</button></span>
		</div>	
		</td>
	</tr>
	<tr>
		<td>
		<select name="Sterm" id="Sterm" class="selectpicker" data-width="auto">
			<option <c:if test="${Sterm eq'1'}">selected</c:if> value="1">上學期</option>
			<option <c:if test="${Sterm eq'2'}">selected</c:if> value="2">下學期</option>
		</select>
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
		</td>
	</tr>
	<tr>
		<td>
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
		  	<span class="input-group-addon">開放選修</span>
            <select name="open" class="form-control">
				<option value=""></option>
				<option <c:if test="${open eq'1'}">selected</c:if> value="1">是</option>
				<option <c:if test="${open eq'2'}">selected</c:if> value="2">否</option>
			</select>
        </div>
		<div class="input-group">
		  	<span class="input-group-addon">開放擋修</span>
            <select name="block" class="form-control">
				<option value=""></option>
				<option <c:if test="${block eq'1'}">selected</c:if> value="1">是</option>
				<option <c:if test="${block eq'2'}">selected</c:if> value="2">否</option>
			</select>
        </div>		
		<div class="input-group">
		  	<span class="input-group-addon">多教師</span>
            <select name="many" class="form-control">
				<option value=""></option>
				<option <c:if test="${many eq'1'}">selected</c:if> value="1">是</option>
				<option <c:if test="${many eq'2'}">selected</c:if> value="2">否</option>
			</select>
        </div>
		<div class="input-group">
		  	<span class="input-group-addon">授課型態</span>
            <select name="ele" class="form-control">
				<option value=""></option>
				<option <c:if test="${ele eq'0'}">selected</c:if> value="0">一般</option>
				<option <c:if test="${ele eq'1'}">selected</c:if> value="1">遠距</option>
				<option <c:if test="${ele eq'2'}">selected</c:if> value="2">輔助</option>
				<option <c:if test="${ele eq'3'}">selected</c:if> value="3">多媒體</option>
			</select>
        </div>
        <div class="input-group">
		  	<span class="input-group-addon">實習費</span>
            <select name="pay" class="form-control">
				<option value=""></option>
				<option <c:if test="${pay eq'1'}">selected</c:if> value="1">有</option>
				<option <c:if test="${pay eq'0'}">selected</c:if> value="0">無</option>				
			</select>
        </div>
        
        <div class="input-group">
		  	<span class="input-group-addon">管制退選</span>
            <select name="nonSeld" class="form-control">
				<option value=""></option>
				<option <c:if test="${nonSeld eq'1'}">selected</c:if> value="1">是</option>
				<option <c:if test="${nonSeld eq'0'}">selected</c:if> value="0">否</option>				
			</select>
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