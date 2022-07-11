package com.fpd.miniwms.controller.dto.request;

import com.fpd.miniwms.controller.dto.base.BaseRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InboundUpdateDetailReqDto extends BaseRequestDto {

    @Schema(description = "입고 상세 아이디", example = "1")
    private Long inboundDetailId;

    @Schema(description = "아이템 아이디", example = "1")
    private Long itemId;

    @Schema(description = "입고 수량", example = "1")
    private Integer inboundQty;
}
