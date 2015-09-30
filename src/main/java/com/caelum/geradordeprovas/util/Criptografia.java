package com.caelum.geradordeprovas.util;

import java.security.MessageDigest;

public class Criptografia {

	public String criptografaSenha(String senha) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(senha.getBytes());
			byte[] crypt = md.digest();
			return bytesToHex(crypt);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return senha;
	}
	
	public String bytesToHex(byte[] data) {  
	      if (data==null) {  
	         return null;  
	      } else {  
	         int len = data.length;  
	         String str = "";  
	         for (int i=0; i<len; i++) {  
	            if ((data[i]&0xFF)<16) str = str + "0"   
	               + java.lang.Integer.toHexString(data[i]&0xFF);  
	            else str = str  
	               + java.lang.Integer.toHexString(data[i]&0xFF);  
	         }  
	         return str.toUpperCase();  
	      }  
	   }     

	
	
	
}
