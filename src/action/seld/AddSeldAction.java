package action.seld;

import java.util.List;
import java.util.Map;

import model.Message;
import action.BaseAction;

/**
 * 行政加退選
 * @author John
 *
 */
public class AddSeldAction extends AddSeldBase{
	
	public String execute(){
		getSession().removeAttribute("css");
		return SUCCESS;
	}

}
