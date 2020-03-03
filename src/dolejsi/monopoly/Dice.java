package dolejsi.monopoly;

import java.util.Random;

public class Dice {
    private final Random random = new Random();

    public int getValue() {
        return random.nextInt(6) + 1;
    }
}
