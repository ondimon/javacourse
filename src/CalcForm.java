import javax.swing.*;
import java.awt.*;

public class CalcForm extends JFrame {
    private final Calc  calc = new Calc();

    public CalcForm() {

        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setMaximumSize(new Dimension(400,50));
        textField.setText(calc.toString());
        add(textField);

        JPanel panelButtons = new JPanel();
        add(panelButtons);
        panelButtons.setLayout(new GridLayout(4, 4));

        char[] chars = {'7', '8', '9', '*', '4', '5', '6', '/', '1', '2', '3', '-', 'C', '0', '=', '+'};
        for (int i = 0; i < chars.length; i++) {
            JButton jb = new JButton(String.valueOf(chars[i]));
            jb.addActionListener(new ButtonListener(textField, calc));
            panelButtons.add(jb);
        }
        revalidate();
    }
}
