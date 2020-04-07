package action.course;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Dtime;
import model.DtimeClass;
import model.Message;
import print.dtime.IntorDoc;
import print.dtime.SylDoc;
import print.dtime.TimeTable;
import action.BaseAction;
import action.course.print.ClassTimetable40;
import action.course.print.CourseCounterTeacher;
import action.course.print.CsCoansw;
import action.course.print.ExamPacketFace;
import action.course.print.List4211;
import action.course.print.List4Course35;
import action.course.print.List4Dtime13;
import action.course.print.List4Dtime32;
import action.course.print.List4DtimeG;
import action.course.print.List4Seld52;
import action.course.print.ListDtime;
import action.course.print.StuSelConfirmTable;
import action.course.print.StuSeltable;
import action.course.print.TeacherCounterCourse;
import action.course.print.TeacherCounterTime;
import action.course.print.elearning.ElearningCou;
import action.course.print.elearning.ElearningOrg;
import action.course.print.elearning.ElearningPer;
import action.course.print.elearning.ElearningSel;

public class CourseManagerBase extends BaseAction{
	
 	public void resetForm(){
		cno="";sno="";dno="";gno=""; zno="";Dtime_oid="";techid="";cscode=""; opt=""; open=""; block=""; many=""; ele=""; pay="";		
			
	}
	
	/**
	 * 編輯1科目多教師
	 * @return
	 */
	public String editMulTech(){
		
		String tmpid=df.sqlGetStr("SELECT techid FROM Dtime WHERE Oid="+Dtime_oid);
		if(tmpid!=null)
		if(tmpid.length()>0){
			Message msg=new Message();
			msg.setError("分組名單不可與掛名教師同時存在");
			this.savMessage(msg);
			return edit();
		}
		
		request.setAttribute("dtime", df.sqlGetMap("SELECT d.Oid, c.ClassName, cs.chi_name FROM Dtime d, Class c, Csno cs WHERE " +
		"d.depart_class=c.ClassNo AND d.cscode=cs.cscode AND d.Oid="+Dtime_oid)); 
		
		request.setAttribute("ss", df.sqlGet("SELECT s.Oid, st.student_no, st.student_name, s.Dtime_teacher FROM " +
		"stmd st, Seld s WHERE st.student_no=s.student_no AND s.Dtime_oid="+Dtime_oid+" ORDER BY st.student_no"));
		
		request.setAttribute("ts", df.sqlGet("SELECT dt.Oid, e.cname FROM Dtime_teacher dt, Dtime d, " +
		"empl e WHERE e.idno=dt.teach_id AND dt.Dtime_oid=d.Oid AND d.Oid="+Dtime_oid));
		
		return "editMulTech";
	}
	
