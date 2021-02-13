package demo.jupiter.displayname;

import demo.jupiter.annotation.TestCaseId;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class HumanizeNameWithTestCaseId extends DisplayNameGenerator.Standard{

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {

        //      System.out.println("generateDisplayNameForMethod");
        String testCaseName = humanizeTestCaseName(testMethod.getName());
        TestCaseId testCaseId = testMethod.getAnnotation(TestCaseId.class);
        //Format method name with testcaseId
        if (testCaseId != null) {
            testCaseName = String.format("[%d] %s", testCaseId.value(), testCaseName);
        }
        return testCaseName;
    }

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        //  System.out.println("generateDisplayNameForClass");
        return humanizeTestCaseName(super.generateDisplayNameForClass(testClass));

    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        //  System.out.println("generateDisplayNameForNestedClass");
        return humanizeTestCaseName(super.generateDisplayNameForClass(nestedClass));

    }

    private static String humanizeTestCaseName(String name) {
        String intermediateString = name
                .replace('_', ' ')
                .replaceAll("([A-Z])", " $1")
                .replaceAll(" {2,}", " ").trim();

        return StringUtils.capitalize(intermediateString);
    }

}