package com.fpd.miniwms.controller.dto.request;

import com.fpd.miniwms.controller.dto.base.BaseRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemCreateReqDto extends BaseRequestDto {

    @Schema(description = "아이템 코드", example = "blue")
    private String itemCode;

    @Schema(description = "아이템 이름", example = "청축키보드")
    private String itemName;

    @Schema(description = "아이템 가격", example = "150000")
    private Integer itemPrice;

}
