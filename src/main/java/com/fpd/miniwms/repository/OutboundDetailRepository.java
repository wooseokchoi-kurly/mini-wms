package com.fpd.miniwms.repository;

import com.fpd.miniwms.domain.OutboundDetail;
import com.fpd.miniwms.domain.OutboundHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboundDetailRepository extends JpaRepository<OutboundDetail, Long> {
    List<OutboundDetail> findByOutboundHeader(OutboundHeader outboundHeader);
}
