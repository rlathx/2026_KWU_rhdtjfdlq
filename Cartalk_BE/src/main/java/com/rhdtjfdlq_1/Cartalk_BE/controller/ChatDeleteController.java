package com.rhdtjfdlq_1.Cartalk_BE.controller;

import com.rhdtjfdlq_1.Cartalk_BE.service.port.ChatDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatDeleteController {

    private final ChatDeleteService chatDeleteService;

    @DeleteMapping("/{chatId}")
    public ResponseEntity<?> deleteChatRoom(@PathVariable Long chatId) {
        chatDeleteService.deleteChatRoom(chatId);
        return ResponseEntity.ok(Map.of("message", "채팅방이 삭제되었습니다."));
    }
}