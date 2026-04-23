package requests;

import EntryPoint.BaseTest;
import io.restassured.response.Response;
import requestFiles.TestRequest;

public class DELETE_Request extends BaseTest {

    public static Response sendDELETERequest(TestRequest request){
        setHeaders(request, request.method);
        return requestSpecs.delete(request.endpoint);
    }
}
