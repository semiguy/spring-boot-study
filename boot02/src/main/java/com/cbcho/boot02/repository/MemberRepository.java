package com.cbcho.boot02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cbcho.boot02.model.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	@Query("SELECT m.uid, count(p) FROM Member m LEFT OUTER JOIN Profile p ON m.uid = p.member WHERE m.uid = ?1 GROUP BY m")
	public List<Object[]> getMemberWithProfileCount(String uid);
	
	@Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p ON m.uid = ?1 AND p.current = true")
	public List<Object[]> getMemberWithProfile(String uid);
}
