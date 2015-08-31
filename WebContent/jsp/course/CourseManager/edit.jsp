<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="inc/js/autoComplete.js"></script>
<script>    
function addTimeTable(){
	$("#timeTmp").show("slow");
}
</script>
<style>
    body .modal {
    /* new custom width */
    width: 760px;
    /* must be half of the width, minus scrollbar on the left (30px) */
    margin-left: -380px;
}
</style>
</head>
<body>
<form action="CourseManager" method="post" class="form-inline">
<div class="alert <c:choose>
  <c:when test="${empty close}">alert-info</c:when>
  <c:when test="${!empty close}">alert-success</c:when>
  <c:otherwise>alert-info</c:otherwise>
</c:choose>">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
   	 正在修改 <strong>${cs.Oid}, ${cs.chi_name}</strong>
</div>
<input type="hidden" name="Dtime_oid" value="${cs.Oid}" />
<%@ include file="/jsp/course/CourseManager/editDetail.jsp"%>
<div class="accordion" id="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse1">上課時段/地點</a>
		</div>
		<div id="collapse1" class="accordion-body collapse <c:if test="${!empty timeTable}">in</c:if>">
			<div class="accordion-inner">
			<%@ include file="/jsp/course/CourseManager/timeTable.jsp"%>
			<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">				
				<button class="btn" onClick="addTimeTable();" type="button">增加時段欄位</button>
				<button class="btn btn-danger demo" name="method:saveDtimeClass" id="saveTime">儲存排課資料</button>
			</div>
			</div>
		</div>
	</div>	
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse2">跨選規則</a>
		</div>
		<div id="collapse2" class="accordion-body collapse <c:if test="${!empty opencs}">in</c:if>">
			<div class="accordion-inner">
			<%@ include file="/jsp/course/CourseManager/openTable.jsp"%>
			<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">
			    <button class="btn" onClick="$('#opencsTable').append($('#opencsTmp').html())" type="button">增加跨選欄位</button>
			    <button class="btn btn-danger demo" name="method:saveOpencs" id="saveTime">儲存跨選規則</button>
			</div>
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse3">擋修規則</a>
		</div>
		<div id="collapse3" class="accordion-body collapse <c:if test="${!empty block}">in</c:if>">
			<div class="accordion-inner">
			<%@ include file="/jsp/course/CourseManager/BlockTable.jsp"%>
			<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">			    
			    <button class="btn" onClick="$('#blockTmp').show('slow')" type="button">增加擋修欄位</button> 
			    <button class="btn btn-danger" name="method:saveBlock">儲存擋修科目代碼</button>
			</div>
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse4">一科目多教師</a>
		</div>
		<div id="collapse4" class="accordion-body collapse <c:if test="${!empty mulTech}">in</c:if>">
			<div class="accordion-inner">
			<%@ include file="/jsp/course/CourseManager/techerTable.jsp"%>
			<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">			    
			    <button class="btn" onClick="$('#techidTmp').show('slow')" type="button">增加教師欄位</button>
			    <button class="btn" <c:if test="${fn:length(dt)<1}">disabled</c:if> name="method:editMulTech">編輯學生分組</button>
			    <button class="btn btn-danger demo" name="method:saveMulTech">儲存多教師資料</button>   
			</div>
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse5">修改記錄</a>
		</div>
		<div id="collapse5" class="accordion-body collapse">
			<div class="accordion-inner">
			<table class="table table-hover">
				<c:forEach items="${dh}" var="h">
				<tr>
					<td>${h.edate},${h.cname},${h.note}</td>
				</tr>
				</c:forEach>
			</table>		
			</div>
		</div>
	</div>
</div>
</form>
<c:if test="${!empty close}">
<script>
$(".demo").attr('disabled', 'disabled');
</script>
	<c:if test="${!close}">
	<script>
	if($("#Sterm").val()!=${school_term}){
		$(".demo").attr('disabled', false);
	}
	</script>
	</c:if>
</c:if>
    	
<c:choose>
	<c:when test="${empty close}">
		<script>$( "a" ).addClass( "text-info" );$( "form" ).addClass( "control-group info" );</script>
	</c:when>
	<c:when test="${!empty close}">
		<script>$( "a" ).addClass( "text-success" );$( "form" ).addClass( "control-group success" );</script>
	</c:when>
	<c:otherwise>
		<script>$( "a" ).addClass( "text-info" );$( "form" ).addClass( "control-group info" );</script>
	</c:otherwise>
</c:choose>
</body>
</html>