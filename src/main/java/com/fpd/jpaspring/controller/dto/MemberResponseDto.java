package com.fpd.jpaspring.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberResponseDto {
    @Schema(description = "회원아이디")
    private Long memberId;
    @Schema(description = "팀 아이디")
    private Long teamId;
    @Schema(description = "회원명")
    private String name;
    @Schema(description = "팀명")
    private String teamName;
    @Schema(description = "성별")
    private String gender;
    @Schema(description = "나이")
    private Integer age;

    @QueryProjection
    public MemberResponseDto(Long memberId, Long teamId, String name, String teamName, String gender, Integer age) {
        this.memberId = memberId;
        this.teamId = teamId;
        this.name = name;
        this.teamName = teamName;
        this.gender = gender;
        this.age = age;
    }
}
