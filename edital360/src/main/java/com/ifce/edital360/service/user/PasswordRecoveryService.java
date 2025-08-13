package com.ifce.edital360.service.user;

import com.ifce.edital360.dto.recuperarsenha.PasswordRecoveryDto;
import com.ifce.edital360.dto.recuperarsenha.PasswordResetRequest;
import com.ifce.edital360.exception.custom.recuperarsenha.CanalRecuperacaoNaoSuportadoException;
import com.ifce.edital360.exception.custom.recuperarsenha.InvalidResetCodeException;
import com.ifce.edital360.exception.custom.recuperarsenha.ResetCodeExpiredException;
import com.ifce.edital360.exception.custom.usuario.UsuarioNaoEncontradoException;
import com.ifce.edital360.model.passwordreset.PasswordResetCode;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.repository.PasswordResetCodeRepository;
import com.ifce.edital360.repository.UserRepository;
import com.ifce.edital360.service.notification.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PasswordRecoveryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetCodeRepository codeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    List<NotificationService> notificationServices;


    public void sendRecoveryCode(PasswordRecoveryDto data) {

        String cpf = data.cpf().replaceAll("\\D", "").trim();

        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        String code = String.format("%06d", secureRandom.nextInt(1_000_000));

        PasswordResetCode resetCode = new PasswordResetCode();
        resetCode.setUser(user);
        resetCode.setCode(code);
        resetCode.setChannel(data.channel());
        resetCode.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        resetCode.setUsed(false);
        codeRepository.save(resetCode);

        notificationServices.stream()
                .filter(s -> s.getChannel() == data.channel())
                .findFirst()
                .orElseThrow(() -> new CanalRecuperacaoNaoSuportadoException("Canal de recuperação não suportado"))
                .send(user, code);
    }


    @Transactional
    public void resetPassword(PasswordResetRequest dto) {
        PasswordResetCode resetCode = codeRepository
                .findByCodeAndChannelAndUsedFalse(dto.code(), dto.channel())
                .orElseThrow(() -> new InvalidResetCodeException("Código de recuperação inválido"));

        if (resetCode.isExpired()) {
            throw new ResetCodeExpiredException("Código expirado");
        }

        resetCode.setUsed(true);
        codeRepository.save(resetCode);

        User user = resetCode.getUser();
        user.setSenha(passwordEncoder.encode(dto.senha()));
        userRepository.save(user);
    }

}
