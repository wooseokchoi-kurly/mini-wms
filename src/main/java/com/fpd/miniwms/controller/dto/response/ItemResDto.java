package com.fpd.miniwms.controller.dto.response;

import com.fpd.miniwms.controller.dto.base.BaseResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemResDto extends BaseResponseDto {

    @Schema(description = "아이템 아이디", example = "1")
    private Long itemId;

    @Schema(description = "아이템 코드", example = "blue")
    private String itemCode;

    @Schema(description = "아이템 이름", example = "청축키보드")
    private String itemName;

    @Schema(description = "아이템 가격", example = "150000")
    private Integer itemPrice;

    @Schema(description = "아이템 사용 여부", example = "false")
    private Boolean isItemUse;
}
