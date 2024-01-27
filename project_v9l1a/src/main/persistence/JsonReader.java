package persistence;

import model.Account;
import model.History;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// represents a reader which can read account from JSON data in storing file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);

    }

    // EFFECTS: reads source file as string and return it
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        double balance = jsonObject.getDouble("balance");
        Account account = new Account(name, age, balance);
        addHistoryList(account, jsonObject);
        return account;
    }

    // EFFECTS: parses account from JSON object and return it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: account
    // EFFECTS: parses histories from JSON object and adds them to account
    private void addHistoryList(Account account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("historyList");

        for (Object json : jsonArray) {
            JSONObject nextHistory = (JSONObject) json;
            addHistory(account, nextHistory);
        }
    }

    // MODIFIES: account
    // EFFECTS: parses history from JSON object and adds them to account
    private void addHistory(Account account, JSONObject jsonObject) {
        String result = jsonObject.getString("result");
        double amount = jsonObject.getDouble("amount");
        History history = new History(amount, result);
        account.addHistory(history);
    }
}
