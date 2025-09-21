package com.ifce.edital360.dto.cadastro;

import com.ifce.edital360.dto.cadastro.validation.PasswordMatches;
import com.ifce.edital360.model.enums.RequirementType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@PasswordMatches
@Schema(description = "DTO para cadastro completo de usuário")
public record CadastroCompletoRequest(

        @Schema(example = "12345678909", description = "CPF com 11 dígitos numéricos")
        @NotBlank String cpf,

        @Schema(example = "Aderbilson Junior")
        @NotBlank String nomeCompleto,

        @Schema(example = "1990-05-20", description = "Data de nascimento no formato yyyy-MM-dd")
        @NotNull LocalDate dataNascimento,

        @Schema(example = "MASCULINO", description = "Sexo, conforme enum Sex (ex.: MASCULINO, FEMININO)")
        @NotBlank String sexo,

        @Schema(example = "José Pereira")
        @NotBlank String nomePai,

        @Schema(example = "Maria Souza")
        @NotBlank String nomeMae,

        @Schema(
                description = "Nível de escolaridade",
                example = "SUPERIOR_COMPLETO",
                allowableValues = {
                        "FUNDAMENTAL_INCOMPLETO",
                        "FUNDAMENTAL_COMPLETO",
                        "MEDIO_INCOMPLETO",
                        "MEDIO_COMPLETO",
                        "SUPERIOR_INCOMPLETO",
                        "SUPERIOR_COMPLETO",
                        "POS_GRADUACAO",
                        "MESTRADO",
                        "DOUTORADO"
                }
        )
        @NotNull RequirementType escolaridade,

        @Schema(example = "221234567")
        @NotBlank String identidade,

        @Schema(example = "MG", description = "UF do documento (2 letras)")
        @NotBlank String ufIdentidade,

        @Schema(example = "60123456", description = "CEP com 8 dígitos numéricos")
        @NotBlank String cep,

        @Schema(example = "CE", description = "UF de residência (2 letras)")
        @NotBlank String uf,

        @Schema(example = "Fortaleza")
        @NotBlank String cidade,

        @Schema(example = "Aldeota")
        @NotBlank String bairro,

        @Schema(example = "Av. Beira Mar")
        @NotBlank String logradouro,

        @Schema(example = "Ap 1001")
        String complemento,

        @Schema(example = "1001")
        @NotBlank String numeroCasa,

        @Schema(example = "85")
        String telefoneDdd,

        @Schema(example = "12345678")
        String telefoneNumero,

        @Schema(example = "85")
        @NotBlank String celularDdd,

        @Schema(example = "987654321")
        @NotBlank String celularNumero,

        @Schema(example = "teste@example.com")
        @Email String email,

        @Schema(example = "teste@example.com")
        @Email String confirmarEmail,

        @Schema(
                description = "Senha: mínimo 8 caracteres, ao menos 1 maiúscula, 1 minúscula e 1 dígito",
                example = "SenhaForte1"
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Senha deve ter 8+ caracteres, letras maiúsculas e minúsculas e números"
        )
        String senha,

        @Schema(example = "SenhaForte1", description = "Confirmação da senha")
        @NotBlank String confirmarSenha
) { }
