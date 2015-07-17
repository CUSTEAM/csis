<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" name="assignSeld" id="assignSeld"/>
<input type="hidden" name="Dtime_teacher_oid" id="Dtime_teacher_oid"/>

<div>
	<c:forEach items="${dt}" var="d">	
	
	<div style="padding-top:2px;">
	<div class="input-prepend">
	<span class="add-on">時數</span>
    <input type="text" class="span1" name="hours" id="hours" value="${d.hours}" autocomplete="off" />
    </div>
	<div class="input-prepend input-append">
	<input type="text" class="span3 techid" name="techids" onClick="this.value=''" 
	id="techids" value="${d.Oid}, ${d.cname}" autocomplete="off" />
	<button class="btn" name="method:assignSeld" onClick="$('#assignSeld').val('${d.Oid}')">全班授權</button>
	</div>	
	<button name="method:delMulTech" class="btn btn-warning demo" onClick="$('#Dtime_teacher_oid').val(${d.dtOid})">刪除</button>
	</div>
	</c:forEach> 
</div>
<div id="techidTmp" style="display:none;">
	<c:forEach begin="1" end="5">
	
	<div style="padding-top:2px;">	
	<div class="input-prepend">
	<span class="add-on">時數</span>
    <input type="text" class="span1" name="hours" id="hours" value="0" autocomplete="off" />
    </div>
	<input type="text" class="span3 techid" name="techids" id="techids" value="" autocomplete="off" />	
	</div>
	</c:forEach>
</div>