<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="btn-group">
	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">校內表格 <span class="caret"></span>
  	</button>
  	<ul class="dropdown-menu">
    	<li><button class="btn btn-link" onClick="$('#pnt').val('ListDtime')" name="method:print" type="submit">通用報表</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('TeacherCounterTime')" name="method:print" type="submit">教師任教時數</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('TeacherCounterCourse')" name="method:print" type="submit">科目教師對照</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('CourseCounterTeacher')" name="method:print" type="submit">教師科目對照</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('ExamPacketFace')" name="method:print" type="submit">試卷封袋</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('CsCoansw')" name="method:print" type="submit">教學評量</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('TimeTableClass')" name="method:print" type="submit">班級課表</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('TimeTableTeacher')" name="method:print" type="submit">教師課表含留校時間</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('TimeTableTeacherNonStay')" name="method:print" type="submit">教師課表不含留校</button></li>	
		<li><button class="btn btn-link" onClick="$('#pnt').val('TimeTableRoom')" name="method:print" type="submit">教室課表</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('SylDoc')" name="method:print" type="submit">課程大綱</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('IntorDoc')" name="method:print" type="submit">課程簡介</button></li>
		<!--li><button class="btn btn-link" onClick="$('#pnt').val('ClassTimetable40')" name="method:print" type="submit">通識課表</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('TeachersTimeTable')" name="method:print" type="submit">一科目多教師課表</button></li-->
		<li><button class="btn btn-link" onClick="$('#pnt').val('StuSeltable')" name="method:print" type="submit">學生選課清單</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('StuSelConfirmTable')" name="method:print" type="submit">學生選課簽收單</button></li>
  	</ul>
</div>



<div class="btn-group">
	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">報部表格 <span class="caret"></span>
  	</button>
  	<ul class="dropdown-menu">
    	<li><button class="btn btn-link" onClick="$('#pnt').val('List4Dtime13')" name="method:print" type="submit">表1-3</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('List4Dtime32')" name="method:print" type="submit">表3-2</button></li>
		<!--li><button class="btn btn-link" onClick="$('#pnt').val('List4Seld52')" name="method:print" type="submit">表5-1</button></li-->
		<li><button class="btn btn-link" onClick="$('#pnt').val('List4DtimeG')" name="method:print" type="submit">技職</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('List4Course35')" name="method:print" type="submit">表3-5</button></li>
  	</ul>
</div>


<div class="btn-group">
	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">遠距表格 <span class="caret"></span>
  	</button>
  	<ul class="dropdown-menu">
    	<li><button class="btn btn-link" onClick="$('#pnt').val('ElearningPer')" name="method:print" type="submit">班級對應</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('ElearningCou')" name="method:print" type="submit">課程對應</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('ElearningOrg')" name="method:print" type="submit">學生對應</button></li>
		<li><button class="btn btn-link" onClick="$('#pnt').val('ElearningSel')" name="method:print" type="submit">選課對應</button></li>	
  	</ul>
</div>



<input type="hidden" name="print" id="pnt"/>