package dolejsi.monopoly;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlay extends JFrame {
    private JTextArea youStartWith$1500TextArea;
    private JButton cancelDoNotPressButton;
    private JPanel mainPanel;

    public HowToPlay(){
        setContentPane(mainPanel);
        cancelDoNotPressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }
}
