package com.cbcho.aop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbcho.aop.domain.Board;

@Mapper
public interface BoardMapper {
	
	void create(Board board) throws Exception;
	
	Board read(Integer boardNo) throws Exception;
	
	void update(Board board) throws Exception;
	
	void delete(Integer boardNo) throws Exception;
	
	List<Board> list() throws Exception;
	
	List<Board> search(@Param("title")String title) throws Exception;
}
