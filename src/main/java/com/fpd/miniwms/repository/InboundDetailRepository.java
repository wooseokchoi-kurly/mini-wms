package com.fpd.miniwms.repository;

import com.fpd.miniwms.domain.InboundDetail;
import com.fpd.miniwms.domain.InboundHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundDetailRepository extends JpaRepository<InboundDetail, Long> {
    List<InboundDetail> findByInboundHeader(InboundHeader inboundHeader);
}
