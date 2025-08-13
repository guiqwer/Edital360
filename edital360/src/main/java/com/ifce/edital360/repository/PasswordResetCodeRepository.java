package com.ifce.edital360.repository;

import com.ifce.edital360.model.enums.RecoveryChannel;
import com.ifce.edital360.model.passwordreset.PasswordResetCode;
import com.ifce.edital360.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, UUID> {
    Optional<PasswordResetCode> findByCodeAndChannelAndUsedFalse(
            String code, RecoveryChannel channel
    );
}
