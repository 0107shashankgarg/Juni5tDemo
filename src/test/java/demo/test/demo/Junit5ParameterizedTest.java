package demo.test.demo;

import demo.constants.DaysOfWeek;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Junit5ParameterizedTest {


    /**
     * How use a simple array as parameter
     */

    @ParameterizedTest
    @ValueSource(strings = {"String1", "String2"})
    void simpleParameterizedFirstTest(String name) {

        System.out.println("The test is dedicated to "+ name);

    }

    /**
     * How use a simple Enum as source  parameter
     */
    @ParameterizedTest
    @EnumSource(DaysOfWeek.class)
    void simpleEnumParameterizedFirstTest(DaysOfWeek name) {

        System.out.println(name);

    }

    /**
     * How use a streaming method as Parameter
     */

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        System.out.println("the string is " + str);
        System.out.println("the number is " + num);
        System.out.println("the list is " + list);
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    /**
     * How use a streaming method as Parameter
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv")
    void testWithCsvFileSourceFromClasspath(String country, String capitol) {
        System.out.println(country + " Capital is " + capitol);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv", numLinesToSkip = 2)
    void testWithCsvFileSourceFromClasspathAndSKip(String country, String capitol) {
        System.out.println(country + " Capital is " + capitol);

    }



}
