package action.ele;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import action.BasePrintXmlAction;
import model.Message;

public class EleResManagerAction extends BasePrintXmlAction{
	
	public String year, term, dept, check, Oid[], thour_real[], checked[];
	
	public String execute(){	
		
		return SUCCESS;
	}
	
	
	public String search(){
		
		StringBuilder sql=new StringBuilder("SELECT ce.name ctname, e.cname, d.name, er.* FROM Elearning_reserve er, empl e, "
		+ "CODE_EMPL_CATEGORY ce, CODE_DEPT d WHERE ce.id=e.category AND d.id=e.unit AND er.techid=e.idno");
		if(!dept.equals(""))sql.append(" AND d.id='"+dept+"'");
		if(!year.equals(""))sql.append(" AND er.school_year="+year);
		if(!term.equals(""))sql.append(" AND er.school_term="+term);
		if(!check.equals("")){
			if(check.equals("1"))sql.append(" AND er.checked IS NOT NULL");
			if(check.equals("2"))sql.append(" AND er.checked IS NULL");
		}
		request.setAttribute("cs", df.sqlGet(sql.toString()));
			
		return SUCCESS;
	}
	
	
	public String edit(){
		Message msg=new Message();
		for(int i=0; i<Oid.length; i++){
			
			if(Oid[i].equals(""))continue;
			
			if(thour_real[i].equals("")){
				msg.setError("請輸入審核時數");
				savMessage(msg);
				return search();
			}
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			try{
				df.exSql("UPDATE Elearning_reserve SET checked='"+sf.format(new Date())+"', thour_real="+thour_real[i]+" WHERE Oid="+Oid[i]);
			}catch(Exception e){
				msg.setError("請確認時");
				savMessage(msg);
				return search();
			}
			
		}
		msg.setSuccess("已儲存");
		savMessage(msg);
		return search();
	}
	
	public String cancel(){
		Message msg=new Message();
		for(int i=0; i<Oid.length; i++){
			
			if(Oid[i].equals(""))continue;
			
			if(thour_real[i].equals("")){
				msg.setError("請輸入審核時數");
				savMessage(msg);
				return search();
			}
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			df.exSql("UPDATE Elearning_reserve SET checked=null, thour_real=null WHERE Oid="+Oid[i]);
		}
		msg.setSuccess("已儲存");
		savMessage(msg);
		return search();
	}
	
	public String del(){
		Message msg=new Message();
		for(int i=0; i<Oid.length; i++){
			
			if(Oid[i].equals(""))continue;
			df.exSql("DELETE FROM Elearning_reserve WHERE Oid="+Oid[i]);
		}
		msg.setSuccess("已刪除");
		savMessage(msg);
		return search();
	}
	
