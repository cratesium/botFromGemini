package com.bot.ai.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class ChatMessage {
    @Id
    private String id;
    private String userMessage;
    private String botResponse;

    public ChatMessage() {}

    public ChatMessage(String userMessage, String botResponse) {
        this.userMessage = userMessage;
        this.botResponse = botResponse;
    }

    // Getters and Setters...
}
