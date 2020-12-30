package action.coansw;

import java.util.List;
import java.util.Map;

import model.Message;
import action.BaseAction;

public class CoanswManager extends BaseAction{
	
	public String Oid;
	public String eOid[];
	public String dOid[];
	//public String topic;
	public String question[];
	public String debug[];
	public String sequence[];
	
	public String cdate[];
	public String name[];
	
	
	
	public String execute(){
		
		request.setAttribute("cals", df.sqlGet("SELECT *, DATE_FORMAT(cdate,'%Y-%m-%d %H:%i')as date FROM SYS_CALENDAR WHERE s_group='Coansw'"));
		
		request.setAttribute("topic", df.sqlGet("SELECT * FROM Question_topic"));
		return SUCCESS;
	}
	
	public String update(){
		for(int i=0; i<name.length; i++){			
			if(!name[i].trim().equals("")){
				df.exSql("UPDATE SYS_CALENDAR SET cdate='"+cdate[i]+"', editor='"+df.sqlGetStr("SELECT cname FROM empl WHERE idno='"+session.get("userid")+"'")+"' WHERE name='"+name[i]+"'");
			}			
		}
		
		request.setAttribute("lock", "1");
		Message msg=new Message();
		msg.setSuccess("已儲存");
		this.savMessage(msg);
		return execute();
	}
	
	public String edit(){
		
		//System.out.println(Oid);
		
		request.setAttribute("topic", Oid);
		request.setAttribute("ques", df.sqlGet("SELECT * FROM Question WHERE topic="+Oid+" ORDER BY sequence"));
		
		
		return "edit";
	}
	
	public String save(){
		Message msg=new Message();
		//新增
		if(!question[0].equals("")){
			
			df.exSql("INSERT INTO Question(question,topic) VALUES('"+question[0]+"',"+Oid+");");
			msg.setSuccess("已建立新題目");
			this.savMessage(msg);
			return edit();
		}
		
		
		for(int i=1; i<eOid.length; i++){
			if(!dOid[i].equals("")){
				df.exSql("DELETE FROM Question WHERE Oid="+dOid[i]);
				msg.setError("已刪除");
				this.savMessage(msg);
				return edit();
			}
			//順序修改
			if(!eOid[i].equals("")){
				df.exSql("UPDATE Question SET sequence='"+sequence[i]+"', question='"+question[i]+"' WHERE Oid="+eOid[i]+"");
				//偵錯題
				if(!debug[i].equals("")){
					df.exSql("UPDATE Question SET debug="+debug[i]+" WHERE Oid="+eOid[i]+"");
				}else{
					df.exSql("UPDATE Question SET debug=null WHERE Oid="+eOid[i]+"");
				}
				msg.setSuccess("已修改");
				this.savMessage(msg);
				return edit();
			}
		}
		
		
		return edit();
	}
	
	/**
	 * 重新計算所有課程分數
	 * @return
	 */
	public String reset(){
		
		//初始作業
		df.exSql("UPDATE Dtime SET effsamples=0, samples=0, coansw=0");
		df.exSql("UPDATE Seld SET coansw_invalid=null");		
		//尋找偵錯題與被偵錯題 TODO 動態定位
		List<Map>c=df.sqlGet("SELECT Oid, Dtime_oid, SUBSTRING(coansw, 1, 10)as ans,"
		+ "SUBSTRING(coansw, 4, 1) as que, SUBSTRING(coansw, 11, 1)as bug FROM Seld WHERE coansw IS NOT NULL");		
		int que, bug, abs;		
		for(int i=0; i<c.size(); i++){
			que=Integer.parseInt(c.get(i).get("que").toString());//計分被偵錯題
			bug=Integer.parseInt(c.get(i).get("bug").toString());//不計分偵錯題			
						
			//排除
			//if(c.get(i).get("ans").toString().equals("1111111111")){
			if(c.get(i).get("ans").toString().equals("1111111111")||c.get(i).get("ans").toString().equals("2222222222")||c.get(i).get("ans").toString().equals("3333333333")||c.get(i).get("ans").toString().equals("4444444444")||c.get(i).get("ans").toString().equals("5555555555")){
				//無效
				df.exSql("UPDATE Dtime SET samples=samples+1 WHERE Oid="+c.get(i).get("Dtime_oid"));
				df.exSql("UPDATE Seld SET coansw_invalid='*'WHERE Oid="+c.get(i).get("Oid"));
				c.get(i).put("check", false);
				continue;
			}				
			abs=Math.abs(que-bug);
			if(abs>1){
				//有效
				df.exSql("UPDATE Dtime SET samples=samples+1, effsamples=effsamples+1, coansw=coansw+"+sum(c.get(i).get("ans").toString())+" WHERE Oid="+c.get(i).get("Dtime_oid"));
				c.get(i).put("check", true);
			}else{
				//無效
				df.exSql("UPDATE Dtime SET samples=samples+1 WHERE Oid="+c.get(i).get("Dtime_oid"));
				df.exSql("UPDATE Seld SET coansw_invalid='*'WHERE Oid="+c.get(i).get("Oid"));
				c.get(i).put("check", false);
			}
				
		}		
		return SUCCESS;
	}
	
	/**
	 * 重新計算無效問卷 TODO 教學評量期間OR結束後自動排程
	 * @return
	 */
	public String setInvalid(){
		
		//初始作業
		//df.exSql("UPDATE Dtime SET effsamples=0, samples=0, coansw=0");
		
		//尋找偵錯題與被偵錯題 TODO 動態定位
		List<Map>c=df.sqlGet("SELECT Oid, Dtime_oid, SUBSTRING(coansw, 1, 10)as ans,"
		+ "SUBSTRING(coansw, 4, 1) as que, SUBSTRING(coansw, 11, 1)as bug FROM Seld WHERE coansw IS NOT NULL");
		
		int que, bug, abs;
		
		for(int i=0; i<c.size(); i++){
			que=Integer.parseInt(c.get(i).get("que").toString());//計分被偵錯題
			bug=Integer.parseInt(c.get(i).get("bug").toString());//不計分偵錯題
			//排除
			//if(c.get(i).get("ans").toString().equals("1111111111")){
			if(c.get(i).get("ans").toString().equals("1111111111")||c.get(i).get("ans").toString().equals("2222222222")||c.get(i).get("ans").toString().equals("3333333333")||c.get(i).get("ans").toString().equals("4444444444")||c.get(i).get("ans").toString().equals("5555555555")){
				//無效
				df.exSql("UPDATE Seld SET coansw_invalid='*'WHERE Oid="+c.get(i).get("Oid"));
				c.get(i).put("check", false);
				continue;
			}				
			abs=Math.abs(que-bug);
			if(abs>1){
				//有效
				df.exSql("UPDATE Dtime SET samples=samples+1, effsamples=effsamples+1, coansw=coansw+"+sum(c.get(i).get("ans").toString())+" WHERE Oid="+c.get(i).get("Dtime_oid"));
				c.get(i).put("check", true);
			}else{
				//無效
				df.exSql("UPDATE Seld SET coansw_invalid='*'WHERE Oid="+c.get(i).get("Oid"));
				c.get(i).put("check", false);
			}
						
		}		
		return SUCCESS;
	}
	
	private Float sum(String ans){		
		int s=0;
		for(int i=0; i<ans.length(); i++){
			s+=Integer.parseInt(ans.substring(i, i+1));
		}		
		return (float)s/ans.length();
	}
	
	

}
