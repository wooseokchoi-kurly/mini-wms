package com.fpd.miniwms.controller.dto.request;

import com.fpd.miniwms.controller.dto.base.BaseRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OutboundCreateReqDto extends BaseRequestDto {

    @Schema(description = "출고 담당자", example = "김컬리")
    private String outboundPic;

    private List<OutboundCreateDetailReqDto> outboundCreateDetailReqDtoList;
}
