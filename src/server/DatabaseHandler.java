package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private Connection _connection = null;

    void connect(String path) {
        try {
            var url = "jdbc:sqlite:" + path;
            _connection = DriverManager.getConnection(url);

            System.out.println("Connection to the database has been established!"); //Debug
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void disconnect() {
        try {
            if (_connection != null) {
                _connection.close();

                System.out.println("The database has been disconnected!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getAnswer(String query) {
        var sql = "SELECT Id, Descriptions FROM Classification";
        try (var statement = _connection.createStatement();
             var result = statement.executeQuery(sql)) {

            while (result.next()) {
                System.out.println(result.getInt("Id") + " " + result.getString("Descriptions"));
            }

        } catch (SQLException ex) {

        }
        return null;
    }
}
