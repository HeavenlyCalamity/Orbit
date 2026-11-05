package com.substring.service;

import com.substring.dto.StudyPlan;
import com.substring.tools.Calculator;
import com.substring.tools.FileWriterTool;
import com.substring.tools.TimeAndDateTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

import java.util.Optional;

public class ChatHelper {

    static  StreamingChatModel chatModel = OpenAiStreamingChatModel.builder()
            .apiKey(System.getenv("NVIDIA_API_KEY"))
            .baseUrl("https://integrate.api.nvidia.com/v1")
            .modelName("nvidia/nemotron-3.5-super-vl-120b-a12b")
            .build();

    static BotAssistant botAssistant = AiServices.builder(BotAssistant.class)
            .streamingChatModel(chatModel)
            .chatMemoryProvider(memoryId ->
                    MessageWindowChatMemory.withMaxMessages(25)
            )
            .tools(new Calculator(),
                    new TimeAndDateTool(),
                    new FileWriterTool()
            )
            .build();
    static StudyPlannerAssistant studyPlannerAssistant = AiServices.builder(StudyPlannerAssistant.class)
            .streamingChatModel(chatModel)
            .chatMemoryProvider(memoryId ->
                    MessageWindowChatMemory.withMaxMessages(25)
            )
            .build();



    public static TokenStream getResponse(Integer conversationId, String input){
        return botAssistant.chatWithBot(conversationId, input);
    }

    public static TokenStream getStudyPlan(Integer conversationId, String topic, Optional<String> timeframe){
        return studyPlannerAssistant.getStudyPlan(conversationId, topic, timeframe);
    }
}
