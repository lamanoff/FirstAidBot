package com.firstaid;
public class Main {

    public static void main(String args[]) {
        var vk_token = System.getenv().get("VK_ACCESS_TOKEN");
        var ai_token = System.getenv().get("AI_ACCESS_TOKEN");
        while (true) {
            try {
                System.out.println("Server started!");
                var answerCreator = new AnswerCreator(ai_token);
                var apiHandler = new APIHandler(answerCreator, vk_token);
            } catch (Exception ex)
            {
                System.out.println("Mainloop exception: " + ex.getMessage());
                System.out.println("Restarting...");
            }
        }
    }
}
