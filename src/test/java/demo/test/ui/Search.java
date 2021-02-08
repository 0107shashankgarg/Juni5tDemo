package demo.test.ui;

import demo.jupiter.annotation.TestCaseId;
import demo.jupiter.displayname.HumanizeNameWithTestCaseId;
import demo.pageobjects.BaseApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(HumanizeNameWithTestCaseId.class)
class Search extends UIBaseTest {

    private static final Logger LOG = LogManager.getLogger(Search.class);

    @TestCaseId(101)
    @Test
    void userIsAbleToLogin() {
        BaseApp.searchPage().open().enterSearchTerm();
    }

}
