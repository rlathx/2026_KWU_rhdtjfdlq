package com.rhdtjfdlq_1.Cartalk_BE.service.impl;

import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseMessageDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseMessageListDto;
import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatMessageEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatRoomEntity;
import com.rhdtjfdlq_1.Cartalk_BE.repository.ChatMessageRepository;
import com.rhdtjfdlq_1.Cartalk_BE.repository.ChatRoomRepository;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseMessageListDto getMessages(Long loginUserId, Long chatId, Integer limit, Long cursor) {

        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("CHAT_ROOM_NOT_FOUND"));

        int pageSize = (limit == null || limit <= 0) ? 30 : limit;

        LocalDateTime threshold = LocalDateTime.now().minusWeeks(2);

        List<ChatMessageEntity> messageEntities;

        if (cursor == null) {
            messageEntities = chatMessageRepository
                    .findByChatRoomIdAndCreatedAtGreaterThanEqualOrderByIdDesc(
                            chatRoom.getId(),
                            threshold,
                            PageRequest.of(0, pageSize + 1)
                    );
        } else {
            messageEntities = chatMessageRepository
                    .findByChatRoomIdAndIdLessThanAndCreatedAtGreaterThanEqualOrderByIdDesc(
                            chatRoom.getId(),
                            cursor,
                            threshold,
                            PageRequest.of(0, pageSize + 1)
                    );
        }

        boolean hasNext = messageEntities.size() > pageSize;

        if (hasNext) {
            messageEntities = messageEntities.subList(0, pageSize);
        }

        List<ResponseMessageDto> messages = messageEntities.stream()
                .map(message -> ResponseMessageDto.builder()
                        .messageId(message.getId())
                        .senderId(message.getSender().getId())
                        .nickname(message.getSender().getNickName())
                        .content(message.getContent())
                        .messageType(message.getMessageType())
                        .createdAt(message.getCreatedAt())
                        .isMine(message.getSender().getId().equals(loginUserId))
                        .build())
                .toList();

        Long nextCursor = null;
        if (hasNext && !messages.isEmpty()) {
            nextCursor = messages.get(messages.size() - 1).getMessageId();
        }

        return ResponseMessageListDto.builder()
                .messages(messages)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}