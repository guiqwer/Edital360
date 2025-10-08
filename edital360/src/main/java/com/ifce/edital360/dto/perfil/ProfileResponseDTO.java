package com.ifce.edital360.dto.perfil;

import com.ifce.edital360.model.enums.RequirementType;
import com.ifce.edital360.model.enums.Sex;
import com.ifce.edital360.model.user.User;

import java.time.LocalDate;
import java.util.UUID;

// Versão com record: muito mais concisa!
public record ProfileResponseDTO(
        UUID id,
        String cpf,
        String nomeCompleto,
        String email,
        LocalDate dataNascimento,
        Sex sex,
        String nomeMae,
        String nomePai,
        RequirementType escolaridade,
        String documentoIdentidade,
        String ufIdentidade,
        String cep,
        String uf,
        String cidade,
        String bairro,
        String logradouro,
        String numeroCasa,
        String complemento,
        String telefoneDdd,
        String telefoneNumero,
        String celularDdd,
        String celularNumero
) {
    // O construtor canônico (que recebe todos os parâmetros) é gerado automaticamente.
    // Podemos criar construtores adicionais, como o que recebe a entidade User.
    public ProfileResponseDTO(User user) {
        this(
                user.getId(),
                user.getCpf(),
                user.getNomeCompleto(),
                user.getEmail(),
                user.getDataNascimento(),
                user.getSexo(),
                user.getNomeMae(),
                user.getNomePai(),
                user.getEscolaridade(),
                user.getDocumentoIdentidade(),
                user.getUfIdentidade(),
                user.getCep(),
                user.getUf(),
                user.getCidade(),
                user.getBairro(),
                user.getLogradouro(),
                user.getNumeroCasa(),
                user.getComplemento(),
                user.getTelefoneDdd(),
                user.getTelefoneNumero(),
                user.getCelularDdd(),
                user.getCelularNumero()
        );
    }
}