package com.firstaid;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;

public class AnswerCreator {
    private AIDataService dataService;

    public AnswerCreator(String token) {
        AIConfiguration configuration = new AIConfiguration(token);
        dataService = new AIDataService(configuration);
    }

    public String getAnswer(String question) {
        try {
            var request = new AIRequest(question);
            var response = dataService.request(request);
            if (response.getStatus().getCode() == 200)
                return response.getResult().getFulfillment().getSpeech();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Пожалуйста, сформулируйте свою проблему более конкретно.";
    }
}
