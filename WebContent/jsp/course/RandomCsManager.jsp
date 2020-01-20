<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學分班課程管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/stupidtable.min.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/autoComplete.js"></script>

</head>
<body>
<div class="bs-callout bs-callout-info" >

    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>學分班課程管理</strong>
    
</div>

<form action="RandomCsManager" method="post" class="form-inline">

<%@ include file="/jsp/course/RandomCsManager/search.jsp"%>	



    
<c:if test="${!empty css}">     
<%@ include file="/jsp/course/RandomCsManager/list.jsp"%>
<script>
$("#row").stupidtable();
</script>
</c:if>
            

</form>





</body>
</html>