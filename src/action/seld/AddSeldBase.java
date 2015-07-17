package action.seld;

import java.util.List;
import java.util.Map;
import model.Message;
import action.BaseAction;

/**
 * 行政加退選
 * @author John
 *
 */
public class AddSeldBase extends BaseAction{
	
	public String dkey;
	public String dvalue;	
	public String term;
	public String stdName;
	public String stdNo;	
	public String Oid;
	public String delNo;
	
	public String cno;
	public String sno;
	public String dno;
	public String gno;
	public String zno;	
	
	public int scrolltop;
	
	public String searchClass(){
		request.setAttribute("css", df.sqlGet("SELECT d.Oid, o.name as opt, d.thour, d.credit, d.Select_Limit,cs.cscode,"
		+ "c.ClassName, cs.chi_name, IFNULL(e.cname,'')as cname, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt FROM "
		+ "CODE_DTIME_OPT o,Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Class c, Csno cs WHERE "
		+ "cs.cscode=d.cscode AND o.id=d.opt AND d.depart_class=c.ClassNo AND d.Sterm='"+term+"' AND "
		+ "c.CampusNo='"+cno+"' AND c.SchoolNo LIKE'"+sno+"%' AND c.DeptNo LIKE'"+dno+"%' AND c.Grade LIKE '"+gno+"%' AND c.SeqNo LIKE '"+zno+"%'"));
		
		return SUCCESS;
	}
	
	/**
	 * 清除本班
	 * @return
	 */
	public String clearSeld(){		
		df.exSql("DELETE FROM Seld WHERE Dtime_oid="+Oid+" AND student_no IN(SELECT student_no FROM stmd WHERE depart_class=(SELECT depart_class FROM Dtime WHERE Oid="+Oid+"))");		
		//Message msg=new Message();
		//msg.setSuccess("已清除"+Oid+"選課");
		//this.savMessage(msg);
		return searchClass();
	}
	
	/**
	 * 清除所有
	 * @return
	 */
	public String clearAllSeld(){
		df.exSql("DELETE FROM Seld WHERE Dtime_oid="+Oid);
		return searchClass();
	}
	
	public String generateThisTerm(){		
		try{
			df.exSql("INSERT INTO Seld(student_no, Dtime_oid)SELECT s.student_no, '"+
			Oid+"' FROM Dtime d, stmd s WHERE d.depart_class=s.depart_class AND d.Oid="+Oid+" "
					+ "ON DUPLICATE KEY UPDATE Seld.student_no=Seld.student_no");
						
		}catch(Exception e){
			e.printStackTrace();
			Message msg=new Message();
			msg.setError("建立"+Oid+"本學期選課時發生錯誤");	
			this.savMessage(msg);
		}
		
		return searchClass();
	}
	
	public String generateUpgrade(){
		Map c=df.sqlGetMap("SELECT c.CampusNo, c.SchoolNo, c.DeptNo, FORMAT(c.Grade-1, 0)as Grade, c.SeqNo FROM Class "
		+ "c, Dtime d WHERE c.ClassNo=d.depart_class AND d.Oid="+Oid);		
		
		try{
			df.exSql("INSERT INTO Seld(student_no, Dtime_oid)SELECT s.student_no, '"+Oid+"' FROM Class c, stmd s WHERE c.ClassNo=s.depart_class AND "
			+ "c.CampusNo='"+c.get("CampusNo")+"' AND c.SchoolNo='"+c.get("SchoolNo")+"' AND c.DeptNo='"+c.get("DeptNo")+"' AND c.Grade='"+
			c.get("Grade")+"' AND c.SeqNo='"+c.get("SeqNo")+"'");
			//msg.setSuccess("已建立"+Oid+"升級選課");
		}catch(Exception e){
			Message msg=new Message();
			msg.setError("已建立"+Oid+"升級選課時發生錯誤");
			this.savMessage(msg);
		}		
		
		return searchClass();
	}
	
	private List getStdSelds(){
		return df.sqlGet("SELECT s.student_no,st.student_name, cd.name as opt, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt," +
				"d.Select_Limit,if(d.extrapay='1','是','')as ext,d.extrapay,s.Oid, s.Dtime_oid, c.cscode, c.chi_name, "
				+ "cl.ClassNo, cl.ClassName,d.credit,d.thour FROM CODE_DTIME_OPT cd," +
				"Seld s, Dtime d, Csno c, Class cl, stmd st WHERE cd.id=d.opt AND st.student_no=s.student_no AND " +
				"cl.ClassNo=d.depart_class AND s.Dtime_oid=d.Oid AND d.cscode=c.cscode AND d.Sterm='"+term+"' AND " +
				"s.student_no='"+stdNo+"' ORDER BY s.Oid DESC");
	}
	
