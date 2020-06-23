import javax.swing.*;
import java.awt.*;

public class ChatForm extends JFrame {
    public ChatForm() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 500, 400, 400);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(2, 1));

        JTextArea chatField = new JTextArea();
        chatField.setEditable(false);
        JScrollPane jsp = new JScrollPane(chatField);

        JTextField messageField = new JTextField();
        JButton buttonSend = new JButton("Send");

        ButtonSendListener sendListener = new ButtonSendListener(messageField, chatField);
        buttonSend.addActionListener(sendListener);
        messageField.addActionListener(sendListener);

        add(panelTop);
        add(panelBottom);

        panelTop.add(jsp);

        panelBottom.add(messageField);
        panelBottom.add(buttonSend);

        setVisible(true);
        messageField.requestFocus();
    }
}
