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
	
	public Key getKey(){
		return this.key;
	}
	
	public String getKeyString(){
		return KeyFactory.keyToString(this.key);
	}
	
	public String getpushId(){
		return this.pushId;
	}
	
	public App getApp(){
		return this.appId;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONString(){
		
		JSONObject JSONTarget = new JSONObject();
			
		JSONTarget.put("Key", KeyFactory.keyToString(this.key));
		JSONTarget.put("PushId", this.pushId);
		JSONTarget.put("Username", this.username);
		JSONTarget.put("Platform", this.platform);
		JSONTarget.put("App", this.appId.getKeyString());
		
		return JSONTarget.toString();
	}
	
	public void setpushId(String pushId){
		
		this.pushId = pushId;
	}
	
	public void setUsername(String username){
		
		this.username = username;
	}

	public void setPlatform(String platform){
	
	this.platform = platform;
}
		
}

