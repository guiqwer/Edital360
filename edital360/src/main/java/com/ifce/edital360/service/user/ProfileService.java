package com.ifce.edital360.service.user;

import com.ifce.edital360.dto.deletarconta.AccountDeleteDTO;
import com.ifce.edital360.dto.perfil.PasswordChangeDTO;
import com.ifce.edital360.dto.perfil.ProfileUpdateDTO;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProfileService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User getProfileById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
    }

    @Transactional
    public User partiallyUpdateProfile(UUID userId, ProfileUpdateDTO updateDTO) {
        User user = getProfileById(userId);


        if (updateDTO.escolaridade() != null) {
            user.setEscolaridade(updateDTO.escolaridade());
        }
        if (updateDTO.cep() != null) {
            user.setCep(updateDTO.cep());
        }
        if (updateDTO.uf() != null) {
            user.setUf(updateDTO.uf());
        }
        if (updateDTO.cidade() != null) {
            user.setCidade(updateDTO.cidade());
        }
        if (updateDTO.bairro() != null) {
            user.setBairro(updateDTO.bairro());
        }
        if (updateDTO.logradouro() != null) {
            user.setLogradouro(updateDTO.logradouro());
        }
        if (updateDTO.complemento() != null) {
            user.setComplemento(updateDTO.complemento());
        }
        if (updateDTO.numeroCasa() != null) {
            user.setNumeroCasa(updateDTO.numeroCasa());
        }
        if (updateDTO.telefoneDdd() != null) {
            user.setTelefoneDdd(updateDTO.telefoneDdd());
        }
        if (updateDTO.telefoneNumero() != null) {
            user.setTelefoneNumero(updateDTO.telefoneNumero());
        }
        if (updateDTO.celularDdd() != null) {
            user.setCelularDdd(updateDTO.celularDdd());
        }
        if (updateDTO.celularNumero() != null) {
            user.setCelularNumero(updateDTO.celularNumero());
        }
        if (updateDTO.email() != null) {
            user.setEmail(updateDTO.email());
        }

        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(UUID userId, PasswordChangeDTO passwordChangeDTO) {
        if (!passwordChangeDTO.newPassword().equals(passwordChangeDTO.confirmPassword())) {
            throw new IllegalArgumentException("A nova senha e a confirmação não correspondem.");
        }

        User user = getProfileById(userId);

        if (!passwordEncoder.matches(passwordChangeDTO.currentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("A senha atual está incorreta.");
        }

        user.setSenha(passwordEncoder.encode(passwordChangeDTO.newPassword()));

        userRepository.save(user);
    }

    @Transactional
    public void deleteAccount(UUID userId, AccountDeleteDTO deleteDTO) {
        User user = getProfileById(userId);

        if (!passwordEncoder.matches(deleteDTO.password(), user.getPassword())) {
            throw new IllegalArgumentException("A senha está incorreta. A exclusão da conta foi cancelada.");
        }

        user.setDeletedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}
