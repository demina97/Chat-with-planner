package kz.aupet.vt152.diplom.Controllers;

import kz.aupet.vt152.diplom.Configuration.JwtTokenUtil;
import kz.aupet.vt152.diplom.Models.Login.LoginData;
import kz.aupet.vt152.diplom.Models.RequestError;
import kz.aupet.vt152.diplom.Models.User;
import kz.aupet.vt152.diplom.service.JwtAuthenticationResponse;
import kz.aupet.vt152.diplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
  
  @Value("${jwt.header}")
  private String tokenHeader;
  
  
  private final AuthenticationManager authenticationManager;
  
  private final UserService userService;
  
  private final JwtTokenUtil jwtTokenUtil;
  
  @Autowired
  public LoginController(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtil jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginData authenticationRequest) throws AuthenticationException {
    
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    
    final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);
    
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }
  
  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public ResponseEntity<?> registration(@RequestBody User user) {
    if (!userService.checkPhoneNumber(user.getPhone())) {
      return new ResponseEntity<>(new RequestError("Пользователь с таким телефоном уже существует"), HttpStatus.BAD_REQUEST);
    }
    
    if (!userService.registry(user)) {
      throw new RuntimeException("Пользователь с таким именем уже существует");
    }
    
    return ResponseEntity.ok(true);
  }
  
  @RequestMapping(value = "/validate", method = RequestMethod.GET)
  public ResponseEntity<?> validate() {
    return ResponseEntity.ok(true);
  }
  
  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);
    
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("User is disabled!", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("Bad credentials!", e);
    }
  }
}