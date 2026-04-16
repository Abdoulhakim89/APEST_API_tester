package requestFiles;

import java.util.List;
import java.util.Map;

public class TestRequest {
    public String name;
    public String type;
    public String request;
    public String endpoint;
    public Map<String, String> headers;
    public Map<String,Object> body;
    public List<Map<String, Object>> assertions;

}
