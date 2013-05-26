/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import no.gitlestadit.gitlemessaging.userdatabase.PMF;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;


import java.io.IOException;
import java.util.List;
/**
 * @author Oddgeir Gitlestad
 *
 */
public class DeleteTarget extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
    		resp.getWriter().println("Method GET not supported. Use POST!");
        
	}
    @SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws IOException{
    	
		String username = req.getParameter("username");
		String platform = req.getParameter("platform");
   		
   		
   		PersistenceManager pm = PMF.get().getPersistenceManager();
   		
   		List<Target> targets = null;
   		
   		Query q = pm.newQuery(Target.class);
   		q.setFilter("username == usernameParam && platform == platformParam");
   		q.declareParameters("String usernameParam, String platformParam");
   		//q.setResult("appId");
   		
   		try {
   		  targets = (List<Target>) q.execute(username, platform);
   		  for(Target t : targets){
   			pm.deletePersistent(t);
   		  }
   		  
   		  } 
   		finally {
   		  pm.close();
   		}
   		
   		
    	}
    	
}
