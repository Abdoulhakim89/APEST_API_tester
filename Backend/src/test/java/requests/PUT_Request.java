package requests;

import EntryPoint.BaseTest;
import io.restassured.response.Response;
import requestFiles.TestRequest;


public class PUT_Request extends BaseTest {

    public static Response sendPUTRequest(TestRequest request){
        setHeaders(request,request.method);
        return requestSpecs.put(request.endpoint);
    }
}
