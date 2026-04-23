package responseFiles;

import java.util.ArrayList;
import java.util.Map;

public class TestResult {
    public String testName;
    public String status;
    public ArrayList<AssertionResults> assertionResults;
//    public Map<String, Object> responseBody;

    public TestResult(String testName, ArrayList<AssertionResults> assertionResults, Map<String, Object> responseBody) {
        this.testName = testName;
        this.status = "Pass";
        this.assertionResults = assertionResults;
//        this.responseBody = responseBody;

        for(var assertion:assertionResults){
            if(!assertion.pass) {
                status = "Fail";
                break;
            };
        }
    }
}
