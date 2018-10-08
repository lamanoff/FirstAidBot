package server;

import java.util.Scanner;

public class APIHandler implements interfaces.APIHandler {

    @Override
    public String receive() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void reply(String content) {
        System.out.println(content);
    }
}
