package com.ifce.edital360.repository;

import com.ifce.edital360.model.edital.Notice;
import com.ifce.edital360.model.enums.StatusNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice, UUID> {

    @Query("select n from Notice n " +
            "where n.exemption is not null " +
            "and n.exemption.enabled = true " +
            "and n.exemption.exemptionStartDate <= :today " +
            "and n.exemption.exemptionEndDate >= :today")
    List<Notice> findActiveExemptions(@Param("today") LocalDate today);

    Page<Notice> findByStatusNotice(StatusNotice statusNotice, Pageable pageable);
}
