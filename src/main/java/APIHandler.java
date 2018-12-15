import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

public class APIHandler {
    public APIHandler(AnswerCreator answerCreator, String access_token) {
        org.apache.log4j.BasicConfigurator.configure();
        var group = new Group(-175100810, access_token);
        var defaultAnswer = "Пожалуйста, сформулируйте проблему более конкретно.";

        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(answerCreator.getAnswer(message.getText()))
                        .send());
        group.onStickerMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(defaultAnswer)
                        .send());
        group.onVoiceMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(defaultAnswer)
                        .send());
        group.onAudioMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(defaultAnswer)
                        .send());
        group.onPhotoMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(defaultAnswer)
                        .send());
    }
}
