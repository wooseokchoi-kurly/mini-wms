package com.fpd.jpaspring.controller;

import com.fpd.jpaspring.controller.dto.TeamCreateDto;
import com.fpd.jpaspring.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "팀 API")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/v1/teams")
    @Operation(summary = "팀 등록 API", description = "팀 등록")
    public ResponseEntity<?> createTeam(@RequestBody TeamCreateDto teamCreateDto) {
        Long team = teamService.createTeam(teamCreateDto);
        return ResponseEntity.ok(team);
    }
}
