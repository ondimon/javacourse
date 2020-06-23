import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSendListener implements ActionListener {
    JTextField produceField;
    JTextArea consumerField;

    public ButtonSendListener(JTextField produceField, JTextArea consumerField) {
        this.produceField = produceField;
        this.consumerField = consumerField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String text = produceField.getText();
        if (text.isBlank()) {
            clearConsumerField();
            return;
        }
        consumerField.append(text);
        consumerField.append("\n\r");
        clearConsumerField();
        produceField.requestFocus();
    }

    private void clearConsumerField() {
        produceField.setText("");
    }
}
