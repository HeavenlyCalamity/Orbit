package com.substring.dto;

import java.util.List;

public record StudyPlan(
        String topic,
        String timeframe,
        List<String> steps,
        List<String> resources,
        Integer estimatedTimeToComplete,
        List<String> skillsToAcquire,
        List<String> portfolioProjects
) {
}
