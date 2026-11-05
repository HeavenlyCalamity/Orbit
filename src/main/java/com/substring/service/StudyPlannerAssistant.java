package com.substring.service;

import com.substring.dto.StudyPlan;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.util.Optional;

public interface StudyPlannerAssistant {
    @UserMessage("""
        You Are an expert professor and an expert study planner,
        you can create best study plans for students and professionals who want to learn 
        {{topic}} in {{timeframe}} if time duration is null, the default will be 6 months.
        You can Provide Information a Proper researched and Well structured roadmap for getting a job in provided field.
        """)
    TokenStream getStudyPlan(@V("topic") String topic, @V("timeframe") Optional<String> timeframe);
}
