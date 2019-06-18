package com.cbcho.boot02.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cbcho.boot02.model.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
	
	public List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);
	
	@Query("SELECT b.bno, b.title, count(r) FROM FreeBoard b LEFT OUTER JOIN b.replies r WHERE b.bno > 0 GROUP BY b")
	public List<Object[]> getPage(Pageable page);
}
