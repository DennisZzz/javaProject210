package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Test for Account Class
class AccountTest {
    private Account testAccount;

    @BeforeEach
    void runBefore() {
        testAccount = new Account("Grace", 19, 200);
    }

    @Test
    void testConstructor() {
        assertEquals("Grace", testAccount.getName());
        assertEquals(19, testAccount.getAge());
        assertEquals(200, testAccount.getBalance());
        assertEquals(0, testAccount.getHistoryList().size());
    }

    @Test
    void testChangeAmount() {
        testAccount.changeAmount(100);
        assertEquals(300, testAccount.getBalance());

        testAccount.changeAmount(120.50);
        assertEquals(420.50, testAccount.getBalance());

        testAccount.changeAmount(-100);
        assertEquals(320.50, testAccount.getBalance());

        testAccount.changeAmount(-120.50);
        assertEquals(200, testAccount.getBalance());

    }

    @Test
    void testAddHistory() {
        History History1 = new History(100.00, "Win");
        History History2 = new History(200.00, "Loss");
        History History3 = new History(500.00, "Win");

        testAccount.addHistory(History1);
        assertEquals(1, testAccount.getHistoryList().size());
        assertEquals(History1, testAccount.getHistoryList().get(0));

        testAccount.addHistory(History2);
        assertEquals(2, testAccount.getHistoryList().size());
        testAccount.addHistory(History3);
        assertEquals(3, testAccount.getHistoryList().size());
        assertEquals(History2, testAccount.getHistoryList().get(1));
        assertEquals(History3, testAccount.getHistoryList().get(2));
    }

    @Test
    void testResetHistory() {
        History History1 = new History(100.00, "Win");
        History History2 = new History(200.00, "Loss");
        History History3 = new History(500.00, "Win");

        testAccount.addHistory(History1);
        testAccount.addHistory(History2);
        testAccount.addHistory(History3);

        testAccount.resetHistory();

        assertEquals(0,testAccount.getHistoryList().size());
    }
}