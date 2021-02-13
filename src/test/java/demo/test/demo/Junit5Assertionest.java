package demo.test.demo;


import demo.jupiter.displayname.HumanizeNameWithTestCaseId;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayNameGeneration(HumanizeNameWithTestCaseId.class)
public class Junit5Assertionest {


    /**
     * How use Assert all eq to soft assertion
     */


    @Test
    void simpleAssertALlTest() {
        assertAll("Asset All Example",
                () -> assertTrue(true, "This is the first assert"),
                () -> assertTrue(false, "This is the second assert"),
                () -> assertEquals(1, 2, "This is the first integer equal assert")
        );
    }

    @Test
    void standardAssertions() {
        assertEquals(4, 5, "The number are not equal");


    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("first case",
                () -> {
                    String firstName = "test";
                    assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = "second assertion";
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }


}

