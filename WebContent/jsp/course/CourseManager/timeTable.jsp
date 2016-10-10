<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="timeTable" style="padding:0px;">
<table class="table">	
	<c:forEach items="${dc}" var="d">
	<tr>
		<td>
		<select name="week" onChange="checkTime()" class="selectpicker" data-width="auto">
			<option value="">刪除</option>
			<option <c:if test="${d.week eq'1'}">selected</c:if> value="1">週一</option>
			<option <c:if test="${d.week eq'2'}">selected</c:if> value="2">週二</option>
			<option <c:if test="${d.week eq'3'}">selected</c:if> value="3">週三</option>
			<option <c:if test="${d.week eq'4'}">selected</c:if> value="4">週四</option>
			<option <c:if test="${d.week eq'5'}">selected</c:if> value="5">週五</option>
			<option <c:if test="${d.week eq'6'}">selected</c:if> value="6">週六</option>
			<option <c:if test="${d.week eq'7'}">selected</c:if> value="7">週日</option>
		</select>
		<select name="begin" onChange="checkTime()" class="selectpicker" data-width="auto">
			<c:forEach begin="1" end="28" var="b">
			<option <c:if test="${d.begin eq b}">selected</c:if> value="${b}">第${b}節</option>
			</c:forEach>
		</select>	
		<select name="end" onChange="checkTime()" class="selectpicker" data-width="auto">
			<c:forEach begin="1" end="28" var="e">
			<option <c:if test="${d.end eq e}">selected</c:if> value="${e}">第${e}節</option>
			</c:forEach>
		</select>
		
		<input type="text" name="place" class="place form-control" autocomplete="off" 
		value="<c:if test="${d.place!=null&& d.place ne''}">${d.place}, ${d.name2}</c:if>" 
		onClick="this.value='';" data-provide="typeahead"/>				
		
		</td>				
	</tr>
	</c:forEach>
</table>
</div>

<div id="timeTmp" style="display:none;">
<table class="table">
	<c:forEach begin="1" end="5">
	<tr>
		<td>		
		<select name="week" onChange="checkTime()" class="selectpicker" data-width="auto">
			<option value="">星期</option>
			<option value="1">週一</option>
			<option value="2">週二</option>
			<option value="3">週三</option>
			<option value="4">週四</option>
			<option value="5">週五</option>
			<option value="6">週六</option>
			<option value="7">週日</option>
		</select>
		<select name="begin" onChange="checkTime()" class="selectpicker" data-width="auto">
			<option value="">節次</option>
			<c:forEach begin="1" end="16" var="b">
			<option value="${b}">第${b}節</option>
			</c:forEach>
		</select>	
		<select name="end" onChange="checkTime()" class="selectpicker" data-width="auto">
			<option value="">節次</option>
			<c:forEach begin="1" end="16" var="e">
			<option value="${e}">第${e}節</option>
			</c:forEach>
		</select>		
		<input type="text" name="place" class="place form-control" autocomplete="off" onClick="this.value='';" data-provide="typeahead"/>				
		</td>				
	</tr>
	</c:forEach>
</table>
</div>
<script>
function checkTime(){
	week=$('select[name="week"]');
	begin=$('select[name="begin"]');
	end=$('select[name="end"]');
	err=false;	
	<c:if test="${!close}">
	if($("#Sterm").val()!=${school_term})
	for(i=0; i<week.length; i++){		
		if(parseInt(end[i].value)<parseInt(begin[i].value)){			
			err=true;
		}		
		for(j=0; j<week.length; j++){
			
			if(i!=j){
				if(parseInt(week[i].value)==parseInt(week[j].value)){
					if(parseInt(begin[j].value)<=parseInt(end[i].value) && parseInt(end[j].value)>=parseInt(begin[i].value)){
						err=true;
					}
				}
			}
		}		
		if(err){
			$("#saveTime").attr("disabled", true);
		}else{
			$("#saveTime").attr("disabled", false);
		}		
	}
	</c:if>
}  
</script>