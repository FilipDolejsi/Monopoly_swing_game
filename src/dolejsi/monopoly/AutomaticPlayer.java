package dolejsi.monopoly;

import java.util.Random;

public class AutomaticPlayer extends Player {
    private Random random;

    AutomaticPlayer(String name, int money) {
        super(name, money);
        this.random = new Random();
    }

    public boolean wantToBuy(Ownable ownable, Board board) {
        return random.nextBoolean();
    }

    @Override
    public boolean isRobot() {
        return true;
    }

    public boolean wantToSell(Ownable ownable, Board board) {
        return random.nextBoolean();
    }
}
