package assertion;

import io.restassured.response.Response;
import requestFiles.TestRequest;
import responseFiles.AssertionResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssertionMapping extends AssertionsMethods {

    public static AssertionResults createAssertion(String assertion, boolean pass, String message){
        return new AssertionResults(assertion, pass, message);
    }

    public static ArrayList<AssertionResults> mapAssertion(List<Map<String, Object>> assertions, TestRequest request, Response response){

        ArrayList<AssertionResults> assertionResults = new ArrayList<>();

        for(var assertion:assertions){
            var currentAssertion = assertion.get("assertion").toString();
            var currentExpectedResult = assertion.get("expected");

            try{
            switch (currentAssertion){
                case "status":
                    statusCode(currentExpectedResult,request, response);
                    assertionResults.add(createAssertion(currentAssertion, true, "Status code "+ currentExpectedResult +" matched"));
                    break;
                case "body_has_property":
                    bodyContainsProperty(currentExpectedResult,request,response);
                    assertionResults.add(createAssertion(currentAssertion, true, "Property "+ currentExpectedResult +" found"));
                    break;
                case "body_has_size":
                    bodyHasSize(currentExpectedResult,request,response);
                    assertionResults.add(createAssertion(currentAssertion, true, "Body size "+ currentExpectedResult +" matched"));
                    break;
                case "has_property_value":
                    bodyHasPropertyValue((Map<String, Object>) currentExpectedResult,request,response);
                    assertionResults.add(createAssertion(currentAssertion, true, "Key value pair found"));
            }

            }catch (AssertionError e){
                    assertionResults.add(createAssertion(currentAssertion, false, e.getMessage()));
            }



        }
        return assertionResults;
    }
}
