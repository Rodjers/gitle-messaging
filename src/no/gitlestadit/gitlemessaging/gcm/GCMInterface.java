package no.gitlestadit.gitlemessaging.gcm;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.google.android.gcm.server.Result;

public class GCMInterface {
	private Sender sender;
	
	public GCMInterface(){
		
		
	}
	
	public String sendMessage(String pushId, String message){
		
		sender = new Sender("AIzaSyAgjn1nSEHbtoSLqt68RBQzFHBcUZb2SVA");
		
		Result result = null;
		
		Message gcmMessage = new Message.Builder()
	    .collapseKey("key")
	    .timeToLive(3)
	    .delayWhileIdle(true)
	    .addData("message", message)
	    .build();
		
		try {
			result = sender.send(gcmMessage, pushId, 3);
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
