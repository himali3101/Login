package com.cg.realestate.utils;

// copy for authorization

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	
	@Value("{jwt.key}")
	private String SECRET_KEY;
	
	private Claims claims;

	public String generateToken(String username, String role) {

		
		return Jwts.builder()  
				.claim("usr",username)  
				.claim("rls",role) 
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)  
				.compact();   
	}
	
	
	
	private Claims extractAllClaims(String token) {
		this.claims= Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return this.claims;
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUsername(String token) {
		extractAllClaims(token);
		return this.claims.get("usr").toString();
	}
	
	public Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

}