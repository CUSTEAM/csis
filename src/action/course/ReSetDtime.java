package action.course;

import java.util.List;
import java.util.Map;

import model.Message;
import action.BaseAction;

public class ReSetDtime extends BaseAction{
	
	
	public String execute(){
		
		
		return SUCCESS;
	}
	
	public String reset(){
		
		
		String term;
		if(getContext().getAttribute("school_term").equals("1")){
			term="2";
		}else{
			term="1";
		}
		
		Message msg=new Message();
		
		
		//Dtime
		df.exSql("UPDATE Dtime SET techid=null, coansw=null, samples=0 WHERE Sterm='"+term+"'");
		msg.setSuccess("已清除課程<br>");
		//Seld
		df.exSql("DELETE FROM Seld WHERE Dtime_oid IN(SELECT Oid FROM Dtime WHERE Sterm='"+term+"')");
		msg.addSuccess("已清除選課<br>");
		//Dtime_class
		df.exSql("DELETE FROM Dtime_class WHERE Dtime_oid IN(SELECT Oid FROM Dtime WHERE Sterm='"+term+"')");
		msg.addSuccess("已清除排課<br>");
		//Dtime_teacher
		df.exSql("DELETE FROM Dtime_teacher WHERE Dtime_oid IN(SELECT Oid FROM Dtime WHERE Sterm='"+term+"')");
		msg.addSuccess("已清除一科目多教師<br>");
		//Dtime_block
		//df.exSql("DELETE FROM Dtime_block WHERE Dtime_oid IN(SELECT Oid FROM Dtime WHERE Sterm='"+term+"')");
		//msg.addSuccess("已清除擋修<br>");
		
		
		this.savMessage(msg);
		
		return SUCCESS;
	}
	
	public String resetSeld(){
		
		List<Map>list=df.sqlGet("SELECT * FROM SeldHist s WHERE s.edate>'2014/4/29'");
		List<Map>tmp;
		String seld;
		for(int i=0; i<list.size(); i++){
			
			tmp=df.sqlGet("SELECT Oid FROM Dtime WHERE depart_class='"+list.get(i).get("depart_class")+"'AND cscode='"+list.get(i).get("cscode")+"'");
			//System.out.println("SELECT Oid FROM Dtime WHERE depart_class='"+list.get(i).get("depart_class")+"'AND cscode='"+list.get(i).get("cscode")+"'");
			//System.out.println(list.get(i).get("StudentNo"));
			for(int j=0; j<tmp.size(); j++){
				
				try{
					seld=df.sqlGetStr("SELECT Oid FROM Seld WHERE student_no='"+list.get(i).get("StudentNo")+"'AND Dtime_oid='"+tmp.get(j).get("Oid")+"'");
				}catch(Exception e){
					System.out.println("find not");
					continue;
				}
				
				
				
				
				
				df.exSql("DELETE FROM Seld WHERE Oid="+seld);
				System.out.println("deleted");
				
				
				
				
			}
		}
		
		
		return SUCCESS;
	}

}
