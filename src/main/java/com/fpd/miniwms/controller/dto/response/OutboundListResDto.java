package com.fpd.miniwms.controller.dto.response;

import com.fpd.miniwms.controller.dto.base.BaseResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OutboundListResDto extends BaseResponseDto {

    private String outboundPic;
    private String outboundStatus;
    private String representItemName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public OutboundListResDto(String outboundPic, String outboundStatus, String representItemName, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.outboundPic = outboundPic;
        this.outboundStatus = outboundStatus;
        this.representItemName = representItemName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
