package server;

public class AnswerCreator {
    private DatabaseHandler _dbHandler;

    public AnswerCreator(DatabaseHandler dbHandler){
        _dbHandler = dbHandler;
    }

    public String getAnswer(String request) {
        return _dbHandler.getAnswer(request);
    }
}
