package com.ifce.edital360.service.notification;

import com.ifce.edital360.model.enums.RecoveryChannel;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    @Autowired
    private EmailService emailService;

    @Override
    public void send(User user, String code) {
        emailService.send(user.getEmail(), "Recuperação de senha",
                "Seu código de recuperação é: " + code);
    }

    @Override
    public RecoveryChannel getChannel() {
        return RecoveryChannel.EMAIL;
    }
}
