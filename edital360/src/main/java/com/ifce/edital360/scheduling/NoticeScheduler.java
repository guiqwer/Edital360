package com.ifce.edital360.scheduling;

import com.ifce.edital360.service.notice.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NoticeScheduler {

    private static final Logger log = LoggerFactory.getLogger(NoticeScheduler.class);
    private final NoticeService noticeService;

    public NoticeScheduler(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateStatusNotices() {
        log.info("Iniciando a atualização de status dos editais em {}", LocalDate.now());
        noticeService.updateStatusNotice();
        log.info("Atualização de status concluída em {}", LocalDate.now());
    }
}
