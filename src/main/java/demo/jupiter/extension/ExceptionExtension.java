package demo.jupiter.extension;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.LifecycleMethodExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;


public class ExceptionExtension implements TestExecutionExceptionHandler, LifecycleMethodExecutionExceptionHandler {


    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        System.out.println("handleTestExecutionException");
        System.out.println(throwable.getMessage());
    }

    @Override
    public void handleBeforeAllMethodExecutionException(ExtensionContext context, Throwable ex) {
        System.out.println("handleBeforeAllMethodExecutionException");
        System.out.println(ex.getMessage());
    }

    @Override
    public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable ex) {
        System.out.println("handleBeforeEachMethodExecutionException");
        System.out.println(ex.getMessage());
    }

    @Override
    public void handleAfterEachMethodExecutionException(ExtensionContext context, Throwable ex) {
        System.out.println("handleAfterEachMethodExecutionException");
        System.out.println(ex.getMessage());
    }

    @Override
    public void handleAfterAllMethodExecutionException(ExtensionContext context, Throwable ex) {
        System.out.println("handleAfterAllMethodExecutionException");
        System.out.println(ex.getMessage());
    }
}