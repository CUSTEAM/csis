package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class List4DtimeG extends BasePrintXmlAction{
	
	List<Map>Pecode9=df.sqlGet("SELECT * FROM Pecode9");
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String term) throws IOException{
		
		Date date=new Date();
		//SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		xml2ods(response, getRequest(), date);
		List<Map>cls;	
		//StringBuilder clsinfo;
		PrintWriter out=response.getWriter();
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>shawn</Author>");
		out.println ("  <LastAuthor>shawn</LastAuthor>");
		out.println ("  <Created>2016-09-26T08:56:02Z</Created>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>5130</WindowHeight>");
		out.println ("  <WindowWidth>11355</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <ProtectStructure>False</ProtectStructure>");
		out.println ("  <ProtectWindows>False</ProtectWindows>");
		out.println (" </ExcelWorkbook>");
		out.println (" <Styles>");
		out.println ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s62'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Top' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s63'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Top' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='14'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='SHEET1'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=SHEET1!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='45' ss:ExpandedRowCount='"+(dtimeList.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s62' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='84'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='68.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='99.75'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='209.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='84'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='216' ss:Span='1'/>");
		out.println ("   <Column ss:Index='8' ss:StyleID='s62' ss:Width='39' ss:Span='2'/>");
		out.println ("   <Column ss:Index='11' ss:StyleID='s62' ss:Width='68.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='13' ss:StyleID='s62' ss:Width='53.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='68.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='16' ss:StyleID='s62' ss:Width='216' ss:Span='2'/>");
		out.println ("   <Column ss:Index='19' ss:StyleID='s62' ss:Width='39'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='53.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='24.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='23' ss:StyleID='s62' ss:Width='75.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='25' ss:StyleID='s62' ss:Width='45.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='27' ss:StyleID='s62' ss:Width='53.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='29' ss:StyleID='s62' ss:Width='68.25' ss:Span='4'/>");
		out.println ("   <Column ss:Index='34' ss:StyleID='s62' ss:Width='84'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='99.75'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='68.25' ss:Span='1'/>");
		
		out.println ("   <Row ss:AutoFitHeight='0' ss:Height='19.5'>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>國外合作遠距教學碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>全英語學程碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>跨校選修碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>教師分類碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>本校職稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>聘書職級碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>科系屬性碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>專兼任碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>課程名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>英文課程名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>科系</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>科系英文名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>學程名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>組別</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>年級</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>班級</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>開課老師</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>學分數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>開課時數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>實習時數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>課程摘要</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>課程大綱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>課程連結</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>備註</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>填表人</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>授課語言1</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>授課語言2</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>證照1</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>證照2</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>部別碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>學制碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>部校訂碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>半全年碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>科目類別</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>教學型態</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>必選修碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>師資來源碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>全英語教學碼</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>節次</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>教室</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>教學內涵</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>課號</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>編號</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		
		/*StringBuilder sb=new StringBuilder("SELECT cd.ename, d.Oid, d.elearning, cs.eng_name, c.Grade, c.DeptNo, cd.name as DeptName,c.CampusNo, c.SchoolNo, "
		+ "c.ClassNo, c.ShortName, c.graduate, d.thour, d.credit,ce.idno as pid, IFNULL(ce.name,'') as pname, e.unit as idno2, e.cname, IFNULL(e.category,'') as cat1, cs.chi_name, "
		+ "d.cscode, d.techid as techid, d.opt, cdo.name as opt2, d.thour, d.credit, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt FROM "
		+ "(Dtime d LEFT OUTER JOIN empl e ON e.idno=d.techid)LEFT OUTER JOIN CodeEmpl ce ON e.pcode=ce.idno,CODE_DEPT cd,"
		+ "CODE_DTIME_OPT cdo, Csno cs, Class c WHERE cd.id=c.DeptNo AND c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND "
		+ "d.opt=cdo.id AND d.Oid IN(");*/
		StringBuilder sb=new StringBuilder("SELECT pc.id, IFNULL(d.Introduction,'')as Introduction, IFNULL(d.Syllabi,'')as Syllabi, IFNULL(d.Syllabi_sub,'')as Syllabi_sub,cd.ename, d.Oid, d.elearning, cs.eng_name, c.Grade, c.DeptNo, cd.name as DeptName,c.CampusNo, c.SchoolNo, "
		+ "c.ClassNo, c.ShortName, c.graduate, d.thour, d.credit,ce.idno as pid, ce.name as pname, e.unit as idno2, IFNULL(e.sname,'')as sname, e.cname, IFNULL(e.category,'') as cat1, cs.chi_name, "
		+ "d.Oid, d.cscode, d.techid as techid, d.opt, cdo.name as opt2, d.thour, d.credit, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt FROM "
		+ "(Dtime d LEFT OUTER JOIN empl e ON e.idno=d.techid)LEFT OUTER JOIN CodeEmpl ce ON e.pcode=ce.idno,CODE_DEPT cd,"
		+ "CODE_DTIME_OPT cdo, Csno cs, Class c LEFT OUTER JOIN CODE_PE9 pc ON pc.id1=c.DeptNo WHERE cd.id=c.DeptNo AND c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND "
		+ "d.opt=cdo.id AND d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		//sb.append(") ORDER BY d.depart_class,d.cscode LIMIT 727, 1");
		sb.append(") ORDER BY d.depart_class,d.cscode");
		//System.out.println(sb);
		String tmpPcode111, tmpPcode112,departClass,tmpDepartClass;
		dtimeList=df.sqlGet(sb.toString());
		//List<Map>pecode9=df.sqlGet("SELECT * FROM CODE_PE9");
		//List<Map>pecode11=df.sqlGet("SELECT * FROM CODE_PE11");
		
		//String allYear,cscode, schoolName,schoolType;
		String schoolName,schoolType;
		boolean practical = false;
		List<Map>csGroup;
		
		List<Map>dtimeTeacher;
		//String language, language1, depType, emplType, pName;
		String language, language1;
		for (int i = 0; i < dtimeList.size(); i++) {	
			
			
			//System.out.println(dtimeList.get(i));
				
			
		//for (int i = 0; i < 725; i++) {
			//System.out.println(dtimeList.get(i));
			//排除代碼
			if (dtimeList.get(i).get("cscode").toString().equals("50000")//排除班會
			|| dtimeList.get(i).get("cscode").toString().equals("T0001")//排除通識
			|| dtimeList.get(i).get("cscode").toString().equals("T0002"))continue;			
			/*String schoolTerm; // 學期轉型
			if (term.equals("1")) {
				schoolTerm = "1";
			} else {
				schoolTerm = "2";
			}*/
			// 部制
			schoolName = dtimeList.get(i).get("SchoolNo").toString();
			schoolType = "";
			if (schoolName.equals("12")) {
				schoolName = "0";
				schoolType = "1002";
			} else if (schoolName.equals("15")) {
				schoolType = "1003";
				schoolName = "0";
			} else if (schoolName.equals("1G")) {
				schoolType = "1006";
				schoolName = "0";
			} else if (schoolName.equals("22")) {
				schoolType = "1002";
				schoolName = "1";
			} else if (schoolName.equals("32")) {
				schoolName = "2";
				schoolType = "1002";
			} else if (schoolName.equals("42")) {
				schoolType = "1001";
				schoolName = "0";
			} else if (schoolName.equals("52")) {
				schoolType = "1001";
				schoolName = "1";
			} else if (schoolName.equals("54")) {
				schoolType = "1005";
				schoolName = "1";
			} else if (schoolName.equals("64")) {
				schoolType = "1005";
				schoolName = "0";
			} else if (schoolName.equals("72")) {
				schoolType = "1001";
				schoolName = "2";
			} else if (schoolName.equals("82")) {
				schoolType = "1001";
				schoolName = "1";
			} else if (schoolName.equals("8G")) {
				schoolType = "1006";
				schoolName = "1";
			} else if (schoolName.equals("92")) {
				schoolType = "1002";
				schoolName = "1";
			}			
			
			
			
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='19.5'>");
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//
			dtimeTeacher=df.sqlGet("SELECT IFNULL(e.sname,'')as sname, e.cname, ce.idno as pid, ce.name as pname, e.category as cat1 FROM empl e "
			+ "LEFT OUTER JOIN CodeEmpl ce ON e.pcode=ce.idno, Dtime_teacher d WHERE e.idno=d.teach_id AND d.Dtime_oid="+
			dtimeList.get(i).get("Oid"));			
			//System.out.println(dtimeTeacher.size());			
			
			//教師分類碼
			out.print ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>");			
			//多教師
			if(dtimeTeacher.size()>0){
				sb=new StringBuilder();
				for(int j=0; j<dtimeTeacher.size(); j++){
					sb.append("1,");						
				}
				sb.delete(sb.length()-1, sb.length());
				if(dtimeList.get(i).get("cname")!=null){
					out.print("1,"+sb);
				}else{
					out.print(sb.toString().trim());
				}
			}else{
				out.print ("1");
			}			
			out.println ("</Data></Cell>");
			//聘書職級碼							
			if(dtimeTeacher.size()>0){
				sb=new StringBuilder();
				for(int j=0; j<dtimeTeacher.size(); j++){
					if(dtimeTeacher.get(j).get("pname")==null||dtimeTeacher.get(j).get("pname").equals("")){
						sb.append(dtimeTeacher.get(j).get("sname")+",");
					}else{
						sb.append(dtimeTeacher.get(j).get("pname")+",");
					}
											
				}
				sb.delete(sb.length()-1, sb.length());
				out.print ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>");
				if(dtimeList.get(i).get("cname")!=null){
					
					if(dtimeList.get(i).get("pname")==null||dtimeList.get(i).get("pname").equals("")){
						
						out.print(dtimeList.get(i).get("sname")+","+sb);
					}else{
						out.print(dtimeList.get(i).get("pname")+","+sb);
					}
					
				}else{
					out.print(sb.toString());
				}
				
				out.println ("    </Data></Cell>");
			}else{					
				
				if(dtimeList.get(i).get("pname")==null||dtimeList.get(i).get("pname").equals("")){
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("sname")+"</Data></Cell>");
					
				}else{
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("pname")+"</Data></Cell>");
					
				}
				
				
			
			}		
			if(dtimeTeacher.size()>0){
				sb=new StringBuilder();
				for(int j=0; j<dtimeTeacher.size(); j++){
					sb.append(getCategory(String.valueOf(dtimeTeacher.get(j).get("pid")))+",");						
				}
				sb.delete(sb.length()-1, sb.length());
				out.print ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>");//聘書職級碼
				if(dtimeList.get(i).get("cname")!=null){
					out.print( getCategory(String.valueOf(dtimeList.get(i).get("pid")))+","+sb);
				}else{
					out.print(sb);
				}
				out.println ("    </Data></Cell>");
			}else{
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+
				getCategory(String.valueOf(dtimeList.get(i).get("pid")))+"</Data></Cell>");
			}				
			//科系屬性碼
			
			//depType=getDept(dtimeList.get(i).get("ClassNo").toString());
			
			
			/*if ((dtimeList.get(i)).get("chi_name").toString().indexOf("體育") >= 0 || dtimeList.get(i).get("chi_name").toString().indexOf("軍訓") >= 0) {
				depType = "9901";
			}
			if ((dtimeList.get(i)).get("opt").toString().equals("3")) {
				depType = "9902";
			}*/
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("id")+"</Data></Cell>");
			
			//專兼任
			/*if(dtimeTeacher.size()>0){
				out.print ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>");
				if(dtimeList.get(i).get("cname")!=null){					
					sb=new StringBuilder();
					for(int j=0; j<dtimeTeacher.size(); j++){
						sb.append(dtimeTeacher.get(j).get("cat1")+",");						
					}
					sb.delete(sb.length()-1, sb.length());
					out.print ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>");
					if(dtimeList.get(i).get("cname")!=null){
						out.print(dtimeList.get(i).get("cat1")+","+sb);
					}else{
						out.print(sb.toString());
					}
				}
				out.print ("    </Data></Cell>");
			}else{					
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("cat1")+"</Data></Cell>");
			}*/
			if(dtimeTeacher.size()>0){
				sb=new StringBuilder();
				for(int j=0; j<dtimeTeacher.size(); j++){
					sb.append(dtimeTeacher.get(j).get("cat1")+",");						
				}
				sb.delete(sb.length()-1, sb.length());
				out.print ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>");//聘書職級碼
				if(dtimeList.get(i).get("cname")!=null){
					out.print( dtimeList.get(i).get("cat1")+","+sb);
				}else{
					out.print(sb);
				}
				out.println ("    </Data></Cell>");
			}else{
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+
				dtimeList.get(i).get("cat1")+"</Data></Cell>");
			}			
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("eng_name")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("DeptName")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("ename")+"</Data></Cell>");//科系英文名稱
			// 學程名稱
			csGroup = df.sqlGet("SELECT cg.cname FROM CsGroupSet cgs, CsGroup cg WHERE "
			+ "cgs.group_oid=cg.Oid AND cgs.cscode='"+ dtimeList.get(i).get("cscode")+"'GROUP BY cg.Oid");
			sb = new StringBuilder();
			if (csGroup.size() > 0) {
				for (int j = 0; j < csGroup.size(); j++) { sb.append(csGroup.get(j).get("cname") + ", "); }
			}
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+sb+"</Data></Cell>");//學程名稱
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//組別
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("Grade")+"</Data></Cell>");//年級
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("ShortName").toString().charAt(dtimeList.get(i).get("ShortName").toString().length()-1)+"</Data></Cell>");//班級
			// 開課老師
			sb = new StringBuilder(); 			
			if (dtimeTeacher.size() > 0) {
				for (int j = 0; j < dtimeTeacher.size(); j++) {
					sb.append(dtimeTeacher.get(j).get("cname")+ ",");
				}
				sb.delete(sb.length()-1, sb.length());
				if(dtimeList.get(i).get("cname")==null){
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+sb+"</Data></Cell>");
				}else{
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("cname") + ","+ sb.toString() + "</Data></Cell>");
				}
			} else {
				if(dtimeList.get(i).get("cname")==null){
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");
				}else{
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("cname")+"</Data></Cell>");
				}
			}
			
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("credit")+"</Data></Cell>");//學分數
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("thour")+"</Data></Cell>");//開課時數
			// 實習時數
			practical=false;
			if (dtimeList.get(i).get("chi_name").toString().indexOf("實習") == -1) {    
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>0</Data></Cell>");
			} else {
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+Float.parseFloat(dtimeList.get(i).get("thour").toString())+"</Data></Cell>");
				practical = true;
			}
			/*
			out.println ("    <Cell ss:StyleID='s63' ss:HRef='http://ap.cust.edu.tw/CIS/Print/teacher/IntorDoc.do?Oid="+ dtimeList.get(i).get("Oid") + "'><Data ss:Type='String'>http://ap.cust.edu.tw/CIS/Print/teacher/IntorDoc.do?Oid="+ dtimeList.get(i).get("Oid") + "</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s63' ss:HRef='http://ap.cust.edu.tw/CIS/Print/teacher/SylDoc.do?Oid="+dtimeList.get(i).get("Oid")+"'><Data ss:Type='String'>http://ap.cust.edu.tw/CIS/Print/teacher/SylDoc.do?Oid="+dtimeList.get(i).get("Oid")+"</Data></Cell>");
			*/
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+replaceXMLSymbol(dtimeList.get(i).get("Introduction").toString())+"</Data></Cell>");
			if(dtimeList.get(i).get("Syllabi_sub").toString().length()>4000){
				//System.out.println(dtimeList.get(i).get("Syllabi_sub").toString().length()+", "+dtimeList.get(i).get("Syllabi").toString().length());
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>內容超過儲存格最大容量</Data></Cell>");
			}else{
				//System.out.println(replaceXMLSymbol(dtimeList.get(i).get("Syllabi").toString().trim()+dtimeList.get(i).get("Syllabi_sub").toString().trim()));
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+replaceXMLSymbol(dtimeList.get(i).get("Syllabi").toString().trim()+dtimeList.get(i).get("Syllabi_sub").toString().trim())+"</Data></Cell>");
			}
			
			out.println ("    <Cell ss:StyleID='s63' ss:HRef='http://ap.cust.edu.tw/CIS/Print/teacher/IntorDoc.do?Oid="+dtimeList.get(i).get("Oid")+"'><Data ss:Type='String'>http://ap.cust.edu.tw/CIS/Print/teacher/IntorDoc.do?Oid="+dtimeList.get(i).get("Oid")+"</Data></Cell>");
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//備註
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//填表人
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+df.sqlGetStr("SELECT COUNT(*)FROM Seld s, Dtime d, stmd st WHERE "
			+ "s.Dtime_oid=d.Oid AND s.student_no=st.student_no AND st.sex='1' AND d.Oid='"
			+ dtimeList.get(i).get("Oid") + "'")+"</Data></Cell>");//男
			
			
			System.out.println("SELECT COUNT(*)FROM Seld s, Dtime d, stmd st WHERE "
					+ "s.Dtime_oid=d.Oid AND s.student_no=st.student_no AND st.sex='2' AND d.Oid='"
					+ dtimeList.get(i).get("Oid") + "'");
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+df.sqlGetStr("SELECT COUNT(*)FROM Seld s, Dtime d, stmd st WHERE "
			+ "s.Dtime_oid=d.Oid AND s.student_no=st.student_no AND st.sex='2' AND d.Oid='"
			+ dtimeList.get(i).get("Oid") + "'")+"</Data></Cell>");//女			
			language = "國語";
			if ((dtimeList.get(i)).get("chi_name").toString().indexOf("日文") >= 0 || dtimeList.get(i).get("chi_name").toString().indexOf("日語") >= 0) {
				language = "日語";
			} else if ((dtimeList.get(i)).get("chi_name").toString().indexOf("英文") >= 0 || dtimeList.get(i).get("chi_name").toString().indexOf("英語") >= 0) {
				language = "英語";
			}
			// 授課語言1
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+language+"</Data></Cell>");
			language1 = "";
			if (!language.equals("國語")) {
				language1 = "國語";
			}
			// 授課語言2
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+language1+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//證照1
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//證照2
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>" + schoolName + "</Data></Cell>");//部別碼
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>" + schoolType + "</Data></Cell>");//學制碼
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>1</Data></Cell>");//部校訂碼
			/*allYear = "0";// 有連號的課程為全年
			cscode = dtimeList.get(i).get("cscode").toString();
			if (cscode.charAt(cscode.length() - 1) != '0') {
				allYear = "1";
			}*/
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>0</Data></Cell>");// 半全年碼(20141016全設定為零)
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");//科目類別
			// 教學型態碼
			String type = "1";
			if (dtimeList.get(i).get("elearning").toString().equals("1")) {
				type = "4";
			}
			if (dtimeList.get(i).get("elearning").equals("2")) {
				type = "8";//輔助教學
			}
			if (practical) {
				type = "5";//實習
			}
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+type+"</Data></Cell>");
			// 必選修碼
			if (dtimeList.get(i).get("opt").equals("1")) {
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>0</Data></Cell>");
			} else {
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>1</Data></Cell>");
			}
			
			// 師資來源碼
			if(dtimeList.get(i).get("techid")!=null&&dtimeList.get(i).get("idno2")!=null){
				if (dtimeList.get(i).get("idno2").equals("0")) {
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>2</Data></Cell>");//通識老師
				} else {
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>1</Data></Cell>");//專業老師
				}
			}else{
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'></Data></Cell>");
			}
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>0</Data></Cell>");//全英語教學碼
			
			
			cls=df.sqlGet("SELECT * FROM Dtime_class d WHERE d.Dtime_oid="+dtimeList.get(i).get("Oid"));
			
			
			
			try {
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+getFucxCls(cls)+"</Data></Cell>");//節次
			}catch(Exception e) {
				out.println ("    <Cell ss:StyleID='s63'></Cell>");
			}
			
			
			try {				
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+getFucxRoom(cls)+"</Data></Cell>");//地點
			}catch(Exception e) {
				out.println ("    <Cell ss:StyleID='s63'></Cell>");
			}
			
			try {
				if(practical) {
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>4</Data></Cell>");//含意
				}else {
					out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+getFucxMean(dtimeList.get(i).get("chi_name").toString()) +"</Data></Cell>");
					
				}
			}catch(Exception e) {
				out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>4</Data></Cell>");
			}
			
			
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("cscode")+"</Data></Cell>");//全英語教學碼
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>"+dtimeList.get(i).get("Oid")+"</Data></Cell>");//編號
			
			out.println ("   </Row>");
			
			
		}
		
		
		
		
		
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.51181102362204722'/>");
		out.println ("    <Footer x:Margin='0.51181102362204722'/>");
		out.println ("    <PageMargins x:Bottom='0.98425196850393704' x:Left='0.74803149606299213'");
		out.println ("     x:Right='0.74803149606299213' x:Top='0.98425196850393704'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>600</HorizontalResolution>");
		out.println ("    <VerticalResolution>600</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <DoNotDisplayGridlines/>");
		out.println ("   <FreezePanes/>");
		out.println ("   <FrozenNoSplit/>");
		out.println ("   <SplitHorizontal>1</SplitHorizontal>");
		out.println ("   <TopRowBottomPane>1</TopRowBottomPane>");
		out.println ("   <ActivePane>2</ActivePane>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("    </Pane>");
		out.println ("    <Pane>");
		out.println ("     <Number>2</Number>");
		out.println ("     <ActiveRow>0</ActiveRow>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		out.println ("</Workbook>");
		out.println ("");
		
		
		
		out.close();
		
	}
	
	private String getFucxMean(String n) {
		
		if(n.indexOf("個案")>0||n.indexOf("實例")>0||n.indexOf("案例")>0||n.indexOf("專題")>0) {
			return "2";
		}
		
		if(n.indexOf("實務")>0||n.indexOf("模擬")>0||n.indexOf("服務學習")>0||n.indexOf("實習")>0) {
			return "3";
		}
		
		return "1";
	}
	
	private String getFucxRoom(List<Map>cls) {
		StringBuilder r=new StringBuilder();
		for(int i=0; i<cls.size(); i++) {
			r.append(cls.get(i).get("place")+",");
			
		}
		r.delete(r.length()-1, r.length());
		return r.toString();
	}
	
	private String getFucxCls(List<Map>cls) {
		StringBuilder clsinfo=new StringBuilder();
		if(cls.size()==1) {
			
			if(!cls.get(0).get("begin").equals(cls.get(0).get("end"))) {				
				clsinfo.append(     fucit(Integer.parseInt(cls.get(0).get("begin").toString()), Integer.parseInt(cls.get(0).get("end").toString()))         );
			}else {
				clsinfo.append(cls.get(0).get("begin")+",");
			}
			
		}else {
			
			for(int j=0; j<cls.size(); j++) {
				if(!cls.get(j).get("begin").equals(cls.get(j).get("end"))) {
					clsinfo.append(this.bl.getWeekOfDay4Zh(Integer.parseInt(cls.get(j).get("week").toString()), "星期")+"-"+fucit(Integer.parseInt(cls.get(j).get("begin").toString()), Integer.parseInt(cls.get(j).get("end").toString()))+"、");
				}else {
					clsinfo.append(this.bl.getWeekOfDay4Zh(Integer.parseInt(cls.get(j).get("week").toString()), "星期")+"-"+cls.get(j).get("begin")+"、");
				}
			}
			if(clsinfo.length()>0)
			clsinfo.delete(clsinfo.length()-1, clsinfo.length());
		}
		
		
		
		return clsinfo.toString();
	}
	
	private String fucit(int begin, int end) {
		
		StringBuilder fucit=new StringBuilder();
		for(int i=begin; i<=end; i++) {
			fucit.append(i+",");
		}
		fucit.delete(fucit.length()-1, fucit.length());
		return fucit.toString();
	}
	
	
	/*private String getDept(String departClass){		

		if (StringUtils.isBlank(departClass))return "";
		String deptCode;
		if(departClass.length()==6){
			deptCode = StringUtils.substring(departClass, 3, 4);
			for(int i=0; i)
			switch (deptCode.charAt(0)) {
			case '0':
				return "999901";
			case '1':
				return "520201";
			case '2':
				return "520101";
			case '3':
				return "520103";
			case '4':
				return "520601";
			case '5':
				return "580101";			
			case '6':
				return "520301";
			case '7':
				return "340301";
			case '8':
				return "";
			case '9':
				return "340501";
			case 'A':
				return "520208";
			case 'B':
				return "520112";
			case 'C':
				return "840202";
			case 'D':
				return "349902";
			case 'E':
				return "520114";
			case 'F':
				return "620601";
			case 'G':
				return "";
			case 'H':
				return "420302";
			case 'I':
				return "810103";
			case 'J':
				return "810202";
			case 'K':
				return "340307";
			case 'U':
				return "810224";
			case 'V':
				return "580101";
			case 'W':
				return "239904";
			case 'X':
				return "340120";
			default:
				return "";
			}
			
			
		}else{
			deptCode = StringUtils.substring(departClass, 3, 5);
			if(deptCode.equals("KA")){
				return"340824";
			}			
			return"";
		}
		
	}*/
	
	/*private String getDept(String departClass){		

		if (StringUtils.isBlank(departClass))return "";
		String deptCode;
		if(departClass.length()==6){
			deptCode = StringUtils.substring(departClass, 3, 4);
			
			
			switch (deptCode.charAt(0)) {
			case '0':
				return "9902";
			case '1':
				return "5202";
			case '2':
				return "5201";
			case '3':
				return "5201";
			case '4':
				return "5206";
			case '5':
				return "5801";			
			case '6':
				return "5203";
			case '7':
				return "3403";
			case '8':
				return "";
			case '9':
				return "3405";
			case 'A':
				return "5202";
			case 'B':
				return "5201";
			case 'C':
				return "8402";
			case 'D':
				return "3499";
			case 'E':
				return "5201";
			case 'F':
				return "6206";
			case 'G':
				return "";
			case 'H':
				return "4203";
			case 'I':
				return "8101";
			case 'J':
				return "8102";
			case 'K':
				return "3403";
			case 'U':
				return "8102";
			case 'V':
				return "5801";
			case 'W':
				return "2399";
			case 'X':
				return "3401";
			default:
				return "";
			}
			
			return "";
			
		}else{
			deptCode = StringUtils.substring(departClass, 3, 5);
			if(deptCode.equals("KA")){
				return"3408";
			}			
			return"";
		}
		
	}*/
	/**
	 * 教師分類碼
	 * @param departClass
	 * @return
	 */
	private String getCategory(String pcode){		
		//System.out.println(pcode);
		if(pcode.equals("21"))return"1";
		if(pcode.equals("22"))return"2";
		if(pcode.equals("40"))return"3";
		if(pcode.equals("23"))return"4";
		if(pcode.equals("19"))return"7";
		return "0";
	}
	
	public String replaceXMLSymbol(String str){
		try{
			str=str.replaceAll("&", "");
			str=str.replaceAll("<", "");
			str=str.replaceAll(">", "");
			str=str.replaceAll("\"", "");
			str=str.replaceAll("", "");
			//str=str.replaceAll(",", "&cedil;");
			str=str.replaceAll("'", "");
			str=str.replaceAll("\"", "");
			str=str.replaceAll("ˋ", "");
			str=str.replaceAll("#ln;", ",");
			
		}catch(NullPointerException e){
			return str;
		}
		
		return str;
	}

}
