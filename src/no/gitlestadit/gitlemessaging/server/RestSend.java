/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;
import javax.servlet.http.*;

import no.gitlestadit.gitlemessaging.gcm.GCMInterface;
import no.gitlestadit.gitlemessaging.userdatabase.DatabaseHandle;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.App;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;


import java.io.IOException;
import java.util.List;
/**
 * @author Oddgeir Gitlestad
 *
 */
public class RestSend extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.getWriter().println("GET not supported. Use POST!");
		
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
		
   		String appKey = req.getParameter("appKey");
   		String message = req.getParameter("message");
   		
   		GCMInterface gcm = new GCMInterface();
   		
   		DatabaseHandle db = new DatabaseHandle();
   		
   		App app = db.getApp(appKey);
   		
   		List<Target> targets = app.getTargets();
   		
   		for (Target t : targets){
   			gcm.sendMessage(t.getpushId(), message);
   		}
   		
   		resp.getWriter().println("Message sent to all users of: " + appKey);
   		
   		//TODO Add actual send mechanism
    }
    
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
    	resp.getWriter().println("DELETE not supported. Use POST!");
    	
    }
}
