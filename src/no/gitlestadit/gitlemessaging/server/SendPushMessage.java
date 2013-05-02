package no.gitlestadit.gitlemessaging.server;

//import javax.jdo.PersistenceManager;
//import javax.jdo.Query;
import javax.servlet.http.*;

//import no.gitlestadit.gitlemessaging.userdatabase.PMF;
//import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;


import java.io.IOException;
//import java.util.List;
/**
 * @author Oddgeir Gitlestad
 *
 */
public class SendPushMessage extends HttpServlet {
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
    	
/*		String username = req.getParameter("userId");
   		String message = req.getParameter("appName");
   		
   		
   		PersistenceManager pm = PMF.get().getPersistenceManager();
   		
   		Target target = null;
   		Query q = pm.newQuery(App.class);
   		q.setFilter("username == nameParam");
   		q.declareParameters("String nameParam");
   		
   		try {
   		  List<App> results = (List<App>) q.execute(appName);
   		  if (!results.isEmpty() && results.size() < 2) {
   		    app = results.get(0);
   		  } else {
   			resp.getWriter().println(req.getParameter("No app with name " + appName));
   		  }
   		} finally {
   		  q.closeAll();
   		}
   		
   		Target target = new Target(pushId, userId, "gcm", app);
   		
   		try {
   			pm.makePersistent(target);
   		}
   		finally {
   			pm.close();
   		}*/
    	}
    	
}
