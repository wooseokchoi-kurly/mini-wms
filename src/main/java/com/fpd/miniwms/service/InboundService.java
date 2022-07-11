package com.fpd.miniwms.service;

import com.fpd.miniwms.controller.dto.request.InboundCreateReqDto;
import com.fpd.miniwms.controller.dto.request.InboundUpdateReqDto;

public interface InboundService {

    /**
     * 입고 생성
     *
     * @param inboundCreateDto: 입고 생성 dto
     * @return inbound header id
     */
    Long createInbound(InboundCreateReqDto inboundCreateDto);

    /**
     * 입고 수정
     * isInboundComplete = false 일 경우에만 수정 가능
     *
     * @param inboundUpdateDto: 입고 수정 dto
     * @return inbound header id
     */
    Long updatedInbound(InboundUpdateReqDto inboundUpdateDto);

    /**
     * 입고 삭제
     * isInboundComplete = false 일 경우에만 삭제 가능
     * sort delete
     *
     * @param inboundHeaderId: inbound header id
     */
    void deleteInbound(Long inboundHeaderId);
}
