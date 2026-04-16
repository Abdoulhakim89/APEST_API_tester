package assertion;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class AssertionMapping extends AssertionsMethods {

    public static void mapAssertion(List<Map<String, Object>> assertions, Response response){

        for(var assertion:assertions){

            var currentAssertion = assertion.get("assertion");
            var currentExpectedResult = assertion.get("expected");

            switch (currentAssertion.toString()){
                case "status":
                    statusCode(currentExpectedResult,response);
                    break;
                case "body_contains_property":
                    bodyContainsProperty(currentExpectedResult,response);
                    break;
                case "body_has_size":
                    bodyHasSize(currentExpectedResult,response);
                    break;
            }


        }
    }
}
