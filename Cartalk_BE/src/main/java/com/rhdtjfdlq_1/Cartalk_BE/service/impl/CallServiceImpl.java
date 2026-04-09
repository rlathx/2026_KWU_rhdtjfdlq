package com.rhdtjfdlq_1.Cartalk_BE.service.impl;

import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseCallDto;
import com.rhdtjfdlq_1.Cartalk_BE.entity.CallEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatRoomEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.UserEntity;
import com.rhdtjfdlq_1.Cartalk_BE.repository.CallRepository;
import com.rhdtjfdlq_1.Cartalk_BE.repository.ChatRoomRepository;
import com.rhdtjfdlq_1.Cartalk_BE.repository.UserRepository;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final CallRepository callRepository;

    private static final int MAX_CALL_COUNT = 3;

    @Override
    @Transactional
    public ResponseCallDto useSafeCall(Long userId, Long chatId) {

        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("CHAT_ROOM_NOT_FOUND"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND"));

        int usedCount = callRepository.countByChatRoomIdAndCallerId(chatId, userId);

        if (usedCount >= MAX_CALL_COUNT) {
            throw new IllegalArgumentException("CALL_LIMIT_EXCEEDED");
        }

        callRepository.save(
                CallEntity.builder()
                        .chatRoom(chatRoom)
                        .caller(user)
                        .build()
        );

        usedCount++;

        int remainingCount = MAX_CALL_COUNT - usedCount;

        return ResponseCallDto.builder()
                .call(
                        ResponseCallDto.CallInfo.builder()
                                .maxCount(MAX_CALL_COUNT)
                                .usedCount(usedCount)
                                .remainingCount(remainingCount)
                                .build()
                )
                .callAvailable(remainingCount > 0)
                .build();
    }
}