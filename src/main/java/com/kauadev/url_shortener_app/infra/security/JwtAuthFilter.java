package com.kauadev.url_shortener_app.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.kauadev.url_shortener_app.domain.user.exceptions.MissingAuthentication;
import com.kauadev.url_shortener_app.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = recoverToken(request);

            if (token != null) {
                String usernameSubject = tokenService.validateTokenAndReturnSubject(token);
                UserDetails user = userRepository.findByUsername(usernameSubject);

                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new MissingAuthentication();
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }

    }

    private String recoverToken(HttpServletRequest request) {
        String authorization = request.getHeader("AUTHORIZATION");

        if (authorization == null)
            return null;

        String token = authorization.replace("Bearer ", "");

        return token;
    }

}
