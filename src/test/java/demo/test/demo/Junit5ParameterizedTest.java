package demo.test.demo;

import demo.constants.DaysOfWeek;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Junit5ParameterizedTest {




    @ParameterizedTest
    @ValueSource(strings = { "shahsank","Anton"})

    void simpleParameterizedFirstTest(String name) {

        System.out.println("The test is dedicated to "+ name);

    }


    @ParameterizedTest
    @EnumSource(DaysOfWeek.class)
    void simpleEnumParameterizedFirstTest(DaysOfWeek name) {

        System.out.println("Today is"+ name);

    }

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


    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv")
    void testWithCsvFileSourceFromClasspath(String country, String capitol) {
        System.out.println(country + " Capital is " + capitol);

    }


    @Test
    void testWithCsvFileSourceFromClasspath() {
        for (int i = 1; i < 100; i++) {

            System.out.println((i % 15 == 0) ? "fizbuzz" : i % 3 == 0 ? "fiz" : i % 5 == 0 ? "buz" : String.valueOf(i));
        }

    }

}
