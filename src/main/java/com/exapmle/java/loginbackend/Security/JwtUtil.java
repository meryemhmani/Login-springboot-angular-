package Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    public static final String SECRET="mySecret1234";
    public static final String AUTH_HEADER="Authorization";
    public static final String PREFIX="Bearer ";

    public static final long EXPIRE_ACCESS_TOKEN=2*60*10000000;
    public static final long EXPIRE_REFRESH_TOKEN=15*60*100000000;

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
