package com.bot.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.ai.model.ChatRequest;
import com.bot.ai.model.ChatResponse;
import com.bot.ai.services.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/message")
    public ChatResponse sendMessage(@RequestBody ChatRequest chatRequest) {
        return chatService.getResponse(chatRequest);
    }
}