package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

/***
 * 受教權1-6
 * @author John
 *
 */
public class List16 extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String term) throws IOException{
	
		Date date=new Date();
		xml2ods(response, getRequest(), date);	
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta http-equiv=application/vnd.ms-excel; charset=UTF-8><style>body{background-color:#cccccc;}td{mso-number-format:\\@;font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		out.println("<tr>");
		out.println("<td>開課單位</td>");
		out.println("<td>開課學制</td>");
		out.println("<td>年級/班別</td>");
		out.println("<td>課號</td>");
		out.println("<td>課程名稱</td>");
		out.println("<td>必修/選修</td>");
		out.println("<td>學分</td>");
		out.println("<td>授課教師</td>");
		out.println("<td>星期節次</td>");
		out.println("<td>上課地點	</td>");
		out.println("<td>就讀學系/科</td>");
		out.println("<td>就讀學制	</td>");
		out.println("<td>年級/班別</td>");
		out.println("<td>學號</td>");
		out.println("<td>姓名</td>");
		out.println("</tr>");
		
		
		StringBuilder sql=new StringBuilder("SELECT d.Oid, d.credit,cc.name as SchoolName,d.cscode,cs.chi_name, "
				+ "d.opt,c.Grade, c.SeqNo, cd.name as deptName , e.cname "
				+ "FROM CODE_SCHOOL cc, Dtime d LEFT OUTER JOIN empl e ON "
				+ "d.techid=e.idno, Class c, CODE_DEPT cd, Csno cs WHERE "
				+ "cc.id=c.SchoolNo AND cs.cscode=d.cscode AND "
				+ "d.depart_class=c.ClassNo AND cd.id=c.DeptNo AND d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++) {	
			
			sql.append("'"+dtimeList.get(i).get("Oid")+"',");
		}		
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		List<Map>cs=df.sqlGet(sql.toString());
		for(int i=0; i<cs.size(); i++) {
			//System.out.println(cs.get(i));
			cs.get(i).put("cls", df.sqlGet("SELECT dc.place, dc.week, dc.begin, dc.end FROM Dtime_class dc WHERE dc.Dtime_oid="+cs.get(i).get("Oid")));
			cs.get(i).put("stds", df.sqlGet("SELECT cs.name as SchoolName, st.student_name, st.student_no, c.Grade, "
					+ "c.SeqNo, cd.name as deptName FROM Seld se, stmd st, Class c, CODE_DEPT cd, CODE_SCHOOL cs "
					+ "WHERE cs.id=c.SchoolNo AND se.csdepart_class=c.ClassNo AND cd.id=c.DeptNo AND "
					+ "c.ClassNo=st.depart_class  AND se.student_no=st.student_no AND "
					+ "se.Dtime_oid="+cs.get(i).get("Oid")));
			
		}
		List<Map>stds;
		List<Map>cls;
		StringBuilder sb, sb1, sb2;
		
		int begin,end;
		
		for(int i=0; i<cs.size(); i++) {
			
			stds=(List<Map>) cs.get(i).get("stds");
			cls=(List<Map>) cs.get(i).get("cls");
			
			sb=new StringBuilder();
			sb1=new StringBuilder();
			
			
			for(int j=0; j<cls.size(); j++) {
				try {
					sb2=new StringBuilder();
					begin=Integer.parseInt(cls.get(j).get("begin").toString());
					end=Integer.parseInt(cls.get(j).get("end").toString());
					for(int k=begin; k<=end; k++) {
						sb2.append(k+",");
					}
					sb2.delete(sb2.length()-1, sb2.length());
					sb.append(  bl.getWeekOfDay(
							Integer.parseInt(cls.get(j).get("week").toString()), "")+""+sb2   );
				}catch(Exception e) {
					
				}
				
			}
			try {
				for(int j=0; j<cls.size(); j++) {
					sb1.append(  cls.get(j).get("place").toString()+","    );
				}
				sb1.delete(sb1.length()-1, sb1.length());
			}catch(Exception e) {
				
			}
			
			try {
				
			}catch(Exception e) {
				
			}
			for(int j=0; j<stds.size(); j++) {
				
				out.println("<tr>");
				out.println("<td>"+cs.get(i).get("deptName")+"</td>");
				out.println("<td>"+cs.get(i).get("SchoolName")+"</td>");
				out.println("<td>"+cs.get(i).get("Grade")+"/"+cs.get(i).get("SeqNo")+"</td>");
				out.println("<td>"+cs.get(i).get("cscode")+"</td>");
				out.println("<td>"+cs.get(i).get("chi_name")+"</td>");
				
				if(cs.get(i).get("opt").toString().equals("2")) {
					out.println("<td>選修</td>");
				}else {
					out.println("<td>必修</td>");
				}
				
				
				
				out.println("<td>"+cs.get(i).get("credit")+"</td>");
				out.println("<td>"+cs.get(i).get("cname")+"</td>");
				
				out.println("<td>"+sb.toString()+"</td>");
				
				
				
				
				
				out.println("<td>"+sb1.toString()+"</td>");
				out.println("<td>"+stds.get(j).get("deptName")+"</td>");
				out.println("<td>"+stds.get(j).get("SchoolName")+"</td>");
				out.println("<td>"+stds.get(j).get("Grade")+"/"+stds.get(j).get("SeqNo")+"</td>");
				out.println("<td>"+stds.get(j).get("student_no")+"</td>");
				out.println("<td>"+stds.get(j).get("student_name")+"</td>");
				out.println("</tr>");
			}
		}
		
		out.close();
		out.flush();
	}
}
