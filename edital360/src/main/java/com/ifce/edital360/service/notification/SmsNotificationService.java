package com.ifce.edital360.service.notification;

import com.ifce.edital360.model.enums.RecoveryChannel;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.service.user.SmsService;
import org.springframework.beans.factory.annotation.Autowired;

public class SmsNotificationService implements NotificationService {

    @Autowired
    private SmsService smsService;

    @Override
    public void send(User user, String code) {
        String phone = user.getCelularDdd() + user.getCelularNumero();
        smsService.send(phone, "Código de recuperação: " + code);
    }

    @Override
    public RecoveryChannel getChannel() {
        return RecoveryChannel.SMS;
    }
}
