package engine;

import EntryPoint.BaseTest;
import assertion.AssertionMapping;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import requestFiles.TestRequest;
import requests.DELETE_Request;
import requests.GET_Request;
import requests.POST_Request;
import requests.PUT_Request;
import responseFiles.AssertionResults;
import responseFiles.TestResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestEngine extends BaseTest {
    public static ArrayList<TestResult> testResults = new ArrayList<>();
    public static String suiteName;
    public static String baseUrl;


    @DataProvider(name = "test_list")
    public Object[][] testProvider(){

        List<TestRequest> testRequests = configurations.tests;
        Object[][] data = new Object[testRequests.size()][1];

        for (int i = 0; i < testRequests.size(); i++) {
            data[i][0] = testRequests.get(i);
        }
        return data;
    }

    @Test(dataProvider = "test_list")
    public void testing__(TestRequest testRequest) {
        suiteName = configurations.suiteName;
        baseUrl = configurations.baseUrl;

         Response currentResponse;
         ArrayList<AssertionResults> currentAssertionsResults;
            switch (testRequest.method){
                case "GET":
                    currentResponse = GET_Request.sendGETRequest(testRequest);
                    currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    taskRunner(testRequest,currentAssertionsResults, currentResponse);
                break;
                case "POST":
                    try {

                    currentResponse = POST_Request.sendPOSTRequest(testRequest);
                    currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    taskRunner(testRequest,currentAssertionsResults, currentResponse);

                    } catch (IllegalArgumentException e){
                        voidBodyHandler(testRequest,e);
                    }
                    break;
                case "DELETE":
                    currentResponse = DELETE_Request.sendDELETERequest(testRequest);
                    currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    taskRunner(testRequest,currentAssertionsResults, currentResponse);
                    break;
                case "PUT":
                    try {
                        currentResponse = PUT_Request.sendPUTRequest(testRequest);
                        currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                        taskRunner(testRequest,currentAssertionsResults, currentResponse);
                    } catch (IllegalArgumentException e){
                        voidBodyHandler(testRequest, e);
                    }
                    break;
            }
        }

        public void taskRunner(TestRequest testRequest, ArrayList<AssertionResults> result, Response response){
            testResults.add(new TestResult(testRequest.name, result, response.getBody().as(Map.class)));
            failChecker(result);
        }
        public void voidBodyHandler(TestRequest testRequest, Exception e){
            ArrayList<AssertionResults> errorResult = new ArrayList<>();
            errorResult.add(new AssertionResults("Request Validation", false, e.getMessage()));
            testResults.add(new TestResult(testRequest.name, errorResult,null));
            Assert.fail(e.getMessage());
        }

        public static ArrayList<TestResult> resultCollector(){
        return testResults;
        }

        public void failChecker(ArrayList<AssertionResults> assertions){
            for(var assertion:assertions){
                if(!assertion.pass) {
                    Assert.fail("Check failure: "+ assertion.message);
                    break;
                }
            }
        }




    }

