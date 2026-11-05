package com.substring.service;

import com.substring.dto.StudyPlan;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

import java.util.Optional;

public class ChatHelper {
    public static TokenStream getResponse(String input){
        StreamingChatModel chatModel = OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("NVIDIA_API_KEY"))
                .baseUrl("https://integrate.api.nvidia.com/v1")
                .modelName("nvidia/nemotron-3.5-super-vl-120b-a12b")
                .build();

        BotAssistant botAssistant = AiServices.create(BotAssistant.class, chatModel);
        return botAssistant.chatWithBot(input);
    }

    public static TokenStream getStudyPlan(String topic, Optional<String> timeframe){
        StreamingChatModel chatModel = OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("NVIDIA_API_KEY"))
                .baseUrl("https://integrate.api.nvidia.com/v1")
                .modelName("nvidia/nemotron-3.5-super-vl-120b-a12b")
                .build();
        StudyPlannerAssistant studyPlannerAssistant = AiServices.create(StudyPlannerAssistant.class, chatModel);
        return studyPlannerAssistant.getStudyPlan(topic, timeframe);
    }
}
