<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改課程基本資料</title>
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

</head>
<body>
<form action="CourseManager" method="post" class="form-inline">

<div class="bs-callout bs-callout-info" id="callout-helper-pull-navbar">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
   	 正在<c:choose>
  <c:when test="${empty close}">修改</c:when>
  <c:when test="${!empty close}">規劃</c:when>
  <c:otherwise>???</c:otherwise>
</c:choose> <strong>${cs.Oid}, ${cs.chi_name}</strong>
</div>
<input type="hidden" name="Dtime_oid" value="${cs.Oid}" />
<%@ include file="/jsp/course/CourseManager/editDetail.jsp"%>


<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="headingOne">
		  <h4 class="panel-title"><a role="button" data-toggle="collapse" 
		  data-parent="#accordion" href="#collapseOne" aria-expanded="true" 
		  aria-controls="collapseOne">上課時段/地點</a></h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
	  		<div class="panel-body">
		  	<%@ include file="/jsp/course/CourseManager/timeTable.jsp"%>
			<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">				
				<button class="btn btn-default" onClick="addTimeTable();" type="button">增加時段欄位</button>
				<button class="btn btn-danger demo" name="method:saveDtimeClass" id="saveTime">儲存排課資料</button>
			</div>
		  	</div>
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="heading2">
		  <h4 class="panel-title"><a role="button" data-toggle="collapse" 
		  data-parent="#accordion" href="#collapse2" aria-expanded="true" 
		  aria-controls="collapse2">跨選規則</a></h4>
		</div>
		<div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2">
		  	<div class="panel-body">
			  	<%@ include file="/jsp/course/CourseManager/openTable.jsp"%>
				<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">
					<button class="btn btn-default" onClick="$('#opencsTable').append($('#opencsTmp').show('slow'))" type="button">增加跨選欄位</button>
					<button class="btn btn-danger demo" name="method:saveOpencs" id="saveTime">儲存跨選規則</button>
				</div>
		  	</div>
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="heading3">
		  <h4 class="panel-title"><a role="button" data-toggle="collapse" 
		  data-parent="#accordion" href="#collapse3" aria-expanded="true" 
		  aria-controls="collapse3">擋修規則</a></h4>
		</div>
		<div id="collapse3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading3">
		  <div class="panel-body">
		  	<%@ include file="/jsp/course/CourseManager/BlockTable.jsp"%>
			<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">			    
			    <button class="btn btn-default" onClick="$('#blockTmp').show('slow')" type="button">增加擋修欄位</button> 
			    <button class="btn btn-danger" name="method:saveBlock">儲存擋修科目代碼</button>
		  	</div>
		</div>
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="heading4">
		  <h4 class="panel-title"><a role="button" data-toggle="collapse" 
		  data-parent="#accordion" href="#collapse4" aria-expanded="true" 
		  aria-controls="collapse4">一科目多教師</a></h4>
		</div>
		<div id="collapse4" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading4">
		  <div class="panel-body">
		  	<%@ include file="/jsp/course/CourseManager/techerTable.jsp"%>
			<div class="btn-group" style="padding-bottom:44px; padding-top:22px;">			    
			    <button class="btn btn-default" onClick="$('#techidTmp').show('slow')" type="button">增加教師欄位</button>
			    <button class="btn btn-default" <c:if test="${fn:length(dt)<1}">disabled</c:if> name="method:editMulTech">編輯學生分組</button>
			    <button class="btn btn-danger" name="method:saveMulTech">儲存多教師資料</button>   
			</div>
		  </div>
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="heading5">
		  <h4 class="panel-title"><a role="button" data-toggle="collapse" 
		  data-parent="#accordion" href="#collapse5" aria-expanded="true" 
		  aria-controls="collapse5">修改記錄</a></h4>
		</div>
		<div id="collapse5" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading5">
		  <div class="panel-body">
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
</body>
</html>