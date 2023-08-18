package com.isfive.usearth.domain.board.repository;

import com.isfive.usearth.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
