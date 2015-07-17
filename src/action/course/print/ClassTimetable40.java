package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import action.BaseAction;

/**
 * 通識課表
 * 課亂開根本沒屁用
 * @author John
 *
 */
public class ClassTimetable40 extends BaseAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String Sterm) throws IOException{
		
		Date date=new Date();
		response.setContentType("application/vnd.ms-word; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".doc");				
		PrintWriter out=response.getWriter();	
		
		out.println("<html>");
		out.println("<head>");
		out.println("<style>td{border:1px solid;}</style>");
		out.println("</head>");
		out.println("<body>");
		
		
		StringBuilder sb=new StringBuilder("SELECT c.ClassNo FROM Dtime d,Class c WHERE d.depart_class=c.ClassNo AND d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"', " );
		}
		sb.delete(sb.length()-2, sb.length()-1);
		sb.append(")");
		dtimeList=df.sqlGet(sb.toString());
		
		
		sb=new StringBuilder("SELECT c.CampusNo, c.SchoolNo, c.ClassName, c.ClassNo FROM " +
		"Dtime d, Class c WHERE d.opt='3'AND c.ClassNo=d.depart_class AND d.depart_class IN(");
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("ClassNo")+"', " );
		}
		sb.delete(sb.length()-2, sb.length()-1);
		sb.append(") GROUP BY c.Oid ORDER BY c.ClassNo, c.DeptNo, c.SchoolNo");		
		List<Map>classes=df.sqlGet(sb.toString());
		
		
		List<Map>dtime_class;
		List<Map>table;
		for(int i=0; i<classes.size(); i++){
			
			out.println("<table width='100%' style='border:0px;' cellpadding='0' cellspacing='0'>");
			out.println("	<tr>");
			out.println("		<td style='border:0px;' align='center'><font size='+3' face='標楷體'><b>"+
			year+"學年第"+Sterm+"學期"+classes.get(i).get("ClassName")+"</b></font></td>");
			out.println("	</tr>");
			out.println("</table>");
			out.println("<br>");
			
			table=df.sqlGet("SELECT ce.idno2, d.Select_Limit, d.techid, d.Oid, cs.ClassName, d.depart_class, c.chi_name, " +
				"c.cscode, c.eng_name, e.cname, d.opt, d.credit, d.thour, d.stu_select, d.open, d.elearning, d.extrapay, d.crozz FROM " +
				"(Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno) LEFT OUTER JOIN CodeEmpl ce ON ce.idno=e.unit, Class cs, Csno c WHERE " +
				"d.opt='3'AND c.cscode=d.cscode AND cs.classNo=d.depart_class AND d.depart_class='"+classes.get(i).get("ClassNo")+"' AND " +
						"d.Sterm='"+Sterm+"'");
			
			
			
			for(int j=0; j<table.size(); j++){
				
				out.println("<table width='100%' cellpadding='0' cellspacing='0'>");
				out.println("	<tr>");
				out.println("		<td align='center' colspan='7'><font size=+1><b>課程編號"+table.get(j).get("Oid")+"</b></font></td>");
				out.println("	</tr>");
				out.println("	<tr>");
				
				out.println("		<td align='center'>課程名稱</td>");
				out.println("		<td align='center'>選別</td>");
				out.println("		<td align='center'>學分</td>");
				out.println("		<td align='center'>時數</td>");
				out.println("		<td align='center'>上課方式</td>");
				
				out.println("		<td align='center'>任課老師</td>");
				
				out.println("	</tr>");
				out.println("	<tr>");
				
				out.println("		<td align='center'>"+table.get(j).get("chi_name")+"</td>");
				
				
				out.println("		<td align='center'>"+table.get(j).get("opt").toString()+"</td>");
				
				
				out.println("		<td align='center'>"+table.get(j).get("credit")+"</td>");
				out.println("		<td align='center'>"+table.get(j).get("thour")+"</td>");
				out.println("		<td align='center'>"+table.get(j).get("elearning")+"</td>");				
				if(table.get(j).get("cname")==null){
					out.println("		<td align='center'>未指定</td>");
				}else{
					out.println("		<td align='center'>"+table.get(j).get("cname")+"</td>");
				}
				
				
				
				out.println("	</tr>");
				
				dtime_class=df.sqlGet("SELECT * FROM Dtime_class WHERE Dtime_oid='"+table.get(j).get("Oid")+"'");
				
				out.println("	<tr>");
				out.println("		<td align='center' colspan='7'>");
				
				for(int k=0; k<dtime_class.size(); k++){
					
					
					out.println("<font size=+1>星期"+dtime_class.get(k).get("week")+", 第"+dtime_class.get(k).get("begin")+
							"至第"+dtime_class.get(k).get("end")+"節, "+dtime_class.get(k).get("place")+"教室</font>");	
					
					if(table.get(j).get("extrapay").equals("1")){
						out.println(", *電腦實習");
					}
					
				}
				
				
				out.println("		</td>");
				
				out.println("	</tr>");
				out.println("</table>");
				
				if((j+1)%7==0){
					out.println("<br clear='all' style='page-break-before:always;'/>");
					
					if((j+1)<table.size()){
						out.println("<table width='100%' style='border:0px;' cellpadding='0' cellspacing='0'>");
						out.println("	<tr>");
						out.println("		<td style='border:0px;' align='center'><font size='+3' face='標楷體'><b>"+
								year+"學年第"+Sterm+"學期"+classes.get(i).get("ClassName")+"</b></font></td>");out.println("	</tr>");
						out.println("</table>");
						out.println("<br>");
					}
					
					
					
				}else{
					out.println("<br>");
				}
				
			}
			
			out.println("<br clear='all' style='page-break-before:always;'/>");	
			
				
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.println("</body>");
		out.println("</html>");
		out.close();
		
		
	}

}
