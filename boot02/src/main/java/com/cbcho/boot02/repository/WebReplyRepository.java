package com.cbcho.boot02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cbcho.boot02.model.WebBoard;
import com.cbcho.boot02.model.WebReply;

public interface WebReplyRepository extends JpaRepository<WebReply, Long> {

	@Query("SELECT r FROM WebReply r WHERE r.board = ?1 AND r.rno > 0 ORDER BY r.rno ASC")
	public List<WebReply> getRepliesOfBoard(WebBoard board);
}
