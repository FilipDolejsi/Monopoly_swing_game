package dolejsi.monopoly;

import java.util.Random;

public class Dice {

    public int getNum1() {
        return random1.nextInt(6) + 1;
    }

    public int getNum2() {
        return random1.nextInt(6)+1;
    }

    private final Random random1 = new Random();
//    private final Random random2 = new Random();
//
//    public int getValue() {
//        final int num1 = random1.nextInt(6) + 1;
//        final int num2 = random2.nextInt(6) + 1;
//        return num1+num2;
//    }
}
