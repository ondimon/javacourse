import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ButtonListener implements ActionListener {
    private Calc calc;
    private static final char[] SIGNS = {'+', '-', '*', '/'};
    private static final char EQUALLY = '=';
    private static final char CLEAR = 'C';
    private JTextField jTextField;

    public ButtonListener(JTextField jTextField, Calc calc) {
        this.jTextField = jTextField;
        this.calc = calc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String buttonText = jButton.getText();
        char charButton = buttonText.charAt(0);

        if(charButton == EQUALLY) {
            calc.calculate();
        } else if(charButton == CLEAR) {
            calc.clear();
        } else if(isSign(charButton)) {
            calc.setSign(charButton);
        }else{
            calc.addNumber(Integer.parseInt(buttonText));
        }
        jTextField.setText(calc.toString());
    }

    private boolean isSign(char charButton) {
        for ( int i = 0; i < SIGNS.length; i++ ) {
            if (SIGNS[i] == charButton) {
                return true;
            }
        }
        return false;
    }
}