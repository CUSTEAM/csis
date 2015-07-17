<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/stupidtable.min.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script>
    $(document).ready(function(){    	
    	
  
    	$(".span8").typeahead({
    		//remote:"#dtimeOid",
    		source : [],
    		items : 30,
    		updateSource:function(inputVal, callback){			
    			$.ajax({
    				type:"POST",
    				url:"/eis/courseSearchMix",
    				dataType:"json",
    				data:{
    					length:20, 
    					nameno:inputVal, 
    					term:$("#term").val()
    				},
    				success:function(d){
    					callback(d.list);
    				}
    			});
    		}		
    	});
    	
    	$(".span3").typeahead({
    		//remote:"#agent",
    		source : [],
    		items : 10,
    		updateSource:function(inputVal, callback){
    			$.ajax({
    				url:"/eis/autoCompAnyCode",
    			    dataType: 'jsonp',
    			    jsonp:'back',          //jsonp請求方法
    			    data:{
    			    	bootstrap:"1",
    			    	idCol:"ClassNo",
    			    	nameCol:"ClassName",
    			    	table:"Class",
    			    	value:inputVal			    
    			    },
    			    cache:false,
    			    type:'POST',
    			    success: function(d) {    			    	
    			    	callback(d.list);
    			    }
    			});
    		}		
    	});
    	
    	
    	/*$("#targetOid").keypress(function(){
    		//alert($("#targetOid").val().indexOf(","));
       		if($("#targetOid").val().indexOf(",")>0){
       			$("#mergeClass").attr("disabled", false);
       			$("#mergeSeld").attr("disabled", false);
        	}else{
        		$("#mergeClass").attr("disabled", true);
       			$("#mergeSeld").attr("disabled", false);
        	}
       	});*/
    	//$("#row").stupidtable();
    });
    	
   	 
</script>
</head>
<body>
<div class="alert">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>本學期併班選課管理</strong>    
</div>
<form action="MergeSeld" method="post">
<table class="table">
		<tr>
			<td class="control-group success">
			<div class="input-prepend">
			<span class="add-on">目標學期</span>
	            <select name="term" id="term">
	            	<option <c:if test="${term eq'1'}">selected</c:if> value="1">第1學期</option>
	            	<option <c:if test="${term eq'2'}">selected</c:if> value="2">第2學期</option>
	            </select>
	        </div>
			<div class="input-prepend">
			<span class="add-on">合併後課程</span>
	            <input name="targetOid" id="targetOid" value="${targetOid}" class="span8" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
	        </div>		
			</td>
		</tr>
</table>
<div class="row">
    <div class="span4">
    <table class="table">	
		<tr>
			<td class="control-group error">
			<div class="alert alert-error">選定被合併班級的學生加選合併後課程</div>
			<c:forEach begin="1" end="5">			
			<div class="input-prepend">
			<span class="add-on">被合併班級</span>
	            <input name="ClassNos" class="span3" data-provide="typeahead" onClick="this.value=''" placeholder="班級代碼或班級名稱" autocomplete="off" type="text"/>
	        </div>
	        <br>
	        </c:forEach>
	        <button id="mergeClass" name="method:mergeClass" class="btn btn-danger" onClick="javascript: return(confirm('確定合併?')); void('')">合併班級</button>		
			</td>
		</tr>
	</table>

</div>
    <div class="span8">
    <table class="table">
		<tr>
			<td class="control-group info">
			<div class="alert alert-info">選定被合併課程的選課學生轉移至合併後課程</div>
			<c:forEach begin="1" end="5">
			
			<div class="input-prepend">
			<span class="add-on">被合併課程</span>
	            <input name="dtimeOids" class="span8" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
	        </div>
	        <br>
	        </c:forEach>
	        <button name="method:mergeSeld" id="mergeSeld" class="btn btn-info" onClick="javascript: return(confirm('確定合併?')); void('')">合併課程</button>		
			</td>
		</tr>
	</table>
    
    </div>
</div>



</form>
<c:if test="${!empty css}">
<table class="table">
	<tr>
		<td>課程編號</td>
		<td>班級名稱</td>
		<td>課程名稱</td>
		<td>選課人數</td>
	</tr>
	<c:forEach items="${css}" var="c">
	<tr>
		<td>${c.Oid}</td>
		<td>${c.ClassName}</td>
		<td>${c.chi_name}</td>
		<td>${c.cnt}</td>
	</tr>
	</c:forEach>
</table>
</c:if>
</body>
</html>