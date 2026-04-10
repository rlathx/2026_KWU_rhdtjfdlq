package com.rhdtjfdlq_1.Cartalk_BE.service.impl;

import com.rhdtjfdlq_1.Cartalk_BE.dto.*;
import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatParticipantEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatRoomEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.UserEntity;
import com.rhdtjfdlq_1.Cartalk_BE.repository.*;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final CarInfoRepository carRepository;

    @Override
    @Transactional
    public ResponseCreateChatDto getOrCreateChatRoom(Long loginUserId, RequestCreateChatDto request) {

        UserEntity loginUser = userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND"));

        UserEntity targetUser = userRepository.findById(request.getTargetUserId())
                .orElseThrow(() -> new IllegalArgumentException("TARGET_USER_NOT_FOUND"));

        if (loginUser.getId().equals(targetUser.getId())) {
            throw new IllegalArgumentException("SELF_CHAT_NOT_ALLOWED");
        }

        Optional<ChatRoomEntity> existingRoom =
                chatParticipantRepository.findExistingChatRoom(loginUser.getId(), targetUser.getId());

        if (existingRoom.isPresent()) {
            return ResponseCreateChatDto.builder()
                    .chatId(existingRoom.get().getId())
                    .isNew(false)
                    .build();
        }

        ChatRoomEntity newRoom = chatRoomRepository.save(
                ChatRoomEntity.builder().build()
        );

        chatParticipantRepository.save(
                ChatParticipantEntity.builder()
                        .chatRoom(newRoom)
                        .user(loginUser)
                        .build()
        );

        chatParticipantRepository.save(
                ChatParticipantEntity.builder()
                        .chatRoom(newRoom)
                        .user(targetUser)
                        .build()
        );

        return ResponseCreateChatDto.builder()
                .chatId(newRoom.getId())
                .isNew(true)
                .build();
    }

    // 🔥 여기 추가된 부분
    @Override
    @Transactional(readOnly = true)
    public ResponseTopChatListDto getTopChats(Long loginUserId) {

        // 1. 내가 참여한 채팅방 조회
        List<ChatRoomEntity> chatRooms =
                chatParticipantRepository.findChatRoomsByUserId(loginUserId);

        // 2. 각 채팅방별 DTO 생성
        List<ResponseTopChatDto> chats = chatRooms.stream()
                .map(chatRoom -> {

                    // 🔹 마지막 메시지
                    var lastMessageOpt = chatMessageRepository
                            .findTopByChatRoomIdOrderByCreatedAtDesc(chatRoom.getId());

                    String lastMessage = lastMessageOpt.map(m -> m.getContent()).orElse(null);
                    var lastMessageAt = lastMessageOpt.map(m -> m.getCreatedAt()).orElse(null);

                    // 🔹 상대방 유저
                    UserEntity opponent = chatParticipantRepository
                            .findOpponent(chatRoom.getId(), loginUserId)
                            .orElseThrow(() -> new RuntimeException("OPPONENT_NOT_FOUND"));

                    // 🔹 차량 정보
                    var carOpt = carRepository.findTopByUserId(opponent.getId());

                    String carNum = carOpt.map(c -> c.getCarNum()).orElse("알 수 없음");
                    boolean isRegisterCar = carOpt.isPresent();

                    return ResponseTopChatDto.builder()
                            .chatId(chatRoom.getId())
                            .carNum(carNum)
                            .isRegisterCar(isRegisterCar)
                            .lastMessage(lastMessage)
                            .lastMessageAt(lastMessageAt)
                            .build();
                })
                // 🔥 마지막 메시지 기준 정렬
                .sorted((a, b) -> {
                    if (a.getLastMessageAt() == null) return 1;
                    if (b.getLastMessageAt() == null) return -1;
                    return b.getLastMessageAt().compareTo(a.getLastMessageAt());
                })
                // 🔥 상위 3개
                .limit(3)
                .toList();

        return new ResponseTopChatListDto(chats);
    }
}