package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

/**
 * 課程通用報表
 * @author John
 *
 */
public class ListDtime extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>DtimeList) throws IOException{	
		Date date=new Date();
		
		xml2ods(response, getRequest(), date);
		
		PrintWriter out=response.getWriter();
		
		if(DtimeList.size()<1){
			out.println ("Not enough columns given to draw the requested table");
			return;
		}
		
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
		out.println ("  <LastPrinted>2014-05-08T04:20:59Z</LastPrinted>");
		out.println ("  <Created>2014-05-08T03:36:34Z</Created>");
		out.println ("  <LastSaved>2014-05-08T04:24:12Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>11880</WindowHeight>");
		out.println ("  <WindowWidth>28800</WindowWidth>");
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
		out.println ("  <Style ss:ID='s65'>");
		out.println ("   <Alignment ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='14'");
		out.println ("    ss:Color='#333333'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s66'>");
		out.println ("   <Borders/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s68'>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s69'>");
		out.println ("   <Alignment ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='14'");
		out.println ("    ss:Color='#333333'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s70'>");
		out.println ("   <Borders/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");		
		
		out.println (" <Worksheet ss:Name='基本資料'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=基本資料!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='12' ss:ExpandedRowCount='"+(DtimeList.size()+100)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s66' ss:DefaultColumnWidth='77.25'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s66' ss:Width='68.25' ss:Span='4'/>");
		out.println ("   <Column ss:Index='6' ss:StyleID='s66' ss:Width='39' ss:Span='2'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s66' ss:Width='68.25' ss:Span='1'/>");
		out.println ("   <Row ss:Height='19.5'>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>課程編號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>科目代碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>科目名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>教師姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>身份</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>選別</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>形態</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>學分</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>時數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>已選人數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>上限人數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<DtimeList.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("cscode")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("cname")+"</Data></Cell>");			
			if(DtimeList.get(i).get("category")==null){
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			}else{
				if(DtimeList.get(i).get("category").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>專</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				}
			}			
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("optName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("eleName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("credit")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("thour")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("cnt")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+DtimeList.get(i).get("Select_Limit")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.70866141732283472'");
		out.println ("     x:Right='0.70866141732283472' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
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
		out.println ("     <ActiveRow>4</ActiveRow>");
		out.println ("     <ActiveCol>5</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		StringBuilder sb=new StringBuilder("SELECT d.Oid, cs.cscode, c.ClassName, cs.chi_name, dc.week, dc.begin, dc.end, dc.place FROM "
				+ "Dtime d, Class c, Csno cs, Dtime_class dc WHERE cs.cscode=d.cscode AND "
				+ "c.ClassNo=d.depart_class AND d.Oid=dc.Dtime_oid AND d.Oid IN(");
		
		
		for(int i=0; i<DtimeList.size(); i++){
			sb.append(DtimeList.get(i).get("Oid")+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")ORDER BY d.depart_class, d.cscode, dc.week, dc.begin");
		
		
		
		List<Map>tmpList=df.sqlGet(sb.toString());
		
		
		out.println (" <Worksheet ss:Name='時間地點'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=時間地點!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='8' ss:ExpandedRowCount='"+(tmpList.size()+100)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s70' ss:DefaultColumnWidth='83.25'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s70' ss:Width='68.25' ss:Span='3'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s70' ss:Width='39'/>");
		out.println ("   <Column ss:StyleID='s70' ss:Width='68.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='8' ss:StyleID='s70' ss:Width='39'/>");
		out.println ("   <Row ss:Height='19.5'>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>課程編號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目代碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>星期</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>開始節次</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>結束節次</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>教室</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<tmpList.size(); i++){
			
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("cscode")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("week")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("begin")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("end")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("place")+"</Data></Cell>");
			out.println ("   </Row>");
			
		}
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.70866141732283472'");
		out.println ("     x:Right='0.70866141732283472' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
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
		out.println ("     <ActiveRow>12</ActiveRow>");
		out.println ("     <ActiveCol>8</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		
		sb=new StringBuilder("SELECT d.Oid, cs.cscode, cs.chi_name, c.ClassName, dc.* FROM Dtime d, Class c, Csno cs, Dtime_cross dc "
				+ "WHERE cs.cscode=d.cscode AND c.ClassNo=d.depart_class AND d.Oid=dc.Dtime_oid AND d.Oid IN(");
		for(int i=0; i<DtimeList.size(); i++){
			sb.append(DtimeList.get(i).get("Oid")+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")ORDER BY d.depart_class");
		tmpList=df.sqlGet(sb.toString());
		
		
		out.println (" <Worksheet ss:Name='跨選規則'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=跨選規則!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='9' ss:ExpandedRowCount='"+(tmpList.size()+10)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s70' ss:DefaultColumnWidth='83.25'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s70' ss:Width='68.25' ss:Span='3'/>");
		out.println ("   <Row ss:Height='19.5'>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>課程編號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目代碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>跨選校區</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>跨選部制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>跨選系所</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>跨選年級</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>跨選班級</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		List<Map<String,String>>allCampus=df.sqlGet("SELECT id, name FROM CODE_CAMPUS");
		List<Map<String,String>>allSchool=df.sqlGet("SELECT id, name FROM CODE_SCHOOL");
		List<Map<String,String>>allDept=df.sqlGet("SELECT id, name FROM CODE_DEPT");
		
		String c,s,d;
		for(int i=0; i<tmpList.size(); i++){
			c=""; s=""; d="";
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("cscode")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("chi_name")+"</Data></Cell>");
			
			for(int j=0; j<allCampus.size(); j++){
				if(allCampus.get(j).get("id").equals(tmpList.get(i).get("Cidno")))c=allCampus.get(j).get("name");
			}
			out.println ("    <Cell><Data ss:Type='String'>"+c+"</Data></Cell>");
			
			for(int j=0; j<allSchool.size(); j++){
				if(allSchool.get(j).get("id").equals(tmpList.get(i).get("Sidno")))s=allSchool.get(j).get("name");
			}
			out.println ("    <Cell><Data ss:Type='String'>"+s+"</Data></Cell>");
			
			for(int j=0; j<allDept.size(); j++){
				if(allDept.get(j).get("id").equals(tmpList.get(i).get("Didno")))d=allDept.get(j).get("name");
			}
			out.println ("    <Cell><Data ss:Type='String'>"+d+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("Grade")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("ClassNo")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.70866141732283472'");
		out.println ("     x:Right='0.70866141732283472' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
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
		out.println ("     <RangeSelection>R1</RangeSelection>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		sb=new StringBuilder("SELECT d.Oid, cs.cscode, cs.chi_name, e.cname, c.ClassName "
				+ "FROM Dtime d, Class c, Csno cs, Dtime_teacher dt, empl e WHERE "
				+ "e.idno=dt.teach_id AND cs.cscode=d.cscode AND c.ClassNo=d.depart_class "
				+ "AND d.Oid=dt.Dtime_oid AND d.Oid IN(");
		for(int i=0; i<DtimeList.size(); i++){
			sb.append(DtimeList.get(i).get("Oid")+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")ORDER BY d.depart_class, d.Oid");
		
		tmpList=df.sqlGet(sb.toString());
		
		out.println (" <Worksheet ss:Name='多教師'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=多教師!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='5' ss:ExpandedRowCount='"+(tmpList.size()+10)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s68' ss:DefaultColumnWidth='106.5'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s68' ss:Width='68.25' ss:Span='3'/>");
		out.println ("   <Row ss:Height='19.5'>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>課程編號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目代碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>授課教師</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<tmpList.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("cscode")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("cname")+"</Data></Cell>");
			out.println ("   </Row>");
		}		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.70866141732283472'");
		out.println ("     x:Right='0.70866141732283472' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
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
		
		
		
		
		
		
		sb=new StringBuilder("SELECT d.Oid, cs.cscode, cs.chi_name, db.cscode as bolckNo, cs1.chi_name as blockName, c.ClassName FROM Dtime d, "
				+ "Class c, Csno cs, Csno cs1, Dtime_block db WHERE cs1.cscode=db.cscode AND "
				+ "cs.cscode=d.cscode AND c.ClassNo=d.depart_class AND d.Oid=db.Dtime_oid AND d.Oid IN(");
		for(int i=0; i<DtimeList.size(); i++){
			sb.append(DtimeList.get(i).get("Oid")+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")ORDER BY d.depart_class, d.Oid");
		tmpList=df.sqlGet(sb.toString());
		
		out.println (" <Worksheet ss:Name='擋修規則'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=擋修規則!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='5' ss:ExpandedRowCount='"+(tmpList.size()+10)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s68' ss:DefaultColumnWidth='105'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s68' ss:Width='68.25' ss:Span='3'/>");
		out.println ("   <Row ss:Height='19.5'>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>課程編號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目代碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>科目名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>擋修課程</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<tmpList.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("cscode")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("bolckNo")+"("+tmpList.get(i).get("blockName")+")</Data></Cell>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.70866141732283472'");
		out.println ("     x:Right='0.70866141732283472' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
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
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		sb=new StringBuilder("SELECT cs.cscode, cs.chi_name, d.Oid, dc.edate, dc.note, e.cname FROM Dtime d, Class c, "
				+ "Csno cs, Dtime_edit_hist dc LEFT OUTER JOIN empl e ON dc.auditor=e.idno WHERE "
				+ "cs.cscode=d.cscode AND c.ClassNo=d.depart_class AND d.Oid=dc.Dtime_oid AND d.Oid IN(");
		for(int i=0; i<DtimeList.size(); i++){
			sb.append(DtimeList.get(i).get("Oid")+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")ORDER BY d.depart_class, d.Oid");
		
		tmpList=df.sqlGet(sb.toString());
		
		out.println (" <Worksheet ss:Name='修改記錄'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=修改記錄!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='2' ss:ExpandedRowCount='"+(tmpList.size()+10)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s68' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s68' ss:AutoFitWidth='0' ss:Width='99'/>");
		out.println ("   <Row>");
		out.println ("    <Cell><Data ss:Type='String'>課程編號</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>編輯者</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<tmpList.size();i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+tmpList.get(i).get("cname")+tmpList.get(i).get("note")+tmpList.get(i).get("edate")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.70866141732283472'");
		out.println ("     x:Right='0.70866141732283472' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
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
		out.println ("     <ActiveRow>27</ActiveRow>");
		out.println ("     <ActiveCol>3</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		out.println ("</Workbook>");
		out.close();		
		out.flush();		
	}
}