package model;

/*
Represents One History of a single Casino Game
 */

import org.json.JSONObject;
import persistence.Writable;

public class History implements Writable {
    private final double amount;
    private final String result;

    // MODIFIES: this
    // EFFECTS: creates a new History with given amount and result
    public History(double amount, String result) {
        this.amount = amount;
        this.result = result;
    }

    // EFFECTS: return a string that represent the result of the game
    public String printHistory() {
        return this.result + " " + this.amount + "$";
    }

    public double getAmount() {
        return amount;
    }

    public String getResult() {
        return result;
    }

    // EFFECTS: return the history as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("amount", amount);
        return jsonObject;
    }
}
