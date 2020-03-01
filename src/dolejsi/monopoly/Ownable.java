package dolejsi.monopoly;

public abstract class Ownable extends BoardTile {
    private final Player owner;
    private final int cost;
    private final int rent;

    public Ownable(int position, Player owner, int cost, int rent) {
        super(position);
        this.owner = owner;
        this.cost = cost;
        this.rent = rent;
    }

    public Player getOwner() {
        return owner;
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        return rent;
    }
}
