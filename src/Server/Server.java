package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private AuthService authService;
    private List<ClientHandler> clients;
    private ExecutorService executorService;

    public static void main(String[] args) {
        new Server(8189);
    }

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        start();
    }

    public AuthService getAuthService() {
        return authService;
    }

    private void start() {
        try (ServerSocket server = new ServerSocket(this.port)) {
            authService = new DBAuthService();
            authService.start();
            executorService = Executors.newCachedThreadPool();
            while(true) {
                System.out.println("Server started on port: " + port);
                System.out.println("Server is waiting for clients...");
                Socket socket = server.accept();
                System.out.println(String.format("Client connected: %s", socket.toString()));
                executorService.execute(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during server startup");
        } finally {
            if (authService != null) {
                authService.stop();
            }
            if(executorService != null) {
                executorService.shutdown();
            }
        }
    }

    public synchronized void log(String text) {
        System.out.println(text);
    }

    public synchronized boolean isNickBusy(String nickname) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getName().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean isNickFree(String nickname) {
        return !isNickBusy(nickname);
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(message);
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}
