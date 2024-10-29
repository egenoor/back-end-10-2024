package ee.ege.veebipood.security;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

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

        if (token.equals("Bearer 123")) {
            List<GrantedAuthority> authorities = new ArrayList<>();
                                                                            // tokeni seest email tokeni seest credentials
            Authentication auth = new UsernamePasswordAuthenticationToken("e@e.com", "First Last", authorities);
            SecurityContextHolder.getContext().setAuthentication(auth); // see rida teeb autendituks
        }
        super.doFilterInternal(request, response, chain);
    }
}
