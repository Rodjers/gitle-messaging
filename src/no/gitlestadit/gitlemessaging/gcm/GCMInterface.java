package no.gitlestadit.gitlemessaging.gcm;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

@SuppressWarnings("unused")
public class GCMInterface {
	private Sender sender;
	
	public GCMInterface(){
		
		sender = new Sender("key");
	}
	
	public void sendMessage(String pushId, String message){
		
		
		
	}

}
