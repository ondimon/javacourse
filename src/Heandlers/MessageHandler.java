package Heandlers;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageHandler implements Runnable, Closeable {

    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;

    public MessageHandler(Socket client) throws IOException {
        this.client = client;
        out = new DataOutputStream(this.client.getOutputStream());
        in = new DataInputStream(this.client.getInputStream());
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        if (isQuit(message)) {
            close();
        }
    }

    @Override
    public void run() {
        listen();
    }

    private void listen() {
        try {
            while (!client.isClosed()) {
                String incomeMessage = in.readUTF();
                if (isQuit(incomeMessage)) {
                    break;
                }
                System.out.printf("Получено сообщение: %s\n\r", incomeMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isQuit(String message) {
        return message.equalsIgnoreCase("quit");
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        client.close();
    }
}
