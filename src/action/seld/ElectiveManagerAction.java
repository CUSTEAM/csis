package action.seld;

import java.util.ArrayList;
import java.util.List;

import model.Message;
import action.BaseAction;

public class ElectiveManagerAction extends BaseAction{
	
	public String Oid[];
	public String level[];
	public String term[];
	public String depart[];
	public String grade[];
	public String begin[];
	public String end[];
	public String sel_min[];
	public String max[];
	public String min[];
	public String nor[];
	
	public String execute(){	
		
		List list=new ArrayList();		
		list.add(df.sqlGet("SELECT s.*, e.cname FROM SYS_CALENDAR_ELECTIVE s LEFT OUTER JOIN empl e ON e.idno=s.idno WHERE s.depart='D' ORDER BY s.depart, s.grade"));		
		list.add(df.sqlGet("SELECT s.*, e.cname FROM SYS_CALENDAR_ELECTIVE s LEFT OUTER JOIN empl e ON e.idno=s.idno WHERE s.depart='N' ORDER BY s.depart, s.grade"));
		list.add(df.sqlGet("SELECT s.*, e.cname FROM SYS_CALENDAR_ELECTIVE s LEFT OUTER JOIN empl e ON e.idno=s.idno WHERE s.depart='H' ORDER BY s.depart, s.grade"));
		
		request.setAttribute("list", list);
		
		return SUCCESS;
	}
	
	public String edit(){
		Message msg=new Message();
		for(int i=0; i<Oid.length; i++){
			
			if(!Oid[i].trim().equals("")){
				try{//Seld_schedule
					df.exSql("UPDATE SYS_CALENDAR_ELECTIVE SET sel_min="+sel_min[i]+", level='"+level[i]+"', " +
					"depart='"+depart[i]+"', grade='"+grade[i]+"', begin='"+begin[i]+"', " +
					"end='"+end[i]+"', max='"+max[i]+"', min='"+min[i]+"', nor='"+nor[i]+"', idno='"+getSession().getAttribute("userid")+"', term='"+term[i]+"' "+
					"WHERE Oid='"+Oid[i]+"'");
					
				}catch(Exception e){
					msg.setError("請檢查欄位");
					this.savMessage(msg);
					return SUCCESS;
				}		
			}	
		}
		
		msg.setSuccess("已儲存");
		this.savMessage(msg);
		return execute();
	}
}
