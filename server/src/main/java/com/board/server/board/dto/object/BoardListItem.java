package com.board.server.board.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {
    private Long id;
    private String title;
    private String content;
    private String createDate;
}
