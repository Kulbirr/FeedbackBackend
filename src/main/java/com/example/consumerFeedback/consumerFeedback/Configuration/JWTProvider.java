package com.example.consumerFeedback.consumerFeedback.Configuration;

import com.example.consumerFeedback.consumerFeedback.ENUM.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JWTProvider {
    static SecretKey key = Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        Role role = null;

        if (auth.getAuthorities() != null && !auth.getAuthorities().isEmpty()) {
            String roleName = auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
            role = Role.valueOf(roleName); // Converting String to Enum
//            System.out.println("Role extracted from Authentication: " + role);
        }

        if (role == null) {
            throw new IllegalStateException("Role not found in the authentication object");
        }

        String jwt = Jwts.builder()
                .setIssuer("Kulbirr")
                .claim("email", auth.getName()) // Adding user email
                .claim("role", role.name())    // Store enum as a String in JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWTConstant.EXPIRATION_TIME))
                .signWith(key)
                .compact();
        return jwt;
    }


    // Method to extract email from JWT token
    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7); // Remove "Bearer " prefix

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)  // Returns a Jws<Claims> object
                .getBody();
        return (String) claims.get("email"); // Retrieve the email from the claims
    }
}
