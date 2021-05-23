package io.lpamintuan.springsectuts.backend.configurations.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.lpamintuan.springsectuts.backend.globals.JwtUtils;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    
    private final String HEADER_NAME = "Authorization";
    private final String AUTH_PREFIX = "Bearer ";

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        String header = request.getHeader(HEADER_NAME);
        String token = null;
        Claims claims = null;

        if(header != null && header.startsWith(AUTH_PREFIX)) {
            token = header.substring(AUTH_PREFIX.length());
        }

        if(token != null) claims = jwtUtils.extractAllClaims(token);

        if(claims != null && claims.getSubject() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
            if(claims.getSubject().equals(userDetails.getUsername()) && !claims.getExpiration().before(new Date())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        chain.doFilter(request, response);
            
    }

    
}
