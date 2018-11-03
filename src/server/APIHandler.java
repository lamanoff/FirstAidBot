package server;

import java.util.Scanner;

public class APIHandler {

    public String receive() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void reply(String content) {
        System.out.println(content);
    }
}
