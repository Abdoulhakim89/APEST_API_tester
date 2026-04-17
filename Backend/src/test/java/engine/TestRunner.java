package running;

import assertion.AssertionMapping;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import requestFiles.TestRequest;
import requests.DELETE_Request;
import requests.GET_Requests;
import requests.POST_Request;
import requests.PUT_Request;
import tests.BaseTest;

import java.util.List;

public class TestRunner extends BaseTest {

    @DataProvider(name = "test_list")
    public Object[][] provideTests(){
        List<TestRequest> testRequests = BaseTest.configurations.tests;
        Object[][] data = new Object[testRequests.size()][1];

        for (int i = 0; i < testRequests.size(); i++) {
            data[i][0] = testRequests.get(i);
        }
        return data;
    }

    @Test(dataProvider = "test_list")
    public void runAssertions(TestRequest testRequest) {
         Response currentResponse;

            switch (testRequest.request){
                case "GET":
                    currentResponse = GET_Requests.sendGETRequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                break;

                case "POST":
                    currentResponse = POST_Request.sendPOSTRequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);
                    break;

                case "DELETE":
                    currentResponse = DELETE_Request.SendDELETERequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest, currentResponse);
                    break;
                case "PUT":
                    currentResponse = PUT_Request.SendPUTRequest(testRequest);
                    AssertionMapping.mapAssertion(testRequest.assertions,testRequest,currentResponse);

            }




        }

    }

