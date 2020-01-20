package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class List4Dtime13 extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String term) throws IOException{
		
		Date date=new Date();
		xml2ods(response, getRequest(), date);		
		PrintWriter out=response.getWriter();
		
		StringBuilder sb=new StringBuilder("SELECT c.DeptNo,c.CampusNo, c.SchoolNo, c.ClassNo,c.graduate, d.thour, d.credit,e.unit as idno2, IFNULL(e.cname,'')as cname, cs.chi_name, "
				+ "d.cscode, d.techid, cdo.name as opt2 FROM Dtime d ,empl e, "
				+ "CODE_DTIME_OPT cdo, Csno cs, Class c WHERE d.techid=e.idno AND c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND "
				+ "d.opt=cdo.id AND d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")");
		
		dtimeList=df.sqlGet(sb.toString());
		
		out.println("<html>");
		out.println("<head><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		// out.println("<c:if test='${not empty dtimeList}'>"); //???
		out.println("	<tr>");
		out.println("		<td>");

		List<Map>pecode9=df.sqlGet("SELECT * FROM CODE_PE9");
		List<Map>pecode11=df.sqlGet("SELECT * FROM CODE_PE11");

		out.println("			<table border='1' align='left' cellpadding='0' cellspacing='1' width='100%'>");
		out.println("");
		out.println("				<tr height='20'>");
		out.println("					<td align='center'>學年</td>");
		out.println("					<td align='center'>學期</td>");
		out.println("					<td align='center'>主聘系所</td>");
		out.println("					<td align='center'>身份識別號</td>");
		out.println("					<td align='center'>教師姓名</td>");
		out.println("					<td align='center'>授課系所</td>");
		out.println("					<td align='center'>授課學制</td>");
		out.println("					<td align='center'>課程名稱</td>");
		out.println("					<td align='center'>通職課程</td>");
		out.println("					<td align='center'>寒暑期開課</td>");
		out.println("					<td align='center'>授課時數</td>");
		out.println("					<td align='center'>開課學分</td>");
		out.println("					<td align='center'>畢業班課程</td>");
		out.println("				</tr>");
		
		//for(int i=0)
		
		String tmpPcode111, tmpPcode112,departClass,tmpDepartClass;
		for (int i = 0; i < dtimeList.size(); i++) {
			if ( dtimeList.get(i).get("cscode").toString().equals("50000")
					||  dtimeList.get(i).get("cscode").toString().equals("T0001")
					||  dtimeList.get(i).get("cscode").toString().equals("T0002")) {
				continue;
			}
			//departClass = dtimeList.get(i).get("ClassNo").toString();
			tmpDepartClass = dtimeList.get(i).get("CampusNo").toString()+dtimeList.get(i).get("ClassNo").toString();			
			out.println("				<tr height='20'>");
			out.println("					<td align='center'>" + year + "</td>");
			out.println("					<td align='center'" + term + "</td>");
			out.println("					<td align='center'>");
			// 取得主聘系所
			for (int j = 0; j < pecode9.size(); j++) {
				try{
					if (pecode9.get(j).get("id1").toString().equals(dtimeList.get(i).get("idno2").toString())) {
						out.println(pecode9.get(j).get("id"));
					}
				}catch(Exception e){
					//out.println("*");
				}
			}
			out.print("</td>");
			out.println("<td align='center'>"+ dtimeList.get(i).get("techid") + "</td>");
			out.println("					<td align='center'>"+  dtimeList.get(i).get("cname") + "</td>");
			out.println("					<td align='center'>");

			// TODO 陳組長說不用管碩士班
			/*
			 * if(
			 * dtimeList.get(i)).get("departClass").toString().charAt(2)=='G' ){
			 * 
			 * for(int k=0; k<pecode9.length; k++){
			 * 
			 * if(
			 * pecode9[k]).get("id1").equals(dtimeList.get(i)).get("idno2"))&&
			 * pecode9[k]).get("id2").equals("G") ){
			 * out.println(pecode9[k]).get("id"));
			 *  } }
			 */

			// 取得開課系所
			for (int k = 0; k < pecode9.size(); k++) {
				// System.out.println(dtimeList.get(i)).get("departClass").toString().charAt(3));

				if (pecode9.get(k).get("id1").toString().equals(dtimeList.get(i).get("DeptNo").toString())) {
					out.println(pecode9.get(k).get("id"));

				}
			}
			out.println("</td>");
			out.println("					<td align='center'>");

			// 取得授課學制
			for (int l = 0; l < pecode11.size(); l++) {
				System.out.println(pecode11.get(l));
				if (pecode11.get(l).get("id1").toString().equals(dtimeList.get(i).get("SchoolNo").toString())) {
					tmpPcode111 = pecode11.get(l).get("id1").toString();
					//tmpPcode112 = pecode11.get(l).get("id2").toString();
					//if (tmpPcode111.equals(tmpDepartClass)||tmpPcode112.equals(tmpDepartClass)) {
						out.println(pecode11.get(l).get("id"));
					//}
				}

			}

			out.print("</td>");

			out.println("					<td>" +dtimeList.get(i).get("chi_name")+ "</td>");

			out.println("					<td align='center'>");

			// 取得通識課程
			if (dtimeList.get(i).get("opt2").toString().equals("通識")|| dtimeList.get(i).get("DeptNo").toString().equals("0")) {
				out.print("Y");
			} else {
				out.print("N");
			}

			out.print("</td");
			out.println("					<td align='center'>N</td>");

			/* 判斷畢業班
			
			String graduate = "N";
			char grade = '0';
			String tmpDepart = departClass.substring(0, 3);
			if (tmpDepartClass.equals("112")) {
				grade = '2';
			} else if (tmpDepartClass.equals("115")) {
				grade = '2';
			} else if (tmpDepartClass.equals("11G")) {
				grade = '2';
			} else if (tmpDepartClass.equals("122")) {
				grade = '2';
			} else if (tmpDepartClass.equals("132")) {
				grade = '2';
			} else if (tmpDepartClass.equals("142")) {
				grade = '2';
			} else if (tmpDepartClass.equals("152")) {
				grade = '2';
			} else if (tmpDepartClass.equals("154")) {
				grade = '4';
			} else if (tmpDepartClass.equals("164")) {
				grade = '4';
			} else if (tmpDepartClass.equals("172")) {
				grade = '2';
			} else if (tmpDepartClass.equals("182")) {
				grade = '2';
			} else if (tmpDepartClass.equals("18G")) {
				grade = '2';
			} else if (tmpDepartClass.equals("192")) {
				grade = '2';
			}
			// System.out.println("departClass.charAt(4)="+departClass.charAt(4)+"
			// departClass="+departClass);
			*/
			

			out.println("					<td align='center'>"+ Integer.parseInt(dtimeList.get(i).get("thour").toString()) * 18 + "</td>");
			out.println("					<td align='center'>"+ dtimeList.get(i).get("credit") + "</td>");
			
			if (dtimeList.get(i).get("graduate").toString().equals("0")) {
				out.println("					<td align='center'>Y</td>");
			}else{
				out.println("					<td align='center'>N</td>");
			}
			

		}
		out.println("	</tr>");
		out.println("</table></td></tr></table>");
		out.println("</body>");

		out.println("</html>");
		out.close();
	}
		

}
