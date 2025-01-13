package com.board.server.member.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {

    private Long sequence;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "아이디는 5자 이상 15자 이하의 영문 대소문자 및 숫자로만 구성되어야 합니다.")
    private String id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    private int age;

}
