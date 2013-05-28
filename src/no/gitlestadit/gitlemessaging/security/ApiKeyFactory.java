package no.gitlestadit.gitlemessaging.security;

import java.security.MessageDigest;
import java.util.UUID;


public class ApiKeyFactory {
	
	public static String createKey(){
		
		String UUIDString = UUID.randomUUID().toString();
		String ApiKey = null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[]hash = digest.digest(UUIDString.getBytes());
			
			StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	            
	             ApiKey = hexString.toString();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ApiKey;
	}

}
