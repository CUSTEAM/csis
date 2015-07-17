package action;

import java.io.IOException;

import javax.servlet.http.Cookie;

import org.apache.struts2.interceptor.ServletRequestAware;

public class Logout extends BaseAction implements ServletRequestAware{	
	//sso logout
	public String execute() throws IOException {		
		getSession().invalidate();
		response.sendRedirect("/ssos/Logout");//轉送至eis
		return null;
	}
}
