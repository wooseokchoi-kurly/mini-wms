package com.fpd.jpaspring.service;

import com.fpd.jpaspring.controller.dto.TeamCreateDto;

public interface TeamService {

    /**
     * 팀생성
     *
     * @param teamCreateDto
     * @return
     */
    Long createTeam(TeamCreateDto teamCreateDto);
}
