package com.rhdtjfdlq_1.Cartalk_BE.service.port;

import com.rhdtjfdlq_1.Cartalk_BE.dto.RequestCreateChatDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseCreateChatDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseTopChatListDto;

public interface ChatService {
    ResponseCreateChatDto getOrCreateChatRoom(Long loginUserId, RequestCreateChatDto request);

    ResponseTopChatListDto getTopChats(Long loginUserId);
}