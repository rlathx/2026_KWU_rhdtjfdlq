package com.rhdtjfdlq_1.Cartalk_BE.controller;

import com.rhdtjfdlq_1.Cartalk_BE.dto.RequestCreateChatDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseCreateChatDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseTopChatListDto;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ResponseCreateChatDto> createChatRoom(
            @RequestBody RequestCreateChatDto request
    ) {
        ResponseCreateChatDto response =
                chatService.getOrCreateChatRoom(request.getUserId(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top")
    public ResponseEntity<ResponseTopChatListDto> getTopChats(
            @RequestParam Long userId   // 🔥 여기 핵심
    ) {

        ResponseTopChatListDto response = chatService.getTopChats(userId);

        return ResponseEntity.ok(response);
    }
}