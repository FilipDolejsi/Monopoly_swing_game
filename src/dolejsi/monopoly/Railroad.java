package dolejsi.monopoly;

public class Railroad extends Ownable{
    public Railroad(int position, Player owner, int cost, int rent) {
        super(position, owner, cost, rent);
    }

    @Override
    public String getName() {
        return "Railroad";
    }
}
