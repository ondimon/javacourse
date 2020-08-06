package Chat.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class.getName());

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
            logger.info("Server started on port: {}", port);
            logger.info("Server is waiting for clients...");
            authService = new DBAuthService();
            authService.start();
            executorService = Executors.newCachedThreadPool();
            while(true) {
                Socket socket = server.accept();
                logger.info("Client connected: {}", socket.toString());
                executorService.execute(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            logger.error("Something went wrong during server startup: " + e.getMessage());
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
        logger.info(text);
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
