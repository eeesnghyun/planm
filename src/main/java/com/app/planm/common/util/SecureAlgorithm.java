package com.app.planm.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureAlgorithm {

	/**
	 * [SHA256]암호화 
	 * @param text : 평문
	 * @param salt : 소트 hex값
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptSha256(String text, String salt) throws NoSuchAlgorithmException {	
		byte[] saltByte = salt.getBytes();		
		byte[] textByte = text.getBytes();
		byte[] bytes = new byte[textByte.length + saltByte.length];
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(bytes);
		
		byte[] byteData = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
		}
        
		return sb.toString();	
	}
	
	/**
	 * [SHA256]소트값 가져오기
	 * @return String
	 */
	public static String getSalt() {
		SecureRandom random = new SecureRandom();
		
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < salt.length; i++) {			
			sb.append(String.format("%02x",salt[i]));	//byte 값을 Hex 값으로 바꾸기
		}
		
		return sb.toString();
	}

}
