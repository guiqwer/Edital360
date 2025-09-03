package com.ifce.edital360.repository;

import com.ifce.edital360.model.notices.ExternalLinks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExternalLinksRepository extends JpaRepository<ExternalLinks, UUID> {
}
