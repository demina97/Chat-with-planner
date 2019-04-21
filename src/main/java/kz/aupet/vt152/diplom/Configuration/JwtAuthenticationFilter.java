package kz.aupet.vt152.diplom.Configuration;

import io.jsonwebtoken.ExpiredJwtException;
import kz.aupet.vt152.diplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtTokenUtil jwtTokenUtil;
  private final String tokenHeader;
  
  private final UserService userService;
  
  @Autowired
  public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,
                                 @Value("${jwt.header}") String tokenHeader, UserService userService) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.tokenHeader = tokenHeader;
    this.userService = userService;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    
    String username = null;
    String authToken = request.getHeader(this.tokenHeader);
    
    if (authToken == null) {
      authToken = request.getParameter("token");
    }
    
    if (authToken != null && !"null".equals(authToken) && authToken.length() > 0) {
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
        userDetails = userService.loadUserByUsername(username);
      } catch (UsernameNotFoundException e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        return;
      }
      
      if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    
    chain.doFilter(request, response);
  }
}
