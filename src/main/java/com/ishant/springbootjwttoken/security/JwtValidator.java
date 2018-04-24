package com.ishant.springbootjwttoken.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ishant.springbootjwttoken.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	//you can give any secret key here , but client should also provide the same during request 
	@Value("${jwt.secret}")
	private String secret;

	public JwtUser validate(String token) {

		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			jwtUser = new JwtUser();
			jwtUser.setUserName(body.getSubject());
			jwtUser.setPassword((String) body.get("password"));
			} catch (Exception e) {
			System.out.println(e);
		}

		return jwtUser;
	}
}
