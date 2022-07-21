package main.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import main.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey jwtSecret;

    public JwtProvider(
            @Value("${jwt.secret.access}")
            String jwtAccessSecret
    ) {
        this.jwtSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
    }

    public String generateToken(User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant expirationInstant = now.plusHours(1).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(expirationInstant);
        return Jwts.builder()
                .setSubject(user.getLogin()) //указывает логин пользователя
                .setExpiration(accessExpiration) //дату до которой токен валиден
                .signWith(jwtSecret) //алгоритм шифрования
                .claim("firstName", user.getFirstName()) //имя пользователя
                .compact();
    }

    public boolean validateAccessToken(String accessToken) { //отвечают за проверку валидности токена
        return validateToken(accessToken, jwtSecret);
    }

    private boolean validateToken(String token, Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret) //устанавливает шифрование
                    .build()
                    .parseClaimsJws(token); //парсит токен согласно требованиям (секретного ключа, даты и имени)
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Claims getAccessClaims(String token) {
        return getClaims(token, jwtSecret);
    }

    private Claims getClaims(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
