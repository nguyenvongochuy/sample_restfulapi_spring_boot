package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.example.domain.SignInRequest;
import com.example.domain.SignInResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/signin")
public class SignInController {

	private static final String EMAIL = "test@gmail.com";
	private static final String PASSWORD = "abc123";
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public @ResponseBody SignInResponse signIn(@RequestBody SignInRequest requestData) {
		
		SignInResponse response = null;
		if (requestData.getEmail().equals(EMAIL) && requestData.getPassword().equals(PASSWORD)) {
			response = new SignInResponse();
			String token = getJWTToken(requestData.getEmail());
			
			response.setEmail(requestData.getEmail());
			response.setToken(token);
			
			
		}
		

		return response;
	}
	
	
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	
	
}
