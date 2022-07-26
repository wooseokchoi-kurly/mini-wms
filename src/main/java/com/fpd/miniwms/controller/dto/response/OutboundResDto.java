package com.fpd.miniwms.controller.dto.response;

import com.fpd.miniwms.controller.dto.base.BaseResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OutboundResDto extends BaseResponseDto {
    @Schema(description = "출고 담당자", example = "김컬리")
    private String outboundPic;

    @Schema(description = "출고 상태", example = "출고대기")
    private String outboundStatus;

    @Schema(description = "출고 상세 목록", example = "출고 상세 목록")
    private List<OutboundResDetailDto> outboundResDetailDtoList;
}
