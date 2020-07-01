package Server;

import Heandlers.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new Server(8189).start();
    }

    public void start() {

        try(ServerSocket serverSocket = new ServerSocket(port);
            Scanner scanner = new Scanner(System.in)
            ) {
            System.out.println("Сервер запущен, ожидаем подключения...");

            try(Socket client =  serverSocket.accept();
               MessageHandler clientHandler = new MessageHandler(client)) {

                System.out.println("Клиент подключился");

                Thread clientListener = new Thread(clientHandler);
                clientListener.start();

                while (true) {
                    System.out.println("Введите сооющение для клиента или quit для выхода...");
                    String message = scanner.nextLine();
                    clientHandler.sendMessage(message);
                    if (message.equalsIgnoreCase("quit")) {
                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
