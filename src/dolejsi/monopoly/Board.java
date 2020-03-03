package dolejsi.monopoly;

public final class Board {
    private final BoardTile[] tiles;
    private final Player[] players;
    private final Chance chance;


    public Board(BoardTile[] tiles, Player[] players, Chance chance) {
        this.tiles = tiles;
        this.players = players;
        this.chance = chance;
    }

    public BoardTile[] getTiles() {
        return tiles;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Chance getChance() {
        return chance;
    }

    public int getJailPosition() {
        return 6;
    }

    public int getGoPosition() {
        return 0;
    }
}
