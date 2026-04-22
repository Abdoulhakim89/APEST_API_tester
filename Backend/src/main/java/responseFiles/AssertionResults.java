package responseFiles;

public class AssertionResults {

    public String assertion;
    public boolean pass;
    public String message;

    public AssertionResults(String assertion,boolean pass, String message) {
        this.assertion = assertion;
        this.pass = pass;
        this.message = message;
    }
}
