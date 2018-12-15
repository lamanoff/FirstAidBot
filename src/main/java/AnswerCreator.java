public class AnswerCreator {
    private DatabaseHandler _dbHandler;

    public AnswerCreator(DatabaseHandler dbHandler) {
        _dbHandler = dbHandler;
    }

    public String getAnswer(String request) {
        var answer = _dbHandler.getAnswer(request);
        if (answer == null)
            return "Пожалуйста, сформулируйте свою проблему более конкретно";
        return answer;
    }
}
