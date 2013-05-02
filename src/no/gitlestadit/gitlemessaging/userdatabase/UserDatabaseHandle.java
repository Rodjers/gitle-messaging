/**
 * 
 */
package no.gitlestadit.gitlemessaging.userdatabase;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * @author Oddgeir Gitlestad
 *
 */
public class UserDatabaseHandle {
	
	private DatastoreService datastore;
	
	public UserDatabaseHandle(){
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
	}
	
	public void registerTarget(String pushId, String userId, String appId){
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity target = new Entity("Target");
		
		target.setProperty("appId", appId);
		target.setProperty("pushId", pushId);
		target.setProperty("userId", userId);

		datastore.put(target);
		
	}
	
	public Entity getTarget(String userId){
		
		Query query = new Query("Target").setFilter(new FilterPredicate("userId", FilterOperator.EQUAL, userId));
		PreparedQuery pq = datastore.prepare(query);
		Entity target = pq.asSingleEntity();
		
		return target;
		
		
	}
	
	public ArrayList<String> getUserIds(String appId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		ArrayList<String> userIds = new ArrayList<String>();
		
		Query query = new Query("Target").setFilter(new FilterPredicate("appId", FilterOperator.EQUAL, appId));
		PreparedQuery pq = datastore.prepare(query);
		Iterator<Entity> target = pq.asIterator();
		
		while(target.hasNext()){
			userIds.add((String)target.next().getProperty("userId"));
		}
		
		return userIds;
	}
	
	public String getPushId(String userId){
		
		Query query = new Query("Target").setFilter(new FilterPredicate("userId", FilterOperator.EQUAL, userId));
		PreparedQuery pq = datastore.prepare(query);
		Entity target = pq.asSingleEntity();
		
		return (String) target.getProperty("pushId");
	}

}