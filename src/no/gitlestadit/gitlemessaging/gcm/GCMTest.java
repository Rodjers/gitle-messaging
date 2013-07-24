package no.gitlestadit.gitlemessaging.gcm;

import no.gitlestadit.gitlemessaging.gcm.GCMInterface;

public class GCMTest {

	public GCMTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GCMInterface gcm = new GCMInterface();
		String pushId = "APA91bF9pPwCe7fZJb43vycqnrmH3QuBbW4q2MceJPSOSFwBrLOwl7wsJtJwUruxIwUY9zJlI4DU3L9e0FYQiH9k0lpQ5sDOKct6c6b_o76RYH8qYrs96xG9ZelfZOEzwmc2eQT4I_fx1tSELBLU3gtT_lqJ9hpvo78xYjOI-Bx6FaQ04IfGDXA";
		gcm.sendMessage(pushId, "test");

	}

}
