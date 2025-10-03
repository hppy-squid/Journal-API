package Journal.service;

import java.util.Date;

import javax.crypto.SecretKey;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

      private final SecretKey SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
    public String generateToken(UserDetails userDetails) {
        
        return Jwts.builder()
                .setSubject(userDetails.getUsername())          
                .setIssuedAt(new Date())                        
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10)) 
                .signWith(SECRET)        
                .compact();
    }
  

    
    public String extractUsername(String token) {
        try {
           System.out.println("Incoming JWT" + token);
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
          } catch (Exception e) {
    
    throw e;
}
    }

   
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
    
}
