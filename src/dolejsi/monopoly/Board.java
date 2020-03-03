package dolejsi.monopoly;

public final class Board {
    private final BoardTile[] tiles;
    private final Player[] players;
    private final Chance chance;
    private final int jailPosition;
    private int currentPlayer;


    public Board(BoardTile[] tiles, Player[] players, Chance chance) {
        this.tiles = tiles;
        this.players = players;
        this.chance = chance;

        this.jailPosition = findJailPosition(tiles);
    }

    private static int findJailPosition(BoardTile[] tiles) {
        int jailPosition = -1;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] instanceof Jail) {
                jailPosition = i;
            }
        }

        if (jailPosition == -1) {
            throw new IllegalArgumentException("Tiles do not contain Jail!");
        }
        return jailPosition;
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
        return jailPosition;
    }

    public int getGoPosition() {
        return 0;
    }

    public Player getCurrentPlayer() {
        return this.players[currentPlayer];
    }

    public BoardTile getTileAt(int currentPosition) {
        return tiles[currentPosition];
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.length) {
            currentPlayer = 0;
        } else if (currentPlayer > players.length) {
            throw new IndexOutOfBoundsException("Unexpected current player: " + currentPlayer);
        }
    }
}
