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
    private final Chance chance;
    public boolean robotStillInJail = false;
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
    private JTextArea playerInfo;
    private JTextArea tileInfo;
    private JButton goByTrainToRailroad;
    private JComboBox railroads;
    private JButton exitButton;
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
                newGameForm.getPlayers() :
                //when cancelled
                asList(
                        new Player("Filip", 1500),
                        new Player("Jan", 1500),
                        new Player("Jarka", 1500)
                );

        this.chance = new Chance();
        this.board = new Board(new BoardTile[]{
                new GoTile(),
                new Building(1, null, 60, 10, "Old Kent Road"),
                new ChanceTile(2, chance),
                new Railroad(3, null, 200, 100),
                new Building(4, null, 120, 40, "Pentoville Road"),
                new Tax(5, -75),
                new Jail(6),
                new Building(7, null, 140, 50, "Whitehall"),
                new ChanceTile(8, chance),
                new Railroad(9, null, 200, 100),
                new Tax(10, -75),
                new Building(11, null, 180, 70, "Marlborough street"),
                new Parking(12),
                new Building(13, null, 220, 90, "Fleet street"),
                new ChanceTile(14, chance),
                new Railroad(15, null, 200, 100),
                new Building(16, null, 260, 110, "Coventry street"),
                new Building(17, null, 280, 120, "Piccadilly"),
                new GoToJail(18),
                new Building(19, null, 300, 130, "Oxford street"),
                new ChanceTile(20, chance),
                new Railroad(21, null, 200, 100),
                new Building(22, null, 400, 400, "Mayfair"),
                new Tax(23, -75),

        }, players, chance);

        this.dice = new Dice();

        showPlayerPositions();
        showTileOwners();

        updatePlayerInfo();

        goByTrainToRailroad.setEnabled(false);

        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                moveInvoked();
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
                if (currentPlayer.getIsJailFree()) {
                    currentPlayer.setIsJailFree(false);
                    currentPlayer.setInJail(false);
                    payJailFee.setEnabled(false);
                    updatePlayerInfo();
                    JOptionPane.showMessageDialog(Start.this, "You redeemed yourself out of jail : )");
                }
                updatePlayerInfo();
            }
        });
        nextPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // switch to next player
                switchToNextPlayer();
                final java.util.List<Player> deadPlayers = board.removeDeadPlayers();

                deadPlayers.forEach(p -> JOptionPane.showMessageDialog(Start.this, "We are very sorry to tell you, but you have been kicked out of our server. It has been an honor to play with you, " + p.getName()+"By the way did you know that Samuil is gayyyyyy!!!!"));
                showTileOwners();
                showPlayerPositions();
                if (board.getPlayers().size() == 1) {
                    JOptionPane.showMessageDialog(Start.this, "Congrats! You won!");
                }
            }
        });
        goByTrainToRailroad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final Player currentPlayer = board.getCurrentPlayer();
                final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
                if(railroads.getSelectedIndex()==0){
                    currentPlayer.goToRailroad1();
                } else if (railroads.getSelectedIndex()==1){
                    currentPlayer.goToRailroad2();
                } else if (railroads.getSelectedIndex()==2){
                    currentPlayer.goToRailroad3();
                } else if (railroads.getSelectedIndex()==3){
                    currentPlayer.goToRailroad4();
                }
                goByTrainToRailroad.setEnabled(false);
                showPlayerPositions();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
                menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                menu.setSize(1500,1000);
            }
        });
    }

    private void updatePlayerInfo() {
        final Player currentPlayer = board.getCurrentPlayer();
        final String jailPlayerInfo;
        if (currentPlayer.isInJail()){
            jailPlayerInfo="You are in jail";
        } else{
            jailPlayerInfo="You are not in jail";
        }

        final boolean isRobot = currentPlayer instanceof AutomaticPlayer;
        String robotInfo;
        if (isRobot){
            robotInfo = "This is a robot";
        } else{
            robotInfo = "You are a player";
        }

        String info = ("Name: " + currentPlayer.getName() + "\n" +
                "Money: " + String.valueOf(currentPlayer.getMoney())+"\n"+
                jailPlayerInfo + "\n" +
                robotInfo);
        playerInfo.setText(info);
        playerInfo.setBackground(playerColors[board.getCurrentPlayerAsInt()]);
    }

    private void moveInvoked() {
        final Player currentPlayer = board.getCurrentPlayer();
        final boolean isRobot = currentPlayer instanceof AutomaticPlayer;
        if (currentPlayer.isInJail() && !currentPlayer.getIsJailFree()) {
            if(playOutOfJail()){
                return;
            }else{
                switchToNextPlayer();
                return;
            }
        }
        nextPlayerButton.setEnabled(true);
        move.setEnabled(false);
        int finalDiceValue = 0;//diceValue1 + diceValue2 added on;

        for (int i = 0; i < 3; i++) {
            // roll dice
            final int diceValue1 = dice.getNum1();
            final int diceValue2 = dice.getNum2();
            diceLabel1.setText(String.valueOf(diceValue1));
            diceLabel2.setText(String.valueOf(diceValue2));
            finalDiceValue += diceValue1 + diceValue2;

            if (diceValue1 == diceValue2) {
                final String message = "You got a double of two " + diceValue1 + "'s. It will automatically throw again";
                showMessageToHuman(isRobot, message);
            } else {
                break;
            }
            if (i == 2) {
                showMessageToHuman(isRobot, "You threw three doubles!!! Go to Jail!!!");
                currentPlayer.goToJail(board.getJailPosition());
                updatePlayerInfo();
                switchToNextPlayer();
                showPlayerPositions();
                return;
            }
        }

//todo: if bool true then switch to next player and continue;
        // advance active player
        currentPlayer.move(finalDiceValue, board.getTiles().length);
        showPlayerPositions();
        showMessageToHuman(isRobot, "You threw a total of " + finalDiceValue + ".");

        final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
        if (currentTile instanceof ChanceTile) {
            chance.next().applyCard(currentPlayer, board);
            updateChanceTileInfo();
            showTileOwners();
            showPlayerPositions();
        } else if (currentTile instanceof Ownable) {

            final Ownable ownable = (Ownable) currentTile;
            updateOwnableTileInfo(ownable);

            if (board.canCurrentPlayerBuy()) {
                buy.setEnabled(true);
                showMessageToHuman(isRobot, ("You can now buy " + ownable.getName()) + " for $" + ownable.getCost() + ".");
                if (currentPlayer == ownable.getOwner()) {
                    sell.setEnabled(true);
                }
            } else if (ownable.getOwner() != currentPlayer && ownable.getOwner()!=null) {
                Player currentOwner = ownable.getOwner();
                showMessageToHuman(isRobot, "YACK... You stepped on " + currentOwner.getName() + "'s property! Pay $" + ownable.getRent() + " for rent!");
                currentOwner.addMoney(ownable.getRent());
                currentPlayer.addMoney(-ownable.getRent());
                showMessageToHuman(isRobot, currentOwner.getName() + " got $" + ownable.getRent() + " from " + currentPlayer.getName() + ".");
            }
            if (currentTile instanceof Railroad){
                goByTrainToRailroad.setEnabled(true);
            }


        } else if (currentTile instanceof GoToJail) {
            showMessageToHuman(isRobot, "Go to Jail :(");
            currentPlayer.goToJail(board.getJailPosition());
            currentPlayer.setInJail(true);
            showMessageToHuman(isRobot, "You are successfully in jail");
            updateJailTileInfo(currentPlayer,currentTile);
        } else if (currentTile instanceof Tax) {
            final int taxToPay = ((Tax) currentTile).getTaxToPay();
            showMessageToHuman(isRobot, "You have landed on the TAX space. Pay the " + -taxToPay + " now!");
            currentPlayer.addMoney(taxToPay);
            updateTaxTileInfo(currentTile);
        }
        updatePlayerInfo();
        showPlayerPositions();
    }

    private void updateTaxTileInfo(BoardTile currentTile) {
        String info = "You have to pay: "+(-((Tax) currentTile).getTaxToPay());
        tileInfo.setText(info);
    }

    private void updateJailTileInfo(Player currentPlayer,BoardTile currentTile) {
        String info;
        if(currentPlayer.isInJail()){
            info=("You are in jail");
        } else{
            info="You are just visiting jail";
        }
        tileInfo.setText(info);
    }

    private void updateChanceTileInfo() {
        String info=("Chance: await chance card");
        tileInfo.setText(info);
    }

    private void updateOwnableTileInfo(Ownable ownable) {
        String info=("Name: " + ownable.getName() + "\n" +
                "Owner: " + (ownable.getOwner()!=null? ownable.getOwner().getName():"No owner") + "\n" +
                "Cost: " + String.valueOf(ownable.getCost()) + "\n" +
                "Rent price: " + String.valueOf(ownable.getRent()));
        tileInfo.setText(info);
    }

    private void showMessageToHuman(boolean isRobot, String message) {
        if (!isRobot) {
            JOptionPane.showMessageDialog(Start.this, message);
        }
    }

    private void buy() {
        final Player currentPlayer = board.getCurrentPlayer();
        final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
        buy.setEnabled(false);
        if (currentTile instanceof Ownable) {
            if(!currentPlayer.isRobot()) {
                JOptionPane.showMessageDialog(Start.this, "You bought the " + ((Ownable) currentTile).getName());
            }
            currentPlayer.addMoney(-((Ownable) currentTile).getCost());
            ((Ownable) currentTile).setOwner(currentPlayer);
            updatePlayerInfo();
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
            if(!currentPlayer.isRobot()){
                JOptionPane.showMessageDialog(Start.this, "You have sold "+((Ownable) currentTile).getName());
            }
            final Ownable ownableTile = (Ownable) currentTile;
            currentPlayer.addMoney(ownableTile.getCost());
            ownableTile.setOwner(null);
            updatePlayerInfo();
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
        payJailFee.setEnabled(currentPlayer.getIsJailFree());
        move.setEnabled(true);
        nextPlayerButton.setEnabled(false);
        updatePlayerInfo();
        diceLabel1.setText("");
        diceLabel2.setText("");
        tileInfo.setText("");
        goByTrainToRailroad.setEnabled(false);
        if(!currentPlayer.isRobot()) {
            JOptionPane.showMessageDialog(Start.this, currentPlayer.getName() + " is playing!");
        } else{
            playRobot();
        }
    }

    private void playRobot() {
        final AutomaticPlayer currentPlayer = (AutomaticPlayer) board.getCurrentPlayer();
        moveInvoked();
        if(!currentPlayer.isInJail()) {
            moveInvoked();
            showPlayerPositions();
            showTileOwners();
            final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
            if (currentTile instanceof Ownable) {
                final Ownable ownable = (Ownable) currentTile;
                if (board.canCurrentPlayerBuy()) {
                    if (currentPlayer.wantToBuy(ownable, board)) {
                        buy();
                    }
                }
                if (board.canCurrentPlayerSell()) {
                    if (currentPlayer.wantToSell(ownable, board)) {
                        sell();
                    }
                }
            }
            showPlayerPositions();
            showTileOwners();
            switchToNextPlayer();
        }
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

    private boolean playOutOfJail() {
        updatePlayerInfo();
        final Player currentPlayer = board.getCurrentPlayer();
        final boolean isRobot = currentPlayer instanceof AutomaticPlayer;
        final BoardTile currentTile = board.getTileAt(currentPlayer.getCurrentPosition());
        final int diceValue1 = dice.getNum1();
        currentPlayer.setInJail(true);
        if (diceValue1 == 6) {
            currentPlayer.setInJail(false);
            showMessageToHuman(isRobot, "You threw the dice and threw a six. Play on!!!");
            updateJailTileInfo(currentPlayer, currentTile);
            updatePlayerInfo();
            return true;
        } else {
            showMessageToHuman(isRobot, "You threw the dice and did not throw a six. Try again later!!!");
            move.setEnabled(false);
            nextPlayerButton.setEnabled(true);
            updateJailTileInfo(currentPlayer, currentTile);
            updatePlayerInfo();
            return false;
        }
    }

}
