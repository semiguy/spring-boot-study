package com.cbcho.boot02.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cbcho.boot02.model.WebBoard;

@Repository
public interface WebBoardRepository extends JpaRepository<WebBoard, Long> {
	
	// title LIKE % ? % AND BNO > ?
	public Page<WebBoard> findByTitleContainingAndBnoGreaterThan(String title, Long num, Pageable page);
	public Page<WebBoard> findByContentContainingAndBnoGreaterThan(String content, Long num, Pageable page);
	public Page<WebBoard> findByWriterContainingAndBnoGreaterThan(String writer, Long num, Pageable page);
	
	
	@Query("SELECT b FROM WebBoard b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<WebBoard> findByTitle(String title);
	
	@Query("SELECT b FROM WebBoard b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<WebBoard> findByContent(@Param("content") String content);
	
	@Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<WebBoard> findByWriter(String writer);
	
	@Query("SELECT b.bno, b.title, b.writer, b.regdate FROM WebBoard b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Object[]> findByTitle2(String title);
	
	@Query(value = "select bno, title, writer from tbl_boards where title like CONCAT('%', ?1, '%') and bno > 0 order by bno desc", nativeQuery = true)
	public List<Object[]> findByTitle3(String title);
	
	@Query("SELECT b FROM WebBoard b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public List<WebBoard> findByPage(Pageable pageable);
	
	// 작성자에 대한 like % 키워드 %
	public Collection<WebBoard> findByWriterContaining(String writer);
	
	// OR 조건의 처리
	public Collection<WebBoard> findByTitleContainingOrContentContaining(String title, String content);
	
	// bno > ? ORDER BY bno DESC
	public Collection<WebBoard> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	// bnd > ? ORDER BY bno DESC limit ?,?
	public List<WebBoard> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	
	// title LIKE % ? % AND BNO > ?
	//public Collection<WebBoard> findByTitleContainingAndBnoGreaterThan(String title, Long num, Pageable page);
}
