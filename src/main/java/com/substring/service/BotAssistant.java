package com.substring.service;

import dev.langchain4j.model.chat.response.ChatResponse;

public interface BotAssistant {
    ChatResponse getResponse(String input);
}
