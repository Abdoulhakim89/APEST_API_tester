package requests;

import EntryPoint.BaseTest;
import io.restassured.response.Response;
import requestFiles.TestRequest;

public class GET_Request extends BaseTest {

    public static Response sendGETRequest(TestRequest request){
        setHeaders(request,request.method);
        return requestSpecs.get(request.endpoint);
    }
}
