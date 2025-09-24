package com.ifce.edital360.repository;

import com.ifce.edital360.model.edital.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice, UUID> {
    @Query("SELECT n FROM Notice n " +
            "WHERE n.exemption IS NOT NULL " +
            "AND n.exemption.exemptionStartDate <= :today " +
            "AND n.exemption.exemptionEndDate >= :today")
    List<Notice> findActiveExemptions(@Param("today") LocalDate today);
}
