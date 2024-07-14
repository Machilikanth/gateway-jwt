package com.jwttoken.service;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

//    @Value("${jwt.secret}")
//    private String secretKeyString;
//
//    private SecretKey secretKey;
//
//    @Value("${jwt.validity}")
//    private long validityInMilliseconds;
    
    

    private static final String SECRET = "638CBE3A90E0303BF3808F40F95A7F02A24B4B5D029C954CF553F79E9EF1DC0384BE681C249F1223F6B55AA21DC070914834CA22C8DD98E14A872CA010091ACC";
    private static final long VALIDITY = TimeUnit.MINUTES.toMillis(5);

//    public JwtService(@Value("${jwt.secret}") String secretKeyString, @Value("${jwt.validity}") long validityInMilliseconds) {
//        this.secretKeyString = secretKeyString;
//        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyString));
//        this.validityInMilliseconds = validityInMilliseconds;
//    }
//    
    
    
    
    
    
    
    

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            claims.put("phone", customUserDetails.getMobileNumber());
        }
        // You can add more claims as needed
        return Jwts.builder()
        		 .claims(claims)
        		 .subject(userDetails.getUsername())
                 .issuedAt(Date.from(Instant.now()))
                 .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                 .signWith(generateKey())
                 .compact();
    }
    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractMobileNumber(String token) {
        return extractClaim(token, claims -> claims.get("phone", String.class));
    }

    public boolean isTokenValid(String token) {
        final Claims claims = getClaims(token);
        return !claims.getExpiration().before(Date.from(Instant.now()));
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                 .verifyWith(generateKey())
                 .build()
                 .parseSignedClaims(jwt)
                 .getPayload();
   }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }
}
