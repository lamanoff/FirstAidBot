package server;

public class Main {
    private static APIHandler _apiHandler;
    private static DatabaseHandler _dbHandler;
    private static AnswerCreator _answerCreator;

    public static void main(String args[]) {
        _apiHandler = new APIHandler();
        _dbHandler = new DatabaseHandler();
        ((DatabaseHandler) _dbHandler).connect("db\\db.db");
        _answerCreator = new AnswerCreator(_dbHandler);

        while (true) {
            var request = _apiHandler.receive();
            var answer = _answerCreator.GetAnswer(request);
            _apiHandler.reply(answer);
        }
        //((DatabaseHandler) _dbHandler).disconnect();
    }
}
