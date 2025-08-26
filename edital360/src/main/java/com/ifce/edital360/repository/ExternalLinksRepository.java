package com.ifce.edital360.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExternalLinksRepository extends JpaRepository<ExternalLinksRepository, UUID> {
}
