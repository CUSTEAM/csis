package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BaseAction;

public class List4Course35 extends BaseAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String term) throws IOException{
		
		Date date=new Date();
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");				
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta http-equiv=application/vnd.ms-excel; charset=UTF-8><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		out.println("<tr>");
		out.println("<td>");

		// SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		// String date=sf.format(new Date());
		//String school;

		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<td align='center'>課程編號 </td>");
		out.println("<td align='center'>當期課號</td>");
		out.println("<td align='center'>課程名稱</td>");
		out.println("<td align='center'>修別</td>");
		out.println("<td align='center'>科目類別</td>");
		out.println("<td align='center'>講授時數</td>");
		out.println("<td align='center'>實習時數</td>");
		out.println("<td align='center'>開課學分數</td>");
		out.println("<td align='center'>第一次上課日期</td>");
		out.println("<td align='center'>修課人數(男)</td>");
		out.println("<td align='center'>修課人數(女)</td>");
		out.println("<td align='center'>主要授課語言</td>");
		out.println("<td align='center'>畢業班課程</td>");
		out.println("<td align='center'>寒暑別</td>");
		out.println("<td align='center'>全程使用外語</td>");
		out.println("<td align='center'>身份識別種類</td>");
		out.println("<td align='center'>身份識別號</td>");
		out.println("<td align='center'>授課時數</td>");
		out.println("<td align='center'>系所代碼</td>");
		out.println("<td>學制代碼</td>");
		out.println("<td>專業技術</td>");
		out.println("<td>專業英語</td>");
		out.println("</tr>");
		String language;
		List dtimeTeacherTmp;
		List<Map>pecode9=df.sqlGet("SELECT * FROM CODE_PE9");
		List<Map>pecode11=df.sqlGet("SELECT * FROM CODE_PE11");
		Map dtime;
		for (int i = 0; i < dtimeList.size(); i++) {
			dtime=df.sqlGetMap(("SELECT d.y_pro, d.y_pro_eng,d.Oid as dOid,  c.*, d.techid, (SELECT COUNT(*)FROM Seld, stmd WHERE Seld.student_no=stmd.student_no AND stmd.sex='1' AND "
			+ "Seld.Dtime_oid=d.Oid)as cnt1, "
			+ "(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt, c.DeptNo,c.SchoolNo,"
			+ "d.credit, d.thour,cs.cscode, cs.chi_name, cdo.name as opt, d.cscode FROM "
			+ "Class c, Csno cs, Dtime d, CODE_DTIME_OPT cdo "
			+ "WHERE c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND d.opt=cdo.id AND d.Oid='"+dtimeList.get(i).get("Oid")+"' ORDER BY d.depart_class,d.cscode"));
			
			if (dtime.get("cscode").toString().equals("50000")|| dtime.get("cscode").toString().equals("T0001")|| dtime.get("cscode").toString().equals("T0002")){
				continue;
			}
			
			
			
			char SchoolNo=dtime.get("SchoolNo").toString().charAt(dtime.get("SchoolNo").toString().length()-1);
			String School_id;
			//System.out.println(SchoolNo);
			if(SchoolNo=='G'){
				School_id="G";
			}else{
				School_id="";
			}
			
			
			//System.out.println(dtime.get("dOid")+":"+dtime.get("cscode"));
			
			
			

			//一科目多教師
			List<Map>dtimeTeacher=df.sqlGet("SELECT * FROM Dtime_teacher WHERE Dtime_oid='"+dtimeList.get(i).get("Oid")+"'");
			//System.out.println("SELECT * FROM Dtime_teacher WHERE Dtime_oid='"+dtimeList.get(i).get("Oid")+"'");
			if(dtimeTeacher.size()>0){
				
				for(int j = 0; j < dtimeTeacher.size(); j++) {
					
					if(dtime.get("techid")!=null){
						if(dtime.get("techid").equals(dtimeTeacher.get(j).get("teach_id")))continue;
					}
					
					//if(dtime.get("techid")!=dtimeTeacher.get(j).get("teach_id")) {
						out.println("<tr>");
						// 課碼
						out.println("<td align='center'>");
						out.println(dtime.get("cscode"));
						out.println("</td>");
						
						out.println("<td>w"+dtime.get("dOid")+"</td>");	
						// 課名
						out.println("<td align='left'>");
						out.println(dtime.get("chi_name"));
						out.println("</td>");
						// 選別
						out.println("<td align='left'>");
						out.println(dtime.get("opt"));
						out.println("</td>");
						// 科目類別
						/*
						 * out.println("<td align='left'>"); String type="專業核心科目";
						 * if(dtime.getOpt().equals("3")){ type="通識科目"; }
						 * if(dtime.getDepartClass().charAt(3)=='0'){ type="共同科目/一般科目"; }
						 */
						out.println("<td align='left'>");
						out.println("");
						out.println("</td>");
						// 時數
						out.println("<td align='center'>");
						out.println(dtime.get("thour"));
						out.println("</td>");
						// 實習
						out.println("<td align='center'>0</td>");
						// 學分
						out.println("<td align='center'>");
						out.println(dtime.get("credit"));
						out.println("</td>");
						// 第一次上課日期
						
						out.println("<td align='center'></td>");
						
						// 學生人數(男)
						out.println("<td align='center'>");
						out.println("");
						out.println(dtime.get("cnt1"));
						out.println("</td>");
						
						// 學生人數(女)
						out.println("<td align='center'>");
						out.println("");
						out.println(Integer.parseInt(dtime.get("cnt").toString())-Integer.parseInt(dtime.get("cnt1").toString()));
						out.println("</td>");

						// 主要授課語言
						language = "國語";
						if (dtime.get("chi_name").toString().indexOf("日文") >= 0 || dtime.get("chi_name").toString().indexOf("日語") >= 0) {
							language = "日語";
						} else if (dtime.get("chi_name").toString().indexOf("英文") >= 0 || dtime.get("chi_name").toString().indexOf("英語") >= 0) {
							language = "英語";
						}
						out.println("<td align='center'>");
						out.println(language);
						out.println("</td>");

						// 畢業班課程
						
						if (dtime.get("graduate").toString().equals("1")) {
							out.println("<td align='center'>是</td>");
						}else{
							out.println("<td align='center'>否</td>");
						}
						
						// 寒暑別
						out.println("<td align='center'>");
						out.println("無");
						out.println("</td>");

						// 全程使用外語
						out.println("<td align='center'>");
						out.println("否");
						out.println("</td>");

						// 身份識別種類
						out.println("<td align='center'>");
						if (dtimeTeacher.get(j).get("teach_id")!= null)out.println("I");
						out.println("</td>");

						// 身份識別號
						//System.out.println(dtimeTeacher.get(j).get("teach_id"));
						out.println("<td align='center'>");
						if(dtimeTeacher.get(j).get("teach_id")==null){
							out.println("xxx");
						}else{
							out.println(dtimeTeacher.get(j).get("teach_id"));
						}
						
						out.println("</td>");

						// 授課時數
						out.println("<td align='center'>");
						out.println(dtimeTeacher.get(j).get("hours"));
						out.println("</td>");

						// 系所代碼
						out.println("<td align='center'>");
						
						SchoolNo=dtime.get("SchoolNo").toString().charAt(dtime.get("SchoolNo").toString().length()-1);
						if(SchoolNo=='G'){
							School_id="G";
						}else{
							School_id="";
						}
						
						for (int k = 0; k < pecode9.size(); k++) {

							if (pecode9.get(k).get("id1").toString().equals(dtime.get("DeptNo").toString())&&pecode9.get(k).get("id2").toString().equals(School_id)) {
								out.println(pecode9.get(k).get("id"));
							}
						}
						out.println("</td>");

						// 學制代碼
						
						out.println("<td align='center'>");
								for (int l = 0; l < pecode11.size(); l++) {
									//System.out.println(pecode11.get(l)+":"+dtime.get("SchoolNo"));
									if (pecode11.get(l).get("id1").toString().equals(dtime.get("SchoolNo").toString())) {
										//tmpPcode111 = pecode11.get(l).get("id1").toString();
										//tmpPcode112 = pecode11.get(l).get("id2").toString();
										//if (tmpPcode111.equals(tmpDepartClass)||tmpPcode112.equals(tmpDepartClass)) {
											out.println(pecode11.get(l).get("id"));
										//}
									}
								}
						out.println("</td>");
					//}else{
						//System.out.println("!!");
					//}
				}
			
				
			}else{
				out.println("  <tr>");
				out.println("<td align='center'>"+dtime.get("cscode")+"</td>");			
				out.println("<td>w"+dtime.get("dOid")+"</td>");			
				out.println("<td align='left'>"+dtime.get("chi_name")+"</td>");			
				out.println("<td align='left'>"+dtime.get("opt")+"</td>");

				out.println("<td align='left'></td>");
				// 時數
				out.println("<td align='center'>"+dtime.get("thour")+"</td>");
				// 實習
				out.println("<td align='center'>0</td>");
				// 學分
				out.println("<td align='center'>"+dtime.get("credit")+"</td>");
				// 第一次上課日期
				/*
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				//Date d = new Date();
				try {
					d = sd.parse(df.sqlGetMap(
							"SELECT wdate FROM week WHERE daynite='"
									+ dtime.getDepartClass().substring(0, 2)
									+ "'").get(0)).get("wdate").toString());
				} catch (Exception e) {
					System.out.println(e);
				}
				c.setTime(d);
				List week = manager
						.ezGetBy("SELECT week FROM Dtime_class WHERE Dtime_oid='"
								+ dtime.getOid() + "' ORDER BY week ASC");
				Integer first = 2;
				if (week.size() > 0) {
					first = Integer.parseInt(((Map) week.get(0)).get("week")
							.toString()) + 1;
					if (first > 7) {
						first = 1;
					}
				}

				c.set(Calendar.DAY_OF_WEEK, first);
				d = c.getTime();
				*/
				out.println("<td align='center'></td>");
				
				// 學生人數(男)
				out.println("<td align='center'>"+dtime.get("cnt1")+"</td>");
				
				// 學生人數(女)
				out.println("<td align='center'>");
				out.println(Integer.parseInt(dtime.get("cnt").toString())-Integer.parseInt(dtime.get("cnt1").toString()));
				out.println("</td>");

				// 主要授課語言
				language = "國語";
				if (dtime.get("chi_name").toString().indexOf("日文") >= 0 || dtime.get("chi_name").toString().indexOf("日語") >= 0) {
					language = "日語";
				} else if (dtime.get("chi_name").toString().indexOf("英文") >= 0 || dtime.get("chi_name").toString().indexOf("英語") >= 0) {
					language = "英語";
				}
				out.println("<td align='center'>");
				out.println(language);
				out.println("</td>");

				// 畢業班課程
				
				if (dtime.get("graduate").toString().equals("1")) {
					out.println("<td align='center'>是</td>");
				}else{
					out.println("<td align='center'>否</td>");
				}
				

				// 寒暑別
				out.println("<td align='center'>");
				out.println("無");
				out.println("</td>");

				// 全程使用外語
				out.println("<td align='center'>");
				out.println("否");
				out.println("</td>");

				// 身份識別種類
				out.println("<td align='center'>");
				if (dtime.get("techid")!= null)out.println("I");
				out.println("</td>");

				// 身份識別號
				
				out.println("<td align='center'>");
//
				if(dtime.get("techid")==null){
					//System.out.println(dtime);
					out.println("");
				}else{					
					out.println(dtime.get("techid"));
				}
				
				out.println("</td>");

				// 授課時數
				out.println("<td align='center'>");
				out.println(dtime.get("thour"));
				out.println("</td>");

				// 系所代碼
				out.println("<td align='center'>");

				
				
				for (int k = 0; k < pecode9.size(); k++) {

					if (pecode9.get(k).get("id1").toString().equals(dtime.get("DeptNo").toString())&&pecode9.get(k).get("id2").toString().equals(School_id)) {
						out.println(pecode9.get(k).get("id"));
					}
				}
				out.println("</td>");

				// 學制代碼
				
				out.println("<td align='center'>");
						for (int l = 0; l < pecode11.size(); l++) {
							//System.out.println(pecode11.get(l)+":"+dtime.get("SchoolNo"));
							if (pecode11.get(l).get("id1").toString().equals(dtime.get("SchoolNo").toString())) {
								//tmpPcode111 = pecode11.get(l).get("id1").toString();
								//tmpPcode112 = pecode11.get(l).get("id2").toString();
								//if (tmpPcode111.equals(tmpDepartClass)||tmpPcode112.equals(tmpDepartClass)) {
								out.println(pecode11.get(l).get("id"));
								//}
							}
						}
				out.println("</td>");
				
				
				if(dtime.get("y_pro").equals("0")){
					out.println("<td>否</td>");
				}else{
					out.println("<td>是</td>");
				}
				if(dtime.get("y_pro_eng").equals("0")){
					out.println("<td>否</td>");
				}else{
					out.println("<td>是</td>");
				}
				
				
				
				out.println("</tr>");
			}
			
			
		
			
			
		}
		out.println("</table>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");

		out.println("</html>");
		out.close();
		
	}

}
