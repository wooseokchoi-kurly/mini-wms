package com.fpd.jpaspring.repository;

import com.fpd.jpaspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * 이름으로 member 조회
     *
     * @param name: member 이름
     * @return
     */
    Optional<Member> findByName(String name);
}
