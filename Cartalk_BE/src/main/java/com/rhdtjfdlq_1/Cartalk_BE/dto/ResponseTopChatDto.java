package com.rhdtjfdlq_1.Cartalk_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ResponseTopChatDto {
    @Getter
    @AllArgsConstructor
    public static class Owner {
        private String nickName;
    }
}
