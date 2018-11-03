package server;

public class AnswerCreator {
    DatabaseHandler _dbHandler;

    public AnswerCreator(DatabaseHandler dbHandler){
        _dbHandler = dbHandler;
    }

    public String GetAnswer(String request) {
        var keyWords = request.split(" ");
        return "";
    }
}
