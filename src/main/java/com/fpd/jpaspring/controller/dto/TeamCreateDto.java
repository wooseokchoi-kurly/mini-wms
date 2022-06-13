package com.fpd.jpaspring.controller.dto;

import com.fpd.jpaspring.domain.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamCreateDto {
    @Schema(description = "팀명", example = "kurly")
    private String teamName;

    @Schema(description = "팀 등급", example = "SSS")
    private String grade;

    public Team toEntity() {
        return Team.builder()
                .teamName(this.getTeamName())
                .grade(this.getGrade())
                .build();
    }
}
