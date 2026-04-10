package com.rhdtjfdlq_1.Cartalk_BE.repository;

import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatParticipantEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatRoomEntity;
import com.rhdtjfdlq_1.Cartalk_BE.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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

    // 🔥 내가 참여한 채팅방 목록
    @Query("""
        select cp.chatRoom
        from ChatParticipantEntity cp
        where cp.user.id = :userId
    """)
    List<ChatRoomEntity> findChatRoomsByUserId(@Param("userId") Long userId);

    // 🔥 상대방 찾기 (1:1 채팅 기준)
    @Query("""
        select cp.user
        from ChatParticipantEntity cp
        where cp.chatRoom.id = :chatRoomId
          and cp.user.id != :userId
    """)
    Optional<UserEntity> findOpponent(
            @Param("chatRoomId") Long chatRoomId,
            @Param("userId") Long userId
    );

    void deleteByChatRoomId(Long chatRoomId);
}