package no.gitlestadit.gitlemessaging.gcm;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.google.android.gcm.server.Result;

public class GCMInterface {
	private Sender sender;
	
	public GCMInterface(){
		
		
	}
	
	public String sendMessage(String pushId){
		
		sender = new Sender("AIzaSyAgjn1nSEHbtoSLqt68RBQzFHBcUZb2SVA");
		
		Result result = null;
		
		Message message = new Message.Builder()
	    .collapseKey("key")
	    .timeToLive(3)
	    .delayWhileIdle(true)
	    .build();
		
		try {
			result = sender.send(message, pushId, 3);
			if (result.getMessageId() != null){
				return "Message sent to" + result.getMessageId();
			}
			else if (result.getErrorCodeName() != null){
				return "Error: " + result.getErrorCodeName();
			}
			else {
				return "New id: " + result.getCanonicalRegistrationId();
			}
		} catch (IOException e) {
			return "IOExceprion";
		}
		
		
	}

}
