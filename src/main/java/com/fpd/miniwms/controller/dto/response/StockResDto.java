package com.fpd.miniwms.controller.dto.response;

import com.fpd.miniwms.controller.dto.base.BaseResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockResDto extends BaseResponseDto {

    @Schema(description = "재고 아이디", example = "1")
    private Long stockId;

    @Schema(description = "아이템 아이디", example = "1")
    private Long itemId;

    @Schema(description = "아이템 이름", example = "갈축 키보드")
    private String itemName;

    @Schema(description = "재고수량", example = "1")
    private Integer stockQty;
}
