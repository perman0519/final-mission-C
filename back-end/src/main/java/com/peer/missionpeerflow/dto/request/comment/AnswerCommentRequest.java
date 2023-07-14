package com.peer.missionpeerflow.dto.request.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerCommentRequest {

    @NotNull(message = "AnswerId를 입력해주세요.")
    private Long answerId;
    @NotBlank(message = "타입을 입력해주세요.")
    private String type;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
