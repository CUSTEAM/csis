<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>課程管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/stupidtable.min.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/autoComplete.js"></script>

</head>
<body>
<div class="bs-callout 
<c:choose>
  <c:when test="${empty close}">bs-callout-info</c:when>
  <c:when test="${!empty close}">bs-callout-success</c:when>
  <c:otherwise>alert-info</c:otherwise>
</c:choose>">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>課程管理</strong>
    <c:if test="${!empty close}">次學期課程規劃期間自 ${cs_plan_begin}至 ${cs_plan_end}</c:if>
</div>

<form action="CourseManager" method="post" class="form-inline">

<%@ include file="/jsp/course/CourseManager/search.jsp"%>	



    
<c:if test="${!empty css}">     
<%@ include file="/jsp/course/CourseManager/list.jsp"%>
<script>
$("#row").stupidtable();
</script>
</c:if>
            

</form>
<c:if test="${!empty close}">
<script>
$(".demo").attr('disabled', 'disabled');
</script>
	<c:if test="${!close}">
	<script>
	$(".demo").attr('disabled', false);
	if($("#Sterm").val()==${school_term}){
		$(".demo").attr('disabled', 'disabled');
	}
	$("#Sterm").change(function(){
		if(this.value=='${school_term}'){
			$(".demo").attr('disabled', 'disabled');
		}else{
			$(".demo").attr('disabled', false);
		}
	});
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