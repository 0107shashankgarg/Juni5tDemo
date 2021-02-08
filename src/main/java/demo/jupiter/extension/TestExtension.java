package demo.jupiter.extension;


import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class TestExtension implements BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback {


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
}