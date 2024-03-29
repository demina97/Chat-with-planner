package kz.aupet.vt152.diplom.Configuration;

import kz.aupet.vt152.diplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  private final JwtAuthenticationEntryPoint unauthorizedHandler;
  
  private final UserService userService;
  
  private final JwtAuthenticationFilter authenticationTokenFilter;
  
  private final PasswordEncoder passwordEncoder;
  
  @Value("${jwt.header}")
  private String tokenHeader;
  
  @Autowired
  public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler, UserService userService, JwtAuthenticationFilter authenticationTokenFilter, PasswordEncoder passwordEncoder) {
    this.unauthorizedHandler = unauthorizedHandler;
    this.userService = userService;
    this.authenticationTokenFilter = authenticationTokenFilter;
    this.passwordEncoder = passwordEncoder;
  }
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userService)
      .passwordEncoder(passwordEncoder);
  }
  
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests()
      .antMatchers("/api/login/**").permitAll()
      .antMatchers("/api/registration/**").permitAll()
      .antMatchers(HttpMethod.OPTIONS).permitAll()
      .anyRequest().authenticated();
    
    httpSecurity
      .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    
    httpSecurity
      .headers()
      .frameOptions().sameOrigin()
      .cacheControl();
    
    httpSecurity.cors();
  }
  
  @Override
  public void configure(WebSecurity web) {
    web
      .ignoring()
      .antMatchers(
        HttpMethod.OPTIONS,
        "*")
      .and()
      .ignoring()
      .antMatchers(
        HttpMethod.POST,
        "/api/login",
        "/api/registration"
      )
      .and()
      .ignoring()
      .antMatchers(
        HttpMethod.GET,
        "/",
        "/*.html",
        "/favicon.ico",
        "/**/*.html",
        "/**/*.css",
        "/**/*.js"
      );
  }
}