package com.rhdtjfdlq_1.Cartalk_BE.controller;

import com.rhdtjfdlq_1.Cartalk_BE.dto.RequestCreateChatDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseCreateChatDto;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/{userId}")
    public ResponseEntity<ResponseCreateChatDto> createChatRoom(
            @PathVariable Long userId,
            @RequestBody RequestCreateChatDto request
    ) {
        ResponseCreateChatDto response = chatService.getOrCreateChatRoom(userId, request);
        return ResponseEntity.ok(response);
    }
}