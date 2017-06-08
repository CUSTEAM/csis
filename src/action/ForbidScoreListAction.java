package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Message;
import action.forbidScoreList.Print;

public class ForbidScoreListAction extends BaseAction{
	
	public String execute() throws Exception {		
		
		return this.SUCCESS;
	}
	
	public String cno;
	public String tno;
	public String stdNo;
	public String nameno;
	public String grade;
	
	public String Oid[];
	public String status[];
	
	public String search() throws Exception{
		
		request.setAttribute("std", df.sqlGet("SELECT (SELECT COUNT(*) FROM Dilg dg, Dilg_rules dr WHERE dr.exam='1' AND dg.abs=dr.id AND " +
		"dg.student_no='"+stdNo+"' AND dg.Dtime_oid=d.Oid)as cnt, s.Oid, st.student_no, st.student_name, c.ClassName, s.status, s.score, " +
		"cs.chi_name, cs.cscode, IFNULL(s.elearn_dilg, 0)as elearn_dilg FROM Seld s, stmd st, Dtime d, Csno cs, Class c WHERE s.student_no=st.student_no AND " +
		"st.depart_class=c.ClassNo AND d.Oid=s.Dtime_oid AND cs.cscode=d.cscode AND d.Sterm='"+getContext().getAttribute("school_term")+"' " +
		"AND st.student_no='"+stdNo+"'"));
		
		return SUCCESS;
	}
	
	public String save() throws Exception{
		//Map s;
		for(int i=0; i<Oid.length; i++){
			if(!status[i].equals("")){				
				df.exSql("UPDATE Seld SET status='1' WHERE Oid="+Oid[i]);
			}else{
				//s=df.sqlGetMap("SELECT s.student_no, d.depart_class, d.cscode "
				//+ "FROM Seld s, Dtime d WHERE d.Oid=s.Dtime_oid AND s.Oid="+Oid[i]);
				df.exSql("UPDATE Seld SET status=null WHERE Oid="+Oid[i]);
				//df.exSql("INSERT INTO SeldHist(StudentNo, depart_class, cscode, idno, type)"
				//+ "VALUES('"+s.get("student_no")+"', '"+s.get("depart_class")+"', '"+s.get("cscode")+"', '"+getSession().getAttribute("userid")+"', 'E');");
			}
		}
		
		return search();
	}
	
	
	public String print() throws IOException{
		
		if(df.sqlGetInt("SELECT COUNT(*) FROM Just j, stmd s, Class c WHERE " +
		"j.student_no=s.student_no AND s.depart_class=c.ClassNo AND " +
		"c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"' AND j.`total_score` IS NOT NULL AND " +
		"c.graduate LIKE'"+grade+"%'")<1){
			
			Message msg=new Message();
			msg.setError("操行成績尚未結算");
			this.savMessage(msg);
			return this.SUCCESS;
		}
		
		Map info=new HashMap();
		info.put("school_year", getContext().getAttribute("school_year"));
		info.put("school_term", getContext().getAttribute("school_term"));		
		
		List cls;		
		List stds;
		
		//班級範圍
		List<Map>list=df.sqlGet("SELeCT cs.name, c.SchoolNo FROM Class c, stmd s, CODE_SCHOOL cs WHERE " +
		"cs.id=c.SchoolNo AND s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"'" +
		"GROUP BY c.SchoolNo");
		
		List<Map>all=new ArrayList();
		
		
		//以學生班級列表
		for(int i=0; i<list.size(); i++){
			
			cls=getCls(list.get(i).get("SchoolNo").toString());
			
			stds= getStds(cls, grade);
			list.get(i).put("cls",stds);
			
			
			if(stds.size()<1){
				continue;
			}
			
			list.get(i).put("cnt", stds.size());
			all.add(list.get(i));
		}		
		
		//以他媽白痴課程為列表
		List shit;
		List idiot;
		
		List<Map>fList=df.sqlGet("SELeCT cs.name, c.SchoolNo FROM Class c, stmd s, CODE_SCHOOL cs WHERE " +
		"cs.id=c.SchoolNo AND s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"'" +
		"GROUP BY c.SchoolNo");		
		List<Map>css=new ArrayList();
		
		for(int i=0; i<fList.size(); i++){			
			shit=getCls(fList.get(i).get("SchoolNo").toString());			
			idiot= getCss(shit, grade);
			fList.get(i).put("cls",idiot);
			if(idiot.size()<1){
				continue;
			}			
			fList.get(i).put("cnt", shit.size());
			css.add(fList.get(i));			
		}
		
		Print p=new Print();
		p.print(response, all, css, info);
		p=null;
		return null;
	}
	
	
	private List getCls(String SchoolNo){		
		return df.sqlGet("SELECT COUNT(*)as cnt, c.ClassNo, c.ClassName FROM Class c LEFT OUTER JOIN stmd s ON s.depart_class=c.ClassNo WHERE " +
		"c.CampusNo='"+cno+"' AND c.SchoolNo='"+SchoolNo+"'" +
		"AND c.graduate LIKE'"+grade+"%' GROUP BY c.ClassNo ORDER BY c.ClassNo");
	}
	
