package com.ifce.edital360.service.user;

import com.ifce.edital360.dto.cadastro.CadastroCompletoRequest;
import com.ifce.edital360.exception.custom.cadastro.*;
import com.ifce.edital360.model.enums.Role;
import com.ifce.edital360.model.enums.Sex;
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
            throw new CepInvalidoException("CEP invalido. Deve conter 8 dígitos numéricos.");
        }
    }

    public void cadastrarUsuario(CadastroCompletoRequest dto) {

        String email = dto.email().trim().toLowerCase();
        String confirmarEmail = dto.confirmarEmail().trim().toLowerCase();
        String cpf = dto.cpf();

        if (!email.equals(confirmarEmail)) {
            throw new EmailNaoConfereException("Os e-mails não conferem.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new EmailJaCadastradoException("Email já cadastrado.");
        }

        if (userRepository.existsByCpf(cpf)){
            throw new CpfInvalidoException("Cpf Ja cadastrado.");
        }

        Sex sexo;
        try {
            sexo = Sex.valueOf(dto.sexo());
        } catch (IllegalArgumentException e) {
            throw new SexoInvalidoException("Sexo inválido.");
        }

        User user = new User();
        user.setCpf(dto.cpf().replaceAll("\\D", "").trim());
        user.setNomeCompleto(dto.nomeCompleto());
        user.setDataNascimento(dto.dataNascimento());
        user.setSexo(sexo);
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


}

