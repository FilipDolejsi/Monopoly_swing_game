package dolejsi.monopoly;

import javax.swing.*;
import java.util.Random;

public final class Chance {
    private Card[] cards;
    private final Random randomCard = new Random();

    public Chance() {
        super();
        this.cards = new Card[]{
                new Card("Your building and loan matures—Collect $150") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        JOptionPane.showMessageDialog(null, getDescription());
                        currentPlayer.addMoney(150);
                    }
                },
                new Card("You have won a crossword competition—Collect $100") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        JOptionPane.showMessageDialog(null, getDescription());
                        currentPlayer.addMoney(100);
                    }
                },
                new Card("Bank pays you dividend of $50") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        JOptionPane.showMessageDialog(null, getDescription());
                        currentPlayer.addMoney(50);
                    }
                },
                new Card("Advance to Go (Collect $200)") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        JOptionPane.showMessageDialog(null, getDescription());
                        currentPlayer.goToGo(board.getGoPosition());
                        currentPlayer.addMoney(200);
                    }
                },
                new Card("Go to jail") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        JOptionPane.showMessageDialog(null, getDescription());
                        currentPlayer.goToJail(board.getJailPosition());
                    }
                },
                new Card("Get Out of Jail Free") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        JOptionPane.showMessageDialog(null, getDescription());
                        currentPlayer.setIsJailFree();
                    }
                },
                new Card("Go Back 3 Spaces") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        JOptionPane.showMessageDialog(null, getDescription());
                        currentPlayer.move(-3, 24);
                    }
                }
        };
    }


    Card next() {
        int nextCard = randomCard.nextInt(cards.length);
        return this.cards[nextCard];
    }

    public Card[] getCards() {
        return cards;
    }
}