	/**
	 * 以學生班級取扣考
	 * @param cls
	 * @param grad
	 * @return
	 */
	private List getStds(List<Map>cls, String grad){
		
		
		
		List stds;
		for(int i=0; i<cls.size(); i++){
			
			stds=df.sqlGet("SELECT cl.ClassName,d.thour, d.Oid, st.student_no, st.student_name, c.chi_name, IFNULL(s.elearn_dilg, 0)as elearn_dilg FROM Seld s, stmd st, Dtime d, Csno c," +
			"Class cl WHERE cl.ClassNo=d.depart_class AND s.Dtime_oid=d.Oid AND d.cscode=c.cscode AND s.student_no=st.student_no AND " +
			"st.depart_class='"+cls.get(i).get("ClassNo")+"' AND s.status='1' AND d.cscode!='50000' AND cl.graduate LIKE'"+grad+"%'");
			checkDilg(stds);
			cls.get(i).put("stds", stds);
		}
		return cls;
	}
	
	/**
	 * 以開課班級取扣考
	 * @param css
	 * @param grad
	 * @return
	 */
	private List getCss(List<Map>cls, String grad){
		
		List stds;
		for(int i=0; i<cls.size(); i++){					
			stds=df.sqlGet("SELECT cl.ClassName,d.thour, d.Oid, st.student_no, st.student_name, c.chi_name, IFNULL(s.elearn_dilg, 0)as elearn_dilg FROM " +
			"Seld s, stmd st, Dtime d, Csno c,Class cl WHERE cl.ClassNo=d.depart_class AND s.Dtime_oid=d.Oid AND " +
			"d.cscode=c.cscode AND s.student_no=st.student_no AND " +
			"d.depart_class='"+cls.get(i).get("ClassNo")+"' AND s.status='1' AND d.cscode!='50000' AND " +
			"cl.graduate LIKE'"+grad+"%' ORDER BY d.cscode");
			checkDilg(stds);
			cls.get(i).put("stds", stds);
		}		
		return cls;
	}
	
	
	
	
	private List checkDilg(List<Map>stds){
		
		List dilgs;
		for(int i=0; i<stds.size(); i++){			
			dilgs=df.sqlGet("SELECT dr.name, COUNT(*)as cnt FROM Dilg d, Dilg_rules dr WHERE d.abs=dr.id AND dr.exam='1' " +
			"AND d.student_no='"+stds.get(i).get("student_no")+"' AND d.Dtime_oid="+stds.get(i).get("Oid")+" GROUP BY d.abs");
			stds.get(i).put("dilgs", dilgs);
		}
		
		return stds;
	}
	

}
