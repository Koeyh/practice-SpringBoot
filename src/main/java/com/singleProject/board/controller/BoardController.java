package com.singleProject.board.controller;

import com.singleProject.board.dto.BoardDto;
import com.singleProject.board.dto.BoardFileDto;
import com.singleProject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:/list";
    }

    // DB 데이터 가져오기
    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        System.out.println("boardDtoList = " + boardDtoList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        // 조회수 처리
        boardService.updateHits(id);
        // 상세 내용 가져오기
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        if(boardDto.getFileAttached() == 1) {
            BoardFileDto boardFileDto = boardService.findFile(id);
            model.addAttribute("boardFile", boardFileDto);
        }
        return "detail";
    }
    // 기존에 작성된 데이터 불러오기
    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDto boardDto, Model model) {
        boardService.update(boardDto);
        BoardDto dto = boardService.findById(boardDto.getId());
        model.addAttribute("board", dto);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/list";
    }
    
}
