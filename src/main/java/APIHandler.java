import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.util.Scanner;

public class APIHandler {
    public APIHandler(AnswerCreator answerCreator, String access_token) {
        org.apache.log4j.BasicConfigurator.configure();
        var group = new Group(-175100810, access_token);

        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(answerCreator.getAnswer(message.getText()))
                        .send());
    }

    public String receive() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void reply(String content) {
        System.out.println(content);
    }
}
