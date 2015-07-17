package action.course;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Dtime;
import model.Message;
import action.BaseAction;

public class CourseManagerAction extends CourseManagerBase{	
	
	public String execute() throws ParseException{
		
				
		
		if(request.getParameter("term")!=null){
			//課程規劃進入
			if(request.getParameter("term").equals("next")){				
				/*
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd h");
				
				Map map=df.sqlGetMap("SELECT level, begin FROM SYS_CALENDAR_ELECTIVE WHERE depart='D' ORDER BY begin");
				
				//第2階段不允許任何編輯
				if(map.get("level").equals("2")){
					getSession().setAttribute("close", true);//不允許任何編輯
					return SUCCESS;
				}
				
				//第3階段允許編輯
				if(map.get("level").equals("3")){
					getSession().setAttribute("close", false);//不允許任何編輯
					return SUCCESS;
				}				
				//若為第1階段前，系務必須在選課前7天動作
				Date b=sf.parse(map.get("begin").toString());
				Calendar c=Calendar.getInstance();
				c.setTime(b);
				c.add(Calendar.WEEK_OF_YEAR, -1);
				*/
				Date d=new Date();
				
				if(d.getTime()>((Date)getContext().getAttribute("date_cs_plan_begin")).getTime() && d.getTime()<((Date)getContext().getAttribute("date_cs_plan_end")).getTime()){
					getSession().setAttribute("close", false);
				}else{
					getSession().setAttribute("close", true);
				}				
				return SUCCESS;
			}
		}
		
				
		return SUCCESS;
	}

}
