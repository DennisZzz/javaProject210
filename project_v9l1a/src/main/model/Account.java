package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
Class that represents an account of player, including player's information, such as name, age, account balance, account
history.
 */
public class Account implements Writable {
    private final String name;
    private final int age;
    private double balance;
    private final List<History> historyList;

    // REQUIRES: amount >= 100, age >= 1
    // MODIFIES: this
    // EFFECTS: initialize a new account with given name, age and amount, and with no game history
    public Account(String name, int age, double amount) {
        this.name = name;
        this.age = age;
        this.balance = amount;
        this.historyList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add the given amount to account balance
    public void changeAmount(double amount) {
        this.balance += amount;
    }

    // MODIFIES: this
    // EFFECTS: add the one game result to personal account history
    public void addHistory(History history) {
        this.historyList.add(history);
        EventLog.getInstance().logEvent(new Event(history.getResult() + history.getAmount()));
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getBalance() {
        return this.balance;
    }

    public List<History> getHistoryList() {
        return this.historyList;
    }

    // EFFECTS: save the account and its fields as a JSON object, and return it
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("age", this.age);
        jsonObject.put("balance", this.balance);
        jsonObject.put("historyList", historyToJson());
        return jsonObject;
    }

    // EFFECTS: return histories in this account as a JSON array
    private JSONArray historyToJson() {
        JSONArray jsonArray = new JSONArray();

        for (History history : this.historyList) {
            jsonArray.put(history.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: reset the history, make this account has no history
    public void resetHistory() {
        this.historyList.clear();
        EventLog.getInstance().clear();
    }
}
