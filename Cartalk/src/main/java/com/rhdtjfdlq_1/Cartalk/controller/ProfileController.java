package com.rhdtjfdlq_1.Cartalk.controller;

import com.rhdtjfdlq_1.Cartalk.dto.RequestProfileDto;
import com.rhdtjfdlq_1.Cartalk.service.port.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class ProfileController {

    private final ProfileService profileService;

    @PatchMapping("/profile/{email}")
    public ResponseEntity<?> updateProfile(
            @PathVariable String email,
            @Valid @RequestBody RequestProfileDto request
    ) {
        String result = profileService.updateProfile(email, request);
        return ResponseEntity.ok(Map.of("message", result));
    }
}