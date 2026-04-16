package running;

import assertion.AssertionMapping;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import requestFiles.TestRequest;
import requests.GET_Requests;
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

            if (testRequest.request.equals("GET"))
            {
                currentResponse = GET_Requests.sendGETRequest(testRequest);
                AssertionMapping.mapAssertion(testRequest.assertions,currentResponse);
            }


        }

    }

