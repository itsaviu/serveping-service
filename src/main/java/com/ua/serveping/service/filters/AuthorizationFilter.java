package com.ua.serveping.service.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String SECRET;

    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("Authorization");
        boolean exclude = httpServletRequest.getRequestURI().matches("/user/register");
        if (!StringUtils.isEmpty(token) && !exclude) {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC512(this.SECRET.getBytes())).build();
                DecodedJWT jwt = verifier.verify(token.replace("Bearer ", ""));
                Claim sub = jwt.getClaim("emailId");
                Claim roles = jwt.getClaim("roles");

                List<SimpleGrantedAuthority> authorities = Stream.of(roles.asArray(String.class))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(sub.asString(), null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException ex) {
                log.error("Exception authentication", ex);
            } catch (Exception ex) {
                log.error("Exception in security check", ex);
            }
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }


}

