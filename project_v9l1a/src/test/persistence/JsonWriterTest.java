package persistence;
import model.History;
import model.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Account account = new Account("Tina", 18, 3000);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccount() {
        try {
            Account account = new Account("Tina", 18, 3000);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            account = reader.read();
            assertEquals("Tina", account.getName());
            assertEquals(0, account.getHistoryList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccount() {
        try {
            Account account = new Account("Tina", 18, 3000);
            account.addHistory(new History(300, "Win"));
            account.addHistory(new History(200, "Loss"));
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            account = reader.read();
            assertEquals("Tina", account.getName());
            List<History> historyList = account.getHistoryList();
            assertEquals(2, account.getHistoryList().size());
            checkHistory("Win", 300, historyList.get(0));
            checkHistory("Loss", 200, historyList.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
