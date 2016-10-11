<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本學期併班選課管理</title>
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
<div class="bs-callout bs-callout-info">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>本學期併班選課管理</strong>    
</div>
<form action="MergeSeld" class="form-inline" method="post">
<div class="panel panel-primary">
<div class="panel-heading">合併課程</div>
<table class="table">
		<tr>
			<td>
			
			<div class="input-group">
		  	<span class="input-group-addon">目標學期</span>
	            <select name="term" id="term" class="form-control">
	            	<option <c:if test="${term eq'1'}">selected</c:if> value="1">第1學期</option>
	            	<option <c:if test="${term eq'2'}">selected</c:if> value="2">第2學期</option>
	            </select>
	        </div>
	        
			<div class="input-group">
		  	<span class="input-group-addon">合併後課程</span>
	            <input name="targetOid" id="targetOid" value="${targetOid}" class="span8 form-control" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
	        </div>		
			</td>
		</tr>
</table>
</div>


<div class="row">
	<div class="col-md-6">
	
	<div class="panel panel-primary">
	<div class="panel-heading">選擇班級</div>
	<div class="panel-body">選定被合併班級的學生加選合併後課程</div>
	<table class="table">
	<c:forEach begin="1" end="5">
	<tr>
		<td>			
		<div class="input-group">
	  		<span class="input-group-addon">被合併班級</span>
	    	<input name="ClassNos" class="span3 form-control" data-provide="typeahead" onClick="this.value=''" placeholder="班級代碼或班級名稱" autocomplete="off" type="text"/>
	    </div>
	    </td>
	</tr>
    
    </c:forEach>
    </table>
    <div class="panel-body">
    	<button id="mergeClass" name="method:mergeClass" class="btn btn-danger" onClick="javascript: return(confirm('確定合併?')); void('')">班級合併</button>
	</div>
    </div>
    
	
	</div>
	<div class="col-md-6">
	
	<div class="panel panel-primary">
	<div class="panel-heading">選擇課程</div>
	<div class="panel-body">選定被合併課程的選課學生轉移至合併後課程</div>
	
			<table class="table">					
			<c:forEach begin="1" end="5">
			<tr>
				<td>
			<div class="input-group">
		  	<span class="input-group-addon">被合併課程</span>
	            <input name="dtimeOids" class="span8 form-control" data-provide="typeahead" onClick="this.value=''" placeholder="課程編號、班級代碼、科目代碼、科目名稱、教師姓名..." autocomplete="off" type="text"/>
	        </div>
	        </td>
	    </tr>
		</c:forEach>
	</table>
	<div class="panel-body"><button name="method:mergeSeld" id="mergeSeld" class="btn btn-danger" onClick="javascript: return(confirm('確定合併?')); void('')">課程合併</button></div>
	        		
			
	</div>
	
	
	</div>
</div>



			







</form>
<c:if test="${!empty css}">
<div class="panel panel-primary">
<div class="panel-heading">合併結果</div>
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
</div>
</c:if>
</body>
</html>