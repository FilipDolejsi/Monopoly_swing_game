package dolejsi.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void move() {
        // GIVEN
        final Player filip = new Player("Filip", 1);
        final int tileCount = 10;

        // WHEN ... move by 1
        filip.move(1, tileCount);
        // THEN
        assertEquals(1, filip.getCurrentPosition());

        // WHEN ... move by another 4
        filip.move(4, tileCount);
        // THEN
        assertEquals(5, filip.getCurrentPosition());

        // WHEN ... move by another 10
        filip.move(10, tileCount);
        // THEN
        assertEquals(5, filip.getCurrentPosition());
    }

    @Test
    void moveToStart() {
        // GIVEN
        final Player filip = new Player("Filip", 1);
        final int tileCount = 10;

        // WHEN ... move by 9
        filip.move(9, tileCount);
        // THEN
        assertEquals(9, filip.getCurrentPosition());

        // WHEN ... move by another 1
        filip.move(1, tileCount);
        // THEN
        assertEquals(0, filip.getCurrentPosition());
    }

    @Test
    void isAliveIsTrueIfHasMoney() {
        // GIVEN
        final Player filip = new Player("Filip", 1);
        // THEN
        assertTrue(filip.isAlive());
    }

    @Test
    void isAliveIsTrueIfHasNoMoney() {
        // GIVEN
        final Player filip = new Player("Filip", 0);
        // THEN
        assertTrue(filip.isAlive());
    }

    @Test
    void isNotAliveIsFalseIfHasDebt() {
        // GIVEN
        final Player filip = new Player("Filip", -1);
        // THEN
        assertFalse(filip.isAlive());
    }

    @Test
    void goToJail() {
    }

    @Test
    void goToGo() {
    }

    @Test
    void getOutOfJailFree() {
    }

    @Test
    void isJailFree() {
    }
}