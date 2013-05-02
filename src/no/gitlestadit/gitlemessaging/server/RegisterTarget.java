/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import no.gitlestadit.gitlemessaging.userdatabase.PMF;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.App;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;


import java.io.IOException;
/**
 * @author Oddgeir Gitlestad
 *
 */
public class RegisterTarget extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
    		resp.getWriter().println("Method GET not supported. Use POST!");
        
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws IOException{
    	
		String userId = req.getParameter("userId");
		String pushId = req.getParameter("pushId");
   		String appName = req.getParameter("appName");
   		
   		
   		PersistenceManager pm = PMF.get().getPersistenceManager();
   		
   		App app = null;
   		
   		Long appId = Long.valueOf(appName).longValue();
   		
   		try {
   		  app = pm.getObjectById(App.class, appId);
   		  Target target = new Target(pushId, userId, app.getPlatform(), app);
   		  app.addTarget(target);
   		  pm.makePersistent(app);
   		} finally {
   		  pm.close();
   		}
   		
   		
    	}
    	
}
