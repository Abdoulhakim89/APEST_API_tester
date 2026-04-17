package requestFiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONReader {
    public static API_test reader(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), API_test.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

