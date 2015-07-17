<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重新設定前學期課程</title>
</head>
<body>
<div class="alert">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>重新設定前學期課程</strong>    
</div>
<div class="alert alert-danger">
<b>重新設定包含: </b>授課(多)教師、排課、教學評量、選課學生、成績<br>
<b>保留: </b>選別、學分、跨選、擋修規則，以及其它所有可沿用資料<br>
<b>備份: </b>歷年課程資料<br>
<b>※ 課程規劃前執行, 課程規劃開始後請勿執行</b>
</div>
<form action="ReSetDtime" method="post" class="form-inline">


<button class="btn" name="method:reset" type="submit">重新設定</button>
<!-- button class="btn" name="method:resetSeld" type="submit">復原加退選</button-->
</form>
</body>
</html>