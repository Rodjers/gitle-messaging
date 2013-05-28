/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;
import javax.servlet.http.*;

import no.gitlestadit.gitlemessaging.userdatabase.DatabaseHandle;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;


import java.io.IOException;
/**
 * @author Oddgeir Gitlestad
 *
 */
public class RestTarget extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		StringBuffer url = req.getRequestURL();
		
		String targetKey = url.substring(url.lastIndexOf("/")+1);
		
		DatabaseHandle db = new DatabaseHandle();
		
		Target target = db.getTarget(targetKey);
		
		if (target != null){
			resp.getWriter().println(target.getJSONString());
		}
		else {
			resp.getWriter().println("Target not found");
		}
		
        
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
		String username = req.getParameter("username");
		String pushId = req.getParameter("pushId");
   		String appKey = req.getParameter("appKey");
   		String platform = req.getParameter("platform");
   		
   		DatabaseHandle db = new DatabaseHandle();
   		
   		String targetKey = db.registerTarget(pushId, appKey, username, platform); 
   		
   		resp.getWriter().println(targetKey);
    }
    
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
		StringBuffer url = req.getRequestURL();
		
		String targetKey = url.substring(url.lastIndexOf("/")+1);
		
		DatabaseHandle db = new DatabaseHandle();
		
		db.deleteTarget(targetKey);
   		
   		resp.getWriter().println("Deleted: " + targetKey);
    }
}
