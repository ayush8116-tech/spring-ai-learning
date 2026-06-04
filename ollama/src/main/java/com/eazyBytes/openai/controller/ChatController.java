package com.eazyBytes.openai.controller;

import com.eazyBytes.openai.view.ChatRequestView;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;
    private final LinkedList<String> messages;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
        this.messages = new LinkedList<>();
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequestView chatRequestView) {

        String content = chatClient
                .prompt()
                .system("""
                        you have to act like a hotel manager and ask politely to buy lend the room of the hotel.
                        """)
                .user(chatRequestView.message())
                .call()
                .content();

        return content;
    }
}
