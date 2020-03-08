package dolejsi.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Start extends JFrame {
    private final Dice dice;
    private final Board board;
    //when using the form, please use reasonable names and place the components in mainPanel.
    //main panels and buttons
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
    private JPanel panel19;
    private JPanel panel20;
    private JPanel panel21;
    private JPanel panel22;
    private JPanel panel23;

    //important labels
    private JLabel playerLabel;
    private JLabel diceLabel1;
    private JLabel diceLabel2;

    //parent player tiles
    private JPanel tile0Players;
    private JPanel tile1Players;
    private JPanel tile2Players;
    private JPanel tile3Players;
    private JPanel tile4Players;
    private JPanel tile5Players;
    private JPanel tile6Players;
    private JPanel tile7Players;
    private JPanel tile8Players;
    private JPanel tile9Players;
    private JPanel tile10Players;
    private JPanel tile11Players;
    private JPanel tile12Players;
    private JPanel tile13Players;
    private JPanel tile14Players;
    private JPanel tile15Players;
    private JPanel tile16Players;
    private JPanel tile17Players;
    private JPanel tile18Players;
    private JPanel tile19Players;
    private JPanel tile20Players;
    private JPanel tile21Players;
    private JPanel tile22Players;
    private JPanel tile23Players;

    private JPanel[] allPanels;

    private JPanel[] tilePlayerPositions;


    private Color[] playerColors = new Color[]{Color.BLUE, Color.RED, Color.GREEN};

    public Start() {
        setContentPane(mainPanel);
        //write code here
        buy.setEnabled(false);
        sell.setEnabled(false);
        payJailFee.setEnabled(false);
        nextPlayerButton.setEnabled(false);

        tilePlayerPositions = new JPanel[]{
                tile0Players,
                tile1Players,
                tile2Players,
                tile3Players,
                tile4Players,
                tile5Players,
                tile6Players,
                tile7Players,
                tile8Players,
                tile9Players,
                tile10Players,
                tile11Players,
                tile12Players,
                tile13Players,
                tile14Players,
                tile15Players,
                tile16Players,
                tile17Players,
                tile18Players,
                tile19Players,
                tile20Players,
                tile21Players,
                tile22Players,
                tile23Players
        };

        allPanels = new JPanel[]{
                panel0,
                panel1,
                panel2,
                panel3,
                panel4,
                panel5,
                panel6,
                panel7,
                panel8,
                panel9,
                panel10,
                panel11,
                panel12,
                panel13,
                panel14,
                panel15,
                panel16,
                panel17,
                panel18,
                panel19,
                panel20,
                panel21,
                panel22,
                panel23
        };

        NewGameForm newGameForm = new NewGameForm();
        newGameForm.setVisible(true);

        final List<Player> players = newGameForm.isOkeyed() ?
                //when okeyed
                newGameForm.getPlayerNames().stream()
                        .map(playerName -> new Player(playerName, 4000))
                        .collect(Collectors.toList()) :
                //when cancelled
                asList(
                        new Player("Filip", 4000),
                        new Player("Jan", 4000),
                        new Player("Jarka", 4000)
                );

        final Chance chance = new Chance();
        this.board = new Board(new BoardTile[]{
                new GoTile(),
                new Building(1, null, 60, 10, "Old Kent Road"),
                new ChanceTile(2, chance),
                new Railroad(3, null, 200, 100),
                new Building(4, null, 300, 100, "Pentoville Road"),
                new Tax(5, -75),
                new Jail(6),
                new Building(7, null, 300, 100, "Whitehall"),
                new ChanceTile(8, chance),
                new Railroad(9, null, 200, 100),
                new Tax(10, -300),
                new Building(11, null, 500, 300, "Marlborough street"),
                new Parking(12),
                new Building(13, null, 600, 200, "Fleet street"),
                new ChanceTile(14, chance),
                new Railroad(15, null, 200, 100),
                new Building(16, null, 650, 300, "Coventry street"),
                new Building(17, null, 650, 350, "Piccadilly"),
                new GoToJail(18),
                new Building(19, null, 750, 400, "Oxford street"),
                new ChanceTile(20, chance),
                new Railroad(21, null, 200, 100),
                new Building(22, null, 800, 600, "Mayfair"),
                new Tax(23, -400),

        }, players, chance);

        this.dice = new Dice();

        playerLabel.setText(players.get(0).getInventory());

        showPlayerPositions();
        showTileOwners();

        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final Player currentPlayer = board.getCurrentPlayer();
                if (currentPlayer.isInJail() && !currentPlayer.getIsJailFree()) {
                    playInJail();
                } else if (currentPlayer.isInJail() && currentPlayer.getIsJailFree()) {
                    payJailFee.setEnabled(true);
                }
                nextPlayerButton.setEnabled(true);
                move.setEnabled(false);
                int finalDiceValue = 0;//diceValue1 + diceValue2 added on;

                for (int i = 0; i < 3; i++) {
                    // roll dice
                    final int diceValue1 = 1;//dice.getNum1();
                    final int diceValue2 = 1;//dice.getNum2();
                    diceLabel1.setText(String.valueOf(diceValue1));
                    diceLabel2.setText(String.valueOf(diceValue2));
                    finalDiceValue += diceValue1 + diceValue2;

                    if (diceValue1 == diceValue2) {
                        JOptionPane.showMessageDialog(null, "You got a double of two " + diceValue1 + "'s. It will automatically throw again");

                    } else {
                        break;
                    }
                    if (i == 2) {
                            JOptionPane.showMessageDialog(null, "You threw three doubles!!! Go to Jail!!!");
                        currentPlayer.goToJail(board.getJailPosition());
                        switchToNextPlayer();
                        showPlayerPositions();
                        return;
                    }
                }

//todo: if bool true then switch to next player and continue;
                // advance active player
                currentPlayer.move(finalDiceValue, board.getTiles().length);
                showPlayerPositions();
                JOptionPane.showMessageDialog(null, "You threw a total of " + finalDiceValue + ".");

                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                if (currentTile instanceof ChanceTile) {
                    chance.next().applyCard(currentPlayer, board);
                } else if (currentTile instanceof Ownable) {

                    final Ownable ownable = (Ownable) currentTile;

                    if (currentPlayer.getMoney()
                            >= ownable.getCost() && ownable.getOwner() == null) {
                        buy.setEnabled(true);
                        JOptionPane.showMessageDialog(null, ("You can now buy " + ownable.getName()) + " for $" + ownable.getCost() + ".");
                        if (currentPlayer == ownable.getOwner()) {
                            sell.setEnabled(true);
                        }
                    } else if (ownable.getOwner() != null) {
                        Player currentOwner = ownable.getOwner();
                        JOptionPane.showMessageDialog(null, "YACK... You stepped on " + currentOwner.getName() + "'s property! Pay $" + ownable.getRent() + " for rent!");
                        currentOwner.addMoney(ownable.getRent());
                        currentPlayer.addMoney(-ownable.getRent());
                        JOptionPane.showMessageDialog(null, currentOwner.getName() + " got $" + ownable.getRent() + " from " + currentPlayer.getName() + ".");
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
                showPlayerPositions();
            }

        });
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buy();
            }
        });


        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sell();
            }
        });
        payJailFee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final Player currentPlayer = board.getCurrentPlayer();
                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                if (currentPlayer.getIsJailFree()) {
                    currentPlayer.setIsJailFree(false);
                }
            }
        });
        nextPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // switch to next player
                switchToNextPlayer();
                final java.util.List<Player> deadPlayers = board.removeDeadPlayers();

                deadPlayers.forEach(p -> JOptionPane.showMessageDialog(null, "We are very sorry to tell you, but you have been kicked out of our server. It has been an honor to play with you, " + p.getName()));
                showTileOwners();
                showPlayerPositions();
                if (board.getPlayers().size() == 1) {
                    JOptionPane.showMessageDialog(null, "Congrats! You won!");
                }
            }
        });
    }

    private void buy() {
        final Player currentPlayer = board.getCurrentPlayer();
        final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
        buy.setEnabled(false);
        if (currentTile instanceof Ownable) {
            JOptionPane.showMessageDialog(null, "You bought the " + ((Ownable) currentTile).getName());
            currentPlayer.addMoney(-((Ownable) currentTile).getCost());
            ((Ownable) currentTile).setOwner(currentPlayer);
            playerLabel.setText(currentPlayer.getInventory());
        } else {
            throw new IllegalArgumentException("There is no building you are on");
        }
        showTileOwners();
    }

    private void sell() {
        final Player currentPlayer = board.getCurrentPlayer();
        final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
        sell.setEnabled(false);

        if (currentTile instanceof Ownable) {
            final Ownable ownableTile = (Ownable) currentTile;
            currentPlayer.addMoney(ownableTile.getCost());
            //todo: look over this again
            ownableTile.setOwner(null);
            playerLabel.setText(currentPlayer.getInventory());
        } else {
            throw new IllegalArgumentException("There is no ownable tile you are on");
        }
        sell.setEnabled(false);
        showTileOwners();
    }

    private void switchToNextPlayer() {
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

    private void showTileOwners() {
        for (int i = 0; i < board.getTiles().length; i++) {
            final BoardTile currentTile = board.getTileAt(i);
            if (!(currentTile instanceof Ownable)) {
                continue;
            }
            final JPanel thisPanel = this.allPanels[i];

            for (int j = 0; j < board.getPlayers().size(); j++) {
                final Player player = board.getPlayers().get(j);

                if (player.equals(((Ownable) currentTile).getOwner())) {
                    thisPanel.setBackground(playerColors[j]);
                    break;
                } else {
                    thisPanel.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void showPlayerPositions() {
        for (int i = 0; i < board.getTiles().length; i++) {
            final JPanel tilePlayerPositionParent = this.tilePlayerPositions[i];

            for (int j = 0; j < board.getPlayers().size(); j++) {
                final JPanel tilePlayerPanel = (JPanel) tilePlayerPositionParent.getComponent(j);
                final Player player = board.getPlayers().get(j);
                if (player.getCurrentPosition() == i) {
                    tilePlayerPanel.setBackground(playerColors[j]);
                } else {
                    tilePlayerPanel.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void playInJail() {
        final Player currentPlayer = board.getCurrentPlayer();
        final int diceValue1 = dice.getNum1();
        if (diceValue1 == 6) {
            currentPlayer.setInJail(false);
            JOptionPane.showMessageDialog(null, "You successfully threw a six. Play on!!!");
        } else {
            JOptionPane.showMessageDialog(null, "You did not successfully throw a six");
            move.setEnabled(false);
            nextPlayerButton.setEnabled(true);
        }
    }

}
