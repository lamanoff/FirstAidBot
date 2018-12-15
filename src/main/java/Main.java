public class Main {

    public static void main(String args[]) {
        var dbPath = new Main().getClass().getResource("db.db").toString();
        var dbHandler = new DatabaseHandler();
        dbHandler.connect(dbPath);
        var answerCreator = new AnswerCreator(dbHandler);
        var apiHandler = new APIHandler(
                answerCreator,
                "");

        //((DatabaseHandler) _dbHandler).disconnect();
    }
}