	public String print() throws IOException{
		
		Date date=new Date();
		xml2ods(response, getRequest(), date);
				
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
		out.println ("  <LastPrinted>2015-03-09T01:58:55Z</LastPrinted>");
		out.println ("  <Created>2015-03-09T01:18:52Z</Created>");
		out.println ("  <LastSaved>2015-03-09T02:04:40Z</LastSaved>");
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
		out.println ("  <Style ss:ID='s92'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s93'>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s94'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='Short Date'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		
		
		StringBuilder sql=new StringBuilder("SELECT e.cname, d.name, er.* FROM Elearning_reserve er, empl e, CODE_DEPT d WHERE e.category='1'AND d.id=e.unit AND er.techid=e.idno");
		if(!dept.equals(""))sql.append(" AND d.id='"+dept+"'");
		if(!year.equals(""))sql.append(" AND er.school_year="+year);
		if(!term.equals(""))sql.append(" AND er.school_term="+term);
		if(!check.equals("")){
			if(check.equals("1"))sql.append(" AND er.checked IS NOT NULL");
			if(check.equals("2"))sql.append(" AND er.checked IS NULL");
		}
		sql.append(" ORDER BY e.unit");
		List<Map>list=df.sqlGet(sql.toString());
		
		out.println (" <Worksheet ss:Name='全部課程'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=全部課程!R1C1:R2C11'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='11' ss:ExpandedRowCount='"+(list.size()+1)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s93' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='15.75'>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='120'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='213'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Span='6'/>");
		out.println ("   <Column ss:Index='11' ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='76.5'/>");
		out.println ("   <Row>");
		out.println ("    <Cell><Data ss:Type='String'>主聘系所</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>姓名</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>課程名稱</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>預計學年</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>預計學期</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>學分數</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>教材時數</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>題庫數</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>作業數</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>審核時數</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>審核日期</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<list.size(); i++){
			
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("name")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("chi_name")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("school_year")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("school_term")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("credit")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("thour")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			if(list.get(i).get("thour_real")==null){
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("thour_real")+"</Data></Cell>");
			}
			if(list.get(i).get("thour_real")==null){
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("checked")+"</Data></Cell>");
			}
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Layout x:Orientation='Landscape'/>");
		if(!year.equals("")&&!term.equals("")){
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;18 "+year+"學年第"+term+"學期遠距課程規劃'/>");
		}else{
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;18 遠距課程規劃'/>");
		}
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;L&amp;D &amp;T&amp;R&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
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
		out.println ("     <ActiveRow>7</ActiveRow>");
		out.println ("     <ActiveCol>8</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println ("  <AutoFilter x:Range='R1C1:R2C11'");
		out.println ("   xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		
		sql=new StringBuilder("SELECT e.cname,c.name FROM empl e, CODE_DEPT c WHERE e.category='1'AND "
		+ "e.unit=c.id AND e.idno NOT IN(SELECT techid FROM Elearning_reserve)");
		if(!dept.equals(""))sql.append("AND e.unit='"+dept+"'");
		sql.append(" ORDER BY c.id");
		
		list=df.sqlGet(sql.toString());
		out.println (" <Worksheet ss:Name='未規劃名單'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=未規劃名單!R1C1:R2C2'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='11' ss:ExpandedRowCount='"+(list.size()+1)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s93' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='15.75'>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='120'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='213'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Span='6'/>");
		out.println ("   <Column ss:Index='11' ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='76.5'/>");
		out.println ("   <Row>");
		out.println ("    <Cell><Data ss:Type='String'>主聘系所</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>姓名</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<list.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("name")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			out.println ("    <Cell ss:Index='11' ss:StyleID='s94'/>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Layout x:Orientation='Landscape'/>");
		if(!year.equals("")&&!term.equals("")){
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;18 "+year+"學年第"+term+"學期遠距課程未規劃'/>");
		}else{
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;18 遠距課程未規劃'/>");
		}
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;L&amp;D &amp;T&amp;R&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
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
		out.println ("     <RangeSelection>C1:C2</RangeSelection>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println ("  <AutoFilter x:Range='R1C1:R2C2' xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		
		
		sql=new StringBuilder("SELECT c.name, e.cname, (SELECT COUNT(*)FROM Elearning_reserve WHERE techid=e.idno)as sho,"
		+ "(SELECT COUNT(*)FROM Elearning_reserve WHERE techid=e.idno AND checked IS NOT NULL)as rel "
		+ "FROM empl e, CODE_DEPT c WHERE e.category='1'AND c.id=e.unit");
		if(!dept.equals(""))sql.append(" AND e.unit='"+dept+"'");
		sql.append(" ORDER BY e.unit");
		list=df.sqlGet(sql.toString());
		
		out.println (" <Worksheet ss:Name='未審核名單'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=未審核名單!R1C1:R2C1'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='11' ss:ExpandedRowCount='"+(list.size()+1)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s93' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='15.75'>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='120'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='213'/>");
		out.println ("   <Column ss:StyleID='s92' ss:AutoFitWidth='0' ss:Span='6'/>");
		out.println ("   <Column ss:Index='11' ss:StyleID='s92' ss:AutoFitWidth='0' ss:Width='76.5'/>");
		out.println ("   <Row>");
		out.println ("    <Cell><Data ss:Type='String'>主聘系所</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>姓名</Data></Cell>");
		out.println ("    <Cell ss:Index='4'><Data ss:Type='String'>應審</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>實審</Data></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<list.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("name")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data></Cell>");
			out.println ("    <Cell ss:Index='4'><Data ss:Type='Number'>"+list.get(i).get("sho")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+list.get(i).get("rel")+"</Data></Cell>");
			out.println ("    <Cell ss:Index='11' ss:StyleID='s94'/>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Layout x:Orientation='Landscape'/>");
		if(!year.equals("")&&!term.equals("")){
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;18 "+year+"學年第"+term+"學期遠距課程規劃不足'/>");
		}else{
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;18 遠距課程規劃不足'/>");
		}
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;L&amp;D &amp;T&amp;R&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
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
		out.println ("     <ActiveRow>5</ActiveRow>");
		out.println ("     <ActiveCol>2</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println ("  <AutoFilter x:Range='R1C1:R2C1' xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		out.println ("</Workbook>");
		out.println ("");
		
		
		return null;
	}

}
