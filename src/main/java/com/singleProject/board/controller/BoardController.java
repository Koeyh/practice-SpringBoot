package com.singleProject.board.controller;

import com.singleProject.board.dto.BoardDto;
import com.singleProject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDto boardDto) throws IOException {
        System.out.println("BoardDto: " + boardDto);
        boardService.save(boardDto);
        return "index";
    }

    // DB 데이터 가져오기
    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        System.out.println("boardDtoList = " + boardDtoList);
        return "list";
    }
}
