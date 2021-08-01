package persistence;

import org.json.JSONObject;

public interface JsonWritable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
