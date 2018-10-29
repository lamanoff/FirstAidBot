package server;

public class Main {
    private static interfaces.APIHandler _apiHandler;
    private static interfaces.DatabaseHandler _dbHandler;

    public static void main(String args[]) {
        _apiHandler = new APIHandler();
        _dbHandler = new DatabaseHandler();
        ((DatabaseHandler) _dbHandler).connect("db\\db.db");
        while (true) {
            var request = _apiHandler.receive();
            _apiHandler.reply(request);
        }
        //((DatabaseHandler) _dbHandler).disconnect();
    }
}
