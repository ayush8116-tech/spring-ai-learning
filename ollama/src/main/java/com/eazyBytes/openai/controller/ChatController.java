package com.eazyBytes.openai.controller;

import com.eazyBytes.openai.view.ChatRequestView;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequestView chatRequestView) {
        String content = chatClient.prompt(chatRequestView.message()).call().content();
        return content;
    }
}
