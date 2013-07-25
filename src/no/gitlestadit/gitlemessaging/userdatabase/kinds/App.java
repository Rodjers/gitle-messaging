package no.gitlestadit.gitlemessaging.userdatabase.kinds;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.ArrayList;
import java.util.List;

@PersistenceCapable
public class App {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String name;
	
	@Persistent
	private String gcmSenderId;
	
	@Persistent
	private String gcmSenderSecret;
	
	@Persistent
	private String apnSenderId;
	
	@Persistent
	private String apnSenderSecret;
	
	@Persistent
	private String wnsSenderId;
	
	@Persistent
	private String wnsSenderSecret;
	
	@Persistent(mappedBy = "appId")
	private List<Target> targets;

	public App(String name){
		this.name = name;
		this.targets = new ArrayList<Target>();
	}
	
	public Key getKey(){
		return this.key;
	}
	
	public String getKeyString(){
		return KeyFactory.keyToString(this.key);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getGcmSenderId(){
		return this.gcmSenderId;
	}
	
	public String getGcmSenderSecret(){
		return this.gcmSenderSecret;
	}
	
	public String getApnSenderId(){
		return this.apnSenderId;
	}
	
	public String getApnSenderSecret(){
		return this.apnSenderSecret;
	}
	
	public String getWnsSenderId(){
		return this.wnsSenderId;
	}
	
	public String getWnsSenderSecret(){
		return this.wnsSenderSecret;
	}
	
	public List<Target> getTargets(){
		return this.targets;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setGcmSenderId(String gcmSenderId){
		this.gcmSenderId = gcmSenderId;
	}
	
	public void setGcmSenderSecret(String gcmSenderSecret){
		this.gcmSenderSecret = gcmSenderSecret;
	}
	
	public void setApnSenderId(String apnSenderId){
		this.apnSenderId = apnSenderId;
	}
	
	public void setApnSenderSecret(String apnSenderSecret){
		this.apnSenderSecret = apnSenderSecret;
	}
	
	public void setWnsSenderId(String wnsSenderId){
		this.wnsSenderId = wnsSenderId;
	}
	
	public void setWnsSenderSecret(String wnsSenderSecret){
		this.wnsSenderSecret = wnsSenderSecret;
	}
	
	public void addTarget(Target target){
		
		if (this.targets == null){
			this.targets = new ArrayList<Target>();
			this.targets.add(target);
		}
		else {
			this.targets.add(target);
		}

	}
	
public void updateTarget(Target oldTarget, Target newTarget){
		
		if (this.targets == null){
			this.targets = new ArrayList<Target>();
			this.targets.add(newTarget);
		}
		else {
			this.targets.remove(oldTarget);
			this.targets.add(newTarget);
		}

	}
	
	public void removeTarget(String username, String platform){
		
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONString(){
		
		
		
		JSONObject JSONApp = new JSONObject();
			
		JSONApp.put("Key", KeyFactory.keyToString(this.key));
		JSONApp.put("Name", this.name);
		JSONApp.put("GcmSenderId", this.gcmSenderId);
		JSONApp.put("ApnSenderId", this.apnSenderId);
		JSONApp.put("WnsSenderId", this.wnsSenderId);
		
		return JSONApp.toString();
	}
}


