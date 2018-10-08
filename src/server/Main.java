package server;

public class Main {
    private static interfaces.APIHandler _apiHandler = new APIHandler();

    public static void main(String agrs[]) {
        while (true) {
            var request = _apiHandler.receive();
            System.out.println("asfkdjfdjg");
            _apiHandler.reply(request);
            if (request.toLowerCase().equals("max"))
                System.out.println("Hola");
        }
    }
}
