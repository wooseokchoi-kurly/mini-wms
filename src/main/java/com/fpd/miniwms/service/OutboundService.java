package com.fpd.miniwms.service;

import com.fpd.miniwms.controller.dto.request.OutboundCreateReqDto;
import com.fpd.miniwms.controller.dto.request.OutboundUpdateReqDto;
import com.fpd.miniwms.controller.dto.response.OutboundListResDto;
import com.fpd.miniwms.controller.dto.response.OutboundResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutboundService {

    /**
     * 출고 목록 조회
     *
     * @param pageable: pageable
     * @return outbound page
     */
    Page<OutboundListResDto> getOutboundList(Pageable pageable);

    /**
     * 출고 조회
     *
     * @param outboundHeaderId
     * @return
     */
    OutboundResDto getOutbound(Long outboundHeaderId);

    /**
     * 출고 생성
     *
     * @param outboundCreateReqDto: 출고 생성 dto
     * @return outbound header id
     */
    Long createOutbound(OutboundCreateReqDto outboundCreateReqDto);

    /**
     * 출고 수정
     *
     * @param outboundUpdateReqDto: 출고 수정 dto
     * @return outbound header id
     */
    Long updateOutbound(OutboundUpdateReqDto outboundUpdateReqDto);

    /**
     * 출고 삭제
     * isOutboundComplete = false 일 경우에만 삭제 가능
     *
     * @param outboundHeaderId: outbound header id
     */
    void deleteOutbound(Long outboundHeaderId);

    /**
     * 출고 완료
     * isOutboundComplete -> true 로 변경
     * 재고 삭제
     *
     * @param outboundHeaderId: outbound header id
     * @return outbound header id
     */
    Long outboundComplete(Long outboundHeaderId);
}
