/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.gitlestadit.gitlemessaging.userdatabase.PMF;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.App;

/**
 * @author Oddgeir Gitlestad
 *
 */
public class RegisterApp extends HttpServlet {

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
		
		String name = req.getParameter("name");
   		String platform = req.getParameter("platform");
   		String np = name + platform;
   		
   		PersistenceManager pm = PMF.get().getPersistenceManager();
   		
   		App app = new App(name, platform);
   		
   		Query q = pm.newQuery(App.class);
   		q.setFilter("name == nameParam && platform == platformParam");
   		q.declareParameters("String namePlatform, String platformParam");
   		
   		Transaction tx = pm.currentTransaction();
   		try {
   			boolean isUnique = true;
   			tx.begin();
   			List<App> apps = (List<App>) q.execute(name,  platform);
   			
   			for (App a : apps){
   				if (np.equals(a.getName() + a.getPlatform())){
   					isUnique = false;
   				}
   			}
   			if (isUnique){
   				pm.makePersistent(app);
   			}
   			else {
   				resp.getWriter().println(name + " for " + platform + " already exists");
   			}
   			
   		}
   		finally {
   			if(tx.isActive()){
   				tx.commit();
   			}
   		}
 
		
   		resp.getWriter().println(req.getParameter(name + " registered for push messages"));
    	}

}
