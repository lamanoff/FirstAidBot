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
            System.out.println("Recieved: " + question);
            var request = new AIRequest(question);
            var response = dataService.request(request);
            if (response.getStatus().getCode() == 200) {
                var answer = response.getResult().getFulfillment().getSpeech();
                System.out.println("Created answer: " + answer);
                if (answer.length() == 0)
                    return "Я тебя не понял(";
                return answer;
            } else
                System.out.println("HTTP error: " + response.getStatus().getCode());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ai exception: " + ex.getMessage());
        }
        return "Не понятно(";
    }
}
