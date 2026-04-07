package com.rhdtjfdlq_1.Cartalk_BE.service.impl;

import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatRoomEntity;
import com.rhdtjfdlq_1.Cartalk_BE.repository.CallRepository;
import com.rhdtjfdlq_1.Cartalk_BE.repository.ChatMessageRepository;
import com.rhdtjfdlq_1.Cartalk_BE.repository.ChatParticipantRepository;
import com.rhdtjfdlq_1.Cartalk_BE.repository.ChatRoomRepository;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.ChatDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatDeleteServiceImpl implements ChatDeleteService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final CallRepository callRepository;

    @Override
    @Transactional
    public void deleteChatRoom(Long chatId) {

        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("CHAT_ROOM_NOT_FOUND"));

        // 1. 안심전화 기록 삭제
        callRepository.deleteByChatRoomId(chatId);

        // 2. 메시지 삭제
        chatMessageRepository.deleteByChatRoomId(chatId);

        // 3. 참여자 삭제
        chatParticipantRepository.deleteByChatRoomId(chatId);

        // 4. 채팅방 삭제
        chatRoomRepository.delete(chatRoom);
    }
}