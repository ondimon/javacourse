package Chat.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringMapMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(Server.class.getName());

    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during client handler initialization");
        }
    }

    @Override
    public void run() {
        try {
            doAuth();
            readMessage();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public String getName() {
        return name;
    }

    public void doAuth() throws IOException {
        while (true) {
            String str = in.readUTF();
            logger.debug(String.format("auth: %s", str));
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nickname = server.getAuthService().getNickByLoginAndPass(parts[1], parts[2]);
                if (nickname != null) {
                    if (server.isNickFree(nickname)) {
                        logger.debug(String.format("auth ok: %s", nickname));
                        sendMessage("/authok " + nickname);
                        name = nickname;
                        server.subscribe(this);
                        server.broadcastMessage(name + " come in chat");
                        break;
                    } else {
                        String msg = String.format("Nickname[%s] is already in use", nickname);
                        logger.debug(msg);
                        sendMessage(msg);
                    }
                } else {
                    String msg = "Incorrect login and/or password";
                    logger.debug(msg);
                    sendMessage(msg);
                }
            }
        }
    }

    // Отправка сообщений обратно клиенту
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();

            logger.trace(String.format("Incoming message from %s: %s", name, strFromClient));

            if (strFromClient.equals("/end")) {
                return;
            }else if (strFromClient.startsWith("/newnick")){
                String[] parts = strFromClient.split("\\s");
                String oldnick = getName();
                String nick = parts[1];
                if(changeNick(nick)) {
                    strFromClient = String.format("%s change name to %s", oldnick, nick);
                    server.broadcastMessage(strFromClient);
                }else{
                    sendMessage("Nick not changed");
                }
            }else {
                server.broadcastMessage(String.format("%s: %s", name, strFromClient));
            }
        }
    }

    public boolean changeNick(String nick) {
        if (server.getAuthService().changeNick(name, nick)) {
            name = nick;
            return true;
        }
        return false;
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " left chat");

        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
