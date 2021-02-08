package demo.jupiter.extension;

import demo.jupiter.annotation.Inject;
import demo.jupiter.annotation.TestCaseId;
import demo.MockDataManager;
import demo.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.List;

import static demo.jupiter.Utils.getMethod;

public class UserParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Inject annotation = AnnotationSupport.findAnnotation(parameterContext.getParameter(), Inject.class).orElse(null);
        return annotation != null &&
                parameterContext.getParameter().getType().equals(User.class);
    }

    @Override
    public User resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        TestCaseId testCaseId = getMethod(extensionContext).getAnnotation(TestCaseId.class);
        if (testCaseId == null)
            throw new ParameterResolutionException("@TestCaseId annotation must be here");

        List<User> users = MockDataManager.getById(testCaseId.value());

        Assertions.assertEquals(1, users.size(), "Inject User not supported for several users");
        return users.get(0);
    }

}
