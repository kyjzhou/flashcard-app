package persistence;

import org.json.JSONObject;

// Modelled based on JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface JsonWritable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
