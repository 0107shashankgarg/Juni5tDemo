package demo.test.ui;

import demo.jupiter.annotation.TestCaseId;
import demo.jupiter.displayname.HumanizeNameWithTestCaseId;
import demo.pageobjects.BaseApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(HumanizeNameWithTestCaseId.class)

        /**
         *     Extensions registered declaratively via @ExtendWith will be executed in the order in which they are declared in the source code.
         *
         *  Programmatic Extension Registration using will be executed in the order in which they are declared in the source code.
         *
         *  Automatic Extension Registration
         *  Enabling Automatic Extension Detection :junit.jupiter.extensions.autodetection.enabled or -Djunit.jupiter.extensions.autodetection.enabled=true
         */


class Search extends UIBaseTest {

    private static final Logger LOG = LogManager.getLogger(Search.class);

    @TestCaseId(101)
    @Test
    void userIsAbleToLogin() {
        BaseApp.landingPage().open();
    }


}


