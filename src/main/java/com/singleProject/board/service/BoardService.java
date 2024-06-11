package com.singleProject.board.service;

import com.singleProject.board.dto.BoardDto;
import com.singleProject.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(BoardDto boardDto) {
        boardRepository.save(boardDto);
    }

    public List<BoardDto> findAll() {
        return boardRepository.findAll();
    }
}
