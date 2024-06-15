package com.singleProject.board.service;

import com.singleProject.board.dto.BoardDto;
import com.singleProject.board.dto.BoardFileDto;
import com.singleProject.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // @RequiredArgsConstructor 사용으로 불필요해진 코드
    // public BoardService(BoardRepository boardRepository) {
    //     this.boardRepository = boardRepository;
    // }

    public void save(BoardDto boardDto) throws IOException {
        // 첨부 파일이 없이 저장할 때
        if(boardDto.getBoardFile().get(0).isEmpty()) {
            boardDto.setFileAttached(0);
            BoardDto saveBoard = boardRepository.save(boardDto);
        } 
        // 첨부 파일이 존재하는 경우
        else {
            boardDto.setFileAttached(1);
            // boardFileDto의 setBoardId를 위해 받아옴
            BoardDto saveBoard = boardRepository.save(boardDto);

            for(MultipartFile boardFile : boardDto.getBoardFile()) {
                // 사용자가 첨부한 파일 명
                String originalFilename = boardFile.getOriginalFilename();
                System.out.println("originalFilename = " + originalFilename);
                // UTC기준 1970.1.1로부터 몇 밀리초가 지났는지와 originalFileanme을 더해 저장된 파일명 만들기
                System.out.println(System.currentTimeMillis());
                String storedFilename = System.currentTimeMillis() + "-" + originalFilename;
                System.out.println("storedFilename = " + storedFilename);
                BoardFileDto boardFileDto = new BoardFileDto();
                boardFileDto.setOriginalFileName(originalFilename);
                boardFileDto.setStoredFileName(storedFilename);
                boardFileDto.setBoardId(saveBoard.getId());
                // String savePath = "D:\\Source\\singleProject\\development\\Visual_Studio_Code\\spring_upload_files\\" + storedFilename;
                String savePath = "D:/Source/singleProject/development/Visual_Studio_Code/spring_upload_files/" + storedFilename;

                boardFile.transferTo(new File(savePath));

                boardRepository.saveFile(boardFileDto);
            }
        }
    }

    public List<BoardDto> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDto findById(Long id) {
        return boardRepository.findById(id);
    }

    public void update(BoardDto boardDto) {
        boardRepository.update(boardDto);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public BoardFileDto findFile(Long id) {
        return boardRepository.findFile(id);
    }
}
