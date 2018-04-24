package com.ishant.springbootjwttoken.security;

import com.ishant.springbootjwttoken.Utility.UtilityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ishant.springbootjwttoken.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtGenerator {


    @Value("${jwt.secret}")
    private String secret;


	 public String generate(JwtUser jwtUser) {

	        Claims claims = Jwts.claims()
	                .setSubject(jwtUser.getUserName());
	        claims.put("password", jwtUser.getPassword());
	        if("Ishant".equalsIgnoreCase(jwtUser.getUserName())) {
	            //i have coded this here ,
                // in real application you will fetch this from DB
                // on the basis of user
                claims.put("role", "admin");
            }else {
                claims.put("role", "user");
            }


        return Jwts.builder()
	                .setClaims(claims)
					.setExpiration(UtilityService.getTokenExpirationTime()) // setting the token expiry for one hour
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	    }



}
