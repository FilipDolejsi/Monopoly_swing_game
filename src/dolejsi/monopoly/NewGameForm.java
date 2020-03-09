package dolejsi.monopoly;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NewGameForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JTextField playerName;
    private JButton addPlayerButton;
    private JCheckBox robotCheckBox;
    private boolean okeyed;
    private final ArrayList<Player> players;

    public NewGameForm() {
        this.players = new ArrayList<>();
        setContentPane(contentPane);
        setModal(true);
        setSize(440,500);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final String newPlayerName = playerName.getText();
                textArea1.append(newPlayerName + '\n');
                playerName.setText("");
                buttonOK.setEnabled(true);
                robotCheckBox.setEnabled(true);
                Player newPlayer;
                if (robotCheckBox.isSelected()){
                    newPlayer = new AutomaticPlayer(newPlayerName, 1500);
                } else{
                    newPlayer = new Player(newPlayerName, 1500);
                }
                players.add(newPlayer);
                if (players.size() >= 3) {
                    addPlayerButton.setEnabled(false);
                }
            }
        });
    }

    private void onOK() {
        this.okeyed = true;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public boolean isOkeyed() {
        return okeyed;
    }

    public List<Player> getPlayers(){
        return this.players;
    }
}
