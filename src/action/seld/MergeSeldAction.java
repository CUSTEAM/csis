package action.seld;

import model.Message;
import action.BaseAction;

/**
 * 併班
 * @author shawn
 *
 */
public class MergeSeldAction extends BaseAction{
	
	public String targetOid;
	public String[] dtimeOids;
	public String[] ClassNos;
	public String term;
	
	public String execute(){		
		return SUCCESS;
	}
	
	public String mergeSeld(){
		Message msg=new Message();
		if(targetOid.indexOf(",")<1){
			msg.setError("請輸入合併後課程");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		int sum=0;
		String Oid=targetOid.substring(0, targetOid.indexOf(","));
		StringBuilder sql=new StringBuilder("SELECT d.Oid, cs.chi_name, c.ClassName,(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt "
		+ "FROM Dtime d, Csno cs, Class c WHERE d.depart_class=c.ClassNo AND d.cscode=cs.cscode AND d.Oid IN("+Oid+",");
		StringBuilder note=new StringBuilder();
		for(int i=0; i<dtimeOids.length; i++){
			if(dtimeOids[i].indexOf(",")>0){
				sum+=1;
				//df.exSql("UPDATE Seld SET Dtime_oid="+targetOid.substring(0, targetOid.indexOf(","))+
				//" WHERE Dtime_oid="+dtimeOids[i].substring(0, dtimeOids[i].indexOf(",")));//合併
				df.exSql("INSERT INTO Seld(Dtime_oid, student_no)SELECT "+targetOid.substring(0, targetOid.indexOf(","))+", s.student_no FROM "
				+ "Seld s WHERE s.Dtime_oid="+dtimeOids[i].substring(0, dtimeOids[i].indexOf(","))+" ON DUPLICATE KEY UPDATE Seld.Oid=Seld.Oid");
				df.exSql("DELETE FROM Seld WHERE Dtime_oid="+dtimeOids[i].substring(0, dtimeOids[i].indexOf(",")));
				
				sql.append(dtimeOids[i].substring(0, dtimeOids[i].indexOf(","))+",");//載入結果
				note.append(dtimeOids[i].substring(0, dtimeOids[i].indexOf(","))+",");//載入記錄
				df.exSql("UPDATE Dtime SET Select_Limit=0 WHERE Oid="+dtimeOids[i].substring(0, dtimeOids[i].indexOf(",")));//變更人數上限
			}
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");		
		if(note.length()<1){
			msg.setError("請輸入被合併課程");
			this.savMessage(msg);
			return SUCCESS;
		}
		note.delete(note.length()-1, note.length());
		
		request.setAttribute("css", df.sqlGet(sql.toString()));		
		df.exSql("INSERT INTO SYS_LOG(action,cname,note)VALUES('MergeSeld','"+df.sqlGetStr("SELECT cname FROM empl WHERE idno='"+getSession().getAttribute("userid")+"'")+"','將"+note+"併入"+Oid+"')");
		df.exSql("UPDATE Dtime SET nonSeld='1', pid="+sum+" WHERE Oid="+Oid);//變更選課規則
		msg.setSuccess("已合併");
		this.savMessage(msg);
		return SUCCESS;
	}
	
	public String mergeClass(){
		Message msg=new Message();
		if(targetOid.indexOf(",")<1){
			msg.setError("請輸入合併後課程");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		
		String Oid=targetOid.substring(0, targetOid.indexOf(","));		
		int sum=0;
		StringBuilder note=new StringBuilder();
		
		for(int i=0; i<ClassNos.length; i++){
			if(ClassNos[i].indexOf(",")>0){
				sum+=1;
				try{
					df.exSql("INSERT INTO Seld(student_no, Dtime_oid)SELECT student_no, "+Oid+" FROM stmd WHERE depart_class='"+ClassNos[i].substring(0, ClassNos[i].indexOf(","))+"'");//合併
				}catch(Exception e){
					msg.addError(ClassNos[i].substring(0, ClassNos[i].indexOf(","))+"無法合併<br>");
				}								
				note.append(ClassNos[i].substring(0, ClassNos[i].indexOf(","))+",");//載入記錄
			}
		}
		
		if(note.length()<1){
			msg.setError("請輸入被合併課程");
			this.savMessage(msg);
			return SUCCESS;
		}
		note.delete(note.length()-1, note.length());
		
		request.setAttribute("css", df.sqlGet("SELECT d.Oid, cs.chi_name, c.ClassName,(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt "
		+ "FROM Dtime d, Csno cs, Class c WHERE d.depart_class=c.ClassNo AND d.cscode=cs.cscode AND d.Oid ="+Oid));		
		df.exSql("INSERT INTO SYS_LOG(action,cname,note)VALUES('MergeSeld','"+df.sqlGetStr("SELECT cname FROM empl WHERE idno='"+getSession().getAttribute("userid")+"'")+"','將"+note+"併入"+Oid+"')");
		//df.exSql("UPDATE Dtime SET nonSeld='1' WHERE Oid="+Oid);//變更選課規則
		df.exSql("UPDATE Dtime SET nonSeld='1', pid="+sum+" WHERE Oid="+Oid);//變更選課規則
		msg.setSuccess("已合併");
		this.savMessage(msg);
		return SUCCESS;
	}
}