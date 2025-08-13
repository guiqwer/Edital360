package com.ifce.edital360.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    public void send(String phoneNumber, String message) {
        log.info("📱 Enviando SMS para {}: {}", phoneNumber, message);
    }
}
