package com.cbcho.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbcho.jpa.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
