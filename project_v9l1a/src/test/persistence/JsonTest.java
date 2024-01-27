package persistence;

import model.History;
import model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Create a checker for Test
public class JsonTest {
    // EFFECTS: check the history matches or not with the information in it
    protected void checkHistory(String result, double amount, History history) {
        assertEquals(result, history.getResult());
        assertEquals(amount, history.getAmount());
    }
}
