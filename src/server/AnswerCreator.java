package server;

import com.google.gson.Gson;
import interfaces.*;

public class AnswerCreator implements interfaces.AnswerCreator {
    public AnswerCreator(APIHandler apiHandler, Parser parser, DatabaseHandler db){

    }

    @Override
    public Gson GetAnswer(Gson jsonRequest) {
        return null;
    }
}
