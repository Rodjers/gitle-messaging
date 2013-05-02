/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;

import javax.servlet.http.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import no.gitlestadit.gitlemessaging.gcm.GCMInterface;
import no.gitlestadit.gitlemessaging.userdatabase.PMF;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;

import java.io.IOException;
import java.util.List;
/**
 * @author Oddgeir Gitlestad
 *
 */
public class SendMessage extends HttpServlet {
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
    	
    		String username = req.getParameter("username");
    		String message = req.getParameter("message");
    		
    		PersistenceManager pm = PMF.get().getPersistenceManager();
       		
       		Query q = pm.newQuery(Target.class);
       		q.setFilter("username == usernameParam && platform == platformParam");
       		q.declareParameters("String namePlatform, String platformParam");
       		q.setResult("pushId");
       		
       		List<String> pushId = (List<String>) q.execute(username, "gcm");
    	
    		GCMInterface gcm = new GCMInterface();
    		
    		String result = gcm.sendMessage(pushId.get(0));
    		
    		resp.getWriter().println(result);
    	}
}
