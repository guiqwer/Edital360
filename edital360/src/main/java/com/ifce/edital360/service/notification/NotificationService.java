package com.ifce.edital360.service.notification;

import com.ifce.edital360.model.enums.RecoveryChannel;
import com.ifce.edital360.model.user.User;

public interface NotificationService {
    void send(User user, String code);
    RecoveryChannel getChannel();
}
