package com.singleProject.board.repository;

import com.singleProject.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    public BoardDto save(BoardDto boardDto) {
        sql.insert("Board.save", boardDto); // board는 mapper의 namespace를 가리킨다
        return boardDto;
    }

    public List<BoardDto> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public BoardDto findById(Long id) {
        // 조회되는 값이 하나라 selectOne 사용
        return sql.selectOne("Board.findById", id);
    }

}
