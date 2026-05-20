package com.rhdtjfdlq_1.Cartalk_BE.service.port;

import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseMessageListDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.RequestSendMessageDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseMessageDto;

public interface ChatMessageService {
    ResponseMessageListDto getMessages(Long loginUserId, Long chatId, Integer limit, Long cursor);
    ResponseMessageDto sendMessage(Long chatId, RequestSendMessageDto request);
}