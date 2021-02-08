package demo.jupiter;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class Utils {

    public static Method getMethod(ExtensionContext extensionContext) {
        return extensionContext.getTestMethod().orElseThrow(() -> new IllegalStateException("Test method must be here"));
    }
}
