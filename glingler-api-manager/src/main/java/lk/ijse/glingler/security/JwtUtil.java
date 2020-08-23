package lk.ijse.glingler.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lk.ijse.glingler.util.SysConfig;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtil {

    public String createToken(String userName,String appType, HttpServletResponse response) {
        //get the current time
        Date today = new Date();
        // add token expiration time /days- hours- minits or seconds
        Calendar instance = Calendar.getInstance();
        //set current date
        instance.setTime(today);
        //add 10 minits to the current time
        instance.add(Calendar.HOUR, 12);
        //token expire time
        Date exTime = instance.getTime();

        //generate secret key for token
        SecretKey secretKey = Keys.hmacShaKeyFor(SysConfig.JWT_SECRET.getBytes());

        //create a token
        String compact = Jwts.builder()
                .setIssuer(SysConfig.GLINGLER_JWT_TOKEN_ISSUER)
                .setSubject(userName)
                .setIssuedAt(today)
                .setExpiration(exTime)
                .claim("appType", appType).
                        signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        if(response!=null){
            response.setHeader("Token", compact);
        }

        return compact;
    }

    public boolean checkValidity(String jws) {
        Jws<Claims> claims = getClaims(jws);
        if (claims != null) {
            Date expiration = claims.getBody().getExpiration();
            Date today = new Date();
            return today.before(expiration);
        } else {
            return false;
        }
    }

    public Jws<Claims> getClaims(String jws) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SysConfig.JWT_SECRET.getBytes());
        try {
            String token = jws.split(" ")[1];
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (Exception ex) {
            return null;
        }

    }

    public String getUserName(String jwt) {
        jwt=jwt.split(" ")[1];
        SecretKey secretKey = Keys.hmacShaKeyFor(SysConfig.JWT_SECRET.getBytes());
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody().getSubject();
    }
}
