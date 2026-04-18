package EntryPoint;

import requestFiles.API_test;
import requestFiles.JSONReader;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import requestFiles.TestRequest;

import static io.restassured.RestAssured.with;

public class BaseTest {

    protected static RequestSpecification requestSpecs;
    protected static ResponseSpecification responseSpecs;
    public static API_test configurations;

    @BeforeClass
    public void setUp(){

        configurations = JSONReader.reader("src/main/java/requestFiles/Requests.json");

        requestSpecs = with()
                .baseUri(configurations.baseUrl);

    }
    public static void setHeaders(TestRequest request, String method){

        if(request.headers != null) requestSpecs.headers(request.headers);

        if(request.body == null && (method.equals("POST") || method.equals("PUT")))
        {
            throw new IllegalArgumentException();
        }
        else if(request.body != null && (method.equals("POST") || method.equals("PUT")))
        {
            requestSpecs.body(request.body);
        }


    }
}
