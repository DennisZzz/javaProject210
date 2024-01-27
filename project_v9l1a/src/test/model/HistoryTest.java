package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Test for History Class
public class HistoryTest {
    private History testHistory;

    @BeforeEach
    void runBefore() {
        testHistory = new History(200.00, "Win");
    }

    @Test
    void testConstructor() {
        assertEquals(200.00, testHistory.getAmount());
        assertEquals("Win", testHistory.getResult());
    }

    @Test
    void testPrintHistory() {
        assertEquals("Win 200.0$", testHistory.printHistory());
    }
}
