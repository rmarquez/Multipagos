package com.metropolitana.multipagos.forms;

import java.security.MessageDigest;

public class EncriptarMd5 {

	/**
	* Encripta un String con el algoritmo MD5.
	*
	* @return El algoritmo encriptado
	* @param password
	*/
	public String encriptarMD5(final String password) {
		String md5="";
		try {
			md5 = hash(password);
		}
		catch (Exception e) {
			throw new Error("Error: Al encriptar el password");
		}
		return md5;
	}

	/**
	* Encripta un String con el algoritmo MD5.
	*
	* @return String
	* @throws Exception
	*/
	private String hash(final String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(password.getBytes());
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
	for (int i = 0; i < size; i++) {
		int u = b[i]&255;
			if (u<16){
				h.append("0"+Integer.toHexString(u));
			}else {
				h.append(Integer.toHexString(u));
			}
		}
	return h.toString();
	}


}
