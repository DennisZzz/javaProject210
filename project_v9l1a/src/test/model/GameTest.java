package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Test for Game Class
public class GameTest {
    private Game testGame;

    @BeforeEach
    void runBefore() {
        testGame = new Game();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testGame.getUserNum());
        assertEquals(0, testGame.getHouseNum());
        assertEquals(0, testGame.getNewCard());
    }

    @Test
    void testCardValue() {
        int testValue = testGame.cardValue(5);
        assertEquals(5, testValue);

        testValue = testGame.cardValue(10);
        assertEquals(10, testValue);

        testValue = testGame.cardValue(11);
        assertEquals(10, testValue);
        testValue = testGame.cardValue(12);
        assertEquals(10, testValue);
        testValue = testGame.cardValue(13);
        assertEquals(10, testValue);
    }

    @Test
    void testGetCardName() {
        testGame.setNewCard(5);
        String testString = testGame.getCardName();
        assertEquals("5", testString);

        testGame.setNewCard(1);
        testString = testGame.getCardName();
        assertEquals("A", testString);

        testGame.setNewCard(11);
        testString = testGame.getCardName();
        assertEquals("J", testString);
        testGame.setNewCard(12);
        testString = testGame.getCardName();
        assertEquals("Q", testString);
        testGame.setNewCard(13);
        testString = testGame.getCardName();
        assertEquals("K", testString);

    }

    @Test
    void testGameOver() {
        testGame.setHouseNum(10);
        testGame.setUserNum(12);
        assertTrue(testGame.gameNotOver());

        testGame.setHouseNum(21);
        testGame.setUserNum(12);
        assertTrue(testGame.gameNotOver());

        testGame.setHouseNum(12);
        testGame.setUserNum(21);
        assertTrue(testGame.gameNotOver());

        testGame.setUserNum(21);
        testGame.setHouseNum(21);
        assertTrue(testGame.gameNotOver());

        testGame.setUserNum(22);
        testGame.setHouseNum(12);
        assertFalse(testGame.gameNotOver());

        testGame.setUserNum(20);
        testGame.setHouseNum(22);
        assertFalse(testGame.gameNotOver());

        testGame.setUserNum(23);
        testGame.setHouseNum(22);
        assertFalse(testGame.gameNotOver());
    }

    @Test
    void testUserHit() {
        String output = testGame.userHit();
        int newCard = testGame.cardValue(testGame.getNewCard());
        assertEquals(newCard, testGame.getUserNum());
        assertEquals("Your card is " + testGame.getCardName(), output);
    }

    @Test
    void testHouseHit() {
        String output = testGame.houseHit();
        int newCard = testGame.cardValue(testGame.getNewCard());
        assertEquals(newCard, testGame.getHouseNum());
        assertEquals("House card is " + testGame.getCardName(), output);
    }

    @Test
    void testUserWinTrue() {
        testGame.setHouseNum(25);
        testGame.setUserNum(23);
        boolean userWin = testGame.userWin();
        assertTrue(userWin);

        testGame.setHouseNum(23);
        testGame.setUserNum(21);
        userWin = testGame.userWin();
        assertTrue(userWin);

        testGame.setHouseNum(19);
        testGame.setUserNum(20);
        userWin = testGame.userWin();
        assertTrue(userWin);

        testGame.setHouseNum(19);
        testGame.setUserNum(21);
        userWin = testGame.userWin();
        assertTrue(userWin);
    }

    @Test
    void testUserWinFalseLargerThan21() {
        testGame.setHouseNum(25);
        testGame.setUserNum(25);
        boolean userWin = testGame.userWin();
        assertFalse(userWin);

        testGame.setHouseNum(25);
        testGame.setUserNum(26);
        userWin = testGame.userWin();
        assertFalse(userWin);

        testGame.setHouseNum(21);
        testGame.setUserNum(21);
        userWin = testGame.userWin();
        assertFalse(userWin);

        testGame.setHouseNum(21);
        testGame.setUserNum(23);
        userWin = testGame.userWin();
        assertFalse(userWin);
    }

    @Test
    void testUserWinFalseSmallerThan21() {
        testGame.setHouseNum(21);
        testGame.setUserNum(19);
        boolean userWin = testGame.userWin();
        assertFalse(userWin);

        testGame.setHouseNum(18);
        testGame.setUserNum(17);
        userWin = testGame.userWin();
        assertFalse(userWin);
    }
}
