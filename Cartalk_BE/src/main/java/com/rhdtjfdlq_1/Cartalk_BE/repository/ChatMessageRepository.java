package com.rhdtjfdlq_1.Cartalk_BE.repository;

import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatMessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

    List<ChatMessageEntity> findByChatRoomIdAndCreatedAtGreaterThanEqualOrderByIdDesc(
            Long chatRoomId,
            LocalDateTime threshold,
            Pageable pageable
    );

    List<ChatMessageEntity> findByChatRoomIdAndIdLessThanAndCreatedAtGreaterThanEqualOrderByIdDesc(
            Long chatRoomId,
            Long cursor,
            LocalDateTime threshold,
            Pageable pageable
    );

    // 🔥 추가 (핵심)
    Optional<ChatMessageEntity> findTopByChatRoomIdOrderByCreatedAtDesc(Long chatRoomId);

    void deleteByChatRoomId(Long chatRoomId);
}