package dolejsi.monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Board {
    private final BoardTile[] tiles;
    private final List<Player> players;
    private final Chance chance;
    private final int jailPosition;
    private int currentPlayer;


    public Board(BoardTile[] tiles, List<Player> players, Chance chance) {
        this.tiles = tiles;
        this.players = new ArrayList<>(players);
        this.chance = chance;

        this.jailPosition = findJailPosition(tiles);

        if (tiles.length == 0 || !(tiles[0] instanceof GoTile)) {
            throw new IllegalArgumentException("Board should have Go on position 0.");
        }
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

    public List<Player> getPlayers() {
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
        return this.players.get(currentPlayer);
    }

    public BoardTile getTileAt(int currentPosition) {
        return tiles[currentPosition];
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        } else if (currentPlayer > players.size()) {
            throw new IndexOutOfBoundsException("Unexpected current player: " + currentPlayer);
        }
    }

    public List<Player> removeDeadPlayers() {
        final List<Player> deadPlayers = getPlayers().stream()
                .filter(player -> !player.isAlive())
                .collect(Collectors.toList());

        this.players.removeAll(deadPlayers);

        // find board tiles owned by dead players
        for (BoardTile tile : this.getTiles()) {
            if (tile instanceof Ownable) {
                Ownable ownable = (Ownable) tile;
                if (deadPlayers.contains(ownable.getOwner())) {
                    ownable.setOwner(null);
                }
            }
        }

        return deadPlayers;
    }
}
