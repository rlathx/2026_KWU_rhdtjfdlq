package com.rhdtjfdlq_1.Cartalk_BE.controller;

import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseMessageListDto;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<ResponseMessageListDto> getMessages(
            @PathVariable Long chatId,
            @RequestParam Long userId,                 // 임시 로그인 사용자 식별
            @RequestParam(required = false, defaultValue = "30") Integer limit,
            @RequestParam(required = false) Long cursor
    ) {
        ResponseMessageListDto response =
                chatMessageService.getMessages(userId, chatId, limit, cursor);

        return ResponseEntity.ok(response);
    }
}