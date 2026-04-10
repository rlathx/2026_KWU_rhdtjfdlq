package com.rhdtjfdlq_1.Cartalk_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor

public class ResponseTopChatListDto {
    private List<ResponseTopChatDto> chats;
}


