package com.substring.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

public class ChatHelper {
    public static String getResponse(String input){
        List<ChatResponse> responses = new ArrayList<>();
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(System.getenv("NVIDIA_API_KEY"))
                .baseUrl("https://integrate.api.nvidia.com/v1")
                .modelName("nvidia/nemotron-3.5-super-vl-120b-a12b")
                .build();

       UserMessage userMessage = UserMessage.from(
               ImageContent.from("https://picsum.photos/seed/picsum/200/300")
       );

        ChatResponse chatResponse = chatModel.chat(userMessage);
        responses.add(chatResponse);
        return chatResponse.aiMessage().text();
    }
}
