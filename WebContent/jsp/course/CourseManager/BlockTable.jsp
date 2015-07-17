<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="blockTable">
	<c:forEach items="${db}" var="b">	
	<div style="padding-top:2px;"><input type="text" class="span3 cscode" name="cscodes" autocomplete="off" onClick="this.value=''" value="${b.cscode}, ${b.chi_name}" /></div>
	</c:forEach> 
</div>
<div id="blockTmp" style="display:none;">
	<c:forEach begin="1" end="5">
	<div style="padding-top:2px;"><input type="text" autocomplete="off" onClick="this.value=''" class="span3 cscode" name="cscodes" value="" /></div>
	</c:forEach>
</div>