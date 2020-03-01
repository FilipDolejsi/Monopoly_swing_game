package dolejsi.monopoly;

public abstract class Card {
    private String description;

    public Card(String description) {
        this.description = description;
    }

    abstract void applyCard(Player currentPlayer, Board board);

    public String getDescription() {
        return description;
    }
}
