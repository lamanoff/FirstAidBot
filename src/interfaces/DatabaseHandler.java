package interfaces;

import server.PollingStage;

public interface DatabaseHandler {
    String getAnswer(PollingStage stage, String query);
    String getClarifyingQuestion(PollingStage stage);
}
