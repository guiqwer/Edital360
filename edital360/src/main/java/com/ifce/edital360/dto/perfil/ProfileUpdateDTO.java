package com.ifce.edital360.dto.perfil;

import com.ifce.edital360.model.enums.RequirementType; // Não esqueça de importar o enum
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProfileUpdateDTO(
        @NotNull(message = "A escolaridade não pode ser vazia.")
        RequirementType escolaridade,

        @NotBlank(message = "O CEP não pode ser vazio.")
        @Size(min = 8, max = 8, message = "O CEP deve ter 8 dígitos.")
        String cep,

        @NotBlank(message = "A UF não pode ser vazia.")
        @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres.")
        String uf,

        @NotBlank(message = "A cidade não pode ser vazia.")
        String cidade,

        @NotBlank(message = "O bairro não pode ser vazio.")
        String bairro,

        @NotBlank(message = "O logradouro não pode ser vazio.")
        String logradouro,

        String complemento,

        @NotBlank(message = "O número da casa não pode ser vazio.")
        String numeroCasa,

        @Size(min = 2, max = 3, message = "O DDD do telefone deve ter 2 ou 3 dígitos.")
        String telefoneDdd,

        @Size(min = 8, max = 9, message = "O número de telefone deve ter 8 ou 9 dígitos.")
        String telefoneNumero,

        @NotBlank(message = "O DDD do celular não pode ser vazio.")
        @Size(min = 2, max = 3, message = "O DDD do celular deve ter 2 ou 3 dígitos.")
        String celularDdd,

        @NotBlank(message = "O número do celular não pode ser vazio.")
        @Size(min = 9, max = 9, message = "O número do celular deve ter 9 dígitos.")
        String celularNumero,

        @NotBlank(message = "O e-mail não pode ser vazio.")
        @Email(message = "Formato de e-mail inválido.")
        String email
) {
}