package com.tutorhub.security.jwt;

import com.tutorhub.security.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import java.util.Map;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

  private final JwtService tokenService;
  private final UserDetailsService userDetailsService;

  @Override
  @SneakyThrows
  public void doFilter(final ServletRequest req,
                       final ServletResponse res,
                       final FilterChain filterChain) {
    String token = resolve((HttpServletRequest) req);
    if (tokenService.isValid(token, TokenType.ACCESS)) {
      Authentication auth = getAuthentication(token);
      if (auth != null) {
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    filterChain.doFilter(req, res);
  }

  private String resolve(final HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return "";
  }

  private Authentication getAuthentication(final String token) {
    Map<String, Object> fields = tokenService.fields(token);

    UserDetails userDetails =
        userDetailsService.loadUserByUsername((String) fields.get("subject"));

    if (userDetails != null) {
      return
          new UsernamePasswordAuthenticationToken(
              userDetails,
              "",
              userDetails.getAuthorities());
    }

    return null;
  }
}
