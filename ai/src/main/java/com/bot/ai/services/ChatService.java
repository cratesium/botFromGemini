package com.bot.ai.services;
import com.bot.ai.*;
import com.bot.ai.model.ChatMessage;
import com.bot.ai.model.ChatRequest;
import com.bot.ai.model.ChatResponse;
import com.bot.ai.repository.ChatMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String apiKey;

    public ChatResponse getResponse(ChatRequest chatRequest) {
        String userMessage = chatRequest.getUserMessage();

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Create the request body
        String requestBody = "{\"message\": \"" + userMessage + "\"}";

        // Create the HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Make the API call
            String response = restTemplate.exchange(geminiApiUrl, HttpMethod.POST, entity, String.class).getBody();

            // Save the chat message to the database
            ChatMessage chatMessage = new ChatMessage(userMessage, response);
            chatMessageRepository.save(chatMessage);
            
            return new ChatResponse(response);
        } catch (Exception e) {
            System.err.println("API call failed: " + e.getMessage());
            return new ChatResponse("Sorry, I couldn't process your request at the moment.");
        }//beacuse api is quite busy so error occuring 
    }
}

