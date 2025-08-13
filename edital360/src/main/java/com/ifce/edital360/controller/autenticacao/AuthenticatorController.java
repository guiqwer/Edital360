package com.ifce.edital360.controller.autenticacao;

import com.ifce.edital360.dto.autenticacao.AuthenticatorDto;
import com.ifce.edital360.dto.autenticacao.LoginResponseDto;
import com.ifce.edital360.service.user.AuthenticatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {

    @Autowired
    private AuthenticatorService authenticatorService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticatorDto authenticatorDto) {
        String token = authenticatorService.login(authenticatorDto);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}

