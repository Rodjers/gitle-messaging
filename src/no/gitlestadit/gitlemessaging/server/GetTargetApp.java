/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.gitlestadit.gitlemessaging.userdatabase.PMF;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.App;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;

/**
 * @author Oddgeir Gitlestad
 *
 */
public class GetTargetApp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
    		resp.getWriter().println("Method GET not supported. Use POST!");
        
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		String name = req.getParameter("username");
   		String platform = req.getParameter("platform");
   		
   		PersistenceManager pm = PMF.get().getPersistenceManager();
   		
   		Query q = pm.newQuery(Target.class);
   		q.setFilter("username == usernameParam && platform == platformParam");
   		q.declareParameters("String usernamePlatform, String platformParam");
   		
   		List<Target> targets = (List<Target>) q.execute(name,  platform);
   		
   		String JSONTargets = null;
   		
   		if (targets.isEmpty()){
   			JSONTargets = "Target not found";
   		}
   		else if(targets.size() == 1){
   			JSONTargets = targets.get(0).getApp().getJSONString();
   		}
   		else if(targets.size() == 0){
   			//TODO Handle more than one app returned
   		}
 
		
   		resp.getWriter().println(JSONTargets);
    	}

}
