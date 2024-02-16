package it.fides.project.auth;

import it.fides.project.models.entities.UserEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtService {
    
	@Value("${jwt.secretKey}")
    private String SECRET_KEY;
	private static final int EXPIRATION_MILLIS = 3_600_000; // 1 hour

	public String createToken(UserEntity user) {
		return Jwts.builder().setSubject(String.valueOf(user.getIdUser()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).compact();
	}

	public void verifyToken(String token, HttpServletResponse response) throws IOException {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parse(token);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unverified Token: Unhauthorized");
		}
	}

	public String extractIdFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
