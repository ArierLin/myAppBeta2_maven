package com.jr.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {

	private final static String hashAlgoritmName = "MD5";
	
	
	/**
	 * 计算加密后的密码
	 * @author jql
	 * @param credentialsSalt	原密码
	 * @param saltSource		盐值
	 * @param hashIterations	加密次数
	 * @return
	 */
	public static Object hashedCredentials(String credentialsSalt,String saltSource,int hashIterations){
		ByteSource salt = ByteSource.Util.bytes(saltSource.getBytes());
		Object result = new SimpleHash(hashAlgoritmName, credentialsSalt, salt,hashIterations);
		return result;
	}
	
}
