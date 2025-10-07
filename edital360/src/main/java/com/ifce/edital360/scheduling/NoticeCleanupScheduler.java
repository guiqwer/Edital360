package com.ifce.edital360.scheduling;

import com.ifce.edital360.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NoticeCleanupScheduler {

    private static final Logger log = LoggerFactory.getLogger(NoticeCleanupScheduler.class);
    private final NoticeRepository noticeRepository;

    public NoticeCleanupScheduler(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void deleteExpiredNotices() {
        log.info("Iniciando limpeza de editais expirados em {}", LocalDate.now());
        noticeRepository.deleteByExamDateBefore(LocalDate.now());
        log.info("Limpeza de editais expirados concluída.");
    }
}
