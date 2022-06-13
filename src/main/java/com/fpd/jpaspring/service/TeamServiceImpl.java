package com.fpd.jpaspring.service;

import com.fpd.jpaspring.controller.dto.TeamCreateDto;
import com.fpd.jpaspring.domain.Team;
import com.fpd.jpaspring.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public Long createTeam(TeamCreateDto teamCreateDto) {
        Team team = teamCreateDto.toEntity();
        Team newTeam = teamRepository.save(team);

        return newTeam.getId();
    }
}
