package engine;

import EntryPoint.BaseTest;
import assertion.AssertionMapping;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import requestFiles.TestRequest;
import requests.DELETE_Request;
import requests.GET_Request;
import requests.POST_Request;
import requests.PUT_Request;

import java.util.List;

public class TestEngine extends BaseTest {

    @DataProvider(name = "test_list")
    public Object[][] provideTests(){
        List<TestRequest> testRequests = BaseTest.configurations.tests;
        Object[][] data = new Object[testRequests.size()][1];

        for (int i = 0; i < testRequests.size(); i++) {
            data[i][0] = testRequests.get(i);
        }
        return data;
    }

    @org.testng.annotations.Test(dataProvider = "test_list")
    public void testing__(TestRequest testRequest) {
         Response currentResponse;

            switch (testRequest.method){
                case "GET":
                    currentResponse = GET_Request.sendGETRequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                break;

                case "POST":
                    currentResponse = POST_Request.sendPOSTRequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    break;

                case "DELETE":
                    currentResponse = DELETE_Request.sendDELETERequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest, currentResponse);
                    break;
                case "PUT":
                    currentResponse = PUT_Request.sendPUTRequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);

            }




        }

    }

