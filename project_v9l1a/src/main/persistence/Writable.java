package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns the JSONObject
    JSONObject toJson();
}
