package tests;

import requestFiles.API_test;
import requestFiles.JSONReader;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.with;

public class BaseTest {

    protected static RequestSpecification requestSpecs;
    protected static ResponseSpecification responseSpecs;
    public static API_test configurations;

    @BeforeClass
    public void setUp(){

        configurations = JSONReader.reader("src/test/java/requestFiles/Requests.json");
        System.out.println(configurations.tests.getFirst().name);
        requestSpecs = with()
                .baseUri(configurations.baseUrl);

    }

//    public static RequestSpecification customRequestSpecification(){
//        return requestSpecs;
//    }
}
