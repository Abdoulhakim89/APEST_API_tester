package requests;

import io.restassured.response.Response;
import requestFiles.TestRequest;
import tests.BaseTest;

public class GET_Requests extends BaseTest {

    public static Response sendGETRequest(TestRequest request){
        if(request.headers != null) requestSpecs.headers(request.headers);
        return requestSpecs.get(request.endpoint);
    }
}
