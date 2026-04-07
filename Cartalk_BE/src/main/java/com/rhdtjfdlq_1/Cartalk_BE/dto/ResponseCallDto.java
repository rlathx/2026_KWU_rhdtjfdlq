package com.rhdtjfdlq_1.Cartalk_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCallDto {

    private CallInfo call;
    private boolean callAvailable;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CallInfo {
        private int maxCount;
        private int usedCount;
        private int remainingCount;
    }
}