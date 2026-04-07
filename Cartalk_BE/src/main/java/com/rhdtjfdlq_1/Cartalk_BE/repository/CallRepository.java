package com.rhdtjfdlq_1.Cartalk_BE.repository;

import com.rhdtjfdlq_1.Cartalk_BE.entity.CallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<CallEntity, Long> {

    int countByChatRoomId(Long chatRoomId);

    void deleteByChatRoomId(Long chatRoomId);
}