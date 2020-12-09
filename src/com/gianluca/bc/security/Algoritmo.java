package com.gianluca.bc.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Algoritmo {

	public static String generaMD5(String password) {
		
		try {
			//MessageDigest ottiene l'algoritmo di hashing
			MessageDigest md = MessageDigest.getInstance("MD5");
			// la password va trasformata in array di bytes e data in pasto a md
			// con una specifica codifica es UTF-8
			byte[] array = md.digest(password.getBytes(Charset.forName("UTF-8")));
			StringBuffer buffer = new StringBuffer();
			//la password digerita va passata allo string buffer, formattata in esadecimale
			for(int i = 0; i < array.length; i++) {
				buffer.append(String.format("%02x", array[i]));
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
