package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler implements interfaces.DatabaseHandler {
    private Connection _connection = null;

    public void connect(String path) {
        try {
            var url = "jdbc:sqlite:" + path;
            _connection = DriverManager.getConnection(url);

            System.out.println("Connection to the database has been established!"); //Debug
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void disconnect(){
        try{
            if (_connection != null)
                _connection.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public String getAnswer(PollingStage stage, String query) {
        return null;
    }

    @Override
    public String getClarifyingQuestion(PollingStage stage) {
        return null;
    }
}