	private List getStdClass(){		
		return df.sqlGet("SELECT distinct c.chi_name,dc.* FROM Seld s,Dtime_class dc,Dtime d,Csno c " +
		"WHERE s.Dtime_oid=dc.Dtime_oid AND d.cscode=c.cscode AND dc.Dtime_oid=d.Oid AND (s.student_no='"+stdNo+"' or s.Dtime_oid='"+dvalue+"')");
	}
	
	private Map getStdInfo(){		
		return df.sqlGetMap("SELECT SUM(d.credit)as credit, SUM(d.thour)as thour FROM " +
		"Dtime d, Seld s WHERE d.Sterm='"+term+"' AND d.Oid=s.Dtime_oid AND s.student_no='"+stdNo+"'");
	}
	
	private List getDtimeSelds(){
		return df.sqlGet("SELECT st.student_no,st.student_name,d.thour,if(d.extrapay='1','是','')as ext,(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt," +
				"d.Select_Limit,s.Oid, s.Dtime_oid, c.cscode, c.chi_name, cl.ClassNo, cl.ClassName FROM " +
				"Seld s, Dtime d, Csno c, Class cl, stmd st WHERE st.student_no=s.student_no AND " +
				"cl.ClassNo=d.depart_class AND s.Dtime_oid=d.Oid AND d.cscode=c.cscode AND d.Sterm='"+term+"' AND " +
				"s.Dtime_oid='"+dvalue+"' ORDER BY st.student_no");		
	}
	
	public String searchStd(){
		Message msg=new Message();
		if(stdNo.trim().equals("")){
			msg.setError("請指定學生");
			savMessage(msg);
			return SUCCESS;
		}
		request.setAttribute("std_selds", getStdSelds());
		request.setAttribute("sumCredit", getStdInfo());
		request.setAttribute("classes", getStdClass());
		return SUCCESS;
	}
	
	public String searchDtime(){
		Message msg=new Message();
		if(dvalue.trim().equals("")){
			msg.setError("請指定課程");
			savMessage(msg);
			return SUCCESS;
		}
		request.setAttribute("dtime_selds", getDtimeSelds());	
		request.setAttribute("classes", getStdClass());
		return SUCCESS;
	}
	
	/**
	 * 儲存
	 */
	private void saveSeld(){		
		df.exSql("INSERT INTO Seld(student_no, Dtime_oid)VALUES('"+stdNo+"', '"+dvalue+"');");
		df.exSql("INSERT INTO SeldHist(type,StudentNo,depart_class,cscode,idno)SELECT 'A','"+stdNo+"', depart_class, cscode, '"+getSession().getAttribute("userid")+"' FROM Dtime WHERE Oid="+dvalue);
	}
	
	/**
	 * 個人加選
	 * @return
	 */
	public String createSeldByStd(){
		Message msg=new Message();
		if(stdNo.trim().equals("")||dvalue.trim().equals("")){
			msg.setError("請指定學生和課程");
			savMessage(msg);
			return SUCCESS;
		}		
		StringBuilder err=check();
		
		if(err.length()>0){
			err.append("<br><button class='btn btn-danger' onClick='$(\"#compel\").trigger(\"click\");'>強制加選</button>");
			msg.setError(err.toString());
			savMessage(msg);
			return SUCCESS;
		}
		
		try{
			saveSeld();
			msg.setSuccess("加選完成");
		}catch(Exception e){
			msg.setError("<br><b>加選失敗，選課資料儲存發生問題</b> ");			
		}		
		savMessage(msg);
		//request.setAttribute("selds", getStdSelds());
		//request.setAttribute("sumCredit", getStdInfo());
		//request.setAttribute("classes", getStdClass());
		return searchStd();
	}
	
