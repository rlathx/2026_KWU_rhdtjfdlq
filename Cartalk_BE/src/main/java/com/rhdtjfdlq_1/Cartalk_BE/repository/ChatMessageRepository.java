package com.rhdtjfdlq_1.Cartalk_BE.repository;

import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatMessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    void deleteByChatRoomId(Long chatRoomId);
}