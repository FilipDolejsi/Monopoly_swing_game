package dolejsi.monopoly;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    //when using the form, please use reasonable names and place the components in panel1.

    private JPanel panel1;
    private JButton button1;

    Player player1 = new Player("Filip", 4000);

    public Start(){
        setContentPane(panel1);
        //write code here

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
            }
        });
    }
}
