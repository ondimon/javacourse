package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
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
            e.printStackTrace();
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
            System.out.println(str);
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nickname = server.getAuthService().getNickByLoginAndPass(parts[1], parts[2]);
                if (nickname != null) {
                    if (server.isNickFree(nickname)) {
                        sendMessage("/authok " + nickname);
                        name = nickname;
                        server.subscribe(this);
                        server.broadcastMessage(name + " come in chat");
                        break;
                    } else {
                        sendMessage(String.format("Nickname[%s] is already in use", nickname));
                    }
                } else {
                    sendMessage("Incorrect login and/or password");
                }
            }
        }
    }

    // Отправка сообщений обратно клиенту
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            System.out.println(String.format("Incoming message from %s: %s", name, strFromClient));

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
            e.printStackTrace();
        }
    }
}
