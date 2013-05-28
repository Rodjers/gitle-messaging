/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.gitlestadit.gitlemessaging.userdatabase.DatabaseHandle;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.App;

/**
 * @author Oddgeir Gitlestad
 *
 */
public class RestApp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		StringBuffer url = req.getRequestURL();
		
		String appKey = url.substring(url.lastIndexOf("/")+1);
		
		DatabaseHandle db = new DatabaseHandle();
		
		App app = db.getApp(appKey);
		
		resp.getWriter().println(app.getJSONString());
        
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		String name = req.getParameter("name");
   		
		DatabaseHandle db = new DatabaseHandle();
		
		db.registerApp(name);
		
   		resp.getWriter().println(req.getParameter(name + " registered for push messages"));
    	}
	
public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
		StringBuffer url = req.getRequestURL();
		
		String appKey = url.substring(url.lastIndexOf("/")+1);
		
		DatabaseHandle db = new DatabaseHandle();
		
		db.deleteApp(appKey);
   		
   		resp.getWriter().println("Deleted: " + appKey);
    }

}
