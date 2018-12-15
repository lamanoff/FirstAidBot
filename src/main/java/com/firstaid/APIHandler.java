package com.firstaid;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

public class APIHandler {
    public APIHandler(AnswerCreator answerCreator, String access_token) {
        org.apache.log4j.BasicConfigurator.configure();
        var group = new Group(-175100810, access_token);
        var defaultAnswer = "Я тебя не понял(";

        group.onSimpleTextMessage(message -> sendMessage(message, answerCreator.getAnswer(message.getText()), group));
        group.onStickerMessage(message -> sendMessage(message, defaultAnswer, group));
        group.onVoiceMessage(message -> sendMessage(message, defaultAnswer, group));
        group.onAudioMessage(message -> sendMessage(message, defaultAnswer, group));
        group.onPhotoMessage(message -> sendMessage(message, defaultAnswer, group));
    }

    private void sendMessage(Message received, String answer, Group group)
    {
        try {
            new Message()
                    .from(group)
                    .to(received.authorId())
                    .text(answer)
                    .send();
        } catch (Exception ex)
        {
            System.out.println("VK Handler exception: " + ex.getMessage());
        }
    }
}
