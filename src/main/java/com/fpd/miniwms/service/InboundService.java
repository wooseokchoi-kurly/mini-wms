package com.fpd.miniwms.service;

import com.fpd.miniwms.controller.dto.request.InboundCreateReqDto;
import com.fpd.miniwms.controller.dto.request.InboundUpdateReqDto;
import com.fpd.miniwms.controller.dto.response.InboundListResDto;
import com.fpd.miniwms.controller.dto.response.InboundResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InboundService {

    /**
     * 입고 목록 조회
     *
     * @param pageable: pageable
     * @return inbound page
     */
    Page<InboundListResDto> getInboundList(Pageable pageable);

    /**
     * 입고 조회
     *
     * @param inboundHeaderId: 입고 헤더 아이디
     * @return inbound response dto
     */
    InboundResDto getInbound(Long inboundHeaderId);

    /**
     * 입고 생성
     *
     * @param inboundCreateReqDto: 입고 생성 dto
     * @return inbound header id
     */
    Long createInbound(InboundCreateReqDto inboundCreateReqDto);

    /**
     * 입고 수정
     * isInboundComplete = false 일 경우에만 수정 가능
     *
     * @param inboundUpdateReqDto: 입고 수정 dto
     * @return inbound header id
     */
    Long updatedInbound(InboundUpdateReqDto inboundUpdateReqDto);

    /**
     * 입고 삭제
     * isInboundComplete = false 일 경우에만 삭제 가능
     * sort delete
     *
     * @param inboundHeaderId: inbound header id
     */
    void deleteInbound(Long inboundHeaderId);

    /**
     * 입고 완료
     * isInboundComplete -> true 로 변경
     * 재고 생성
     *
     * @param inboundHeaderId: inbound header id
     * @return inbound header id
     */
    Long inboundComplete(Long inboundHeaderId);
}
