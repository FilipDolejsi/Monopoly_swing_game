package dolejsi.monopoly;

public class Player {
    private final String name;
    private int money;
    private int currentPosition;
    private boolean inJail;
    private boolean jailFree;


    Player(String name, int money) {
        this.name = name;
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
        if (currentPosition > tileCount) {
            currentPosition -= tileCount;
            money += 200;
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

    public void getOutOfJailFree() {
        this.jailFree = true;
    }

    public boolean isJailFree() {
        return jailFree;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
