package persistence;

import model.Account;
import model.History;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account account = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account account = reader.read();
            assertEquals("Cosine", account.getName());
            assertEquals(0, account.getHistoryList().size());
        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonReader reader = new JsonReader("./data/testGeneralAccount.json");
        try {
            Account account = reader.read();
            assertEquals("Cosine", account.getName());
            List<History> historyList = account.getHistoryList();
            assertEquals(2, historyList.size());
            checkHistory("Win", 100, historyList.get(0));
            checkHistory("Loss", 200, historyList.get(1));

        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }
}
