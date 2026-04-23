package assertion;

import io.restassured.response.Response;
import requestFiles.TestRequest;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertionsMethods {

    public static void logger(Response response, TestRequest request){
//        System.out.println("\n"+request.assertions.getFirst().get("assertion") + "\n______________________________________\n");
//        response.then().log().body();
//        System.out.println("\n_____________________________");
    }
    public static void statusCode(Object expectedStatusCode,TestRequest request, Response response){
        assertThat(response.statusCode(), equalTo(expectedStatusCode));
    }

    public static void bodyContainsProperty(Object property,TestRequest request, Response response){
        assertThat(response.getBody().asString(), containsString(property.toString()));
    }
    public static void bodyHasSize(Object expectedLength,TestRequest request, Response response){
        assertThat(response.getBody().asString().length(), equalTo(expectedLength) );
    }
    public static void bodyHasPropertyValue(Map<String, Object> keyValuePair,TestRequest request, Response response){
        var property = keyValuePair.get("property");
        var value = keyValuePair.get("value");
        assertThat(response.path(property.toString()), equalTo(value));
    }

}
