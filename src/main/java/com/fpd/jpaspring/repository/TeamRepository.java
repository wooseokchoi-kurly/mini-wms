package com.fpd.jpaspring.repository;

import com.fpd.jpaspring.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
