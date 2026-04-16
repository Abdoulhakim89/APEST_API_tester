package assertion;

import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertionsMethods {

    public static void statusCode(Object expectedStatusCode, Response response){
        assertThat(response.statusCode(), equalTo(expectedStatusCode));
    }

    public static void bodyContainsProperty(Object property, Response response){
        assertThat(response.getBody().asString(), containsString(property.toString()));
    }
    public static void bodyHasSize(Object expectedLength, Response response){
        assertThat(response.getBody().asString().length(), equalTo(expectedLength) );
    }
}
