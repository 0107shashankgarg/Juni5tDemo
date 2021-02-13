package demo.test.demo;


import demo.jupiter.annotation.Release;
import demo.jupiter.displayname.HumanizeNameWithTestCaseId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnOs;

import static org.junit.jupiter.api.condition.OS.MAC;
import static org.junit.jupiter.api.condition.OS.WINDOWS;


@DisplayNameGeneration(HumanizeNameWithTestCaseId.class)
public class Junit5ExtraFeaturesTest {


    /**
     * Use of DisplayName to make a more meaning full name is logs and and reports
     */


    @DisplayName("This test is named as per Standard")
    @Test
    void simpleDispalyNameFirstTest() {

        System.out.println("The test is dedicated to customization of display name");

    }

    /**
     * Use of custom extension for formatting for a class and method
     */

    @Test
    void simpleHumanizedNameTest() {
        System.out.println("The test is dedicated to Humanize of display name");
    }

    /**
     * Use of repeated test and few condition test which can be useful"
     */

    @RepeatedTest(2)
    @EnabledOnOs(MAC)
    void onlyOnMacOs() {
        System.out.println("This is MAC only ");
    }


    @Test
    @EnabledOnOs(WINDOWS)
    void onWindowsOnly() {
        System.out.println("This test is for MAC and LINUX only ");
    }


    @Test
    @DisabledOnOs(WINDOWS)
    void forWindowsOnly() {
        System.out.println("This is not WINDOWS System");
    }


    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
        System.out.println("onlyOn64BitArchitectures");
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
    void onlyOn32BitArchitectures() {
        System.out.println("onlyOn32BitArchitectures");
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


    /**
     * Use of combined custom annotation"
     */

   @Release
   void customAnnotationTest() {
       System.out.println(" this is a custom Annotation test");
   }


}

