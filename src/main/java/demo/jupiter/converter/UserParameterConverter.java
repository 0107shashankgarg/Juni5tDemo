package demo.jupiter.converter;

import demo.jupiter.annotation.TestCaseId;
import demo.MockDataManager;
import demo.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.List;

public class UserParameterConverter implements ArgumentConverter {

    public User convert(Object state, ParameterContext parameterContext) throws ArgumentConversionException {
        Assertions.assertEquals(parameterContext.getParameter().getType(), User.class, "Can only convert to User");

        TestCaseId testCaseId = parameterContext.getDeclaringExecutable().getAnnotation(TestCaseId.class);
        if (testCaseId == null)
            throw new ParameterResolutionException("@TestCaseId annotation must be here");

        if (state instanceof User.State) {
            List<User> users = MockDataManager.getById(testCaseId.value());
            return users.stream()
                    .filter(adv -> state == adv.getState())
                    .findFirst()
                    .orElseThrow(() -> new ArgumentConversionException("User not found for State <" + state + ">"));
        } else
            throw new ArgumentConversionException("Parameter with type <" + state.getClass() + "> not supported here!");
    }
}
