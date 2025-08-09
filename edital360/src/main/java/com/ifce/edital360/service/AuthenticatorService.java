package com.ifce.edital360.service;

import com.ifce.edital360.dto.autenticacao.AutenticacaoDto;
import com.ifce.edital360.infra.security.TokenService;
import com.ifce.edital360.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login(AutenticacaoDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((User) auth.getPrincipal());
    }


}
