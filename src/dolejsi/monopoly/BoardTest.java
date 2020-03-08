package dolejsi.monopoly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Player player1;
    private Player player2;
    private Chance chance;
    private Board board;

    @BeforeEach
    void setUp() {
        this.player1 = new Player("Filip", 4000);
        this.player2 = new Player("Pilif", 4);
        this.chance = new Chance();
        this.board = new Board(new BoardTile[]{
                new GoTile(),
                new Building(1, null, 300, 100, "Doghouse"),
                new ChanceTile(2, chance),
                new Jail(3)
                //todo: spater
        }, asList(
                player1,
                player2
        ), chance);
    }

    @org.junit.jupiter.api.Test
    void getJailPosition() {
        assertEquals(3, this.board.getJailPosition());
    }

    @org.junit.jupiter.api.Test
    void getGoPosition() {
        assertEquals(0, this.board.getGoPosition());
    }

    @org.junit.jupiter.api.Test
    void getTileAt() {
        assertTrue(board.getTileAt(0) instanceof GoTile);
        assertTrue(board.getTileAt(1) instanceof Building);
        assertTrue(board.getTileAt(2) instanceof ChanceTile);
        assertTrue(board.getTileAt(3) instanceof Jail);
    }

    @org.junit.jupiter.api.Test
    void nextNextPlayer() {
        // GIVEN
        final Player currentPlayer1 = board.getCurrentPlayer();
        assertSame(player1, currentPlayer1);
        // WHEN
        board.nextPlayer();
        board.nextPlayer();

        // THEN
        final Player currentPlayer3 = board.getCurrentPlayer();
        assertSame(player1, currentPlayer3);
    }

    @Test
    public void removesPlayerWithDebt() {
        // GIVEN
        player2.addMoney(-1000);
        // WHEN
        final List<Player> deadPlayers = board.removeDeadPlayers();
        // THEN
        assertEquals(1, deadPlayers.size());
        assertIterableEquals(singletonList(player2), deadPlayers);
    }
}