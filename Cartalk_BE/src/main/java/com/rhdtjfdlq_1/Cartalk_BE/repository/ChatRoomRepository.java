package com.rhdtjfdlq_1.Cartalk_BE.repository;

import com.rhdtjfdlq_1.Cartalk_BE.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
}