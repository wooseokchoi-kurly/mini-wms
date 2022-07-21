package com.fpd.miniwms.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class InboundListResDto {

    private String inboundPic;
    private String inboundStatus;
    private String representItemName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public InboundListResDto(String inboundPic, String inboundStatus, String representItemName, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.inboundPic = inboundPic;
        this.inboundStatus = inboundStatus;
        this.representItemName = representItemName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
