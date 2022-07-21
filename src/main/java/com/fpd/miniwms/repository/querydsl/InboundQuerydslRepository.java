package com.fpd.miniwms.repository.querydsl;

import com.fpd.miniwms.controller.dto.response.InboundListResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InboundQuerydslRepository {
    /**
     * 입고 목록 조회
     *
     * @param pageable: pageable
     * @return inbound page
     */
    Page<InboundListResDto> getInboundList(Pageable pageable);
}
