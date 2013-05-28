/**
 * 
 */
package no.gitlestadit.gitlemessaging.userdatabase;

import javax.jdo.PersistenceManager;

import no.gitlestadit.gitlemessaging.userdatabase.kinds.App;
import no.gitlestadit.gitlemessaging.userdatabase.kinds.Target;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * @author Oddgeir Gitlestad
 *
 */
public class DatabaseHandle {
	private PersistenceManager pm;
	
	public DatabaseHandle(){
		
		pm = PMF.get().getPersistenceManager();
		
	}
	
	public String registerTarget(String pushId, String appKey, String username, String platform){
		
		String targetKey = null;
		
		try {
	   		  App app = pm.getObjectById(App.class, appKey);
	   		  Target target = new Target(pushId, username, platform, app);
	   		 // targetKey = target.getKeyString();
	   		  //TODO Get the target id after adding
	   		  app.addTarget(target);
	   		  pm.makePersistent(app);
	   		} finally {
	   		  pm.close();
	   		}
		
		return targetKey;
	}
	
	public Target getTarget(String targetKey){
		
		//TODO Add throws entity not found thingy
		
		Key k = KeyFactory.stringToKey(targetKey);
		
		Target target = pm.getObjectById(Target.class, k);
		
		return target;
	}
	
	public void deleteTarget(String targetKey){
		
		pm.deletePersistent(this.getTarget(targetKey));
	}
	
	public App getApp(String appKey){
		
		Key k = KeyFactory.stringToKey(appKey);
		
		App app = pm.getObjectById(App.class, k);
		
		return app;
	}
	
	public String registerApp(String appName){
		
		String appKey = null;
		
		App app = new App(appName);
		
		pm.makePersistent(app);
		
		return appKey;
	}
	
public void deleteApp(String appKey){
		
		pm.deletePersistent(this.getApp(appKey));
	}

}