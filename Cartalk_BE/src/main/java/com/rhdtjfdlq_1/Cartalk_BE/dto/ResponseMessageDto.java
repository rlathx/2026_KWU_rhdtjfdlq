package com.rhdtjfdlq_1.Cartalk_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMessageDto {

    private Long messageId;
    private Long senderId;
    private String nickname;
    private String content;
    private String messageType;
    private LocalDateTime createdAt;
    private boolean isMine;
}