	private StringBuilder check(){
		StringBuilder err=new StringBuilder();
		Map dtime=df.sqlGetMap("SELECT d.Select_Limit, cs.cscode, cs.chi_name FROM Dtime d, Csno cs WHERE d.cscode=cs.cscode AND d.Oid="+dvalue);
			
		//重複chi_name選課
		if(df.sqlGetInt("SELECT COUNT(*) FROM ScoreHist s, Csno c WHERE " +
		"s.cscode=c.cscode AND c.chi_name='"+dtime.get("chi_name")+"' AND s.student_no='"+stdNo+"' AND " +
		"(s.score>=60 || s.score IS NULL)")>0){		
			err.append("課程已修得<br>");
		}		
		
		//重複cscode選課
		if(df.sqlGetInt("SELECT COUNT(*) FROM ScoreHist s, Dtime d WHERE d.cscode=s.cscode AND " +
		"d.Oid="+dvalue+" AND s.student_no='"+stdNo+"' AND (s.score>=60 || s.score IS NULL)")>0||		
		
		df.sqlGetInt("SELECT COUNT(*)FROM Seld s, Dtime d WHERE s.Dtime_oid=d.Oid AND " +
		"d.cscode='"+dtime.get("cscode")+"' AND s.student_no='"+stdNo+"' AND d.Sterm='"+term+"'")>0){
			err.append("課程已修得或本學期重複修課<br>");
		}		
		
		//超過選課上限 TODO 操他媽是多少？
		if(df.sqlGetInt("SELECT SUM(d.credit) FROM Dtime d, Seld s WHERE " +
		"d.Sterm='"+term+"' AND d.Oid=s.Dtime_oid AND s.student_no='"+stdNo+"'")>22){	
			err.append("超過規定選課上限<br>");			
		}
		
		//衝堂		
		List<Map>thisTime=df.sqlGet("SELECT week, begin, end FROM Dtime_class  WHERE Dtime_oid="+dvalue);
		List<Map>tmp;
		for(int i=0; i<thisTime.size(); i++){
			try{
				tmp=cm.checkReSelds(stdNo, dvalue, 
						thisTime.get(i).get("week").toString(),
						thisTime.get(i).get("begin").toString(),
						thisTime.get(i).get("end").toString(), String.valueOf(term));				
				if(tmp!=null){					
					for(int j=0; j<tmp.size(); j++){
						err.append("上課時間重複: 星期"+tmp.get(j).get("week")+"第"+tmp.get(j).get("begin")
								+"至"+tmp.get(j).get("end")+"節, "+tmp.get(j).get("ClassName")+", "+tmp.get(j).get("chi_name"));
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				err.append("課程設定有問題無法偵測衝堂<br>");
			}
		}
		
		//上限
		try{
			int selected=df.sqlGetInt("SELeCT COUNT(*) FROM Seld WHERE Dtime_oid="+dvalue);
			if((selected+1)>Integer.parseInt(dtime.get("Select_Limit").toString())){
				err.append("超過人數上限(已選"+selected+")<br>");
			}			
		}catch(Exception e){
			err.append("課程上限設定有問題<br>");
		}
		
		return err;
	}
	
	/**
	 * 強制加選
	 * @return
	 */
	public String compel(){
		Message msg=new Message();
		try{
			saveSeld();
			msg.setSuccess("加選完成");
		}catch(Exception e){
			e.printStackTrace();
			msg.setError("<b>加選失敗，本學期重複選課</b> ");			
		}
		savMessage(msg);
		request.setAttribute("selds", getStdSelds());
		request.setAttribute("sumCredit", getStdInfo());
		request.setAttribute("classes", getStdClass());
		return SUCCESS;
	}
	
	public String deleteSeldByStd(){
		Message msg=new Message();
		df.exSql("INSERT INTO SeldHist(type,StudentNo,depart_class,cscode,idno)SELECT 'D','"+delNo+"', depart_class, cscode, '"+getSession().getAttribute("userid")+"' FROM Dtime WHERE Oid="+df.sqlGetStr("SELECT Dtime_oid FROM Seld WHERE Oid="+Oid));
		df.exSql("DELETE FROM Seld WHERE Oid="+Oid);
		
		
		msg.setSuccess("已刪除");
		savMessage(msg);
		/*
		request.setAttribute("selds", getStdSelds());
		request.setAttribute("sumCredit", getStdInfo());
		request.setAttribute("classes", getStdClass());
		*/
		return searchStd();
	}
	
	public String deleteSeldByDtime(){
		Message msg=new Message();
		df.exSql("INSERT INTO SeldHist(type,StudentNo,depart_class,cscode,idno)SELECT 'D','"+delNo+"', depart_class, cscode, '"+getSession().getAttribute("userid")+"' FROM Dtime WHERE Oid="+df.sqlGetStr("SELECT Dtime_oid FROM Seld WHERE Oid="+Oid));
		df.exSql("DELETE FROM Seld WHERE Oid="+Oid);
		msg.setSuccess("已刪除");
		savMessage(msg);
		/*
		request.setAttribute("selds", getStdSelds());
		request.setAttribute("sumCredit", getStdInfo());
		request.setAttribute("classes", getStdClass());
		*/
		return searchDtime();
	}

}
