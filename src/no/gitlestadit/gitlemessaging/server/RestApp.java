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
		
		
		
		if(app != null){
			resp.setContentType("application/json");
			resp.getWriter().println(app.getJSONString());
		}
		else {
			resp.setContentType("text/plain");
			resp.getWriter().println("App not found");
		}
        
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		String name = req.getParameter("name");
   		
		DatabaseHandle db = new DatabaseHandle();
		
		App app = db.registerApp(name);
		
		resp.setContentType("application/json");
   		resp.getWriter().println(app.getJSONString());
    	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		StringBuffer url = req.getRequestURL();
		
		String appKey = url.substring(url.lastIndexOf("/")+1);
		String gcmSenderId = req.getParameter("gcmSenderId");
		String gcmSenderSecret = req.getParameter("gcmSenderSecret");
		String apnSenderId = req.getParameter("apnSenderId");
		String apnSenderSecret = req.getParameter("apnSenderSecret");
		String wnsSenderId = req.getParameter("wnsSenderId");
		String wnsSenderSecret = req.getParameter("wnsSenderSecret");
   		
		DatabaseHandle db = new DatabaseHandle();
		
		App app = db.getApp(appKey);
		
		app.setApnSenderSecret(apnSenderSecret);
		app.setApnSenderId(apnSenderId);
		app.setGcmSenderId(gcmSenderId);
		app.setGcmSenderSecret(gcmSenderSecret);
		app.setWnsSenderId(wnsSenderId);
		app.setWnsSenderSecret(wnsSenderSecret);		
		
		db.updateApp(app);
		
   		resp.getWriter().println(req.getParameter(app.getJSONString()));
    	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
		StringBuffer url = req.getRequestURL();
		
		String appKey = url.substring(url.lastIndexOf("/")+1);
		
		DatabaseHandle db = new DatabaseHandle();
		
		db.deleteApp(appKey);
   		
   		resp.getWriter().println("Deleted: " + appKey);
    }

}
