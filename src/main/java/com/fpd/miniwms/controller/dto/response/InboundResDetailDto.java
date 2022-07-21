package com.fpd.miniwms.controller.dto.response;

import com.fpd.miniwms.controller.dto.base.BaseResponseDto;
import com.fpd.miniwms.domain.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InboundResDetailDto extends BaseResponseDto {

    @Schema(description = "아이템", example = "아이템")
    private Item item;

    @Schema(description = "입고 수량", example = "1")
    private Integer inboundQty;
}
