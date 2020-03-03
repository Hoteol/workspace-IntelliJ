package com.example.demo.board.infra;

import com.example.demo.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    // 카멜표기법 where절의 변수에 따라 And, Or해서 몇개 가능
    Optional<Board> findByDeletedAndId(boolean deleted, int id);
}
