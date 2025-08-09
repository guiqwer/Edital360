package com.ifce.edital360.service;

import com.ifce.edital360.dto.cadastro.CadastroCompletoRequest;
import com.ifce.edital360.exception.custom.cadastro.*;
import com.ifce.edital360.model.user.Role;
import com.ifce.edital360.model.user.Sexo;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    public void verificacaoCpfCep(String cpf, String cep){
        if(!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("Cpf invalido. Deve conter 11 dígitos numéricos.");
        }

        if(userRepository.existsByCpf(cpf)){
            throw new CpfJaCadastradoException("CPF já cadastrado.");
        }

        if(!cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP invalido. Deve conter 8 dígitos numéricos.");
        }
    }
    public void cadastrarUsuario(CadastroCompletoRequest dto) {
        String email = dto.email().trim().toLowerCase();
        String confirmarEmail = dto.confirmarEmail().trim().toLowerCase();

        if (!email.equals(confirmarEmail)) {
            throw new EmailNaoConfereException("Os e-mails não conferem.");
        }

        if (!dto.senha().equals(dto.confirmarSenha())) {
            throw new SenhaInvalidaException("As senhas não conferem.");
        }

        if (!senhaValida(dto.senha())) {
            throw new SenhaInvalidaException("Senha com no mínimo 8 caracteres contendo pelo menos: uma letra maiúscula, uma letra minúscula e um número.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new EmailJaCadastradoException("Email já cadastrado.");
        }

        User user = new User();
        user.setCpf(dto.cpf());
        user.setNomeCompleto(dto.nomeCompleto());

        try {
            user.setSexo(Sexo.valueOf(dto.sexo()));
        } catch (IllegalArgumentException e) {
            throw new SexoInvalidoException("Sexo inválido.");
        }

        user.setDataNascimento(dto.dataNascimento());
        user.setNomePai(dto.nomePai());
        user.setNomeMae(dto.nomeMae());
        user.setEscolaridade(dto.escolaridade());
        user.setDocumentoIdentidade(dto.identidade());
        user.setUfIdentidade(dto.ufIdentidade());
        user.setCep(dto.cep());
        user.setUf(dto.uf());
        user.setCidade(dto.cidade());
        user.setBairro(dto.bairro());
        user.setLogradouro(dto.logradouro());
        user.setComplemento(dto.complemento());
        user.setNumeroCasa(dto.numeroCasa());

        user.setTelefoneDdd(dto.telefoneDdd());
        user.setTelefoneNumero(dto.telefoneNumero());
        user.setCelularDdd(dto.celularDdd());
        user.setCelularNumero(dto.celularNumero());

        user.setEmail(email);
        user.setSenha(passwordEncoder.encode(dto.senha()));

        user.setRole(Role.CANDIDATO);

        userRepository.save(user);
    }

    private boolean senhaValida(String senha) {
        return senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

}

