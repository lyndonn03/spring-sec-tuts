package io.lpamintuan.springsectuts.backend.configurations.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.lpamintuan.springsectuts.backend.globals.JwtUtils;
import io.lpamintuan.springsectuts.backend.models.AuthenticationResponse;
import io.lpamintuan.springsectuts.backend.models.CustomUserDetails;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = new JwtUtils();
        this.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
            CustomUserDetails userDetails;
            try {
                userDetails = new ObjectMapper().readValue(request.getInputStream(), CustomUserDetails.class);
                return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>())
                );
            } catch (IOException e) {
                throw new RuntimeException("No user found.");
            }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                
            CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
            Map<String, Object> mapObj = new HashMap<>();
            mapObj.put("username", userDetails.getUsername());
            String token = jwtUtils.createToken(mapObj, userDetails.getUsername());
            AuthenticationResponse authResponse = new AuthenticationResponse(token, userDetails.getUsername());
            String res = new ObjectMapper().writeValueAsString(authResponse);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(res);

    }


}
