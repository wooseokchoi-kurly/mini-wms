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
public class InboundResDto extends BaseResponseDto {
    @Schema(description = "입고 담당자", example = "김컬리")
    private String inboundPic;

    @Schema(description = "입고 상태", example = "입고대기")
    private String inboundStatus;

    @Schema(description = "입고 상세 목록", example = "입고 상세 목록")
    private List<InboundResDetailDto> inboundResDetailDtoList;

}
