package com.ddr.drugtrace.conf;

import com.ddr.drugtrace.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// AuthenticationJwtTokenFilter.java
public class AuthenticationJwtTokenFilter extends OncePerRequestFilter {
 
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
 
    @Autowired
    private UserDetailsService userDetailsService;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headerAuth = request.getHeader("Authorization");
 
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            String jwtToken = headerAuth.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
 
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
 
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        chain.doFilter(request, response);
    }
}