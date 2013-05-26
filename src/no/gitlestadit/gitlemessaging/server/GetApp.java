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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import no.gitlestadit.gitlemessaging.userdatabase.PMF;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.App;

/**
 * @author Oddgeir Gitlestad
 *
 */
public class GetApp extends HttpServlet {

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
			
			Key k = KeyFactory.stringToKey(appKey);
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			App app = pm.getObjectById(App.class, k);
		
    		resp.getWriter().println(app.getJSONString());
        
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		String name = req.getParameter("name");
   		String platform = req.getParameter("platform");
   		
   		PersistenceManager pm = PMF.get().getPersistenceManager();
   		
   		Query q = pm.newQuery(App.class);
   		q.setFilter("name == nameParam && platform == platformParam");
   		q.declareParameters("String namePlatform, String platformParam");
   		
   		List<App> apps = (List<App>) q.execute(name,  platform);
   		
   		String JSONApps = null;
   		
   		if (apps.isEmpty()){
   			JSONApps = "App not found";
   		}
   		else if(apps.size() == 1){
   			JSONApps = apps.get(0).getJSONString();
   		}
   		else if(apps.size() == 0){
   			//TODO Handle more than one app returned
   		}
 
		
   		resp.getWriter().println(JSONApps);
    	}

}
