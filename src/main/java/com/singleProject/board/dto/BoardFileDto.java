package com.singleProject.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BoardFileDto {
    private Long id;
    private Long boardId;
    // 사용자가 올린 파일 명
    private String originalFileName;
    // 저장 된 파일 명
    private String storedFileName;
}
