package com.fpd.miniwms.repository;

import com.fpd.miniwms.domain.InboundHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundHeaderRepository extends JpaRepository<InboundHeader, Long> {
}
