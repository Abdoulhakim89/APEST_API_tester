package requests;

import io.restassured.response.Response;
import requestFiles.TestRequest;
import tests.BaseTest;

public class DELETE_Request extends BaseTest {

    public static Response SendDELETERequest(TestRequest request){
        setHeaders(request);
        return requestSpecs.delete(request.endpoint);
    }

}
