package demo.test.demo;


import demo.jupiter.annotation.Release;
import demo.jupiter.displayname.HumanizeNameWithTestCaseId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnOs;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.OS.MAC;
import static org.junit.jupiter.api.condition.OS.WINDOWS;


@DisplayNameGeneration(HumanizeNameWithTestCaseId.class)
public class Junit5ExtraFeaturesTest {


    @DisplayName("This test is named as per Standard")
    @Test
    void simpleDispalyNameFirstTest() {

        System.out.println("The test is dedicated to customization of display name");

    }


    @Test
    void simpleHumanizedNameTest() {
        System.out.println("The test is dedicated to customization of display name");
    }

    @Test
    void simpleAssertALlTest()
    {
        assertAll("Asset All Example",
                () -> assertTrue(true, "This is the first assert"),
                () -> assertTrue(true, "This is the second assert"),
                () -> assertEquals(1 , 2, "This is the first integer equal assert")
        );
    }

    @RepeatedTest(2)
    @EnabledOnOs(MAC)
    void onlyOnMacOs() {
        System.out.println("This is MAC only ");
    }


    @Test
    @EnabledOnOs({ WINDOWS})
    void onWindowsOnly() {
        System.out.println("This is MAC and LINUX only ");
    }


    @Test
    @DisabledOnOs(WINDOWS)
    void notOnWindows() {
        System.out.println("This is not WINDOWS");
    }


    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
        System.out.println("onlyOn64BitArchitectures");
    }



    @Order(1)
    @DisabledIf("customCondition")
    void customConditionTest() {
        System.out.println(" this is a custom test");
    }

    @Order(2)
    boolean customCondition() {
        return true;
    }


 //Cuatom Anotation

   @Release
   void customAnnotationTest() {
       System.out.println(" this is a custom Annotation test");
   }


}

