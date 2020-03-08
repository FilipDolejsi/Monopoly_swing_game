package dolejsi.monopoly;

public abstract class BoardTile {
    public BoardTile(int position) {
        this.position = position;
    }

    private final int position;

    public int getPosition() {
        return position;
    }


}
