package com.victorpalha.aspop_spring.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import com.victorpalha.aspop_spring.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTProvider jwtProvider;
    public JWTAuthenticationFilter(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");
        if(header != null) {
            try {
                DecodedJWT decodedJWT = jwtProvider.decodeTokenWithoutValidation(header);
                String jwtTypeToken = decodedJWT.getClaim("role").asString();
                MemberRole memberRole = MemberRole.valueOf(jwtTypeToken);

                DecodedJWT validatedToken = jwtProvider.verify(header);
                request.setAttribute("member_id", validatedToken.getSubject());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        validatedToken.getSubject(),
                        null,
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            catch (Exception e) {
                System.err.println("Invalid token: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        filterChain.doFilter(request, response);
    }
}
