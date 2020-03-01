package dolejsi.monopoly;

import java.util.Random;

public final class Chance {
    public Chance() {
        this.cards = new Card[]{
                new Card("Your building and loan matures—Collect $150") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        currentPlayer.addMoney(150);
                    }
                },
                new Card("You have won a crossword competition—Collect $100") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        currentPlayer.addMoney(100);
                    }
                },
                new Card("Bank pays you dividend of $50") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        currentPlayer.addMoney(50);
                    }
                },
                new Card("Advance to Go (Collect $200)") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        currentPlayer.goToGo(board.getGoPosition());
                        currentPlayer.addMoney(200);
                    }
                },
                new Card("Go to jail") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        currentPlayer.goToJail(board.getJailPosition());
                    }
                },
                new Card("Get Out of Jail Free") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        currentPlayer.getOutOfJailFree();
                    }
                },
                new Card("Go Back 3 Spaces") {
                    @Override
                    void applyCard(Player currentPlayer, Board board) {
                        currentPlayer.move(-3, 24);
                    }
                }
        };
    }

    private Card[] cards;
    private int nextCard;

    Random randomCard = new Random();

    Card next(){
        nextCard=randomCard.nextInt(cards.length)+1;
        throw new UnsupportedOperationException();
    }

    public Card[] getCards() {
        return cards;
    }
}
