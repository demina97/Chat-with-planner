package kz.aupet.vt152.diplom.Configuration;

import io.jsonwebtoken.ExpiredJwtException;
import kz.aupet.vt152.diplom.Models.Login.JwtUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtTokenUtil jwtTokenUtil;
  private final String tokenHeader;
  
  public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,
                                 @Value("${jwt.header}") String tokenHeader) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.tokenHeader = tokenHeader;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String requestHeader = request.getHeader(this.tokenHeader);
    
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (tokenHeader.equals(cookie.getName())) {
          requestHeader = cookie.getValue();
        }
      }
    }
    
    
    String username = null;
    String authToken = null;
    
    if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
      authToken = requestHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException e) {
        logger.error("an error occurred during getting username from token", e);
      } catch (ExpiredJwtException e) {
        logger.warn("the token is expired and not valid anymore", e);
      }
    } else {
      logger.warn("couldn't find bearer string, will ignore the header");
    }
    
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      logger.debug("security context was null, so authorizing user");
      
      UserDetails userDetails;
      try {
        userDetails = new JwtUser("user", "111");
      } catch (UsernameNotFoundException e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        return;
      }
      
      
      // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
      // the database compellingly. Again it's up to you ;)
      if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    
    chain.doFilter(request, response);
  }
}
