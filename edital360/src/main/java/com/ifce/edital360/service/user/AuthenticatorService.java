package com.ifce.edital360.service.user;

import com.ifce.edital360.dto.autenticacao.AuthenticatorDto;
import com.ifce.edital360.config.security.TokenService;
import com.ifce.edital360.exception.custom.usuario.InvalidLoginException;
import com.ifce.edital360.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login(AuthenticatorDto dto) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            return tokenService.generateToken((User) auth.getPrincipal());
        } catch (BadCredentialsException e) {
            // Usuário ou senha inválidos
            throw new InvalidLoginException("CPF ou senha inválidos.");}
    }
}
