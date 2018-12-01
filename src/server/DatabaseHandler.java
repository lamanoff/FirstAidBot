package server;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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

    private ResultSet makeQuery(String sql) {
        try (var statement = _connection.createStatement();
             var result = statement.executeQuery(sql)) {
            return result;
        } catch (SQLException ex) {
            return null;
        }
    }

    private boolean haveRequiredWord(String[] collection, String word, int precision) {
        for (var element :
                collection) {
            if (StringUtils.getLevenshteinDistance(element, word) <= precision)
                return true;
        }
        return false;
    }


    public String getAnswer(String query) {
        var sql = "SELECT Id, Descriptions FROM Classification";
        var queryWords = query.split(" ");
        try (var statement = _connection.createStatement();
             var response = statement.executeQuery(sql)) {
            if (response == null)
                return null;
            while (response.next()) {
                var descriptions = response.getString("Descriptions");
                var answerKeywords = descriptions.split(";");
                for (var queryWord : queryWords) {
                    if (haveRequiredWord(answerKeywords, queryWord, 2)) {
                        var id = response.getInt("id");
                        return makeQuery("SELECT Id, Answer FROM Classification WHERE Id = " + id).getString("Answer");
                    }
                }

                //Arrays.asList(1,2,3,4,5).stream().filter( i -> i%2 == 0).mapToInt(i -> i).sum();
                //System.out.println(result.getInt("Id") + " " + result.getString("Descriptions"));
            }
        } catch (SQLException ex) {
            return null;
        }

        return null;
    }
}
