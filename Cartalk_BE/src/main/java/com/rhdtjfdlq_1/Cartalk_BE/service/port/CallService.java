package com.rhdtjfdlq_1.Cartalk_BE.service.port;

import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseCallDto;

public interface CallService {
    ResponseCallDto useSafeCall(Long userId, Long chatId);
}