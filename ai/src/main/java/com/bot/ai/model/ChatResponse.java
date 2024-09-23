package com.bot.ai.model;

public class ChatResponse {
    private String botResponse;

    public ChatResponse() {}

    public ChatResponse(String botResponse) {
        this.botResponse = botResponse;
    }

    public String getBotResponse() {
        return botResponse;
    }

    public void setBotResponse(String botResponse) {
        this.botResponse = botResponse;
    }
}
