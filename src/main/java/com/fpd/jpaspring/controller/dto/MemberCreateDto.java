package com.fpd.jpaspring.controller.dto;

import com.fpd.jpaspring.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCreateDto {

    @Schema(description = "회원 이름", example = "김컬리")
    private String name;

    @Schema(description = "성별(남성|여성)", example = "남성")
    private String gender;

    @Schema(description = "나이", example = "22")
    private Integer age;

    @Schema(description = "팀아이디", example = "1")
    private Long teamId;

    public Member toEntity() {
        return Member.builder()
                .name(this.getName())
                .age(this.getAge())
                .gender(this.getGender())
                .teamId(this.teamId)
                .build();
    }
}
