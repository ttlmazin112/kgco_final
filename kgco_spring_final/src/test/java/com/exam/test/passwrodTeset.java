package com.exam.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Setter;

public class passwrodTeset {
	@Setter(onMethod_ = @Autowired)
	private static PasswordEncoder passwordEncoder;
	
	public static String test() {
		
		StringBuffer eid = new StringBuffer();
		eid.append("FA000001");
		String encodePassword = passwordEncoder.encode(eid);
		return encodePassword;
	}
	
	
	public static void main(String[] args) {
		
		String str =test();
	}
}
