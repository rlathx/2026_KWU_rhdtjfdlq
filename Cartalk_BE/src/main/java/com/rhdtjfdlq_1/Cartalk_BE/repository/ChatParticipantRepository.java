package com.rhdtjfdlq_1.Cartalk_BE.repository;

import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatParticipantEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatParticipantRepository extends JpaRepository<ChatParticipantEntity, Long> {

    @Query("""
        select cp1.chatRoom
        from ChatParticipantEntity cp1
        join ChatParticipantEntity cp2
          on cp1.chatRoom.id = cp2.chatRoom.id
        where cp1.user.id = :userId1
          and cp2.user.id = :userId2
    """)
    Optional<ChatRoomEntity> findExistingChatRoom(
            @Param("userId1") Long userId1,
            @Param("userId2") Long userId2
    );

    void deleteByChatRoomId(Long chatRoomId);
}