package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Client extends JFrame {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    private JPanel cards;
    private JTextField msgInputField;
    private JTextField loginField;
    private JTextField passField;

    private JTextArea chatArea;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String login;
    private ArrayList<String> history;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client();
            }
        });
    }

    public Client() {
        history = new ArrayList<>();
        prepareGUI();

        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        setAuthorized(false);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if(strFromServer.startsWith("/authok")) {
                            setAuthorized(true);
                            loadHistory();
                            showHistory();
                            break;
                        }
                        chatArea.append(strFromServer + "\n");
                    }

                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            break;
                        }

                        history.add(strFromServer);
                        chatArea.append(strFromServer);
                        chatArea.append("\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();

    }

    private void loadHistory(){
        try(FileInputStream fileInputStream = new FileInputStream(getHistoryFileName());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            history = (ArrayList<String>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showHistory(){
        if(history == null){
            return;
        }
        int startIndex = 0;
        if (history.size() > 100) {
            startIndex = history.size() - 100;
        }
        for ( int i = startIndex; i < history.size() - 1; i++ ) {
            chatArea.append(history.get(i));
            chatArea.append("\n");
        }
    }


    private String getHistoryFileName() {
        return "src/data/history_" + login + ".txt";
    }
    private void setAuthorized(boolean authorized) {

        CardLayout cl = (CardLayout)(cards.getLayout());
        if (authorized) {
            cl.show(cards, "bottom");
        }else{
            cl.show(cards, "auth");
        }
        repaint();
    }

    public void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                out.writeUTF(msgInputField.getText());
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error sending message");
            }
        }
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileOutputStream fileOutputStream =  new FileOutputStream(getHistoryFileName());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(history);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareGUI() {
        // Параметры окна
        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Текстовое поле для вывода сообщений
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        //
        JPanel authPanel = new JPanel(new FlowLayout());
        loginField = new JTextField(10);
        passField = new JPasswordField(10);
        JButton authButton = new JButton("Login");
        authPanel.add(new JLabel("login:"));
        authPanel.add(loginField);
        authPanel.add(new JLabel("pass:"));
        authPanel.add(passField);
        authPanel.add(authButton);

        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login  = loginField.getText();
                    out.writeUTF("/auth " + login + " " + passField.getText());
                    loginField.setText("");
                    passField.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        // Нижняя панель с полем для ввода сообщений и кнопкой отправки сообщений
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Send");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        bottomPanel.add(msgInputField, BorderLayout.CENTER);


        cards = new JPanel(new CardLayout());
        cards.add("auth", authPanel);
        cards.add("bottom", bottomPanel);
        add(cards, BorderLayout.SOUTH);



        btnSendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        msgInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Настраиваем действие на закрытие окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                    closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });

        setVisible(true);
    }


}
