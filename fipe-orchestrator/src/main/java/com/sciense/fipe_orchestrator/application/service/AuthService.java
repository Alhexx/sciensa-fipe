package com.sciense.fipe_orchestrator.application.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sciense.fipe_orchestrator.application.dto.LoginRequestDTO;
import com.sciense.fipe_orchestrator.application.dto.LoginResponseDTO;
import com.sciense.fipe_orchestrator.config.security.JwtService;
import com.sciense.fipe_orchestrator.config.security.UserDetailsImpl;
import com.sciense.fipe_orchestrator.config.security.UserDetailsServiceImpl;

@Service
public class AuthService { 
  private final UserDetailsServiceImpl userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder, JwtService jwtService) {
      this.userDetailsService = userDetailsService;
      this.passwordEncoder = passwordEncoder;
      this.jwtService = jwtService;
  }

  public LoginResponseDTO login(LoginRequestDTO dto) {
    UserDetailsImpl user = userDetailsService.loadUserByUsername(dto.getEmailOrCpf());

    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("Credenciais inválidas");
    }

    String token = jwtService.generateToken(user);

    return new LoginResponseDTO(token);
  }
  
}