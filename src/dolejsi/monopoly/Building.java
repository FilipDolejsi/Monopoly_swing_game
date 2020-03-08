package dolejsi.monopoly;

public class Building extends Ownable {
    private final String name;

    public Building(int position, Player owner, int cost, int rent, String name) {
        super(position, owner, cost, rent);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
