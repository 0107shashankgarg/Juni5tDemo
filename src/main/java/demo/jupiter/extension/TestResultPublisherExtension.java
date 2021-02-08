package demo.jupiter.extension;

import com.codepine.api.testrail.TestRail;
import demo.config.ConfigMapping;
import demo.jupiter.Utils;
import demo.jupiter.annotation.TestCaseId;
import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import java.io.File;
import java.util.Optional;

import static com.codeborne.selenide.Screenshots.takeScreenShot;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;


public class TestResultPublisherExtension implements TestWatcher, AfterTestExecutionCallback, BeforeAllCallback, AfterAllCallback {

    private static TestRailIntegration testRailInstance;
    private static final Logger LOG = LogManager.getLogger(TestResultPublisherExtension.class);
    private boolean shouldNotPublishTestResult = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties()).publishResultsTestrail();

    private static ExtensionContext.Store getStore(ExtensionContext extensionContext) {
        return extensionContext.getRoot().getStore(Namespace.GLOBAL);
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        publishResult(extensionContext, "failed", 5);
        //Statuscode 5 --> Failed in TestRail

    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        publishResult(extensionContext, "passed", 1);
        //Update Test Rail Statuscode 1 --> Passed in TestRail
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        // Send screenshot to RP only if test failed
        context.getExecutionException().ifPresent(this::sendScreenshotOnFailure);
    }

    private void sendScreenshotOnFailure(Throwable exception) {
        // check we are running UI test
        if (!hasWebDriverStarted()) return;
        String screenshotFileName = Long.toString(System.currentTimeMillis());
        String error = exception.getMessage();

        LOG.error("RP_MESSAGE#FILE#{}#{}", new File(takeScreenShot(screenshotFileName)), error);
    }

    private void publishResult(ExtensionContext extensionContext, String message, int statusCode) {

        if (shouldNotPublishTestResult) {

            TestCaseId testCaseId = Utils.getMethod(extensionContext).getAnnotation(TestCaseId.class);

            if (testCaseId == null)

                throw new ParameterResolutionException("@TestCaseId annotation must be here");
            testRailInstance.updateTestCaseStatus(getStore(extensionContext).get("testRailConnection", TestRail.class),
                    getStore(extensionContext).get("testRailRunId", Integer.class), testCaseId.value() , statusCode);
        }

    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {

        try {
            if (shouldNotPublishTestResult && getStore(extensionContext).get("testRailConnection", TestRail.class) == null) {

                testRailInstance = new TestRailIntegration();
                TestRail testRailConnection = testRailInstance.setUpTestrailInstance();
                getStore(extensionContext).put("testRailConnection", testRailConnection);
                getStore(extensionContext).put("testRailProjectId", testRailInstance.returnProject(testRailConnection));
                getStore(extensionContext).put("testRailRunId", testRailInstance.returnRun(testRailConnection, getStore(extensionContext).get("testRailProjectId", Integer.class)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            shouldNotPublishTestResult = false;
        }

    }


    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        System.out.println(" After All");
    }
}


