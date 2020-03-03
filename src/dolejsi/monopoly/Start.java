package dolejsi.monopoly;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    //when using the form, please use reasonable names and place the components in panel1.

    private JPanel panel1;
    private JButton button1;

    public Start() {
        setContentPane(panel1);
        //write code here

        final Player player1 = new Player("Filip", 4000);
        final Chance chance = new Chance();
        final Board board = new Board(new BoardTile[]{
                new GoTile(),
                new Building(1, null, 300, 100, "Doghouse"),
                new ChanceTile(2, chance),
                new Jail(3)
                //todo: spater

        }, new Player[]{
                player1
        }, chance);

        Dice dice = new Dice();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // roll dice
                final int diceValue = dice.getValue();

                // advance active player
                final Player currentPlayer = board.getCurrentPlayer();
                currentPlayer.move(diceValue, board.getTiles().length);

                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                if (currentTile instanceof ChanceTile) {
                    chance.next().applyCard(currentPlayer, board);
                }
                // todo: implement other tile logic

                // switch to next player
                board.nextPlayer();
            }
        });
    }
}
