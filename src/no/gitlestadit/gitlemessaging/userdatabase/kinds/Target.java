package no.gitlestadit.gitlemessaging.userdatabase.kinds;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.json.simple.JSONObject;

@PersistenceCapable
public class Target {

		@PrimaryKey
		@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
		private Key key;
	
        @Persistent
        private String pushId;
        
        @Persistent
        private String username;

        @Persistent
        private String platform;

        @Persistent
        private App appId;

	public Target(String pushId, String username, String platform, App appId){
		
		this.pushId = pushId;
		this.username = username;
		this.platform = platform;
		this.appId = appId;
	}
	
	public App getApp(){
		return this.appId;
	}
		
}

