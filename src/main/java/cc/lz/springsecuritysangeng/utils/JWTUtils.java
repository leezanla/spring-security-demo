package cc.lz.springsecuritysangeng.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTUtils {

    // 生成签名密钥
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // JWT的有效时间（例如：1小时）
    private static final long EXPIRATION_TIME = 3600 * 1000;

    /**
     * 生成JWT
     *
     * @param subject the subject (e.g., username)
     * @return the generated JWT
     */
    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /**
     * 验证JWT
     *
     * @param token the JWT
     * @return true if the token is valid, false otherwise
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 解析JWT
     *
     * @param token the JWT
     * @return the claims
     */
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从JWT中获取subject
     *
     * @param token the JWT
     * @return the subject
     */
    public static String getSubject(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
}
