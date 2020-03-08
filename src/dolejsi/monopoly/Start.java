package dolejsi.monopoly;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    //when using the form, please use reasonable names and place the components in mainPanel.

    private JPanel mainPanel;
    private JButton move;
    private JButton buy;
    private JButton sell;
    private JButton payJailFee;
    private JButton nextPlayerButton;
    private JPanel panel0;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel13;
    private JPanel panel14;
    private JPanel panel15;
    private JPanel panel16;
    private JPanel panel17;
    private JPanel panel18;
    private JLabel playerLabel;
    private JLabel diceLabel1;
    private JLabel diceLabel2;

    JPanel[] allPanels;

    public Start() {
        setContentPane(mainPanel);
        //write code here
        buy.setEnabled(false);
        sell.setEnabled(false);
        payJailFee.setEnabled(false);
        nextPlayerButton.setEnabled(false);

        Player player1 = new Player("Filip", 4000);
        Player player2 = new Player("Jan",4000);
        Player player3 = new Player("Jarka", 4000);

        final Chance chance = new Chance();
        final Board board = new Board(new BoardTile[]{
                new GoTile(),
                new Building(1, null, 60, 10, "Old Kent Road"),
                new ChanceTile(2, chance),
                new Railroad(3, null, 200, 100),
                new Building(4, null, 300, 100, "Manhouse"),
                new Tax(5, -75),
                new Jail(6),
                new Building(7, null, 300, 100, "Lolhouse"),
                new ChanceTile(8, chance),
                new Railroad(9, null, 200, 100),
                new Tax(10, -300),
                new Building(11, null, 500, 300, "HELLOHOUSE"),
                new Parking(12),
                new Building(13, null, 600, 200, "New house"),
                new ChanceTile(14, chance),
                new Railroad(15, null, 200, 100),
                new Building(16, null, 650, 300, "HELLO"),
                new Building(17, null, 650, 350, "filipie"),
                new GoToJail(18),
                new Building(19, null, 750, 400, "Aftermath"),
                new ChanceTile(20, chance),
                new Railroad(21, null, 200, 100),
                new Building(22, null, 800, 600, "Expensive"),
                new Tax(23, -400),

        }, new Player[]{
                player1,
                player2,
                player3
        }, chance);

        Dice dice = new Dice();

        playerLabel.setText(player1.getInventory());

        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextPlayerButton.setEnabled(true);
                move.setEnabled(false);
                // roll dice

                final int diceValue1 = dice.getNum1();
                final int diceValue2 = dice.getNum2();
                final int finalDiceValue = diceValue1 + diceValue2;

                diceLabel1.setText(String.valueOf(diceValue1));
                diceLabel2.setText(String.valueOf(diceValue2));


                // advance active player
                final Player currentPlayer = board.getCurrentPlayer();
                currentPlayer.move(finalDiceValue, board.getTiles().length);
                JOptionPane.showMessageDialog(null, "You got "+ finalDiceValue);

                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                if (currentTile instanceof ChanceTile) {
                    chance.next().applyCard(currentPlayer, board);
                } else if (currentTile instanceof Ownable) {

                    final Ownable ownable = (Ownable) currentTile;

                    if (currentPlayer.getMoney()
                            >= ownable.getCost() && ownable.getOwner()==null)
                    {
                        buy.setEnabled(true);
                        JOptionPane.showMessageDialog(null, ("You can now buy "+ ownable.getName()) + " for $"+ ownable.getCost()+".");
                        if (currentPlayer== ownable.getOwner()){
                            sell.setEnabled(true);
                        }
                    }else if (ownable.getOwner() != null){
                        Player currentOwner= ownable.getOwner();
                        JOptionPane.showMessageDialog(null,"YACK... You stepped on "+currentOwner.getName()+"'s property! Pay $"+((Building) currentTile).getRent()+" for rent!");
                        currentOwner.addMoney(ownable.getRent());
                        currentPlayer.addMoney(-ownable.getRent());
                        JOptionPane.showMessageDialog(null,currentOwner.getName()+" got $"+((Building) currentTile).getRent()+" from "+currentPlayer+".");
                    }


                } else if (currentTile instanceof GoToJail) {
                    JOptionPane.showMessageDialog(null, "Go to Jail :(");
                    currentPlayer.goToJail(board.getJailPosition());
                    currentPlayer.setInJail(true);
                    JOptionPane.showMessageDialog(null, "You are successfully in jail");
                    //todo: oveřit
                } else if (currentTile instanceof Tax) {
                    final int taxToPay = ((Tax) currentTile).getTaxToPay();
                    JOptionPane.showMessageDialog(null, "You have landed on the TAX space. Pay the " + -taxToPay + " now!");
                    currentPlayer.addMoney(taxToPay);
                    //todo: update label when add Money happens
                }
                playerLabel.setText(currentPlayer.getInventory());

            }
        });
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final Player currentPlayer = board.getCurrentPlayer();
                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                buy.setEnabled(false);
                if (currentTile instanceof Ownable) {
                    JOptionPane.showMessageDialog(null, "You bought the "+((Ownable)currentTile).getName());
                    currentPlayer.addMoney(-((Ownable) currentTile).getCost());
                    ((Ownable) currentTile).setOwner(currentPlayer);
                    playerLabel.setText(currentPlayer.getInventory());
                } else {
                    throw new IllegalArgumentException("There is no building you are on");
                }
            }
        });


        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final Player currentPlayer = board.getCurrentPlayer();
                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                sell.setEnabled(false);

                if (currentTile instanceof Ownable) {
                    currentPlayer.addMoney(((Ownable) currentTile).getCost());
                    //todo: look over this again
                    ((Ownable) currentTile).setOwner(null);
                    playerLabel.setText(currentPlayer.getInventory());
                } else {
                    throw new IllegalArgumentException("There is no ownable tile you are on");
                }
                sell.setEnabled(false);
            }
        });
        payJailFee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final Player currentPlayer = board.getCurrentPlayer();
                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                if (currentPlayer.getIsJailFree()){
                    currentPlayer.setIsJailFree();
                }
            }
        });
        nextPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // switch to next player
                board.nextPlayer();
                final Player currentPlayer = board.getCurrentPlayer();
                buy.setEnabled(false);
                sell.setEnabled(false);
                payJailFee.setEnabled(false);
                move.setEnabled(true);
                nextPlayerButton.setEnabled(false);
                playerLabel.setText(currentPlayer.getInventory());
                diceLabel1.setText("");
                diceLabel2.setText("");
            }
        });
    }
}
