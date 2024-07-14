package com.toucan.rtp.gt.util;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	public static final String SECRET = "638CBE3A90E0303BF3808F40F95A7F02A24B4B5D029C954CF553F79E9EF1DC0384BE681C249F1223F6B55AA21DC070914834CA22C8DD98E14A872CA010091ACC";

	public boolean isTokenValid(String token) {
		final Claims claims = getClaims(token);
		return !claims.getExpiration().before(Date.from(Instant.now()));
	}

	private Claims getClaims(String jwt) {
		return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(jwt).getPayload();
	}

	private SecretKey generateKey() {
		byte[] decodedKey = Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodedKey);
	}



}