package ee.ege.veebipood.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(@Lazy AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request);
        System.out.println(request.getMethod());
        // System.out.println(request.getHeaders(HttpHeaders.AUTHORIZATION));
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(token);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
            System.out.println(token);

            String security = "9j1guhInbR6amGtnEVlaRplTTP/cQIMmF3T3+9Pim6x1ynBnwmbBq5+K0YH1mVU4F/fay74Tz1aiCXuVzXdhpw==";
            SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(security));

            Claims claims = Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey)))
//                    .build()
//                    .parseClaimsJws(requestToken)
//                    .getBody();

//            String jwtToken = Jwts.builder()
//                    .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey)), SignatureAlgorithm.HS512)
//                    .setIssuer("Mihkel's webshop")
//                    .setExpiration(expiration)
//                    .setSubject(person.getId().toString())
//                    .setAudience(String.valueOf(person.isAdmin()))
//                    .compact();

            System.out.println(claims.get("email"));
            System.out.println(claims.get("firstName"));
            System.out.println(claims.get("lastName"));

            String email = claims.get("email").toString();
            String credentials = claims.get("firstName") + " " + claims.get("lastName");
            boolean admin = claims.get("admin").equals("true");

            List<GrantedAuthority> authorities = new ArrayList<>();
            if (admin) {
                GrantedAuthority authority = new SimpleGrantedAuthority("admin");
                authorities.add(authority);
            }
                                                                            // tokeni seest email tokeni seest credentials
            Authentication auth = new UsernamePasswordAuthenticationToken(email, credentials, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth); // see rida teeb autendituks
        }
        super.doFilterInternal(request, response, chain);
    }
}
