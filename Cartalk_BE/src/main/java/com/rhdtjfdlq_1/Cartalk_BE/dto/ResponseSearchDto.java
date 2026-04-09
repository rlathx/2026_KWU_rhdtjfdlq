package com.rhdtjfdlq_1.Cartalk_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseSearchDto {

    private String carNum;
    private Owner owner;

    @Getter
    @AllArgsConstructor
    public static class Owner {
        private String nickName;
    }
}