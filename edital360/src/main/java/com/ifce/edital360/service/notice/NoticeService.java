package com.ifce.edital360.service.notice;

import com.ifce.edital360.repository.ExternalLinksRepository;
import com.ifce.edital360.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ExternalLinksRepository externalLinksRepository;


}
