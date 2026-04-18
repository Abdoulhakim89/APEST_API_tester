package assertion;

import io.restassured.response.Response;
import requestFiles.TestRequest;

import java.util.List;
import java.util.Map;

public class AssertionMapping extends AssertionsMethods {

    public static void mapAssertion(List<Map<String, Object>> assertions, TestRequest request, Response response){

        for(var assertion:assertions){
            var currentAssertion = assertion.get("assertion");
            var currentExpectedResult = assertion.get("expected");

            switch (currentAssertion.toString()){
                case "status":
                    statusCode(currentExpectedResult,request, response);
                    break;
                case "body_has_property":
                    bodyContainsProperty(currentExpectedResult,request,response);
                    break;
                case "body_has_size":
                    bodyHasSize(currentExpectedResult,request,response);
                    break;
                case "has_property_value":
                    bodyHasPropertyValue((Map<String, Object>) currentExpectedResult,request,response);
            }


        }
    }
}
