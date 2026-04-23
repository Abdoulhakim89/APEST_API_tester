package EntryPoint;


import engine.TestEngine;
import requestFiles.API_test;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import requestFiles.RequestConfig;
import requestFiles.TestRequest;

import static io.restassured.RestAssured.with;

public class BaseTest {

    protected static RequestSpecification requestSpecs;
    protected static ResponseSpecification responseSpecs;
    public static API_test configurations;

    @BeforeClass
    public void setUp(){
//        configurations = JSONReader.reader("Backend/src/main/resources/Requests.json");
        configurations = RequestConfig.config;
        System.out.println(configurations.suiteName);
        requestSpecs = with()
                .baseUri(configurations.baseUrl);


    }
    public static void setHeaders(TestRequest request, String method){

        if(request.headers != null) requestSpecs.headers(request.headers);

        if(request.body == null && (method.equals("POST") || method.equals("PUT")))
        {
            throw new IllegalArgumentException("Request method requires a body");
        }
        else if(request.body != null && (method.equals("POST") || method.equals("PUT")))
        {
            requestSpecs.body(request.body);
        }


    }
}
