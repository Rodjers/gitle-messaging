/**
 * 
 */
package no.gitlestadit.gitlemessaging.userdatabase;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
		Target target = null;
		
		try {
			  target = getTarget(pushId, appKey);
			  
			  if(target == null){
	   		  App app = pm.getObjectById(App.class, appKey);
	   		  target = new Target(pushId, username, platform, app);
	   		 // targetKey = target.getKeyString();
	   		  //TODO Get the target id after adding
	   		  app.addTarget(target);
	   		  pm.makePersistent(app);
	   		  targetKey = getTarget(pushId, appKey).getKeyString();
			  }
			  else {
				  targetKey = target.getKeyString();
			  }
			  
	   		} finally {
	   		  pm.close();
	   		}
		
		return targetKey;
	}
	
	public Target getTarget(String targetKey){
		
		Key k = null;
		
		try {
			k = KeyFactory.stringToKey(targetKey);
		}
		catch (IllegalArgumentException e){
			return null;
		}
		Target target = null;
		
		try {
			target = pm.getObjectById(Target.class, k);
		}
		catch (Exception e){
			
		}
		
		
		return target;
	}
	
public Target getTarget(String pushId, String appKey){
		
		//TODO Add throws entity not found thingy
		
		Key k = KeyFactory.stringToKey(appKey);
		
		Target target = null;
		App app = null;
		
		try {
			app = pm.getObjectById(App.class, k);
			List<Target> targets = app.getTargets();
			
			for (Target t : targets){
				if(t.getpushId().equals(pushId)){
					target = t;
				}
			}
		}
		catch (Exception e){
			
		}
		
		
		return target;
	}
	
	public void deleteTarget(String targetKey){
		
		pm.deletePersistent(this.getTarget(targetKey));
	}
	
	public App getApp(String appKey){
		
		Key k = null;
		
		try {
			k = KeyFactory.stringToKey(appKey);
		}
		catch (IllegalArgumentException e){
			return null;
		}
		
		App app = null;
		
		try{
			app = pm.getObjectById(App.class, k);
		}
		catch (Exception e){
			
		}
		
		
		return app;
	}
	
	public App getAppByName(String name){
		App app = null;
		return app;
	}
	
	@SuppressWarnings("unchecked")
	public List<App> getApps(){
		
		List<App> apps = null;
		
		Query q = pm.newQuery(App.class);
		
		apps = (List<App>) q.execute();
		
		return apps;
	}
	
	public App registerApp(String appName){
		
			
		App app = new App(appName);
		
		App retApp = pm.makePersistent(app);
		
		return retApp;
	}
	
public void updateApp(App app){
		
		pm.makePersistent(app);
	}
	
public void deleteApp(String appKey){
		
		pm.deletePersistent(this.getApp(appKey));
	}

}