	/**
	 * 儲存一科目多教師學生名單
	 * @return
	 */
	public String saveMultSeld(){
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '修改分組名單');");
		if(stds!=null)
		for(int i=0; i<stds.length; i++){
			if(!techids[i].trim().equals("")){
				df.exSql("UPDATE Seld SET Dtime_teacher='"+techids[i]+"'WHERE Oid="+stds[i]);
			}else{
				df.exSql("UPDATE Seld SET Dtime_teacher=null WHERE Oid="+stds[i]);
			}
		}		
		return editMulTech();
	}	
	
	/**
	 * 快速查詢
	 * @return
	 */
	public String searchMix(){
		if(Dtime_oid.indexOf(",")>0){
			Dtime_oid=Dtime_oid.substring(0, Dtime_oid.indexOf(","));
		}else{
			return SUCCESS;
		}		
		return edit();
	}
	
	/**
	 * 標準查詢條件
	 * @return
	 */
	public List<Map>getDtimeList(){			
		StringBuilder sb=new StringBuilder("SELECT e.category, ce.sname as eleName, IFNULL(e.cname,'')as cname,(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt, d.Select_Limit," +
		"d.Oid, cd.name as optName, cs.cscode,cs.chi_name, d.credit, c.ClassName, d.thour, d.y_pro, d.y_pro_eng FROM CODE_DTIME_ELEARNING ce, CODE_DTIME_OPT cd," +
		"Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Csno cs, Class c WHERE d.elearning=ce.id AND cd.id=d.opt AND d.cscode=cs.cscode AND d.depart_class=c.ClassNo AND " +		
		"d.Sterm='"+Sterm+"'");
		
		if(!location.equals(""))sb.append("AND d.Oid IN(SELECT Dtime_oid FROM Dtime_class WHERE place='"+location.substring(0, location.indexOf(","))+"')");
		
		if(!opt.equals(""))sb.append("AND d.opt='"+opt+"'");
		if(!ele.equals(""))sb.append("AND d.elearning='"+ele+"'");
		if(!pay.equals(""))sb.append("AND d.extrapay='"+pay+"'");
		if(!nonSeld.equals(""))sb.append("AND d.nonSeld='"+nonSeld+"'");
		
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		
		if(!y_pro.equals(""))sb.append("AND d.y_pro='"+y_pro+"'");
		if(!y_pro_eng.equals(""))sb.append("AND d.y_pro_eng='"+y_pro_eng+"'");
		
		if(techid.indexOf(",")>0){//test empty for techid
			sb.append("AND e.idno ='"+df.sqlGetStr("SELECT idno FROM empl WHERE Oid="+techid.substring(0, techid.indexOf(",")))+"' ");
		}		
		
		//文字模糊搜尋
		if(cscode.trim().length()>0){
			if(cscode.indexOf(",")>0){//test empty for cscode
				sb.append("AND d.cscode='"+cscode.substring(0, cscode.indexOf(","))+"' ");
			}else{
				sb.append("AND cs.chi_name LIKE '%"+cscode+"%'");
			}
		}
				
		if(open.equals("1")){
			sb.append("AND d.Oid IN(SELECT Dtime_oid FROM Dtime_cross)");
		}else if(open.equals("2")){
			sb.append("AND d.Oid NOT IN(SELECT Dtime_oid FROM Dtime_cross)");
		}		
		if(block.equals("1")){
			sb.append("AND d.Oid IN(SELECT Dtime_oid FROM Dtime_block)");
		}else if(block.equals("2")){
			sb.append("AND d.Oid NOT IN(SELECT Dtime_oid FROM Dtime_block)");
		}		
		if(many.equals("1")){
			sb.append("AND d.Oid IN(SELECT Dtime_oid FROM Dtime_teacher)");
		}else if(many.equals("2")){
			sb.append("AND d.Oid NOT IN(SELECT Dtime_oid FROM Dtime_teacher)");
		}		
		sb.append("ORDER BY d.depart_class, d.cscode");	
		List<Map>dtimes=df.sqlGet(sb.toString());
		for(int i=0; i<dtimes.size(); i++){
			dtimes.get(i).put("time", df.sqlGet("SELECT * FROM Dtime_class WHERE Dtime_oid="+dtimes.get(i).get("Oid")));
		}
		return dtimes;
	}
	
	private List getDtimeOids(){
		StringBuilder sb=new StringBuilder("SELECT e.Oid as emplOid, d.Oid FROM Dtime d LEFT OUTER JOIN empl e ON e.idno=d.techid," +
		"Class c WHERE c.ClassNo=d.depart_class ");		
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		if(!opt.equals(""))sb.append("AND opt='"+opt+"'");
		if(!ele.equals(""))sb.append("AND d.elearning='"+ele+"'");
		if(!pay.equals(""))sb.append("AND d.extrapay='"+pay+"'");		
		sb.append("AND c.CampusNo='"+cno+"'AND "+				
		"d.Sterm='"+Sterm+"'");			
		if(techid.indexOf(",")>0){//test empty for techid
			sb.append("AND e.idno ='"+df.sqlGetStr("SELECT idno FROM empl WHERE Oid="+techid.substring(0, techid.indexOf(",")))+"' ");
		}		
		if(cscode.indexOf(",")>0){//test empty for cscode
			sb.append("AND d.cscode='"+cscode.substring(0, cscode.indexOf(","))+"' ");
		}		
		if(open.equals("1")){
			sb.append("AND d.Oid IN(SELECT Dtime_oid FROM Dtime_cross)");
		}else if(open.equals("2")){
			sb.append("AND d.Oid NOT IN(SELECT Dtime_oid FROM Dtime_cross)");
		}		
		if(block.equals("1")){
			sb.append("AND d.Oid IN(SELECT Dtime_oid FROM Dtime_block)");
		}else if(block.equals("2")){
			sb.append("AND d.Oid NOT IN(SELECT Dtime_oid FROM Dtime_block)");
		}		
		if(many.equals("1")){
			sb.append("AND d.Oid IN(SELECT Dtime_oid FROM Dtime_teacher)");
		}else if(many.equals("2")){
			sb.append("AND d.Oid NOT IN(SELECT Dtime_oid FROM Dtime_teacher)");
		}		
		sb.append("ORDER BY d.depart_class, d.cscode");
		return df.sqlGet(sb.toString());
	}
	
	/**
	 * 標準查詢
	 * @return
	 */
	public String search(){
		session.put("css", getDtimeList());//讓返回鍵有效因此採用session	
		return SUCCESS;
	}
	
	/**
	 * 修改指定課程
	 * @return
	 */
	public String edit(){
		
		Map<String, String>map=df.sqlGetMap("SELECT d.y_pro, d.y_pro_eng,d.nonSeld, d.Oid, d.Sterm, d.opt, d.credit, d.thour, d.elearning, d.extrapay, d.Select_LImit," +
		"cs.cscode, cs.chi_name, e.Oid as techid, e.cname, c.SchoolType, c.CampusNo, c.SchoolNo, c.DeptNo, Grade, SeqNo, c.ClassName FROM " +
		"Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Csno cs, Class c WHERE " +
		"d.cscode=cs.cscode AND d.depart_class=c.ClassNo AND d.Oid="+Dtime_oid);	
		request.setAttribute("cs", map);
		
		//排課
		request.setAttribute("dc", df.sqlGet("SELECT dc.*, n.name2 FROM Dtime_class dc LEFT OUTER JOIN Nabbr n ON dc.place=n.room_id WHERE dc.Dtime_oid="+Dtime_oid));
		//多教師
		request.setAttribute("dt", df.sqlGet("SELECT e.Oid, e.cname, dt.hours, dt.Oid as dtOid FROM Dtime d, Dtime_teacher dt, empl e WHERE d.Oid=dt.Dtime_oid AND e.idno=dt.teach_id AND d.Oid="+Dtime_oid));
		//跨選
		request.setAttribute("ot", df.sqlGet("SELECT d.* FROM Dtime_cross d WHERE d.Dtime_oid="+Dtime_oid));
		//擋修
		request.setAttribute("db", df.sqlGet("SELECT c.chi_name, d.* FROM Dtime_block d, Csno c WHERE d.cscode=c.cscode AND d.Dtime_oid="+Dtime_oid));
		//修改記錄
		request.setAttribute("dh", df.sqlGet("SELECT d.*, e.cname FROM Dtime_edit_hist d LEFT OUTER JOIN empl e ON d.auditor=e.idno WHERE d.Dtime_oid="+Dtime_oid+" ORDER BY d.edate DESC"));
		
		cno=map.get("CampusNo");
		sno=map.get("SchoolNo");
		dno=map.get("DeptNo");
		gno=map.get("Grade");
		zno=map.get("SeqNo");
		
		return "edit";
	}
	
	/**
	 * 返回
	 * @return
	 */
	public String back(){
		
		if(getSession().getAttribute("css")==null)return SUCCESS;
		StringBuilder sb=new StringBuilder("SELECT ce.sname as eleName, e.cname,(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt, d.Select_Limit," +
		"d.Oid, cd.name as optName, cs.cscode,cs.chi_name, d.credit, c.ClassName, d.thour FROM CODE_DTIME_ELEARNING ce, CODE_DTIME_OPT cd," +
		"Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Csno cs, Class c WHERE d.elearning=ce.id AND d.cscode=cs.cscode AND " +
		"c.ClassNo=d.depart_class AND cd.id=d.opt AND d.Oid IN("+Dtime_oid+",");
		List<Map>css=(List) getSession().getAttribute("css");		
		for(int i=0; i<css.size(); i++){
			sb.append(css.get(i).get("Oid")+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")ORDER BY d.depart_class, d.cscode");
		getSession().setAttribute("css", df.sqlGet(sb.toString()));
		
		/*
		 * 
		 * 
	public String opt;
	public String open;//開放選修
	public String block;//開放擋修
	public String many;//多教師
	public String ele;//遠距型態
	public String pay;//實習費
	public String cno, sno, dno, gno, zno;
		 */
		this.techid="";
		this.cscode="";
		this.opt="";
		this.open="";
		this.block="";
		this.many="";
		this.ele="";
		this.pay="";
		this.sno=""; this.dno=""; this.gno=""; this.zno="";
		
		return SUCCESS;
	}
	
	/**
	 * 檢核
	 * @param msg
	 * @return
	 */
	public Message check(Message msg){
		depart_class=getDepartClass(cno, sno, dno, gno, zno);
		List<Map>errCls;//=new ArrayList();//錯誤列表		
		StringBuilder err=new StringBuilder();
		List<Map>cls;
		
		if(techid.indexOf(",")>0){techid=getTechid(techid);};//教師		
		
		//班級重複開課
		if(df.sqlGet("SELECT Oid FROM Dtime WHERE depart_class='"+depart_class+"' AND cscode='"+cscode+"' AND Sterm='"+Sterm+"'").size()>1){
			err.append("班級重複開課");
		}
		
		//班級衝堂
		if(week!=null)
		for(int i=0; i<week.length; i++){
			if(week[i].equals("")||begin[i].equals("")||end[i].equals("")){continue;}//若任1欄位空白			
			if(week[i].equals("d")){continue;}//標記為刪除不檢查			
			cls=df.sqlGet("SELECT c.ClassName, cs.chi_name, dc.* FROM Dtime d, Dtime_class dc, Class c, Csno cs WHERE c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND " +
			"d.Sterm='"+Sterm+"' AND d.Oid=dc.Dtime_oid AND d.depart_class='"+depart_class+"' AND d.Oid!="+Dtime_oid+" ORDER BY dc.week");//已存排課
			
			errCls=cm.checkClass(Integer.parseInt(week[i]), Integer.parseInt(begin[i]), Integer.parseInt(end[i]), cls);	
			if(errCls.size()>0){
				err.append("班級衝堂<br>");				
				for(int j=0; j<errCls.size(); j++){
					err.append(errCls.get(j).get("Dtime_oid")+":"+errCls.get(j).get("ClassName")+errCls.get(j).get("chi_name")+",週"+errCls.get(j).get("week")+"第"+errCls.get(j).get("begin")+"-"+errCls.get(j).get("end")+"節<br>");
				}
			}
			
			//教師衝堂
			if(techid.length()>0){	
				cls=df.sqlGet("SELECT d.Oid, dc.* FROM Dtime d, Dtime_class dc WHERE d.Oid=dc.Dtime_oid AND d.Sterm='"+Sterm+"' AND d.techid='"+techid+"' AND d.Oid!="+Dtime_oid+"");
				cls.addAll(df.sqlGet("SELECT dc.* FROM Dtime_class dc, Dtime_teacher dt, Dtime d WHERE dc.Dtime_oid=dt.Dtime_oid AND dt.Dtime_oid=d.Oid AND d.Sterm='"+Sterm+"' AND dt.teach_id='"+techid+"'"));
				errCls=cm.checkClass(Integer.parseInt(week[i]), Integer.parseInt(begin[i]), Integer.parseInt(end[i]), cls);			
				
				if(errCls.size()>0){	
					err.append("教師衝堂<br>");
					for(int j=0; j<errCls.size(); j++){
						err.append(errCls.get(j).get("Dtime_oid")+"週"+errCls.get(j).get("week")+", 第"+errCls.get(j).get("begin")+"-"+errCls.get(j).get("end")+"節<br>");
					}
				}
			}
			
			//多教師衝堂			
			for(int j=0; j<techids.length; j++){
				if(!techids[j].trim().equals("")){
					//掛名教師排課
					cls=df.sqlGet("SELECT e.cname, d.Oid, dc.* FROM Dtime d, Dtime_class dc,empl e WHERE e.idno=d.techid AND d.Oid=dc.Dtime_oid AND d.Sterm='"+Sterm+"' AND d.techid='"+getTechid(techids[j])+"' AND d.Oid!="+Dtime_oid);
					//一科目多教師排課
					cls.addAll(df.sqlGet("SELECT e.cname, dc.* FROM empl e, Dtime_class dc, Dtime_teacher dt, Dtime d WHERE dt.teach_id=e.idno AND dc.Dtime_oid=dt.Dtime_oid AND dt.Dtime_oid=d.Oid AND d.Sterm='"+Sterm+"' AND dt.teach_id='"+getTechid(techids[j])+"'AND d.Oid!="+Dtime_oid));
					errCls=cm.checkClass(Integer.parseInt(week[i]), Integer.parseInt(begin[i]), Integer.parseInt(end[i]), cls);
					if(errCls.size()>0){
						err.append("多教師衝堂<br>");
						for(int k=0; k<errCls.size(); k++){
							err.append(errCls.get(k).get("cname")+",週"+errCls.get(k).get("week")+", 第"+errCls.get(k).get("begin")+"-"+errCls.get(k).get("end")+"節<br>");
						}
					}
					
				}
			}			
			
			//教室衝堂				
			if(place[i].indexOf(",")>0){
				place[i]=place[i].substring(0, place[i].indexOf(","));//地點格式化
				cls=df.sqlGet("SELECT c.ClassName,cs.chi_name,dc.* FROM Dtime d, Dtime_class dc,Class c,Csno cs WHERE d.depart_class=c.ClassNo AND d.cscode=cs.cscode AND d.Oid=dc.Dtime_oid AND d.Sterm='"+Sterm+"' AND dc.place='"+place[i]+"'AND d.Oid!="+Dtime_oid);
				errCls=cm.checkClass(Integer.parseInt(week[i]), Integer.parseInt(begin[i]), Integer.parseInt(end[i]), cls);			
				if(errCls.size()>0){	
					err.append("教室衝堂<br>");
					for(int j=0; j<errCls.size(); j++){
						err.append(errCls.get(j).get("place")+":"+errCls.get(j).get("ClassName")+errCls.get(j).get("chi_name")+"週"+errCls.get(j).get("week")+", 第"+errCls.get(j).get("begin")+"-"+errCls.get(j).get("end")+"節<br>");
					}
				}
			}		
		}
		if(err.length()>0)msg.setWarning(err.toString());
		return msg;
	}
	
	/**
	 * 報表處理
	 * @return
	 * @throws IOException 
	 */
	public String print() throws IOException{	
		String Syear=getContext().getAttribute("school_year").toString();
		//System.out.println(print);
		switch(print) { 
			//通用報表
        	case "ListDtime": ListDtime ld=new ListDtime();
        	ld.print(response, getDtimeList());
        	break;        	
        	//教師任教時數
        	case "TeacherCounterTime": TeacherCounterTime tct=new TeacherCounterTime();
        	tct.print(response, getDtimeOids());
        	break;
        	//科目教師對照
        	case "TeacherCounterCourse": TeacherCounterCourse tcc=new TeacherCounterCourse();
        	tcc.print(response, getDtimeOids(), Sterm);
        	break;
        	
        	//CourseCounterTeacher
        	case "CourseCounterTeacher": CourseCounterTeacher courseCounterTeacher=new CourseCounterTeacher();
        	courseCounterTeacher.print(response, getDtimeOids(), Sterm);
        	break;
        	
        	//TimeTableClass
        	case "TimeTableClass": TimeTable timeTableClass=new TimeTable();
        	timeTableClass.print(response, getDtimeOids(),null, null, null, Syear, Sterm, null);
        	break;
        	
        	//TimeTableTeacher
        	case "TimeTableTeacher": timeTableClass=new TimeTable();
        	timeTableClass.print(response, null, getDtimeOids(), null, null, Syear, Sterm, null);
        	break;
        	
        	//TimeTableTeacherNonStay
        	case "TimeTableTeacherNonStay": timeTableClass=new TimeTable();
        	timeTableClass.print(response, null, getDtimeOids(), null, null, Syear, Sterm, "1");
        	break;
        	
        	//TimeTableRoom
        	case "TimeTableRoom": timeTableClass=new TimeTable();
        	timeTableClass.print(response, null, null, null, getDtimeOids(), Syear, Sterm, null);
        	break;
        	
        	//coansw
        	case "CsCoansw": CsCoansw coansw=new CsCoansw();
        	coansw.print(response, getDtimeOids(), getContext().getAttribute("school_year").toString(), Sterm);
        	break;
        	
        	//StuSeltable
        	case "StuSeltable": StuSeltable stuSeltable=new StuSeltable();
        	stuSeltable.print(response, df.sqlGet("SELECT ClassName, ClassNo FROM Class WHERE CampusNO LIKE'"+cno+"%'AND SchoolNO LIKE'"+sno+"%'AND DeptNo LIKE'"+dno+"%'AND Grade LIKE'"+gno+"%'AND SeqNo LIKE'"+zno+"%'"), getContext().getAttribute("school_year").toString(), getContext().getAttribute("school_term").toString(),Sterm);
        	break;
        	
        	//ExamPacketFace
        	case "ExamPacketFace": ExamPacketFace examPacketFace=new ExamPacketFace();
        	examPacketFace.print(response, getDtimeOids(), getContext().getAttribute("school_year").toString(), Sterm);
        	break;
        	
        	//StuSelConfirmTable
        	case "StuSelConfirmTable": StuSelConfirmTable stuSelConfirmTable=new StuSelConfirmTable();
        	stuSelConfirmTable.print(response, getDtimeOids(), getContext().getAttribute("school_year").toString(), getContext().getAttribute("school_term").toString(), Sterm);
        	break;
        	
        	//Elearning
        	//ElearningPer
        	case"ElearningPer":ElearningPer elearningPer=new ElearningPer();
        	elearningPer.print(response);
        	break;
        	
        	//ElearningCou
        	case"ElearningCou":ElearningCou elearningCou=new ElearningCou();
        	elearningCou.print(response, getDtimeOids(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"));
        	break;
        	
        	//ElearningCou
        	case"ElearningOrg":ElearningOrg elearningOrg=new ElearningOrg();
        	elearningOrg.print(response);
        	break;
        	
        	//ElearningSel
        	case"ElearningSel":ElearningSel elearningSel=new ElearningSel();
        	elearningSel.print(response, getDtimeOids(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"));
        	break;
        	
        	//to E
        	//List4Dtime13
        	case"List4Dtime13":List4Dtime13 list4Dtime13=new List4Dtime13();
        	list4Dtime13.print(response, getDtimeOids(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"));
        	break;
        	
        	//List4Dtime32
        	case"List4Dtime32":List4Dtime32 list4Dtime32=new List4Dtime32();
        	list4Dtime32.print(response, getDtimeOids(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"));
        	break;
        	
        	//List4Seld52
        	case"List4Seld52":List4Seld52 list4Seld52=new List4Seld52();
        	list4Seld52.print(response, getDtimeOids(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"));
        	break;
        	
        	//List4DtimeG
        	case"List4DtimeG":List4DtimeG list4DtimeG=new List4DtimeG();
        	list4DtimeG.print(response, getDtimeList(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"));
        	break;
        	
        	//SylDoc
        	case"SylDoc":SylDoc SylDoc=new SylDoc();
        	SylDoc.print(response, getDtimeList(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"), false);
        	break;
        	
        	//SylDoc
        	case"IntorDoc":IntorDoc IntorDoc=new IntorDoc();
        	IntorDoc.print(response, getDtimeList(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"), false);
        	break;
        	
        	//List4Course35
        	case"List4Course35":List4Course35 list4Course35=new List4Course35();
        	list4Course35.print(response, getDtimeList(), (String)getContext().getAttribute("school_year"), (String)getContext().getAttribute("school_term"));
        	break;
        	
        	//List4Course35
        	case"List4211":List4211 List4211=new List4211();
        	List4211.print(response, cno,sno,dno,gno,zno,getContext().getAttribute("school_year").toString(),getContext().getAttribute("school_term").toString());
        	break;
        
	        default:Message msg=new Message();
	        msg.setError("無報表對應程式");
	        savMessage(msg);
	        return SUCCESS;
		}		
		return null;
	}
	
	/**
	 * 增加課程
	 * @return
	 */
	public String add(){
		StringBuilder err=new StringBuilder();
		
		depart_class=getDepartClass(cno, sno, dno, gno, zno);
		cscode=getCscode(cscode);
		if(depart_class==null){
			err.append("無此班級<br>");
		}
		
		if(opt.equals("")){
			err.append("未指定選別<br>");
		}
		
		if(cscode==null){
			err.append("無此課程代碼<br>");
		}		
		
		if(err.length()>0){
			Message msg=new Message();
			msg.setError(err.toString());
			this.savMessage(msg);				
			return SUCCESS;
		}
		
		Dtime d=new Dtime();
		d.setDepartClass(depart_class);		
		d.setCscode(cscode);
		d.setOpt(opt);
		d.setCredit(0f);
		d.setSamples(0);
		d.setThour(1);
		d.setSterm(Sterm);
		d.setSelectLimit(50);
		d.setY_pro(y_pro);
		d.setY_pro_eng(y_pro_eng);
		d.setCoansw(0f);
		if(ele.equals("")){d.setElearning("0");}else{d.setElearning(ele);}
		if(pay.equals("")){d.setExtrapay("0");}else{d.setExtrapay(pay);}
		if(nonSeld.equals("")){d.setNonSeld("0");}else{d.setNonSeld(nonSeld);}
		
		if(!y_pro.equals("")){d.setY_pro(y_pro);}else{d.setY_pro("0");}
		if(!y_pro_eng.equals("")){d.setY_pro_eng(y_pro_eng);}else{d.setY_pro_eng("0");}
		df.update(d);
		Dtime_oid=d.getOid().toString();
		
		Message msg=new Message();
		check(msg);
		if(msg.getWarning()!=null){
			savMessage(msg);
		}
		
		
		return edit();
	}
	
	/**
	 * 刪除課程
	 * @return
	 */
	public String delete(){		
		df.exSql("DELETE FROM Dtime_class WHERE Dtime_oid="+Dtime_oid);//刪排課
		df.exSql("DELETE FROM Dtime_teacher WHERE Dtime_oid="+Dtime_oid);//刪多教師
		df.exSql("DELETE FROM Dtime_block WHERE Dtime_oid="+Dtime_oid);//刪跨選
		df.exSql("DELETE FROM Dtime WHERE Oid="+Dtime_oid);//刪課程
		df.exSql("INSERT INTO SYS_LOG(action,cname,note)VALUES('/csis/CourseManager','"+getSession().getAttribute("userid")+"','將"+Dtime_oid+"刪除');");
		Message msg=new Message();
		msg.setSuccess("刪除完成");
		savMessage(msg);
		return back();
	}
	
	/**
	 * 儲存排課
	 * @return
	 */
	public String saveDtimeClass(){
		request.setAttribute("timeTable", true);
		Message msg=new Message();	
		df.exSql("DELETE FROM Dtime_class WHERE Dtime_oid="+Dtime_oid);//刪除資料庫中現存全部排課
		
		DtimeClass dc;
		for(int i=0; i<week.length; i++){
			
			if(!week[i].equals("")&&!begin[i].equals("")&&!end[i].equals("")){
				
				
				dc=new DtimeClass();
				dc.setDtimeOid(Integer.parseInt(Dtime_oid));
				dc.setWeek(Integer.parseInt(week[i]));
				dc.setBegin(begin[i]);
				dc.setEnd(end[i]);	
				try {
					dc.setPlace(place[i].substring(0, place[i].indexOf(",")));
				}catch(Exception e) {
					dc.setPlace(null);
				}
				
				
				//該死的學分班
				if(Integer.parseInt(Sterm)>2)
				if(!beginDate[i].equals("")&& !endDate[i].equals("")) {
					
					try {
						dc.setBeginDate(sf.parse(beginDate[i]));
						dc.setEndDate(sf.parse(endDate[i]));
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
				
				try{	
					//df.exSql("INSERT INTO Dtime_class(week,begin,end,place,Dtime_oid)VALUES('"+week[i]+"','"+begin[i]+"','"+end[i]+"','"+getPlace(place[i])+"',"+Dtime_oid+");");
					df.update(dc);
				}catch(Exception e){
					e.printStackTrace();
					msg.setError("排課重複");
					savMessage(msg);
					return edit();
				}
				
			}
		}		
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '修改上課時段');");
		msg=check(msg);	
		msg.setSuccess("儲存完成");
		savMessage(msg);
		return edit();
	}
	
	/**
	 * 儲存多教師
	 * @return
	 */
	public String saveMulTech(){
		request.setAttribute("mulTech", true);
		Message msg=new Message();
		//df.exSql("DELETE FROM Dtime_teacher WHERE Dtime_oid="+Dtime_oid);
		for(int i=0; i<techids.length; i++){
			if(!techids[i].trim().equals("")){				
				try{
					df.exSql("INSERT INTO Dtime_teacher(Dtime_oid, teach_id, hours)VALUES("+Dtime_oid+",'"+getTechid(techids[i])+"', "+hours[i]+") ON DUPLICATE KEY UPDATE hours="+hours[i]+", teach_id='"+getTechid(techids[i])+"';");
				}catch(Exception e){
					msg.setError("輸入資料有誤");
					savMessage(msg);
					return edit();
				}
			}			
		}
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '修改一科目多教師');");
		msg=check(msg);	
		msg.setSuccess("儲存完成");
		savMessage(msg);
		return edit();
	}
	
	public String delMulTech(){
		
		request.setAttribute("mulTech", true);
		Message msg=new Message();
		df.exSql("DELETE FROM Dtime_teacher WHERE Oid="+Dtime_teacher_oid);
		df.exSql("UPDATE Seld SET Dtime_teacher=null WHERE Dtime_teacher="+Dtime_teacher_oid);
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '刪除一科目多教師');");
		msg.setSuccess("刪除完成");
		savMessage(msg);
		return edit();
	}
	
	/**
	 * 儲存跨選規則
	 * @return
	 */
	public String saveOpencs(){
		request.setAttribute("opencs", true);
		Message msg=new Message();
		df.exSql("DELETE FROM Dtime_cross WHERE Dtime_oid="+Dtime_oid);
		for(int i=0; i<cidno.length; i++){
			if(!cidno[i].equals("")&&!sidno[i].equals("")&&!didno[i].equals("")&&!grade[i].equals("")&&!classes[i].equals("")){
				try{
					df.exSql("INSERT INTO Dtime_cross(Cidno, Sidno, Didno, Grade, ClassNo, Dtime_oid)VALUES('"+cidno[i]+"', '"+sidno[i]+"', '"+didno[i]+"', '"+grade[i]+"', '"+classes[i]+"', "+Dtime_oid+")");
				}catch(Exception e){
					msg.setError("跨選規則重複");
					savMessage(msg);
					return edit();
				}
			}
			
			/*else{
				if(cidno[i].equals("")||sidno[i].equals("")||didno[i].equals("")||grade[i].equals("")||classes[i].equals("")){
					msg.setError("跨選規則不完整");
					savMessage(msg);
					return edit();
				}
			}*/
			
			
		}
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '修改跨選規則');");
		msg.setSuccess("已儲存");
		msg=check(msg);
		savMessage(msg);
		return edit();
	}
	
	/**
	 * 儲存擋修規則
	 * @return
	 */
	public String saveBlock(){
		request.setAttribute("block", true);
		Message msg=new Message();
		df.exSql("DELETE FROM Dtime_block WHERE Dtime_oid="+Dtime_oid);
		for(int i=0; i<cscodes.length; i++){
			if(cscodes[i].indexOf(",")>0){
				try{
					df.exSql("INSERT INTO Dtime_block(cscode,Dtime_oid)VALUES('"+cscodes[i].substring(0, cscodes[i].indexOf(","))+"', "+Dtime_oid+");");
				}catch(Exception e){
					msg.setError("擋修規則重複");
					savMessage(msg);
					return edit();
				}
			}
		}
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '修改擋修規則');");
		msg=check(msg);	
		msg.setSuccess("儲存完成");
		savMessage(msg);
		return edit();
	}
	
	/**
	 * 儲存課程基本資料
	 * @return
	 */
	public String saveDtime(){
		Message msg=new Message();
		depart_class=getDepartClass(cno, sno, dno, gno, zno);	
		cscode=getCscode(cscode);
		if(depart_class==null){
			msg.setError("無此班級");
			this.savMessage(msg);				
			return edit();
		}
		if(cscode==null){
			msg.setError("無此課程代碼");
			this.savMessage(msg);				
			return edit();
		}
		
		Dtime d=(Dtime) df.hqlGetListBy("FROM Dtime WHERE Oid="+Dtime_oid).get(0);		
		
		if(!techid.trim().equals("")){
			d.setTechid(getTechid(techid));
		}else{
			d.setTechid(null);
		}
		
		d.setOpt(opt);
		d.setCredit(credit);
		d.setThour(thour);
		d.setElearning(ele);
		d.setExtrapay(pay);
		d.setSelectLimit(Select_Limit);
		d.setNonSeld(nonSeld);		
		d.setCscode(cscode);
		d.setDepartClass(depart_class);
		
		if(!y_pro.equals("")){d.setY_pro(y_pro);}else{d.setY_pro(null);}
		if(!y_pro_eng.equals("")){d.setY_pro_eng(y_pro_eng);}else{d.setY_pro_eng(null);}
			
		try{
			df.update(d);	
		}catch(Exception e){
			e.printStackTrace();
			msg.setError("儲存失敗, 請檢查各欄位資料");
			savMessage(msg);
			return edit();
		}
			
		msg=check(msg);
		msg.setSuccess("儲存完成");
		savMessage(msg);
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '修改課程內容');");
		return edit();
	}
	
	/**
	 * 轉換教師Oid為idno
	 */
	public String getTechid(String str){
		try{
			str=str.substring(0, str.indexOf(","));
			return df.sqlGetStr("SELECT idno FROM empl WHERE Oid="+str);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * change place to nabbr format
	 * @param str
	 * @return
	 */
	public String getPlace(String str){
		try{
			return str.substring(0, str.indexOf(","));
		}catch(Exception e){
			return "";
		}		
	}
	
	/**
	 * change cscode to Csno format
	 * @param cscode
	 * @return
	 */
	public String getCscode(String str){			
		try{
			return str.substring(0, str.indexOf(","));
		}catch(Exception e){
			return null;
		}		
	}
	
	/**
	 * 轉換班級cno,sno,dno,gno,zno to depart_class
	 * @return
	 */
	public String getDepartClass(String cno, String sno, String dno, String gno, String zno){
		try{
			return df.sqlGetStr("SELECT ClassNo FROM Class WHERE CampusNo='"+cno+"' AND SchoolNo='"+sno+"' AND DeptNo='"+dno+"' AND Grade='"+gno+"' AND SeqNo='"+zno+"'");
		}catch(Exception e){
			return null;
		}
	}
	
	
	public String assignSeld;
	/**
	 * assign all seld for only techid;
	 * @return
	 */
	public String assignSeld(){
		request.setAttribute("mulTech", true);
		Message msg=new Message();
		df.exSql("INSERT INTO Dtime_edit_hist(Dtime_oid, auditor, note)VALUES("+Dtime_oid+", '"+getSession().getAttribute("userid")+"', '一科目多教師全班授權');");
		String tech_id=df.sqlGetStr("SELECT idno FROM empl WHERE Oid="+assignSeld);
		df.exSql("UPDATE Seld SET Dtime_teacher="+df.sqlGetStr("SELECT Oid FROM Dtime_teacher WHERE teach_id='"+tech_id+"' AND Dtime_oid="+Dtime_oid)+" WHERE Dtime_oid="+Dtime_oid);
		df.exSql("UPDATE Dtime SET techid=null WHERE Oid="+Dtime_oid);
		msg.setSuccess("已儲存");
		this.savMessage(msg);
		return edit();
	}
	
	private Map getForm(){
		
		Map map=new HashMap();
		map.put("Dtime_oid", Dtime_oid);
		
		map.put("cscode", cscode);
		map.put("techid", getTechid(techid));
		map.put("Dtime_oid", Dtime_oid);
		
		return map;
	}
	
	public String Dtime_oid;
	public String depart_class;
	public String Dtime_teacher_oid;
	
	public Float credit;	
	public int thour, Select_Limit;	
	public String cscode, techid;
	
	public String printType;
	
	public String Sterm;
	public String opt;
	public String open;//開放選修
	public String block;//開放擋修
	public String nonSeld;//開放退選
	public String many;//多教師
	public String ele;//遠距型態
	public String pay;//實習費
	public String cno, sno, dno, gno, zno;
	
	public String y_pro, y_pro_eng;
	
	public String[] cidno, sidno, didno, grade, classes;	
	public String[] cscodes, techids, hours, stds, week, begin, end, place, beginDate, endDate;
	
	
	public String location;
	public String print;//報表型態
	
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	
	private void saveLog(){
		
		
	}	

}