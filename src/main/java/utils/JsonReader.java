package utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class JsonReader {
    public static JSONObject parse(String fileName) {
        InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(fileName);
        assert inputStream != null;
        return new JSONObject(new JSONTokener(inputStream));
    }
}
