package com.ifce.edital360.repository;

import com.ifce.edital360.model.notices.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice, UUID> {
}
