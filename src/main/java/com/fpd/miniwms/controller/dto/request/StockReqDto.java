package com.fpd.miniwms.controller.dto.request;

import com.fpd.miniwms.controller.dto.base.BaseRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockReqDto extends BaseRequestDto {
    @Schema(description = "재고 아이디", example = "1")
    private Long stockId;

    @Schema(description = "아이템 아이디", example = "1")
    private Long itemId;

    @Schema(description = "업데이트할 재고수량", example = "1")
    private Integer updateStockQty;
}
