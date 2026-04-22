package responseFiles;

import java.util.ArrayList;

public class API_Testresult {
    public String suite;
    public String baseUrl;
    public ArrayList<TestResult> testResults;

    public API_Testresult(String suite, String baseUrl, ArrayList<TestResult> testResults) {
        this.suite = suite;
        this.baseUrl = baseUrl;
        this.testResults = testResults;
    }
}
