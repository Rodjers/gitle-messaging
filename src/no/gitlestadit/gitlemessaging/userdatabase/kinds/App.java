package no.gitlestadit.gitlemessaging.userdatabase.kinds;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

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
	private String platform;
	
	@Persistent(mappedBy = "appId")
	private List<Target> targets;

	public App(String name, String platform){
		this.name = name;
		this.platform = platform;
		this.targets = new ArrayList<Target>();
	}
	
	public String getKey(){
		return String.valueOf(this.key.getId());
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPlatform(){
		return this.platform;
	}
	
	public List<Target> getTargets(){
		return this.targets;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPlatform(String platform){
		this.platform = platform;
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
}


