package requests;

import EntryPoint.BaseTest;
import io.restassured.response.Response;
import requestFiles.TestRequest;

public class POST_Request extends BaseTest {

    public static Response sendPOSTRequest(TestRequest request){
        setHeaders(request,"POST");
        return requestSpecs.post(request.endpoint);
    }
}
