package Client;

import Heandlers.MessageHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;



    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {


        try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
             MessageHandler serverHandler = new MessageHandler(socket);
             Scanner scanner = new Scanner(System.in)
        )  {

            Thread serverListener = new Thread(serverHandler);
            serverListener.start();

            while (true) {
                System.out.println("Введите сообщение для сервера или quit для выхода...");

                String message = scanner.nextLine();
                serverHandler.sendMessage(message);
                if (message.equalsIgnoreCase("quit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
