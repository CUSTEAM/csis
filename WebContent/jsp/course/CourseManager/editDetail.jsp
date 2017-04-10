<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table">
	</tr>
	<tr>
		<td>
		<input type="hidden" name="Sterm" id="Sterm" value="${cs.Sterm}" />
		<select disabled class="selectpicker" data-width="auto">
			<option <c:if test="${cs.Sterm eq'1'}">selected</c:if> value="1">上學期</option>
			<option <c:if test="${cs.Sterm eq'2'}">selected</c:if> value="2">下學期</option>
		</select>
		<%@ include file="/inc/jsp-kit/classSelector.jsp"%>
		<c:choose>
		    <c:when test="${cs.SchoolType eq'D'}"><span class="label label-info"></c:when>
		    <c:when test="${cs.SchoolType eq'N'}"><span class="label label-success"></c:when>
		    <c:when test="${cs.SchoolType eq'H'}"><span class="label label-warning"></c:when>
		    <c:otherwise><span class="label"></c:otherwise>
		</c:choose>
		${cs.ClassName}</span>
		</td>
	</tr>
	<tr>
		<td>		
		<div class="input-group">
		  	<span class="input-group-addon">選別</span>
            <select name="opt" class="form-control">
				<option <c:if test="${cs.opt eq'1'}">selected</c:if> value="1">必修</option>
				<option <c:if test="${cs.opt eq'2'}">selected</c:if> value="2">選修</option>
				<option <c:if test="${cs.opt eq'3'}">selected</c:if> value="3">通識</option>
			</select>
        </div>
        <div class="input-group">
		  	<span class="input-group-addon">學分</span>
            <input type="text" style="width:60px;" class="form-control" name="credit" id="creidt" value="${cs.credit}" autocomplete="off" />
        </div>
        <div class="input-group">
		  	<span class="input-group-addon">時數</span>
            <input type="text" style="width:60px;" class="form-control" name="thour" id="thour" value="${cs.thour}" autocomplete="off" />
        </div>
        <div class="input-group">
		  	<span class="input-group-addon">上限</span>
            <input type="text" style="width:60px;" class="form-control" name="Select_Limit" id="Select_Limit" value="${cs.Select_Limit}" autocomplete="off" />
        </div>        
		<div class="input-group">
		  	<span class="input-group-addon">授課型態</span>
            <select name="ele" class="form-control">
				<option <c:if test="${cs.elearning eq'0'}">selected</c:if> value="0">一般</option>
				<option <c:if test="${cs.elearning eq'1'}">selected</c:if> value="1">遠距</option>
				<option <c:if test="${cs.elearning eq'2'}">selected</c:if> value="2">輔助</option>
				<option <c:if test="${cs.elearning eq'3'}">selected</c:if> value="3">多媒體</option>
			</select>
        </div>
        <div class="input-group">
		  	<span class="input-group-addon">實習費</span>
            <select name="pay" class="form-control">
				<option <c:if test="${cs.extrapay eq'1'}">selected</c:if> value="1">有</option>
				<option <c:if test="${cs.extrapay eq'0'}">selected</c:if> value="0">無</option>				
			</select>
        </div>
        <div class="input-group">
		  	<span class="input-group-addon">管制退選</span>
            <select name="nonSeld" class="form-control">
				<option <c:if test="${cs.nonSeld eq'1'}">selected</c:if> value="1">是</option>
				<option <c:if test="${cs.nonSeld eq'0'}">selected</c:if> value="0">否</option>				
			</select>
        </div>
		</td>
	</tr>
	<tr>
		<td>		
			<div class="input-group">
		  	<span class="input-group-addon">授課教師</span>
				<input type="text" class="form-control techid" name="techid" id="techid" value="<c:if test="${!empty cs.techid}">${cs.techid}, ${cs.cname}</c:if>" onClick="this.value='';" autocomplete="off" data-provide="typeahead"/>
				
			</div>			
			<div class="input-group">
		  	<span class="input-group-addon">科目名稱</span>
				<input type="text" class="form-control cscode" name="cscode" id="cscode" value="${cs.cscode},${cs.chi_name}" onClick="this.value='';" autocomplete="off" data-provide="typeahead"/>
			</div>
			
			<div class="input-group">
		  	<span class="input-group-addon">專業技術</span>
            <select name="y_pro" class="form-control">		
				<option <c:if test="${cs.y_pro eq'1'}">selected</c:if> value="1">是</option>
				<option <c:if test="${cs.y_pro eq'0'}">selected</c:if> value="0">否</option>
				
			</select>
        	</div>			
			
			<div class="input-group">
		  	<span class="input-group-addon">專業英語</span>
            <select name="y_pro_eng" class="form-control">
				<option <c:if test="${cs.y_pro_eng eq'1'}">selected</c:if> value="1">是</option>
				<option <c:if test="${cs.y_pro_eng eq'0'}">selected</c:if> value="0">否</option>				
			</select>
        	</div>
		</td>
	</tr>
	<tr>
		<td>		
			<div class="btn-group">			
			<button class="btn btn-default demo" onClick="javascript: return(confirm('確定刪除?')); void('')" name="method:delete">刪除課程</button>
		    <button class="btn btn-danger demo" name="method:saveDtime">儲存課程基本資料</button>			
		    </div>		    
		    <button name="method:back" class="btn btn-default">返回查詢列表</button>
		    <div class="btn-group">
			    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown">列印</a>
			    <ul class="dropdown-menu">
			    	<li><a class="btn btn-link" href="SylDoc?Oid=${cs.Oid}">課程大綱</a></li>
			    	<li><a class="btn btn-link" href="IntorDoc?Oid=${cs.Oid}">中英文簡介</a></li>
			    	<li><a class="btn btn-link" href="DtimeSelds?Oid=${cs.Oid}">選課學生</a></li>
			    </ul>
			    </div>
		</td>
	</tr>
</table>