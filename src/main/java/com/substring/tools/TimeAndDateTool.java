package com.substring.tools;

import dev.langchain4j.agent.tool.Tool;

public class TimeAndDateTool {
    @Tool(name = "getCurrentTimeAndDate")
    public static String getCurrentTimeAndDate() {
        IO.println("Getting current time and date");
        return java.time.LocalDateTime.now().toString();
    }
}
