package dolejsi.monopoly;

import java.util.Objects;

public class Player {
    private final String name;
    private int money;
    private int currentPosition;

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    private boolean inJail = false;
    private boolean jailFree = false;


    Player(String name, int money) {
        this.name = Objects.requireNonNull(name);
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    void move(int moveBy, int tileCount) {
        currentPosition += moveBy;
        if (currentPosition >= tileCount) {
            currentPosition -= tileCount;
            addMoney(200);
        }
    }

    public void addMoney(int moneyToAdd) {
        money += moneyToAdd;
    }

    public void goToJail(int jailPosition) {
        currentPosition = jailPosition;
        this.inJail = true;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void goToGo(int goPosition) {
        currentPosition = goPosition;
    }

    public void setIsJailFree(boolean isJailFree) {
        this.jailFree = isJailFree;
    }

    public boolean getIsJailFree() {
        return jailFree;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public String getInventory() {
        return name+" has $"+money+". You own "+" buildings or railroads.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean isAlive() {
        return money >= 0;
    }

    public boolean isRobot() {
        return false;
    }

    public void goToRailroad1() {
        currentPosition=3;
    }

    public void goToRailroad2() {
        currentPosition=9;
    }

    public void goToRailroad3() {
        currentPosition=15;
    }

    public void goToRailroad4() {
        currentPosition=21;
    }
}
