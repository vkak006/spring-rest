package ls.electric.demo.component.users.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.component.users.domain.User;
import ls.electric.demo.config.security.role.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthenticationService {
    @Value("${springbootwebfluxjjwt.jjwt.secret}")
    private String secret;


    public Mono<String> logout(ServerHttpRequest request){
        return Mono.just(request.getHeaders().get("Authorization").get(0))
                .map(auth -> auth.replace("Bearer ",""))
                .map(token -> {
                    if(validationToken(token)){
                        Date expirationDate = extractToken(token);
                        //redis같은 인메모리 데이터 베이스활용시, token값을 db에 저장해두고 사용하여 관리가능.
                    }
                    return token;
                });
    }

    public Date extractToken(String token){
        try{
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claims.getBody().getExpiration();

        }catch (Exception e){
            log.info("token is not valid : " + e.toString());
            return null;
        }
    }

    public boolean validationToken(String token){
        try{
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            boolean isNotExpire =claims.getBody().getExpiration().after(new Date());

            //이미 로그아웃 된 사용자인지 체크 필요
            
            return isNotExpire;

        }catch (Exception e){
            log.info("token is not valid : " + e.toString());
            return false;
        }
    }
}
