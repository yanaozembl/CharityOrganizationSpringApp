package com.example.CharityOrganizationSpringApp.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.CharityOrganizationSpringApp.security.JWTUtil;
import com.example.CharityOrganizationSpringApp.services.UsersDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// catches requests
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UsersDetailsService usersDetailsService;

    public JWTFilter(JWTUtil jwtUtil, UsersDetailsService usersDetailsService) {
        this.jwtUtil = jwtUtil;
        this.usersDetailsService = usersDetailsService;
    }

    // get access to http request
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            if(jwt.isBlank())
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid jwt Token Bearer Header");
            else {
                try{
                String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                UserDetails userDetails = usersDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                userDetails.getPassword(),
                                userDetails.getAuthorities());

                if (SecurityContextHolder.getContext().getAuthentication() == null)
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } catch (JWTVerificationException exc) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Invalid JWT Token");
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    // todo для того, чтобы работало, нужно добавить header (authorization - "Bearer + jwtToken")
}
