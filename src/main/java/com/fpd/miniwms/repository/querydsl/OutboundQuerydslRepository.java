package com.fpd.miniwms.repository.querydsl;

import com.fpd.miniwms.controller.dto.response.OutboundListResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutboundQuerydslRepository {
    /**
     * 출고 목록 조회
     *
     * @param pageable: pageable
     * @return outbound page
     */
    Page<OutboundListResDto> getOutboundList(Pageable pageable);
}
