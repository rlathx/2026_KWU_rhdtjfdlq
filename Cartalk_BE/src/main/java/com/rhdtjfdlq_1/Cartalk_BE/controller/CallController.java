package com.rhdtjfdlq_1.Cartalk_BE.controller;

import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseCallDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.RequestCallDto;
import com.rhdtjfdlq_1.Cartalk_BE.service.port.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class CallController {

    private final CallService callService;

    @PostMapping("/{chatId}/calls")
    public ResponseEntity<ResponseCallDto> useSafeCall(
            @PathVariable Long chatId,
            @RequestBody RequestCallDto request
    ) {
        ResponseCallDto response = callService.useSafeCall(request.getUserId(), chatId);
        return ResponseEntity.ok(response);
    }
}