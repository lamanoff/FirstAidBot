package com.firstaid;
public class Main {

    public static void main(String args[]) {
        var dbHandler = new DatabaseHandler();
        var dbPath = dbHandler.getClass().getResource("db.db").toString();
        dbHandler.connect(dbPath);
        var answerCreator = new AnswerCreator(dbHandler);
        var access_token = System.getenv().get("ACCESS_TOKEN");
        var apiHandler = new APIHandler(answerCreator, access_token);

        //((com.firstaid.DatabaseHandler) _dbHandler).disconnect();
    }
}
