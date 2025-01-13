package com.board.server.board.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PatchBoardRequestDTO {
    private String title;
    private String content;
}
