package engine;

import EntryPoint.BaseTest;
import assertion.AssertionMapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import requestFiles.TestRequest;
import requests.DELETE_Request;
import requests.GET_Request;
import requests.POST_Request;
import requests.PUT_Request;
import responseFiles.API_Testresult;
import responseFiles.AssertionResults;
import responseFiles.TestResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestEngine extends BaseTest {
    public ArrayList<TestResult> testResults = new ArrayList<>();


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
    public void testing__(TestRequest testRequest) throws JsonProcessingException{

         Response currentResponse;
         ArrayList<AssertionResults> currentAssertionsResults;
            switch (testRequest.method){
                case "GET":
                    currentResponse = GET_Request.sendGETRequest(testRequest);
                    currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    taskRunner(testRequest,currentAssertionsResults, currentResponse);
                break;
                case "POST":
                    currentResponse = POST_Request.sendPOSTRequest(testRequest);
                    currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    taskRunner(testRequest,currentAssertionsResults, currentResponse);
                    break;
                case "DELETE":
                    currentResponse = DELETE_Request.sendDELETERequest(testRequest);
                    currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    taskRunner(testRequest,currentAssertionsResults, currentResponse);
                    break;
                case "PUT":
                    currentResponse = PUT_Request.sendPUTRequest(testRequest);
                    currentAssertionsResults = AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    taskRunner(testRequest,currentAssertionsResults, currentResponse);
                    break;
            }
        }

        public void taskRunner(TestRequest testRequest, ArrayList<AssertionResults> result, Response response){
            testResults.add(new TestResult(testRequest.name, result, response.getBody().as(Map.class)));
            failChecker(result);
        }

        public ArrayList<TestResult> resultCollector(){
        return this.testResults;
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

