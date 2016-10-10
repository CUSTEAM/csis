<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分組名單管理</title>
<script src="/eis/inc/js/plugin/stupidtable.min.js"></script>
<script>
    $(document).ready(function(){
    	
    	$("#row").stupidtable();
    });    
</script>
</head>
<body>
<form action="CourseManager" method="post" class="form-inline">
<div class="bs-callout bs-callout-info" id="callout-helper-pull-navbar">
   	 正在修改 <strong>${dtime.Oid}, ${dtime.ClassName}, ${dtime.chi_name}</strong>     
    <div class="btn-group">	    
    	<button class="btn btn-danger btn-small" name="method:saveMultSeld">儲存學生分組</button>
		<button class="btn btn-default btn-small" name="method:edit">返回課程</button>
		
	</div>
    
</div>
<input type="hidden" name="Dtime_oid" value="${dtime.Oid}" />

<div class="row">
<div class="col-md-6">
<div class="panel panel-primary">
<div class="panel-heading">第1至${fn:length(ss)/2}名學生</div>
<table class="table" id="row">
	<thead>
        <tr>
        	
	        <th nowrap data-sort="string" style="cursor:n-resize;">學號 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">姓名 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">分組教師 <i class="icon-chevron-down"></i></th>	        
	        <th></th>
        </tr>
    </thead>
	<tbody>	
	<c:forEach items="${ss}" begin="0" end="${fn:length(ss)/2}" var="s">
	<tr>
		<td nowrap>${s.student_no}<input type="hidden" name="stds" value="${s.Oid}" /></td>
		<td nowrap>${s.student_name}</td>
		<td width="100%">
		<div style="display:none;">${s.Dtime_teacher}</div>
		<select name="techids" class="selectpicker" data-width="auto">
			<option value="">未選擇分組</option>
			<c:forEach items="${ts}" var="t">
			<option <c:if test="${t.Oid eq s.Dtime_teacher}">selected</c:if> value="${t.Oid}">${t.cname}</option>
			</c:forEach>
		</select>
		<c:if test="${!empty s.Dtime_teacher}"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
		</td>
	</tr>
	</c:forEach>	
	</tbody>
</table>
</div>

</div>
<div class="col-md-6">
<div class="panel panel-primary">
<div class="panel-heading">第${fn:length(ss)/2}至${fn:length(ss)}名學生</div>
<table class="table" id="row">
	<thead>
        <tr>
        	
	        <th nowrap data-sort="string" style="cursor:n-resize;">學號 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">姓名 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">分組教師 <i class="icon-chevron-down"></i></th>	        
	        <th></th>
        </tr>
    </thead>
	<tbody>		
	<c:forEach items="${ss}" begin="${(fn:length(ss)/2)+1}" end="${fn:length(ss)}" var="s">
	<tr>
		<td nowrap>${s.student_no}<input type="hidden" name="stds" value="${s.Oid}" /></td>
		<td nowrap>${s.student_name}</td>
		<td width="100%">
		<div style="display:none;">${s.Dtime_teacher}</div>
		<select name="techids" class="selectpicker" data-width="auto">
			<option value="">未選擇分組</option>
			<c:forEach items="${ts}" var="t">
			<option <c:if test="${t.Oid eq s.Dtime_teacher}">selected</c:if> value="${t.Oid}">${t.cname}</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</div>

</div>


</div>






<div class="btn-group">    
	<button class="btn btn-danger" name="method:saveMultSeld">儲存學生分組</button>
	<button class="btn btn-default" name="method:edit">返回課程</button>
	
</div>


</form>
</body>
</html>