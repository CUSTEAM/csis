package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BaseAction;

/**
 * 教學評量
 * @author John
 *
 */
public class CsCoansw extends BaseAction{
	
	public void print(HttpServletResponse response, List<Map>list, String year, String term) throws IOException{		
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");			
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
		out.println ("  <LastPrinted>2014-04-03T02:14:45Z</LastPrinted>");
		out.println ("  <Created>2014-04-03T01:41:46Z</Created>");
		out.println ("  <LastSaved>2014-04-03T02:15:29Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>12540</WindowHeight>");
		out.println ("  <WindowWidth>25125</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <ActiveSheet>1</ActiveSheet>");
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
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s64'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s72'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#D9D9D9' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s76'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s79'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s87'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s95'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s96'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s117'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#D9D9D9' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s118'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#D9D9D9' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");		
		
		StringBuilder sb=new StringBuilder("SELECT d.Oid, c.ClassName, IFNULL(e.cname,'')as cname, cs.chi_name, d.samples, IFNULL(d.coansw, 0.00)as coansw, "
		+ "d.effsamples as cnt FROM Dtime d LEFT OUTER JOIN empl e ON e.idno=d.techid, Csno cs, Class c WHERE d.nonCoansw IS NULL AND "
		+ "d.cscode=cs.cscode AND d.depart_class=c.ClassNo AND d.Oid IN(");
						
		for(int i=0; i<list.size(); i++){
			sb.append(list.get(i).get("Oid")+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")AND d.samples>0 && d.coansw>0 ORDER BY d.techid");
		
		list=df.sqlGet(sb.toString());
		
		out.println (" <Worksheet ss:Name='課程列表 - 以50為基準'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=SHEET2!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='7' ss:ExpandedRowCount='"+(list.size()+100)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s62' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='80.25'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='283.5'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='52.5'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='42' ss:Span='1'/>");
		out.println ("   <Column ss:Index='7' ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='41.25'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>課程編號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>課程名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>教師姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>樣本數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>有效數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>平均值</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");		
		
		int a=0,b=0,c=0,d=0,samples;
		float coansw;
		for(int i=0; i<list.size(); i++){
			//if(list.get(i).get("cname")==null)continue;
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("samples")+"</Data></Cell>");			
			samples=Integer.parseInt(list.get(i).get("cnt").toString());
			
			System.out.println(Float.parseFloat( list.get(i).get("coansw").toString() )       /samples);
			coansw=50+(  Float.parseFloat( list.get(i).get("coansw").toString() )       /samples)*10;
			
			/*if(Float.parseFloat( list.get(i).get("coansw").toString() )/samples<2){
				System.out.println(50+(  (Float.parseFloat( list.get(i).get("coansw").toString() )/samples)*10    );
			}*/
				
			if(samples>0){
				if(coansw>=90)a+=1;
				if(coansw>=80&&coansw<90)b+=1;
				if(coansw>=70&&coansw<80)c+=1;
				if(coansw<70)d+=1;
				out.println ("    <Cell><Data ss:Type='String'>"+samples+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+Math.rint(coansw*100)/100+"</Data></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			}
			
			out.println ("   </Row>");
		}		
		out.println ("  </Table>");
		
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'");
		out.println ("     x:Data='&amp;C&amp;&quot;標楷體,粗體&quot;&amp;18中華科技大學"+year+"學年 第"+term+"學期 課程學習滿意度調查統計表'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984' x:Data='&amp;L"+sf.format(date)+"&amp;R&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.23622047244094491'");
		out.println ("     x:Right='0.23622047244094491' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>1</ActiveRow>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		
		
		out.println (" <Worksheet ss:Name='統計列表 - 50為基準'>");
		out.println ("  <Table ss:ExpandedColumnCount='2' ss:ExpandedRowCount='6' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='93'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='54.75'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s117'><Data ss:Type='String'>平均值</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s118'><Data ss:Type='String'>課程數</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>70以下</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s87'><Data ss:Type='Number'>"+d+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>70~79.9</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s87'><Data ss:Type='Number'>"+c+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>80~89.9</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s87'><Data ss:Type='Number'>"+b+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s95'><Data ss:Type='String'>90以上</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s96'><Data ss:Type='Number'>"+a+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>17</ActiveRow>");
		out.println ("     <ActiveCol>11</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println ("  <ConditionalFormatting xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <Range>R2C2:R5C2</Range>");
		out.println ("  </ConditionalFormatting>");
		out.println (" </Worksheet>");
		
		
		
		
		
		//out.println ("   <Row>");
		//out.println ("    <Cell ss:StyleID='s76'><Data ss:Type='String'>"+(a+b+c+d)+"</Data></Cell>");
		//out.println ("    <Cell ss:StyleID='s76' ss:Formula='=SUM(R[-4]C:R[-1]C)'><Data ss:Type='Number'>261</Data></Cell>");
		//out.println ("   </Row>");
		
		out.println (" <Worksheet ss:Name='課程列表 - 以0為基準'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=SHEET2!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='7' ss:ExpandedRowCount='"+(list.size()+100)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s62' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='80.25'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='283.5'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='52.5'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='42' ss:Span='1'/>");
		out.println ("   <Column ss:Index='7' ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='41.25'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>課程編號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>課程名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>教師姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>樣本數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>有效數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>平均值</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		a=0;b=0;c=0;d=0;samples=0;
		coansw=0;
		for(int i=0; i<list.size(); i++){
			//if(list.get(i).get("cname")==null)continue;
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("samples")+"</Data></Cell>");			
			samples=Integer.parseInt(list.get(i).get("cnt").toString());
			coansw=(Float.parseFloat(list.get(i).get("coansw").toString())/samples)*20;
				
			if(samples>0){
				if(coansw>=90)a+=1;
				if(coansw>=80&&coansw<90)b+=1;
				if(coansw>=70&&coansw<80)c+=1;
				if(coansw<70)d+=1;
				out.println ("    <Cell><Data ss:Type='String'>"+samples+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+Math.rint(coansw*100)/100+"</Data></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			}
			
			out.println ("   </Row>");
		}		
		out.println ("  </Table>");
		
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'");
		out.println ("     x:Data='&amp;C&amp;&quot;標楷體,粗體&quot;&amp;18中華科技大學"+year+"學年 第"+term+"學期 課程學習滿意度調查統計表'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984' x:Data='&amp;L"+sf.format(date)+"&amp;R&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.23622047244094491'");
		out.println ("     x:Right='0.23622047244094491' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>1</ActiveRow>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		
		
		out.println (" <Worksheet ss:Name='統計列表 - 0為基準'>");
		out.println ("  <Table ss:ExpandedColumnCount='2' ss:ExpandedRowCount='6' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='93'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='54.75'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s117'><Data ss:Type='String'>平均值</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s118'><Data ss:Type='String'>課程數</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>70以下</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s87'><Data ss:Type='Number'>"+d+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>70~79.9</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s87'><Data ss:Type='Number'>"+c+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>80~89.9</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s87'><Data ss:Type='Number'>"+b+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s95'><Data ss:Type='String'>90以上</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s96'><Data ss:Type='Number'>"+a+"</Data></Cell>");
		out.println ("   </Row>");		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>17</ActiveRow>");
		out.println ("     <ActiveCol>11</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println ("  <ConditionalFormatting xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <Range>R2C2:R5C2</Range>");
		out.println ("  </ConditionalFormatting>");
		out.println (" </Worksheet>");
		//
		
		sb=new StringBuilder("SELECT d.Oid, e.cname, st.student_no, st.student_name, c.ClassName, cs.chi_name, s.coansw, IFNULL(s.coansw_invalid,'')as coansw_invalid "
				+ "FROM Dtime d, empl e, Seld s, Csno cs, stmd st, Class c WHERE d.techid=e.idno AND "
				+ "s.coansw IS NOT NULL AND d.Oid=s.Dtime_oid AND s.student_no=st.student_no AND "
				+ "c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND d.Oid IN(");
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
			sb.append(list.get(i).get("Oid")+",");
		}		
		sb.delete(sb.length()-1, sb.length());
		sb.append(")ORDER BY d.Oid, st.student_no");
		System.out.println(sb);
		list=df.sqlGet(sb.toString());
		
		List<Map>ques=df.sqlGet("SELECT question, IFNULL(debug, '')as debug FROM Question WHERE topic='1'");
		
		out.println (" <Worksheet ss:Name='全校細節'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=SHEET2!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='"+(7+ques.size())+"' ss:ExpandedRowCount='"+(list.size()+1000)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s62' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='80.25'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='283.5'/>");
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='52.5'/>");		
		
		for(int i=0; i<ques.size(); i++){
			out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='42' />");
		}
		
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='52.5'/>");	
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='52.5'/>");	
		out.println ("   <Column ss:StyleID='s64' ss:AutoFitWidth='0' ss:Width='52.5'/>");	
		
		
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>課程編號</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>班級名稱</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>課程名稱</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>教師姓名</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		
		for(int i=0; i<ques.size(); i++){
			out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>"+ques.get(i).get("question")+"</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		}
		
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>學號</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>姓名</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>無效</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
		
		out.println ("   </Row>");
		
		for(int i=0; i<list.size(); i++){
			//if(list.get(i).get("cname")==null)continue;
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("Oid")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data></Cell>");
			
			for(int j=0; j<ques.size(); j++){
				out.println ("    <Cell><Data ss:Type='Number'>"+list.get(i).get("coansw").toString().substring(j,j+1)+"</Data></Cell>");
			}
			
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("student_no")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("student_name")+"</Data></Cell>");	
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("coansw_invalid")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'");
		out.println ("     x:Data='&amp;C&amp;&quot;標楷體,粗體&quot;&amp;18中華科技大學"+year+"學年 第"+term+"學期 課程學習滿意度調查統計表'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984' x:Data='&amp;L"+sf.format(date)+"&amp;R&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.23622047244094491'");
		out.println ("     x:Right='0.23622047244094491' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>1</ActiveRow>");
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
	
	/*private void recount(){
		
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
			if((que==3 && bug==3)){
				//有效
				df.exSql("UPDATE Dtime SET samples=samples+1, effsamples=effsamples+1, coansw=coansw+"+sum(c.get(i).get("ans").toString())+" WHERE Oid="+c.get(i).get("Dtime_oid"));
				c.get(i).put("check", true);
				
			}else{				
				//排除
				if(c.get(i).get("ans").toString().equals("1111111111")){
				//if(c.get(i).get("ans").toString().equals("1111111111")||c.get(i).get("ans").toString().equals("2222222222")||c.get(i).get("ans").toString().equals("3333333333")||c.get(i).get("ans").toString().equals("4444444444")||c.get(i).get("ans").toString().equals("5555555555")){
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
		}		
	
	}
	
	private Float sum(String ans){		
		int s=0;
		for(int i=0; i<ans.length(); i++){
			s+=Integer.parseInt(ans.substring(i, i+1));
		}		
		return (float)s/ans.length();
	}
	*/
}