package dolejsi.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu  extends JFrame{
    private JPanel panel1;
    private JButton HOWTOPLAYButton;
    private JButton PLAYButton;
    private JButton EXITButton;

    public Menu() {
        setContentPane(panel1);
        PLAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                Start start = new Start();
                start.setVisible(true);
                start.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                start.setSize(1500,1000);
            }
        });
        HOWTOPLAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HowToPlay howToPlay = new HowToPlay();
                howToPlay.setVisible(true);
                howToPlay.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                howToPlay.setSize(500,300);
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }
}
