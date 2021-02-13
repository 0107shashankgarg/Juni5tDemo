package demo.jupiter.extension;


import org.junit.jupiter.api.extension.*;


public class TestExtension implements BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback, TestExecutionExceptionHandler {


    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("afterEach'");
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("beforeEach");
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        System.out.println("beforeTestExecution");
    }

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) {

        System.out.println(throwable.getMessage());
    }
}